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
import com.comp1008.MrugalaBartlett.FieldKnow.Finance.ItemRequest;

public class ItemRequestsDB extends DBHandler {

	public static final String ROW_ID = "_id";

	public static final String KEY_USERID = "User";
	public static final String KEY_ITEM = "Item";
	public static final String KEY_INFO = "Info";
	public static final String KEY_LAT = "Lat";
	public static final String KEY_LONG = "Long";
	public static final String KEY_SENT = "Sent";
	public static final String TABLE_NAME = "itemrequests";

	public ItemRequestsDB(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public long addItemRequest(String Item, String Info, double Latitude,
			double Longitude) {
		Long user = MainApplication.getId();
		ContentValues args = new ContentValues();
		args.put(KEY_USERID, user);
		args.put(KEY_ITEM, Item);
		args.put(KEY_INFO, Info);
		args.put(KEY_LAT, Latitude);
		args.put(KEY_LONG, Longitude);
		args.put(KEY_SENT, 0);
		long id = addData(TABLE_NAME, args);

		return id;
	}

	public void addLocation(Double latitude, Double longitude, long id) {
		ContentValues args = new ContentValues();
		args.put(KEY_LAT, latitude);
		args.put(KEY_LONG, longitude);
		updateData(TABLE_NAME, args, ROW_ID, id);
	}

	public ArrayList<ItemRequest> getItemRequests() {
		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<ItemRequest> itemRequests = new ArrayList<ItemRequest>();

		String selectAll = "SELECT * FROM " + TABLE_NAME;

		Cursor cursor = runRawQuery(TABLE_NAME, selectAll);
		if (cursor.moveToFirst()) {
			do {
				ItemRequest item = setItem(cursor);
				if (item.getStatus() == 0) {
					itemRequests.add(item);
				}
			} while (cursor.moveToNext());
		}
		db.close();
		return itemRequests;
	}

	private ItemRequest setItem(Cursor cursor) {
		ItemRequest item = new ItemRequest();
		item.setItem(cursor.getInt(0), cursor.getString(2),
				cursor.getString(3), cursor.getDouble(4), cursor.getDouble(5),
				cursor.getInt(6));
		return item;
	}

	public void updateStatus(long id) {
		ContentValues args = new ContentValues();
		args.put(KEY_SENT, 1);
		updateData(TABLE_NAME, args, ROW_ID, id);
	}
}
