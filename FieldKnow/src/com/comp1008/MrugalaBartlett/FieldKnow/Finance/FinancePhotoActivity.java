/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.Finance;

import android.content.Intent;

import com.comp1008.MrugalaBartlett.FieldKnow.PhotoActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.FinancePhotosDB;

public class FinancePhotoActivity extends PhotoActivity {

	@Override
	protected void returnIntent() {
		Intent myIntent = new Intent(FinancePhotoActivity.this,
				FinanceActivity.class);
		FinancePhotoActivity.this.startActivity(myIntent);

	}

	@Override
	protected void saveData(String realPath) {
		FinancePhotosDB db = new FinancePhotosDB(this);
		db.addFinancePhoto(realPath);
	}

}
