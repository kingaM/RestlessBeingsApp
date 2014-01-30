/**
 * @author Gemma Bartlett (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.Web;

import java.io.File;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.comp1008.MrugalaBartlett.FieldKnow.R;

public class WebActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] MENUITEMS = getResources().getStringArray(R.array.web_array);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, MENUITEMS);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent myIntent;
		switch (position) {
		case 0:
			myIntent = new Intent(WebActivity.this, WebViewActivity.class);
			WebActivity.this.startActivity(myIntent);
			break;
		case 1:
			File file = new File("/sdcard/my_downloads/index.html");
			if (file.exists()) {
				myIntent = new Intent(WebActivity.this, LoadPagesActivity.class);
				WebActivity.this.startActivity(myIntent);
			} else {
				Toast.makeText(
						WebActivity.this,
						"Pages not found. Please download them from Settings page.",
						Toast.LENGTH_LONG).show();
			}

			break;
		default:
			myIntent = new Intent(WebActivity.this, WebActivity.class);
			WebActivity.this.startActivity(myIntent);
			break;
		}
	}

}