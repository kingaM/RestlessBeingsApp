/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.DataCollect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.comp1008.MrugalaBartlett.FieldKnow.Formatting;
import com.comp1008.MrugalaBartlett.FieldKnow.R;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.PersonDB;

//Gets living conditions information of a Person, it is one of the tabs in DataCollect
public class LivingConditionsActivity extends Activity {
	Long id;
	PersonDB db = new PersonDB(this);
	ScrollView sv;
	private OnClickListener submit_listener2 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			EditText currentLocation = (EditText) findViewById(R.id.currentLocation);
			EditText livingCond = (EditText) findViewById(R.id.livingCondition);
			EditText toilet = (EditText) findViewById(R.id.toiletFacilities);
			EditText water = (EditText) findViewById(R.id.waterSource);
			EditText nutrition = (EditText) findViewById(R.id.nutrition);
			EditText dayAct = (EditText) findViewById(R.id.activitiesDay);
			EditText nightAct = (EditText) findViewById(R.id.activitiesNight);

			String dayActParsed = Formatting.deleteMultiLine(dayAct.getText()
					.toString());
			String nightActParsed = Formatting.deleteMultiLine(nightAct
					.getText().toString());

			db.updateDeatails(id, currentLocation.getText().toString(),
					livingCond.getText().toString(), toilet.getText()
							.toString(), water.getText().toString(), nutrition
							.getText().toString(), dayActParsed, nightActParsed);

			switchTabInActivity(2);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.living_conditions);
		sv = (ScrollView) findViewById(R.id.scrollLiving);

		final Button submitButton2 = (Button) findViewById(R.id.submit2);
		submitButton2.setOnClickListener(submit_listener2);

		Bundle extras = getIntent().getExtras();
		id = extras.getLong("id");
	}

	public void onStart(Bundle savedInstanceState) {
		sv.fullScroll(View.FOCUS_UP);
	}

	public void switchTabInActivity(int indexTabToSwitchTo) {
		DataCollectActivity ParentActivity;
		ParentActivity = (DataCollectActivity) this.getParent();
		ParentActivity.switchTab(indexTabToSwitchTo);
	}

}
