/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.DataCollect;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.comp1008.MrugalaBartlett.FieldKnow.MenuActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.MyOnItemSelectedListener;
import com.comp1008.MrugalaBartlett.FieldKnow.R;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.HistoryPersonDB;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.PersonDB;

//Gets history information of a Person, it is one of the tabs in DataCollect
//Enables the Person to have multiple histories
public class HistoryActivity extends Activity {

	PersonDB db = new PersonDB(this);
	HistoryPersonDB dbHistory = new HistoryPersonDB(this);
	MyOnItemSelectedListener spinnerListener = new MyOnItemSelectedListener();
	long id;
	int tab;

	private OnClickListener submitH_listener = new OnClickListener() {
		@Override
		public void onClick(View v) {

			EditText workingCond = (EditText) findViewById(R.id.workingConditions);
			EditText occupation = (EditText) findViewById(R.id.occupation);
			EditText earnings = (EditText) findViewById(R.id.earnings);
			EditText notes = (EditText) findViewById(R.id.notes);

			Calendar cal = Calendar.getInstance();

			TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			long historyId = Long.parseLong(telephonyManager.getDeviceId())
					+ cal.getTimeInMillis();

			dbHistory.createHistory(historyId, id, spinnerListener.getString(),
					workingCond.getText().toString(), occupation.getText()
							.toString(), earnings.getText().toString(), notes
							.getText().toString());
			db.addHistory(historyId, id); // stored the id of the latest history
											// entry in the person table
			workingCond.setText("");
			occupation.setText("");
			earnings.setText("");
			notes.setText("");

			if (tab == 1) {
				switchTabInActivity(3); // if it is in the TabView it switches
										// the tab
			} else {
				Intent intent = new Intent().setClass(HistoryActivity.this,
						MenuActivity.class); // if it is in the edit activity it
												// goes back to the menu
				startActivity(intent);
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history);

		Spinner spinner = (Spinner) findViewById(R.id.education);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.levelOfEducation,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(spinnerListener);

		final Button submitHButton = (Button) findViewById(R.id.submitH);
		submitHButton.setOnClickListener(submitH_listener);

		Bundle extras = getIntent().getExtras();
		id = extras.getLong("id");
		tab = extras.getInt("tab");
	}

	public void switchTabInActivity(int indexTabToSwitchTo) {
		DataCollectActivity ParentActivity;
		ParentActivity = (DataCollectActivity) this.getParent();
		ParentActivity.switchTab(indexTabToSwitchTo);
	}
}
