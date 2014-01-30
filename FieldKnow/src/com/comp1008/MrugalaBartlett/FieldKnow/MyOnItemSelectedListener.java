/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 * Can be used as a class on its own, or as a superclass
 * Listener for Spinner Widgets 
 */

package com.comp1008.MrugalaBartlett.FieldKnow;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class MyOnItemSelectedListener implements OnItemSelectedListener {
	protected String stringAtPosition = null;
	protected int position = 0;

	public int getPosition() {
		return position;
	}

	public String getString() {
		return stringAtPosition;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		position = pos;
		stringAtPosition = parent.getItemAtPosition(pos).toString();
		setText();
	}

	@Override
	public void onNothingSelected(AdapterView parent) {
		// Do nothing.
	}

	// Used when the class is extended to set text/view on a particular item
	// selected
	public void setText() {
		// do nothing
	}
}
