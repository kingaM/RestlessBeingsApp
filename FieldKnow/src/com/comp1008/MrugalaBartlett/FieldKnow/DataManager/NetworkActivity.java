package com.comp1008.MrugalaBartlett.FieldKnow.DataManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class NetworkActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ConnectivityManager mgrConn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		TelephonyManager mgrTel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		Intent it;
		Bundle extras = getIntent().getExtras();
		int method = extras.getInt("method");
		if (mgrConn.getActiveNetworkInfo() == null
				|| (mgrConn.getActiveNetworkInfo() != null && mgrConn
						.getActiveNetworkInfo().getState() != NetworkInfo.State.CONNECTED)
				|| mgrTel.getNetworkType() != TelephonyManager.NETWORK_TYPE_UMTS) {
			Toast.makeText(
					NetworkActivity.this,
					"No internet connection... \n Transferring data is impossible at the moment.",
					Toast.LENGTH_LONG).show();
			finish();
		} else {
			if(method == 0)
				it = new Intent(NetworkActivity.this, EmailActivity.class);
			else
				it = new Intent(NetworkActivity.this, GSONActivity.class);
			startActivity(it);
			finish();
		}
	}
}
