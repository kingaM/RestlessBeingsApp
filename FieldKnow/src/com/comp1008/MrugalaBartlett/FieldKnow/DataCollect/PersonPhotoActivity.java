/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.DataCollect;

import android.content.Intent;
import android.os.Bundle;

import com.comp1008.MrugalaBartlett.FieldKnow.MenuActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.PhotoActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.PersonDB;

public class PersonPhotoActivity extends PhotoActivity {

	@Override
	protected void returnIntent() {
		Intent myIntent = new Intent(this, MenuActivity.class);
		startActivity(myIntent);

	}

	@Override
	protected void saveData(String realPath) {
		Bundle extras = getIntent().getExtras();
		Long id = extras.getLong("id");
		PersonDB db = new PersonDB(this);
		db.updatePhoto(id, realPath);

	}

}
