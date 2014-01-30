/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 * This is not tested properly. It doesn't show an error when I run it, however I didn't know
 * how to check if the data is actually sent to the server
 * 
 * uses Google GSON and Apache Commons Net Libraries 
 */

package com.comp1008.MrugalaBartlett.FieldKnow.DataManager;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.PersonDB;
import com.google.gson.Gson;

public class GSONActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		PersonDB personDB = new PersonDB(this);
		ArrayList<Person> people = personDB.getAllPeople();

		Gson gson = new Gson();
		String jsonPeople = gson.toJson(people);
		
		String username = "worker1";
        String host = "restlessbeings-data.herokuapp.com";
        String password = "abc123";
 
        String url = "http://" + host +":80";
 
        try {
            HttpClient client = new DefaultHttpClient();
 
            AuthScope as = new AuthScope(host, 443);
            UsernamePasswordCredentials upc = new UsernamePasswordCredentials(
                    username, password);
 
            ((AbstractHttpClient) client).getCredentialsProvider()
                    .setCredentials(as, upc);
 
            BasicHttpContext localContext = new BasicHttpContext();
 
            BasicScheme basicAuth = new BasicScheme();
            localContext.setAttribute("preemptive-auth", basicAuth);
 
            HttpPost httpost = new HttpPost(url);
            httpost.setEntity(new ByteArrayEntity(jsonPeople.getBytes("UTF8"))); 
            
            httpost.setHeader("json", jsonPeople); 
            HttpResponse response = client.execute(httpost); 
 
            HttpEntity entity = response.getEntity();
            Object content = EntityUtils.toString(entity);
 
            Log.d("GSON", "OK: " + content.toString());
 
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("GSON", "Error: " + e.getMessage());
        }
	}
}

