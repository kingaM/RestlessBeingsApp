/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 * Partly taken from Dean Mohamedally 
 */

package com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//provides a superclass for all the database classes used in this app
public class DBHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "FieldKnowDB";

	public DBHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Adding new data
	public long addData(String tablename, ContentValues contentvalues) {
		SQLiteDatabase db = this.getWritableDatabase();
		long id = db.insert(tablename, null, contentvalues);
		db.close();
		return id;
	}

	// deleting all rows of a table, returns the number of rows deleted
	public int deleteAll(String tablename) {
		SQLiteDatabase db = this.getWritableDatabase();
		int rows = db.delete(tablename, null, null);
		db.close();
		return rows;
	}

	// CRUD Rules - create, read, update and delete operations that any database
	// can do

	public void deleteByID(String tablename, String rowID, long id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(tablename, rowID + "=" + id, null);
		db.close();
	}

	public void deleteByString(String tablename, String rowID, String User) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(tablename, rowID + "='" + User + "'", null);
		db.close();
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	// running a query that returns a cursor to the query's data
	public Cursor runRawQuery(String tablename, String select) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(select, null);
		// database needs to be closed within the subclass
		return cursor;

	}

	// Updating single data item, nothing to return
	public void updateData(String tablename, ContentValues contentValues,
			String rowID, long id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.update(tablename, contentValues, rowID + "=" + id, null);
		db.close(); // Closing database connection

	}
}