/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.DataManager;

/**
 * Sends the Database and Photos as a zip file in an e-mail attachment
 * the zip file is not deleted after sending the data
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.comp1008.MrugalaBartlett.FieldKnow.Formatting;
import com.comp1008.MrugalaBartlett.FieldKnow.MainApplication;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.FinancePhotosDB;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.PersonDB;

public class EmailActivity extends Activity {

	public ArrayList<String> getPaths() {
		PersonDB personDB = new PersonDB(EmailActivity.this);
		FinancePhotosDB financePhotos = new FinancePhotosDB(EmailActivity.this);

		ArrayList<Person> people = personDB.getAllPeople();
		Iterator<Person> it = people.iterator();
		ArrayList<String> photos = financePhotos.getPaths();

		while (it.hasNext()) {
			Person person = it.next();
			if (person.getPhoto() != null) {
				photos.add(person.getPhoto());
			}
		}
		return photos;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Calendar cal = Calendar.getInstance();
		String date = Formatting.formatDateTime(cal.getTimeInMillis());

		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		long zipID = Long.parseLong(telephonyManager.getDeviceId())
				+ cal.getTimeInMillis(); // for unique file name

		ArrayList<String> pathsAL = getPaths(); // gets the paths of photos from
												// the database

		String dbPath = this.getDatabasePath("FieldKnowDB").getAbsolutePath();
		pathsAL.add(dbPath);

		String email = getSharedPreferences("myPrefs", MODE_PRIVATE).getString(
				"email", "field.know.test@gmail.com");

		String filePath = Environment.getExternalStorageDirectory() + "/"
				+ "FieldKnowZIP" + zipID + ".zip";
		File zipFile = new File(filePath);

		Compress comp = new Compress(pathsAL, zipFile.getAbsolutePath());
		comp.zip();

		Intent mailIntent = new Intent(Intent.ACTION_SEND);
		mailIntent.setType("multipart/mixed");
		mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { email });
		mailIntent.putExtra(Intent.EXTRA_SUBJECT, "FieldKnowDB");
		mailIntent.putExtra(Intent.EXTRA_TEXT, "FieldKnow Database from "
				+ MainApplication.getUsername() + ". Sent on " + date);
		mailIntent.putExtra(Intent.EXTRA_STREAM,
				Uri.parse("file://" + zipFile.getAbsolutePath()));
		startActivity(Intent.createChooser(mailIntent, "Send mail..."));

		Boolean isDataWipe = getSharedPreferences("myPrefs", MODE_PRIVATE)
				.getBoolean("dataWipe", true);

		Log.d("DATA WIPE", isDataWipe + "");

		if (isDataWipe) {
			PersonDB dbPerson = new PersonDB(EmailActivity.this);
			dbPerson.deleteAll();
		}
		// zipFile.delete(); - was meant to delete the zip after the e-mail is
		// sent, however it does it before that and as a result the zip is empty
		finish();
	}
}
