/**
 * @author Gemma Bartlett (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow;

import android.app.Application;

public class MainApplication extends Application {

	private static String username;
	private static String password;
	private static String email;
	private static long id;

	public static String getEmail() {
		return email;
	}

	public static long getId() {
		return id;
	}

	public static String getPassword() {
		return password;
	}

	public static String getUsername() {
		return username;
	}

	public static void setEmail(String email) {
		MainApplication.email = email;
	}

	public static void setId(long id) {
		MainApplication.id = id;
	}

	public static void setPassword(String password) {
		MainApplication.password = password;
	}

	public static void setUsername(String username) {
		MainApplication.username = username;
	}

}