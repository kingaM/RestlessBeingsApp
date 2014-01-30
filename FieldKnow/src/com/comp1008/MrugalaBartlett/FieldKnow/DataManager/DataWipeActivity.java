/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.DataManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

import com.comp1008.MrugalaBartlett.FieldKnow.MenuActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.R;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.PersonDB;

public class DataWipeActivity extends Activity {

	private OnClickListener wipe_listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			PersonDB dbPerson = new PersonDB(DataWipeActivity.this);
			dbPerson.deleteAll();
			Intent intent = new Intent().setClass(DataWipeActivity.this,
					MenuActivity.class);
			startActivity(intent);
		}
	};

	public void onCheckboxClicked(View v) {
		// Perform action on clicks, depending on whether it's now checked
		getSharedPreferences("myPrefs", MODE_PRIVATE).edit()
				.putBoolean("dataWipe", ((CheckBox) v).isChecked()).commit();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_wipe);

		final CheckBox dataWipe = (CheckBox) findViewById(R.id.dataWipeBool);
		dataWipe.setChecked(getSharedPreferences("myPrefs", MODE_PRIVATE)
				.getBoolean("dataWipe", true));

		final Button button = (Button) findViewById(R.id.dataWipeNow);
		button.setOnClickListener(wipe_listener);
	}
}
