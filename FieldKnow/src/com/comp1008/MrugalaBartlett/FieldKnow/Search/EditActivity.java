/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.Search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.comp1008.MrugalaBartlett.FieldKnow.MenuActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.MyOnItemSelectedListener;
import com.comp1008.MrugalaBartlett.FieldKnow.R;
import com.comp1008.MrugalaBartlett.FieldKnow.DataCollect.HistoryActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Person;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.PersonDB;

public class EditActivity extends Activity {
	public class MyOnItemSelectedListenerEdit extends MyOnItemSelectedListener {
		@Override
		public void setText() {
			Integer variable = null;
			switch (position) {
			case 0: // choice = Age
				variable = person.getAge();
				break;
			case 1: // choice = Height
				variable = person.getHeight();
				break;
			}
			oldData.setText(stringAtPosition + ": " + variable);
		}
	}

	MyOnItemSelectedListenerEdit its = new MyOnItemSelectedListenerEdit();
	PersonDB personDB = new PersonDB(this);
	Person person;
	Long id;

	TextView oldData;

	private OnClickListener submit_listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.addHistory:
				Intent intentH = new Intent().setClass(EditActivity.this,
						HistoryActivity.class);

				intentH.putExtra("id", id);
				intentH.putExtra("tab", 0);
				startActivity(intentH);
				break;

			case R.id.submitEdit:
				int choice = its.getPosition();
				EditText text = (EditText) findViewById(R.id.newData);
				String newData = text.getText().toString();
				switch (choice) {
				case 0: // choice = Age
					personDB.updateAge(id, newData);
					break;
				case 1: // choice = Height
					personDB.updateHeight(id, newData);
					break;
				}
				Intent intent = new Intent().setClass(EditActivity.this,
						MenuActivity.class);
				startActivity(intent);
				break;
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);

		Bundle extras = getIntent().getExtras();
		id = extras.getLong("id");

		person = personDB.findPerson(id);

		Spinner spinner = (Spinner) findViewById(R.id.editField);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.edit, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(its);

		final Button historyButton = (Button) findViewById(R.id.addHistory);
		historyButton.setOnClickListener(submit_listener);

		final Button submitButton = (Button) findViewById(R.id.submitEdit);
		submitButton.setOnClickListener(submit_listener);

		TextView name = (TextView) findViewById(R.id.oldPersonName);
		name.setText("Name: " + person.getFullName());

		oldData = (TextView) findViewById(R.id.oldOtherData);
	}

}
