/**
 * @author Gemma Bartlett (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.UserRelated;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.comp1008.MrugalaBartlett.FieldKnow.MenuActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.R;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.DataWipeActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.Web.GetZipAddressActivity;

public class SettingsActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] MENUITEMS = getResources().getStringArray(
				R.array.settings_array);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, MENUITEMS);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent myIntent;
		switch (position) {
		case 0:
			myIntent = new Intent(SettingsActivity.this,
					UpdateDetailsActivity.class);
			break;
		case 1:
			myIntent = new Intent(SettingsActivity.this,
					GetZipAddressActivity.class);
			break;
		case 2:
			myIntent = new Intent(SettingsActivity.this, UpdateEmail.class);
			break;
		case 3:
			myIntent = new Intent(SettingsActivity.this, DataWipeActivity.class);
			break;
		case 4:
			myIntent = new Intent(SettingsActivity.this, MenuActivity.class);
			break;
		default:
			myIntent = new Intent(SettingsActivity.this, SettingsActivity.class);
			break;
		}

		SettingsActivity.this.startActivity(myIntent);
	}

}