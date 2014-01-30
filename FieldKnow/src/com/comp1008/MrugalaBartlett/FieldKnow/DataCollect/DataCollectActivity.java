/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.DataCollect;

import java.util.Calendar;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TabHost;

import com.comp1008.MrugalaBartlett.FieldKnow.Formatting;
import com.comp1008.MrugalaBartlett.FieldKnow.MainApplication;
import com.comp1008.MrugalaBartlett.FieldKnow.R;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.PersonDB;

public class DataCollectActivity extends TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_colect);

		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		Calendar cal = Calendar.getInstance();
		String date = Formatting.formatDateTime(cal.getTimeInMillis());

		PersonDB dbUser = new PersonDB(DataCollectActivity.this);

		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		long personID = Long.parseLong(telephonyManager.getDeviceId())
				+ cal.getTimeInMillis();

		long id = dbUser.addRow(personID, date, MainApplication.getId());

		intent = new Intent().setClass(this, BasicInfoActivity.class);
		intent.putExtra("id", id);
		spec = tabHost.newTabSpec("basic").setIndicator("Basic Info")
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, LivingConditionsActivity.class);
		intent.putExtra("id", id);
		spec = tabHost.newTabSpec("living").setIndicator("Living Conditions")
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, HistoryActivity.class);
		intent.putExtra("id", id);
		intent.putExtra("tab", 1);
		spec = tabHost.newTabSpec("history").setIndicator("History")
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, ParentsActivity.class);
		intent.putExtra("id", id);
		spec = tabHost.newTabSpec("parents").setIndicator("Parents")
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, CriticalConditionActivity.class);
		intent.putExtra("id", id);
		spec = tabHost.newTabSpec("critical")
				.setIndicator("Critical Conditions").setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, CurrentLocationActivity.class);
		intent.putExtra("id", id);
		spec = tabHost.newTabSpec("location").setIndicator("Current Location")
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, PersonPhotoActivity.class);
		intent.putExtra("id", id);
		spec = tabHost.newTabSpec("camera").setIndicator("Camera")
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, ClosedQsActivity.class);
		intent.putExtra("id", id);
		spec = tabHost.newTabSpec("closed").setIndicator("Closed Qs")
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, RankedQsActivity.class);
		intent.putExtra("id", id);
		spec = tabHost.newTabSpec("ranked").setIndicator("Ranked Qs")
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, CommentQsActivity.class);
		intent.putExtra("id", id);
		spec = tabHost.newTabSpec("comment").setIndicator("Comment Qs")
				.setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
	}

	public void switchTab(int tab) {
		TabHost tabHost = getTabHost();
		tabHost.setCurrentTab(tab);
	}
}
