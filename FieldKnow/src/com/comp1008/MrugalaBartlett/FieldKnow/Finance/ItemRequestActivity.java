/**
 * @author Gemma Bartlett (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.Finance;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.comp1008.MrugalaBartlett.FieldKnow.R;

public class ItemRequestActivity extends Activity {

	LocationManager mlocManager = null;
	LocationListener mlocListener;
	AlertDialog.Builder alert;
	private OnClickListener mAddListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			EditText Item = (EditText) findViewById(R.id.editText1);
			EditText Info = (EditText) findViewById(R.id.editText2);
			Intent it = new Intent(ItemRequestActivity.this,
					FinanceGetLocationActivity.class);
			it.putExtra("item", Item.getText().toString());
			it.putExtra("info", Info.getText().toString());
			startActivity(it);
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itemrequest);
		Button setButton = (Button) findViewById(R.id.next);
		setButton.setOnClickListener(mAddListener);
	}

}
