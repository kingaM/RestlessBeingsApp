package com.comp1008.MrugalaBartlett.FieldKnow.Finance;

import com.comp1008.MrugalaBartlett.FieldKnow.MainApplication;

public class ItemRequest {

	private long id;
	private String user;
	private String item;
	private String info;
	private double longitude;
	private double latitude;
	private int sent;

	public ItemRequest() {
		this.user = MainApplication.getUsername();
	}

	public long getId() {
		return id;
	}

	public String getInfo() {
		return info;
	}

	public String getItem() {
		return item;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public int getStatus() {
		return sent;
	}

	public String getUser() {
		return user;
	}

	public void setItem(long id, String item, String info, double longitude,
			double latitude, int sent) {
		this.id = id;
		this.item = item;
		this.info = info;
		this.longitude = longitude;
		this.latitude = latitude;
		this.sent = sent;
	}
}
