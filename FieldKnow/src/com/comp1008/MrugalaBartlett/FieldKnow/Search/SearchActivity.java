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
import android.widget.Toast;

import com.comp1008.MrugalaBartlett.FieldKnow.MyOnItemSelectedListener;
import com.comp1008.MrugalaBartlett.FieldKnow.R;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.PersonDB;

public class SearchActivity extends Activity {
	MyOnItemSelectedListener spinnerData = new MyOnItemSelectedListener();
	PersonDB personDB = new PersonDB(SearchActivity.this);
	Spinner spinner;

	private OnClickListener submit_listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			EditText text = (EditText) findViewById(R.id.searchFor);

			int choice = spinnerData.getPosition();

			if (text.getText().toString() == null) {
				Toast.makeText(SearchActivity.this,
						"Please fill out all fields", Toast.LENGTH_LONG).show();
			} else {
				Intent intent = new Intent().setClass(SearchActivity.this,
						CustomListActivity.class);

				intent.putExtra("Search", text.getText().toString());
				intent.putExtra("Choice", choice);
				startActivity(intent);
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);

		final Button submitButton = (Button) findViewById(R.id.submitSearch);
		submitButton.setOnClickListener(submit_listener);

		spinner = (Spinner) findViewById(R.id.searchBy);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.search, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setOnItemSelectedListener(spinnerData);
		spinner.setAdapter(adapter);
	}
}
