/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.DataManager;

public class HistoryPerson {
	private String education;
	private String workingCond;
	private String occupation;
	private String earnings;
	private String notes;

	public String getEarnings() {
		return earnings;
	}

	public String getEducation() {
		return education;
	}

	public String getNotes() {
		return notes;
	}

	public String getOccupation() {
		return occupation;
	}

	public String getWorkingCond() {
		return workingCond;
	}

	public void setEarnings(String earnings) {
		this.earnings = earnings;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public void setWorkingCond(String workingCond) {
		this.workingCond = workingCond;
	}

}
