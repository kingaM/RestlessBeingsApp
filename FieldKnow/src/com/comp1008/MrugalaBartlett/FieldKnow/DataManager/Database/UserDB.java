/**
 * @author Gemma Bartlett (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.comp1008.MrugalaBartlett.FieldKnow.MainApplication;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.User;

public class UserDB extends DBHandler {
	int id = 0;

	public static final String KEY_ROWID = "_id";
	public static final String KEY_USER = "User";
	public static final String KEY_PWD = "Pass";
	public static final String KEY_FNAME = "FName";
	public static final String KEY_LNAME = "LName";
	public static final String KEY_EMAIL = "Email";
	public static final String KEY_WEBCOUNT = "WebCount";
	private static final String TAG = "DBAdapter";
	private static final String TABLE_NAME = "User";

	private static final String TABLE_CREATE = "create table User (_id integer primary key, "
			+ "User text not null, Pass text not null, FName text not null, LName text not null, Email text not null, WebCount int not null);";

	private static final String TABLE_CREATE2 = "create table PersonInfo (_id integer primary key, "
			+ "date text not null, user integer not null, FullName text, Gender text, Age int, Height int,"
			+ " Religion text, PlaceOfBirth text, CurrentLocation text, LivingCondition text, ToiletFacilities text, "
			+ "WaterSource text, Nutrition text, DayActivities text, NightActivities text, "
			+ "_idHistory text, "
			+ "NameF text, ContactF text, OccupationF text, EducationF text, EarningsF text, NameM text, ContactM text, OccupationM text, EducationM text, EarningsM text, "
			+ "NGO text, Immunisations text, Illness text, Doctors text, Photo text, Latitude double, Longitude double);";

	private static final String TABLE_CREATE_H = "create table PersonHistory (_id integer primary key autoincrement, "
			+ "_idPerson text not null, Education text, WorkingConditions text, Occupation text, Earnigns text, Notes text);";

	private static final String TABLE_CREATE3 = "create table itemrequests (_id integer primary key autoincrement, "
			+ "User text not null, Item text not null, Info text, Lat double, Long double, Sent int);";

	private static final String TABLE_CREATE4 = "create table financephotos (_id integer primary key autoincrement, User integer not null , Path text not null);";

	public UserDB(Context context) {
		super(context);
	}

	public void addUser(Long id, String FName, String LName, String Email,
			String User, String Pass) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_ROWID, id);
		initialValues.put(KEY_USER, User);
		initialValues.put(KEY_PWD, Pass);
		initialValues.put(KEY_FNAME, FName);
		initialValues.put(KEY_LNAME, LName);
		initialValues.put(KEY_EMAIL, Email);
		initialValues.put(KEY_WEBCOUNT, 0);
		addData(TABLE_NAME, initialValues);

	}

	public boolean checkLogin(String User, String Pass) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME
				+ " WHERE User=? AND Pass=?", new String[] { User, Pass });
		if (mCursor != null) {
			if (mCursor.getCount() > 0) {
				db.close();
				return true;
			}
		}
		db.close();
		return false;
	}

	public boolean checkUser(String User) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE User = '" + User
				+ "'";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToFirst()) {
			db.close();
			return false;
		} else {
			db.close();
			return true;
		}
	}

	public ArrayList<User> getAllUsers() {
		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<User> userList = new ArrayList<User>();
		String selectAll = "SELECT * FROM " + TABLE_NAME;

		Cursor cursor = runRawQuery(TABLE_NAME, selectAll);
		if (cursor.moveToFirst()) {
			do {
				User user = new User(cursor.getLong(0), cursor.getString(1),
						cursor.getString(2), cursor.getString(3),
						cursor.getString(4), cursor.getString(5));
				userList.add(user);
			} while (cursor.moveToNext());
		}
		db.close();
		return userList;
	}

	public String getEmail(String username) {
		String updateEmail = "";
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "select * from " + TABLE_NAME + " where " + KEY_USER
				+ "= '" + username + "'";
		Cursor cursor = runRawQuery(TABLE_NAME, query);
		if (cursor.moveToFirst()) {
			do {
				updateEmail = cursor.getString(5);
			} while (cursor.moveToNext());
		}
		db.close();

		return updateEmail;
	}

	public long getUserID(String username) {
		long id = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "select * from " + TABLE_NAME + " where " + KEY_USER
				+ "= '" + username + "'";
		Cursor cursor = runRawQuery(TABLE_NAME, query);
		if (cursor.moveToFirst()) {
			do {
				id = cursor.getLong(0);
			} while (cursor.moveToNext());
		}
		db.close();

		return id;
	}

	public int incrementCount() {
		Long userID = MainApplication.getId();
		int count = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "select * from " + TABLE_NAME + " where " + KEY_ROWID
				+ "= '" + userID + "'";
		Cursor cursor = runRawQuery(TABLE_NAME, query);
		if (cursor.moveToFirst()) {
			do {
				count = cursor.getInt(5);
			} while (cursor.moveToNext());
		}
		db.close();

		count++;

		ContentValues args = new ContentValues();
		args.put(KEY_WEBCOUNT, count);
		updateData(TABLE_NAME, args, KEY_ROWID, userID);

		return count;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE);
		db.execSQL(TABLE_CREATE2);
		db.execSQL(TABLE_CREATE_H);
		db.execSQL(TABLE_CREATE3);
		db.execSQL(TABLE_CREATE4);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

	public void updateUser(String Pass, String Email) {
		Long userID = MainApplication.getId();
		ContentValues args = new ContentValues();
		args.put(KEY_PWD, Pass);
		args.put(KEY_EMAIL, Email);
		updateData(TABLE_NAME, args, KEY_ROWID, userID);
	}
}
