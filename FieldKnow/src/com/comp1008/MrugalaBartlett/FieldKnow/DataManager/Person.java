/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.DataManager;

import java.util.ArrayList;

import com.comp1008.MrugalaBartlett.FieldKnow.MainApplication;

public class Person {

	private long id;
	private String user;
	private String fullName;
	private String gender;
	private Integer age;
	private Integer height;
	private String religion;
	private String pob;
	private String location;
	private String living;
	private String toilet;
	private String water;
	private String nutrition;
	private String dayAct;
	private String nightAct;
	private ArrayList<HistoryPerson> histories;
	private String nameF;
	private String contactF;
	private String occupationF;
	private String educationF;
	private String earningsF;
	private String nameM;
	private String contactM;
	private String occupationM;
	private String educationM;
	private String earningsM;
	private String ngo;
	private String immunisations;
	private String illness;
	private String doctors;
	private String photo;
	private String latitude;
	private String longitude;

	public Person() {
		this.user = MainApplication.getUsername();
	}

	public Integer getAge() {
		return age;
	}

	public String getContactF() {
		return contactF;
	}

	public String getContactM() {
		return contactM;
	}

	public String getDayAct() {
		return dayAct;
	}

	public String getPob() {
		return pob;
	}

	public String getDoctors() {
		return doctors;
	}

	public String getEarningsF() {
		return earningsF;
	}

	public String getEarningsM() {
		return earningsM;
	}

	public String getEducationF() {
		return educationF;
	}

	public String getEducationM() {
		return educationM;
	}

	public String getFullName() {
		return fullName;
	}

	public String getGender() {
		return gender;
	}

	public Integer getHeight() {
		return height;
	}

	public ArrayList<HistoryPerson> getHistories() {
		return histories;
	}

	public long getId() {
		return id;
	}

	public String getIllness() {
		return illness;
	}

	public String getImmunisations() {
		return immunisations;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLiving() {
		return living;
	}

	public String getLocation() {
		return location;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getNameF() {
		return nameF;
	}

	public String getNameM() {
		return nameM;
	}

	public String getNgo() {
		return ngo;
	}

	public String getNightAct() {
		return nightAct;
	}

	public String getNutrition() {
		return nutrition;
	}

	public String getOccupationF() {
		return occupationF;
	}

	public String getOccupationM() {
		return occupationM;
	}

	public String getPhoto() {
		return photo;
	}

	public String getReligion() {
		return religion;
	}

	public String getToilet() {
		return toilet;
	}

	public String getUser() {
		return user;
	}

	public String getWater() {
		return water;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setContactF(String contactF) {
		this.contactF = contactF;
	}

	public void setContactM(String contactM) {
		this.contactM = contactM;
	}

	public void setCriticalCond(String ngo, String immunisations,
			String illness, String doctors) {
		this.ngo = ngo;
		this.immunisations = immunisations;
		this.illness = illness;
		this.doctors = doctors;
	}

	public void setDayAct(String dayAct) {
		this.dayAct = dayAct;
	}

	public void setDeatails(String location, String living, String toilet,
			String water, String nutrition, String dayAct, String nightAct) {
		this.location = location;
		this.living = living;
		this.toilet = toilet;
		this.water = water;
		this.nutrition = nutrition;
		this.dayAct = dayAct;
		this.nightAct = nightAct;
	}

	public void setPob(String pob) {
		this.pob = pob;
	}

	public void setDoctors(String doctors) {
		this.doctors = doctors;
	}

	public void setEarningsF(String earningsF) {
		this.earningsF = earningsF;
	}

	public void setEarningsM(String earningsM) {
		this.earningsM = earningsM;
	}

	public void setEducationF(String educationF) {
		this.educationF = educationF;
	}

	public void setEducationM(String educationM) {
		this.educationM = educationM;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public void setHistories(ArrayList<HistoryPerson> histories) {
		this.histories = histories;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setIllness(String illness) {
		this.illness = illness;
	}

	public void setImmunisations(String immunisations) {
		this.immunisations = immunisations;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLiving(String living) {
		this.living = living;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setNameF(String nameF) {
		this.nameF = nameF;
	}

	public void setNameM(String nameM) {
		this.nameM = nameM;
	}

	public void setNgo(String ngo) {
		this.ngo = ngo;
	}

	public void setNightAct(String nightAct) {
		this.nightAct = nightAct;
	}

	public void setNutrition(String nutrition) {
		this.nutrition = nutrition;
	}

	public void setOccupationF(String occupationF) {
		this.occupationF = occupationF;
	}

	public void setOccupationM(String occupationM) {
		this.occupationM = occupationM;
	}

	public void setParents(String nameF, String contactF, String occupationF,
			String educationF, String earningsF, String nameM, String contactM,
			String occupationM, String educationM, String earningsM) {
		this.nameF = nameF;
		this.contactF = contactF;
		this.occupationF = occupationF;
		this.educationF = educationF;
		this.earningsF = earningsF;
		this.nameM = nameM;
		this.contactM = contactM;
		this.occupationM = occupationM;
		this.educationM = educationM;
		this.earningsM = earningsM;
	}

	public void setPerson(String fullName, String gender, Integer age,
			Integer height, String religion, String dob) {
		this.fullName = fullName;
		this.gender = gender;
		this.age = age;
		this.height = height;
		this.religion = religion;
		this.pob = dob;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public void setToilet(String toilet) {
		this.toilet = toilet;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setWater(String water) {
		this.water = water;
	}

}
