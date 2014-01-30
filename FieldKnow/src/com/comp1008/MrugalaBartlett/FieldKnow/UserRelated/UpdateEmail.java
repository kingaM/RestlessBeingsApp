/**
 * @author Gemma Bartlett (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.UserRelated;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.comp1008.MrugalaBartlett.FieldKnow.R;

public class UpdateEmail extends Activity {

	EditText Email;
	private String currentEmail;

	private OnClickListener mAddListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.update:

				getSharedPreferences("myPrefs", MODE_PRIVATE).edit()
						.putString("email", Email.getText().toString())
						.commit();

				Toast.makeText(UpdateEmail.this, "Updated", Toast.LENGTH_LONG)
						.show();

				Intent it = new Intent(UpdateEmail.this, SettingsActivity.class);
				startActivity(it);
			}

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updateemail);
		Button setButton = (Button) findViewById(R.id.update);
		setButton.setOnClickListener(mAddListener);
		currentEmail = getSharedPreferences("myPrefs", MODE_PRIVATE).getString(
				"email", "field.know.test@gmail.com");
		Email = (EditText) findViewById(R.id.editText1);
		Email.setText(currentEmail);
	}
}
