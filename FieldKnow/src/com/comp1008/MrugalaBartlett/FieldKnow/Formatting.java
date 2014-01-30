/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Formatting {

	public static boolean changeToBool(String string) {
		boolean bool = false;
		if (string.equals("Yes")) {
			bool = true;
		}
		return bool;
	}

	public static Integer changeToInt(String string)
			throws NumberFormatException {
		Integer number = null;
		if (!string.equals("")) {
			number = Integer.parseInt(string);
		}
		return number;
	}

	public static String deleteMultiLine(String string) {
		if (!string.equals("")) {
			string = string.replace("\n", ", ");
		}
		return string;
	}

	public static String formatDateTime(long milis) {
		String format = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(milis));
	}
}
