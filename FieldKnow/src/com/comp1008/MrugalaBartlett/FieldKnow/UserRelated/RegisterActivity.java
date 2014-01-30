/**
 * @author Gemma Bartlett (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.UserRelated;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.comp1008.MrugalaBartlett.FieldKnow.FieldKnowMainActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.Formatting;
import com.comp1008.MrugalaBartlett.FieldKnow.R;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.User;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.UserDB;

public class RegisterActivity extends Activity {
	EditText User, Pass1, Pass2, FName, LName, Email;

	private OnClickListener mAddListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			FName = (EditText) findViewById(R.id.editText1);
			LName = (EditText) findViewById(R.id.editText2);
			Email = (EditText) findViewById(R.id.editText4);
			User = (EditText) findViewById(R.id.editText6);
			Pass1 = (EditText) findViewById(R.id.editText7);
			Pass2 = (EditText) findViewById(R.id.editText8);

			Calendar cal = Calendar.getInstance();
			Formatting.formatDateTime(cal.getTimeInMillis());

			TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

			long userID = Long.parseLong(telephonyManager.getDeviceId())
					+ cal.getTimeInMillis();

			User user = new User(userID, FName.getText().toString(), LName
					.getText().toString(), Email.getText().toString(), User
					.getText().toString(), Pass1.getText().toString());

			String prompt = user.checkInput(Pass2.getText().toString());
			if (prompt != null) {
				Toast.makeText(RegisterActivity.this, prompt, Toast.LENGTH_LONG)
						.show();
			} else {
				UserDB dbUser = new UserDB(RegisterActivity.this);
				if (!dbUser.checkUser(User.getText().toString())) {
					Toast.makeText(RegisterActivity.this,
							"That username has already been used",
							Toast.LENGTH_LONG).show();
				} else {
					dbUser.addUser(userID, FName.getText().toString(), LName
							.getText().toString(), Email.getText().toString(),
							User.getText().toString(), Pass1.getText()
									.toString());
					Intent it = new Intent(RegisterActivity.this,
							FieldKnowMainActivity.class);
					startActivity(it);
				}
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newuser);
		Button setButton = (Button) findViewById(R.id.register);
		setButton.setOnClickListener(mAddListener);
	}
}
