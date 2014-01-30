/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.DataCollect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.comp1008.MrugalaBartlett.FieldKnow.Formatting;
import com.comp1008.MrugalaBartlett.FieldKnow.MyOnItemSelectedListener;
import com.comp1008.MrugalaBartlett.FieldKnow.R;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.PersonDB;

//Gets the basic information of a Person, it is one of the tabs in DataCollect
public class BasicInfoActivity extends Activity {

	PersonDB db = new PersonDB(this);
	String s;
	Long id;

	MyOnItemSelectedListener spinnerListener = new MyOnItemSelectedListener();

	private OnClickListener submit_listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			final RadioGroup gender = (RadioGroup) findViewById(R.id.gender);
			RadioButton rbutton = (RadioButton) findViewById(gender
					.getCheckedRadioButtonId());

			EditText fullName = (EditText) findViewById(R.id.fullName);
			EditText height = (EditText) findViewById(R.id.height);
			EditText age = (EditText) findViewById(R.id.age);
			EditText pob = (EditText) findViewById(R.id.pob);
			String genderChoice = null;

			if (rbutton != null) {
				genderChoice = rbutton.getText().toString();
			}
			Integer ageParsed = Formatting
					.changeToInt(age.getText().toString());
			Integer heightParsed = Formatting.changeToInt(height.getText()
					.toString());

			db.updatePerson(id, fullName.getText().toString(), genderChoice,
					ageParsed, heightParsed, spinnerListener.getString(), pob
							.getText().toString());

			fullName.setText("");
			height.setText("");
			age.setText("");
			pob.setText("");
			gender.clearCheck();

			switchTabInActivity(1);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.basic_info);

		Spinner spinner = (Spinner) findViewById(R.id.religion);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.religions, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(spinnerListener);

		final Button submitButton = (Button) findViewById(R.id.submit);
		submitButton.setOnClickListener(submit_listener);

		Bundle extras = getIntent().getExtras();
		id = extras.getLong("id");
	}

	public void switchTabInActivity(int indexTabToSwitchTo) {
		DataCollectActivity ParentActivity;
		ParentActivity = (DataCollectActivity) this.getParent();
		ParentActivity.switchTab(indexTabToSwitchTo);
	}
}
