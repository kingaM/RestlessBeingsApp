/**
 * @author Gemma Bartlett (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.UserDB;
import com.comp1008.MrugalaBartlett.FieldKnow.UserRelated.RegisterActivity;

public class FieldKnowMainActivity extends Activity {
	EditText User;
	EditText Pass;

	private OnClickListener mAddListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.loginButton:
				User = (EditText) findViewById(R.id.login);
				Pass = (EditText) findViewById(R.id.password);

				String username = User.getText().toString();

				UserDB dbUser = new UserDB(FieldKnowMainActivity.this);

				if (dbUser.checkLogin(username, Pass.getText().toString())) {
					Intent it = new Intent(FieldKnowMainActivity.this,
							MenuActivity.class);
					MainApplication.setUsername(username);
					MainApplication.setPassword(Pass.getText().toString());
					Log.d("id username", dbUser.getUserID(username) + "");
					MainApplication.setId(dbUser.getUserID(username));
					MainApplication.setEmail(dbUser.getEmail(username));
					startActivity(it);
				} else {
					Toast.makeText(FieldKnowMainActivity.this,
							"Invalid Username/Password", Toast.LENGTH_LONG)
							.show();
				}
				User.setText("");
				Pass.setText("");
				break;

			case R.id.newuserButton:
				Intent it = new Intent(FieldKnowMainActivity.this,
						RegisterActivity.class);
				startActivity(it);
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		Button LoginButton = (Button) findViewById(R.id.loginButton);
		Button RegisterButton = (Button) findViewById(R.id.newuserButton);
		LoginButton.setOnClickListener(mAddListener);
		RegisterButton.setOnClickListener(mAddListener);
	}
}
