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
import android.util.Log;

import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Person;

public class PersonDB extends DBHandler {
	public Context c;
	int id = 0;

	public static final String KEY_ROWID = "_id";

	public static final String KEY_DATE = "date";
	public static final String KEY_USER = "user";
	public static final String KEY_NAME = "FullName";
	public static final String KEY_GENDER = "Gender";
	public static final String KEY_AGE = "Age";
	public static final String KEY_HEIGHT = "Height";
	public static final String KEY_RELIGION = "Religion";
	public static final String KEY_POB = "PlaceOfBirth";
	public static final String KEY_LOCATION = "CurrentLocation";
	public static final String KEY_LIVING = "LivingCondition";
	public static final String KEY_TOILET = "ToiletFacilities";
	public static final String KEY_WATER = "WaterSource";
	public static final String KEY_NUTRITION = "Nutrition";
	public static final String KEY_DAY_ACT = "DayActivities";
	public static final String KEY_NIGHT_ACT = "NightActivities";
	public static final String KEY_HISTORYID = "_idHistory";
	public static final String KEY_NAME_F = "NameF";
	public static final String KEY_CONTACT_F = "ContactF";
	public static final String KEY_OCCUPATION_F = "OccupationF";
	public static final String KEY_EDUCATION_F = "EducationF";
	public static final String KEY_EARNINGS_F = "EarningsF";
	public static final String KEY_NAME_M = "NameM";
	public static final String KEY_CONTACT_M = "ContactM";
	public static final String KEY_OCCUPATION_M = "OccupationM";
	public static final String KEY_EDUCATION_M = "EducationM";
	public static final String KEY_EARNINGS_M = "EarningsM";
	public static final String KEY_NGO = "NGO";
	public static final String KEY_IMMUNISATIONS = "Immunisations";
	public static final String KEY_ILLNESS = "Illness";
	public static final String KEY_DOCTORS = "Doctors";
	public static final String KEY_PHOTO = "Photo";
	public static final String KEY_LAT = "Latitude";
	public static final String KEY_LONG = "Longitude";
	private static final String TAG = "PersonDB";
	private static final String TABLE_NAME = "PersonInfo";

	public PersonDB(Context context) {
		super(context);
		c = context;
	}

