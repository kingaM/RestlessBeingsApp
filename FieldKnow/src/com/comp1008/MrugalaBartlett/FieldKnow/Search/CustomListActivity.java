/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 */

package com.comp1008.MrugalaBartlett.FieldKnow.Search;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.comp1008.MrugalaBartlett.FieldKnow.R;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Person;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Database.PersonDB;

public class CustomListActivity extends Activity {
	PersonDB personDB = new PersonDB(CustomListActivity.this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_list);
		Bundle extras = getIntent().getExtras();

		int choice = extras.getInt("Choice");
		String text = extras.getString("Search");

		ArrayList<Person> foundPeople = new ArrayList<Person>();
		foundPeople = search(choice, text);

		final ListView lv1 = (ListView) findViewById(R.id.ListView01);
		lv1.setAdapter(new MyCustomBaseAdapter(this, foundPeople));

		lv1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				Person person = (Person) lv1.getItemAtPosition(position);
				Intent intent = new Intent().setClass(CustomListActivity.this,
						EditActivity.class);
				intent.putExtra("id", person.getId());
				startActivity(intent);
			}
		});
	}

	public ArrayList<Person> search(int choice, String text) {
		ArrayList<Person> foundPeople = new ArrayList<Person>();
		ArrayList<Person> people = personDB.getAllPeople();
		Iterator<Person> it = people.iterator();

		switch (choice) {
		case 0:
			while (it.hasNext()) { // Choice = name
				Person c = it.next();
				if (c.getFullName() != null && c.getFullName().equals(text)) {
					foundPeople.add(c);
				}
			}
			break;
		case 1:
			while (it.hasNext()) { // Choice = location
				Person c = it.next();
				if (c.getLocation() != null && c.getLocation().equals(text)) {
					foundPeople.add(c);
				}
			}
			break;
		case 2:
			while (it.hasNext()) { // Choice = age
				Person c = it.next();
				if (c.getAge() != null && c.getAge().toString().equals(text)) {
					foundPeople.add(c);
				}
			}
			break;
		}
		return foundPeople;
	}
}