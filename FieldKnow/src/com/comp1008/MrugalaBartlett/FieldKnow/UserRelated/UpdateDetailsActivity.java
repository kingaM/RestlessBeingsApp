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

import com.comp1008.MrugalaBartlett.FieldKnow.MainApplication;
import com.comp1008.MrugalaBartlett.FieldKnow.MenuActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.R;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.UserDB;

public class UpdateDetailsActivity extends Activity {
	UserDB db = new UserDB(this);
	EditText User, Pass, FName, LName, Email;
	private String currentPass = MainApplication.getPassword();
	private String currentEmail = MainApplication.getEmail();

	private OnClickListener mAddListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.update:
				UserDB dbUser = new UserDB(UpdateDetailsActivity.this);

				dbUser.updateUser(Pass.getText().toString(), Email.getText()
						.toString());

				dbUser.close();

				MainApplication.setPassword(Pass.getText().toString());
				MainApplication.setEmail(Email.getText().toString());

				Toast.makeText(UpdateDetailsActivity.this, "Updated",
						Toast.LENGTH_LONG).show();

				Intent it = new Intent(UpdateDetailsActivity.this,
						MenuActivity.class);
				startActivity(it);
			}

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		Button setButton = (Button) findViewById(R.id.update);
		setButton.setOnClickListener(mAddListener);
		Email = (EditText) findViewById(R.id.editText3);
		Pass = (EditText) findViewById(R.id.editText5);
		Pass.setText(currentPass);
		Email.setText(currentEmail);

	}
}
