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
import android.widget.Spinner;

import com.comp1008.MrugalaBartlett.FieldKnow.MyOnItemSelectedListener;
import com.comp1008.MrugalaBartlett.FieldKnow.R;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.PersonDB;

//Gets information about parents of a Person, it is one of the tabs in DataCollect
public class ParentsActivity extends Activity {

	MyOnItemSelectedListener spinnerListenerM, spinnerListenerF;
	Long id;
	PersonDB db = new PersonDB(this);

	private OnClickListener submit_listenerP = new OnClickListener() {
		@Override
		public void onClick(View v) {
			EditText nameF = (EditText) findViewById(R.id.nameF);
			EditText contactF = (EditText) findViewById(R.id.contactF);
			EditText occupationF = (EditText) findViewById(R.id.occupationF);
			EditText earningsF = (EditText) findViewById(R.id.earningsF);

			EditText nameM = (EditText) findViewById(R.id.nameM);
			EditText contactM = (EditText) findViewById(R.id.contactM);
			EditText occupationM = (EditText) findViewById(R.id.occupationM);
			EditText earningsM = (EditText) findViewById(R.id.earningsM);

			db.updateParents(id, nameF.getText().toString(), contactF.getText()
					.toString(), occupationF.getText().toString(),
					spinnerListenerF.getString(), earningsF.getText()
							.toString(), nameM.getText().toString(), contactM
							.getText().toString(), occupationM.getText()
							.toString(), spinnerListenerM.getString(),
					earningsM.getText().toString());

			nameF.setText("");
			contactF.setText("");
			occupationF.setText("");
			earningsF.setText("");
			nameM.setText("");
			contactM.setText("");
			occupationM.setText("");
			earningsM.setText("");

			switchTabInActivity(4);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parents);

		spinnerListenerF = new MyOnItemSelectedListener();
		spinnerListenerM = new MyOnItemSelectedListener();

		Spinner spinnerF = (Spinner) findViewById(R.id.educationF);
		ArrayAdapter<CharSequence> adapterF = ArrayAdapter.createFromResource(
				this, R.array.levelOfEducation,
				android.R.layout.simple_spinner_item);
		adapterF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerF.setAdapter(adapterF);

		spinnerF.setOnItemSelectedListener(spinnerListenerF);

		Spinner spinnerM = (Spinner) findViewById(R.id.educationM);
		ArrayAdapter<CharSequence> adapterM = ArrayAdapter.createFromResource(
				this, R.array.levelOfEducation,
				android.R.layout.simple_spinner_item);
		adapterM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerM.setAdapter(adapterM);

		spinnerM.setOnItemSelectedListener(spinnerListenerM);

		final Button submitButton = (Button) findViewById(R.id.submitP);
		submitButton.setOnClickListener(submit_listenerP);

		Bundle extras = getIntent().getExtras();
		id = extras.getLong("id");
	}

	public void switchTabInActivity(int indexTabToSwitchTo) {
		DataCollectActivity ParentActivity;
		ParentActivity = (DataCollectActivity) this.getParent();
		ParentActivity.switchTab(indexTabToSwitchTo);
	}
}
