/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.HistoryPerson;

public class HistoryPersonDB extends DBHandler {

	public static final String KEY_ROWID = "_id";

	public static final String KEY_PERSONID = "_idPerson";
	public static final String KEY_EDUCATION = "Education";
	public static final String KEY_WORKING_COND = "WorkingConditions";
	public static final String KEY_OCCUPATION = "Occupation";
	public static final String KEY_EARNINGS = "Earnigns";
	public static final String KEY_NOTES = "Notes";
	private static final String TABLE_NAME = "PersonHistory";

	private static final String TABLE_CREATE_H = "create table HistoryPerson (_id integer primary key, "
			+ "_idPerson text not null, Education text, WorkingConditions text, Occupation text, Earnings text, Notes text);";

	public HistoryPersonDB(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public long createHistory(long id, long userId, String education,
			String workingCond, String occupation, String earnings, String notes) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_ROWID, id);
		initialValues.put(KEY_PERSONID, userId);
		initialValues.put(KEY_EDUCATION, education);
		initialValues.put(KEY_WORKING_COND, workingCond);
		initialValues.put(KEY_OCCUPATION, occupation);
		initialValues.put(KEY_EARNINGS, earnings);
		initialValues.put(KEY_NOTES, notes);
		return addData(TABLE_NAME, initialValues);
	}

	public ArrayList<HistoryPerson> getHistories(long id) {
		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<HistoryPerson> personList = new ArrayList<HistoryPerson>();
		String getHist = "SELECT * FROM " + TABLE_NAME + " WHERE "
				+ KEY_PERSONID + "=" + id;

		Cursor cursor = runRawQuery(TABLE_NAME, getHist);
		if (cursor.moveToFirst()) {
			do {
				HistoryPerson hist = new HistoryPerson();
				hist.setEducation(cursor.getString(2));
				hist.setWorkingCond(cursor.getString(3));
				hist.setOccupation(cursor.getString(4));
				hist.setEarnings(cursor.getString(5));
				hist.setNotes(cursor.getString(6));
				personList.add(hist);
			} while (cursor.moveToNext());
		}
		db.close();
		return personList;
	}

}