	public void addHistory(Long historyID, long id) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_HISTORYID, historyID);
		updateData(TABLE_NAME, initialValues, KEY_ROWID, id);
	}

	public void addLatAndLong(long id, double lat, double lon) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_LAT, lat);
		initialValues.put(KEY_LONG, lon);
		updateData(TABLE_NAME, initialValues, KEY_ROWID, id);
	}

	public long addRow(long personID, String date, long id) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_ROWID, personID);
		initialValues.put(KEY_DATE, date);
		initialValues.put(KEY_USER, id);
		return addData(TABLE_NAME, initialValues);
	}

	public void deleteAll() {
		deleteAll(TABLE_NAME);
		deleteAll("PersonHistory");
	}

	public Person findPerson(long id) {
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "select * from " + TABLE_NAME + " where " + KEY_ROWID
				+ "=" + id;
		Cursor cursor = runRawQuery(TABLE_NAME, query);
		Person person = null;
		if (cursor.moveToFirst()) {
			do {
				person = setPerson(cursor);
			} while (cursor.moveToNext());
		}
		db.close();

		return person;
	}

	public ArrayList<Person> getAllPeople() {
		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<Person> people = new ArrayList<Person>();

		String selectAll = "SELECT * FROM " + TABLE_NAME;

		Cursor cursor = runRawQuery(TABLE_NAME, selectAll);
		if (cursor.moveToFirst()) {
			do {
				Person person = setPerson(cursor);
				people.add(person);
			} while (cursor.moveToNext());
		}
		db.close();
		return people;
	}

	public Context getContext() {
		return c;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// db.execSQL(TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS personinfo");
		onCreate(db);
	}

	private Person setPerson(Cursor cursor) {
		HistoryPersonDB histories = new HistoryPersonDB(c);
		Person person = new Person();
		long id = cursor.getLong(0);
		person.setId(id);
		person.setPerson(cursor.getString(3), cursor.getString(4),
				cursor.getInt(5), cursor.getInt(6), cursor.getString(7),
				cursor.getString(8));
		person.setDeatails(cursor.getString(9), cursor.getString(10),
				cursor.getString(11), cursor.getString(12),
				cursor.getString(13), cursor.getString(14),
				cursor.getString(15));
		person.setParents(cursor.getString(17), cursor.getString(18),
				cursor.getString(19), cursor.getString(20),
				cursor.getString(21), cursor.getString(22),
				cursor.getString(23), cursor.getString(24),
				cursor.getString(25), cursor.getString(26));
		person.setCriticalCond(cursor.getString(27), cursor.getString(28),
				cursor.getString(29), cursor.getString(30));
		person.setPhoto(cursor.getString(31));
		person.setLatitude(cursor.getString(32));
		person.setLongitude(cursor.getString(33));
		person.setHistories(histories.getHistories(id));

		return person;
	}

	public void updateAge(long id, String newAge) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_AGE, newAge);
		updateData(TABLE_NAME, initialValues, KEY_ROWID, id);
	}

	public void updateCriticalCond(Long id, String ngo, String immunisations,
			String illness, String doctors) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NGO, ngo);
		initialValues.put(KEY_IMMUNISATIONS, immunisations);
		initialValues.put(KEY_ILLNESS, illness);
		initialValues.put(KEY_DOCTORS, doctors);
		updateData(TABLE_NAME, initialValues, KEY_ROWID, id);
	}

	public void updateDeatails(Long id, String location, String living,
			String toilet, String water, String nutrition, String dayAct,
			String nightAct) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_LOCATION, location);
		initialValues.put(KEY_LIVING, living);
		initialValues.put(KEY_TOILET, toilet);
		initialValues.put(KEY_WATER, water);
		initialValues.put(KEY_NUTRITION, nutrition);
		initialValues.put(KEY_DAY_ACT, dayAct);
		initialValues.put(KEY_NIGHT_ACT, nightAct);
		updateData(TABLE_NAME, initialValues, KEY_ROWID, id);
	}

	public void updateHeight(Long id, String newHeight) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_HEIGHT, newHeight);
		updateData(TABLE_NAME, initialValues, KEY_ROWID, id);
	}

	public void updateParents(Long id, String nameF, String contactF,
			String occupationF, String educationF, String earningsF,
			String nameM, String contactM, String occupationM,
			String educationM, String earningsM) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME_F, nameF);
		initialValues.put(KEY_CONTACT_F, contactF);
		initialValues.put(KEY_OCCUPATION_F, occupationF);
		initialValues.put(KEY_EDUCATION_F, educationF);
		initialValues.put(KEY_EARNINGS_F, earningsF);
		initialValues.put(KEY_NAME_M, nameM);
		initialValues.put(KEY_CONTACT_M, contactM);
		initialValues.put(KEY_OCCUPATION_M, occupationM);
		initialValues.put(KEY_EDUCATION_M, educationM);
		initialValues.put(KEY_EARNINGS_M, earningsM);
		updateData(TABLE_NAME, initialValues, KEY_ROWID, id);
	}

	public void updatePerson(Long id, String fullName, String gender,
			Integer ageParsed, Integer heightParsed, String religion, String dob) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, fullName);
		initialValues.put(KEY_GENDER, gender);
		initialValues.put(KEY_AGE, ageParsed);
		initialValues.put(KEY_HEIGHT, heightParsed);
		initialValues.put(KEY_RELIGION, religion);
		initialValues.put(KEY_POB, dob);
		updateData(TABLE_NAME, initialValues, KEY_ROWID, id);
	}

	public void updatePhoto(long id, String photo) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_PHOTO, photo);
		updateData(TABLE_NAME, initialValues, KEY_ROWID, id);
	}
}
