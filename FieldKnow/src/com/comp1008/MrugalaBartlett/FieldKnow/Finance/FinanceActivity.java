/**
 * @author Gemma Bartlett (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.Finance;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.comp1008.MrugalaBartlett.FieldKnow.MenuActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.R;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.ItemRequestsDB;

public class FinanceActivity extends ListActivity {

	ItemRequestsDB itemsDB = new ItemRequestsDB(FinanceActivity.this);
	private String updateEmail;

	public String genBody() {
		String emailBody = "";
		ArrayList<ItemRequest> items = itemsDB.getItemRequests();
		Iterator<ItemRequest> it = items.iterator();

		while (it.hasNext()) {
			ItemRequest c = it.next();

			long id = c.getId();
			itemsDB.updateStatus(id);
			double longitude = c.getLongitude();
			double latitude = c.getLatitude();

			if ((longitude == 0.0) && (latitude == 0.0)) {
				emailBody = emailBody + "Item: " + c.getItem() + "\nDetails: "
						+ c.getInfo() + "\nLocation not obtained\n";
			} else {
				emailBody = emailBody + "Item: " + c.getItem() + "\nDetails: "
						+ c.getInfo() + "\nLongitude: " + c.getLongitude()
						+ "\nLatitude: " + c.getLatitude() + "\n\n\n";
			}
		}
		return emailBody;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] MENUITEMS = getResources().getStringArray(
				R.array.finance_array);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, MENUITEMS);
		setListAdapter(adapter);

		updateEmail = getSharedPreferences("myPrefs", MODE_PRIVATE).getString(
				"email", "field.know.test@gmail.com");
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent myIntent;
		switch (position) {
		case 0:
			myIntent = new Intent(FinanceActivity.this,
					ItemRequestActivity.class);
			FinanceActivity.this.startActivity(myIntent);
			break;
		case 1:
			myIntent = new Intent(FinanceActivity.this,
					FinancePhotoActivity.class);
			FinanceActivity.this.startActivity(myIntent);
			break;
		case 2:
			sendEmail(genBody());
			break;
		case 3:
			myIntent = new Intent(FinanceActivity.this, MenuActivity.class);
			startActivity(myIntent);
			break;
		default:
			myIntent = new Intent(FinanceActivity.this, FinanceActivity.class);
			FinanceActivity.this.startActivity(myIntent);
			break;
		}

	}

	private void sendEmail(String emailBody) {
		try {
			Intent emailIntent = new Intent(Intent.ACTION_SEND);

			String aEmailList[] = { updateEmail };

			emailIntent.putExtra(Intent.EXTRA_EMAIL, aEmailList);
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Item Requests");
			emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);

			emailIntent.setType("message/rfc822");

			startActivity(Intent.createChooser(emailIntent,
					"Select Email Application"));
		} catch (Exception ex) {
			Log.e("SendEmail", "Can't send email", ex);
		}
	}

}