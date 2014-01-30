/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.Finance;

import android.content.Intent;
import android.os.Bundle;

import com.comp1008.MrugalaBartlett.FieldKnow.GetLocationActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.ItemRequestsDB;

public class FinanceGetLocationActivity extends GetLocationActivity {

	@Override
	protected void saveData() {
		String item = null;
		String info = null;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			item = extras.getString("item");
			info = extras.getString("info");
		}

		ItemRequestsDB itemsDB = new ItemRequestsDB(this);
		itemsDB.addItemRequest(item, info, longitude, latitude);
		Intent it1 = new Intent(this, FinanceActivity.class);
		startActivity(it1);
	}

}
