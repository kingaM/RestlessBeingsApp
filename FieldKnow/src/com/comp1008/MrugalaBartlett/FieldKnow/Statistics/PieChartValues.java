/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.Statistics;

import android.graphics.Color;

//Stores label and value for PieChart and assigns each part of the chart a colour
public class PieChartValues {

	private int value;
	private String label;
	private int color;
	static int i;

	public PieChartValues(int value, String label) {
		super();
		this.value = value;
		this.label = label + " " + value;
		color = setColorAuto();
		i++;
	}

	public int getColor() {
		return color;
	}

	public String getLabel() {
		return label;
	}

	public int getValue() {
		return value;
	}

	public void setColor(int color) {
		this.color = color;
	}

	private int setColorAuto() {
		int[] colors = new int[] { Color.BLACK, Color.BLUE, Color.YELLOW,
				Color.CYAN, Color.GREEN, Color.MAGENTA };

		return colors[i % 6];
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "PieChartValues [value=" + value + ", label=" + label
				+ ", color=" + color + "]";
	}

}
