/**
 * @author Kinga Mrugala and Gemma Bartlett (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comp1008.MrugalaBartlett.FieldKnow.MainApplication;

public class FinancePhotosDB extends DBHandler {

	public static final String KEY_ID = "id";

	public static final String KEY_USER = "User";
	public static final String KEY_PATH = "Path";
	public static final String TABLE_NAME = "financephotos";

	public FinancePhotosDB(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void addFinancePhoto(String path) {
		Long user = MainApplication.getId();
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_PATH, path);
		initialValues.put(KEY_USER, user);

		addData(TABLE_NAME, initialValues);
	}

	public ArrayList<String> getPaths() // retuns paths of photos
	{
		ArrayList<String> paths = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "select * from " + TABLE_NAME;
		Cursor cursor = runRawQuery(TABLE_NAME, query);
		if (cursor.moveToFirst()) {
			do {
				paths.add(cursor.getString(2));
			} while (cursor.moveToNext());
		}
		db.close();
		return paths;
	}

}
