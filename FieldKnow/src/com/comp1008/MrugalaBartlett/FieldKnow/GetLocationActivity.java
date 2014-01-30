/**
 * @author Gemma Bartlett and Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 * 
 * based on code from http://stackoverflow.com/questions/9662320/gps-getting-the-distance-time-while-running-and-walking
 */

package com.comp1008.MrugalaBartlett.FieldKnow;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.comp1008.MrugalaBartlett.FieldKnow.R;

public abstract class GetLocationActivity extends Activity implements OnClickListener,
		android.content.DialogInterface.OnClickListener {

	class MyLocationListener implements LocationListener {
		@Override
		public void onLocationChanged(Location location) {
			if (location != null) {

				locManager.removeUpdates(locListener);

				longitude = location.getLongitude();
				latitude = location.getLatitude();

				editTextShowLocation.setText("Location:\n" + longitude + "\n"
						+ latitude);
				progress.setVisibility(View.GONE);
			}
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}
	}

	private TextView editTextShowLocation;
	private Button buttonGetLocation;
	private Button buttonSave;

	private ProgressBar progress;
	private LocationManager locManager;

	private LocationListener locListener = new MyLocationListener();
	private boolean gps_enabled = false;
	private boolean network_enabled = false;
	protected Double longitude = 0.0;

	protected Double latitude = 0.0;

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if (which == DialogInterface.BUTTON_NEUTRAL) {
			editTextShowLocation
					.setText("Sorry, location is not determined. To fix this please enable location providers");
		} else if (which == DialogInterface.BUTTON_POSITIVE) {
			startActivity(new Intent(
					android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonGetLocation:
			progress.setVisibility(View.VISIBLE);
			// exceptions will be thrown if provider is not permitted.
			try {
				gps_enabled = locManager
						.isProviderEnabled(LocationManager.GPS_PROVIDER);
			} catch (Exception ex) {
			}
			try {
				network_enabled = locManager
						.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			} catch (Exception ex) {
			}

			// don't start listeners if no provider is enabled
			if (!gps_enabled && !network_enabled) {
				AlertDialog.Builder builder = new Builder(this);
				builder.setTitle("Attention!");
				builder.setMessage("Sorry, location is not determined. Please enable location providers");
				builder.setPositiveButton("OK", this);
				builder.setNeutralButton("Cancel", this);
				builder.create().show();
				progress.setVisibility(View.GONE);
			}

			if (gps_enabled) {
				locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
						0, 0, locListener);
			}
			if (network_enabled) {
				locManager.requestLocationUpdates(
						LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
			}
			break;
		case R.id.save:
			saveData();
			break;
			
		}
	}

	protected abstract void saveData();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location);
		editTextShowLocation = (TextView) findViewById(R.id.locationText);

		progress = (ProgressBar) findViewById(R.id.progressBar1);
		progress.setVisibility(View.GONE);

		buttonGetLocation = (Button) findViewById(R.id.buttonGetLocation);
		buttonSave = (Button) findViewById(R.id.save);
		buttonGetLocation.setOnClickListener(this);
		buttonSave.setOnClickListener(this);

		locManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
	}

}
