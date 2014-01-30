/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.Statistics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.achartengine.ChartFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.comp1008.MrugalaBartlett.FieldKnow.Formatting;
import com.comp1008.MrugalaBartlett.FieldKnow.MyOnItemSelectedListener;
import com.comp1008.MrugalaBartlett.FieldKnow.R;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Person;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.PersonDB;

//Stats: age at location, people at location, gender at location

public class StatisticsActivity extends Activity {

	public class MyOnItemSelectedListenerStats extends MyOnItemSelectedListener {
		@Override
		public void setText() // shows the appropriate fields to fill in,
								// according to user's choice
		{
			switch (position) {
			case 0: // choice = Age
				fromLL.setVisibility(View.VISIBLE);
				toLL.setVisibility(View.VISIBLE);
				genderLL.setVisibility(View.GONE);
				break;
			case 1: // choice = Location
				fromLL.setVisibility(View.GONE);
				toLL.setVisibility(View.GONE);
				genderLL.setVisibility(View.GONE);
				break;
			case 2: // choice = Gender
				fromLL.setVisibility(View.GONE);
				toLL.setVisibility(View.GONE);
				genderLL.setVisibility(View.VISIBLE);
				break;
			}
		}
	}

	private MyOnItemSelectedListener spinnerListener = new MyOnItemSelectedListenerStats();
	private PersonDB personDB = new PersonDB(StatisticsActivity.this);
	private ArrayList<Person> people;
	private ArrayList<String> locations;
	private ArrayList<PieChartValues> pieChartValues;
	private Iterator<String> locationsIT;
	private Iterator<Person> peopleIT;
	private ArrayList<Person> foundPeople;
	private LinearLayout fromLL;
	private LinearLayout toLL;

	private LinearLayout genderLL;

	private OnClickListener submit_listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			stats();
		}
	};

	public ArrayList<String> getLocations() {
		ArrayList<String> locations = new ArrayList<String>();

		Iterator<Person> it = people.iterator();

		while (it.hasNext()) {
			Person c = it.next();
			if (c.getPob() != null) {
				locations.add(c.getPob());
			}
		}

		// gets rid of repetition, and shows all the possible locations
		HashSet<String> hs = new HashSet<String>();
		hs.addAll(locations);
		locations.clear();
		locations.addAll(hs);

		return locations;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statistics);

		fromLL = (LinearLayout) findViewById(R.id.FromLL);
		toLL = (LinearLayout) findViewById(R.id.ToLL);
		genderLL = (LinearLayout) findViewById(R.id.GenderLL);

		Spinner spinner = (Spinner) findViewById(R.id.statisticsSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.statistics, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(spinnerListener);

		final Button submitButton = (Button) findViewById(R.id.submitStat);
		submitButton.setOnClickListener(submit_listener);

		fromLL.setVisibility(View.GONE);
		toLL.setVisibility(View.GONE);
		genderLL.setVisibility(View.GONE);
	}

	public void searchAge() {
		EditText fromTxt = (EditText) findViewById(R.id.fromStat);
		EditText toTxt = (EditText) findViewById(R.id.toStat);

		Integer fromInt = Formatting.changeToInt(fromTxt.getText().toString());
		Integer toInt = Formatting.changeToInt(toTxt.getText().toString());

		if (fromInt == null || toInt == null) {
			Toast.makeText(StatisticsActivity.this,
					"Please enter the needed deatails", Toast.LENGTH_LONG)
					.show();
			return;
		}

		while (locationsIT.hasNext()) {
			String location = locationsIT.next();
			int ageAtLocation = searchAgeAtLocation(fromInt, toInt, location);
			PieChartValues chart = new PieChartValues(ageAtLocation, location);
			pieChartValues.add(chart);
		}

		Labels labels = DrawChart.pieChart(pieChartValues);
		Intent intent = ChartFactory.getPieChartIntent(this,
				labels.getSeries(), labels.getRenderer(), "Locations");
		startActivity(intent);

		fromTxt.setText("");
		toTxt.setText("");
	}

	public int searchAgeAtLocation(int from, int to, String location) {
		while (peopleIT.hasNext()) {
			Person c = peopleIT.next();
			if (c.getPob() != null && c.getAge() != null && c.getAge() >= from
					&& c.getAge() <= to && c.getPob().equals(location)
					&& c.getAge() != 0) {
				foundPeople.add(c);
			}
		}
		return foundPeople.size();
	}

	public void searchGender() {

		final RadioGroup gender = (RadioGroup) findViewById(R.id.genderStat);
		RadioButton rbutton = (RadioButton) findViewById(gender
				.getCheckedRadioButtonId());

		String genderChoice = null;

		if (rbutton != null) {
			genderChoice = rbutton.getText().toString();
		} else {
			Toast.makeText(StatisticsActivity.this,
					"Please enter the needed deatails", Toast.LENGTH_LONG)
					.show();
			return;
		}

		while (locationsIT.hasNext()) {
			String location = locationsIT.next();
			int genderAtLocation = searchGenderAtLocation(genderChoice,
					location);
			PieChartValues chart = new PieChartValues(genderAtLocation,
					location);
			pieChartValues.add(chart);
		}

		gender.clearCheck();

		Labels labels = DrawChart.pieChart(pieChartValues);
		Intent intent = ChartFactory.getPieChartIntent(this,
				labels.getSeries(), labels.getRenderer(), "Gender");
		startActivity(intent);

	}

	public int searchGenderAtLocation(String gender, String location) {
		while (peopleIT.hasNext()) {
			Person c = peopleIT.next();
			if (c.getPob() != null && c.getPob().equals(location)
					&& c.getGender() != null && c.getGender().equals(gender)) {
				foundPeople.add(c);
			}
		}
		return foundPeople.size();
	}

	public void searchLocation() {

		while (locationsIT.hasNext()) {
			String location = locationsIT.next();
			int pplAtLocation = searchPplAtLocation(location);
			PieChartValues chart = new PieChartValues(pplAtLocation, location);
			pieChartValues.add(chart);
		}

		Labels labels = DrawChart.pieChart(pieChartValues);
		Intent intent = ChartFactory.getPieChartIntent(this,
				labels.getSeries(), labels.getRenderer(), "Locations");
		startActivity(intent);
	}

	public int searchPplAtLocation(String location) {
		while (peopleIT.hasNext()) {
			Person c = peopleIT.next();
			if (c.getPob() != null && c.getPob().equals(location)) {
				foundPeople.add(c);
			}
		}
		return foundPeople.size();
	}

	public void stats() {
		people = personDB.getAllPeople();
		peopleIT = people.iterator();
		locations = getLocations();
		locationsIT = locations.iterator();
		pieChartValues = new ArrayList<PieChartValues>();
		foundPeople = new ArrayList<Person>();

		switch (spinnerListener.getPosition()) {
		case 0:
			searchAge();
			break;
		case 1:
			searchLocation();
			break;
		case 2:
			searchGender();
			break;
		}
	}
}
