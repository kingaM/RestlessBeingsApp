/**
 * @author Gemma Bartlett (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.Web;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.UserDB;

public class LoadPagesActivity extends Activity {

	WebView browser;
	UserDB db = new UserDB(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UserDB db = new UserDB(this);
		db.incrementCount();
		browser = new WebView(this);
		browser.loadUrl("file://sdcard/my_downloads/index.html");
		setContentView(browser);
	}
}