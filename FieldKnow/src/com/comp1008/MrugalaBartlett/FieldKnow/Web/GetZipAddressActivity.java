/**
 * @author Gemma Bartlett (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.Web;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.comp1008.MrugalaBartlett.FieldKnow.R;

public class GetZipAddressActivity extends Activity {
	private OnClickListener mAddListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String url = ((EditText) findViewById(R.id.editText1)).getText()
					.toString();

			Intent it = new Intent(GetZipAddressActivity.this,
					DownloadActivity.class);
			it.putExtra("var1", url);
			startActivity(it);
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download);
		Button setButton = (Button) findViewById(R.id.download);
		setButton.setOnClickListener(mAddListener);
	}
}
