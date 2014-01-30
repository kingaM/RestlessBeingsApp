/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.DataCollect;

import android.os.Bundle;

import com.comp1008.MrugalaBartlett.FieldKnow.GetLocationActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.PersonDB;

public class CurrentLocationActivity extends GetLocationActivity {

	@Override
	protected void saveData() {
		Bundle extras = getIntent().getExtras();
		Long id = extras.getLong("id");
		
		PersonDB personDB = new PersonDB(this);
		personDB.addLatAndLong(id, latitude, longitude);
		
		switchTabInActivity(6);
		
	}
	
	public void switchTabInActivity(int indexTabToSwitchTo) {
		DataCollectActivity ParentActivity;
		ParentActivity = (DataCollectActivity) this.getParent();
		ParentActivity.switchTab(indexTabToSwitchTo);
	}
}
