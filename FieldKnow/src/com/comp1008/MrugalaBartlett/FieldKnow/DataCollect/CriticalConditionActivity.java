/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.DataCollect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.comp1008.MrugalaBartlett.FieldKnow.R;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.PersonDB;

//Gets information about critical conditions of a Person, it is one of the tabs in DataCollect
public class CriticalConditionActivity extends Activity {

	PersonDB db = new PersonDB(this);
	String immunisations;
	Long id;

	private OnClickListener submit_listenerCC = new OnClickListener() {
		@Override
		public void onClick(View v) {
			final RadioGroup ngo = (RadioGroup) findViewById(R.id.ngos);
			RadioButton rbutton = (RadioButton) findViewById(ngo
					.getCheckedRadioButtonId());

			EditText illness = (EditText) findViewById(R.id.illness);
			EditText doctors = (EditText) findViewById(R.id.doctors);
			EditText immunisations = (EditText) findViewById(R.id.immunisationsText);
			String ngoChoice = null;

			if (rbutton != null) {
				ngoChoice = rbutton.getText().toString();
			}

			db.updateCriticalCond(id, ngoChoice, immunisations.getText()
					.toString(), illness.getText().toString(), doctors
					.getText().toString());

			illness.setText("");
			doctors.setText("");
			immunisations.setText("");
			ngo.clearCheck();

			switchTabInActivity(5);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.critical_cond);

		final Button submitButtonCC = (Button) findViewById(R.id.submitCC);
		submitButtonCC.setOnClickListener(submit_listenerCC);

		Bundle extras = getIntent().getExtras();
		id = extras.getLong("id");
	}

	public void switchTabInActivity(int indexTabToSwitchTo) {
		DataCollectActivity ParentActivity;
		ParentActivity = (DataCollectActivity) this.getParent();
		ParentActivity.switchTab(indexTabToSwitchTo);
	}
}
