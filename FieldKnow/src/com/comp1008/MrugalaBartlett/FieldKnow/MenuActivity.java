package com.comp1008.MrugalaBartlett.FieldKnow;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.comp1008.MrugalaBartlett.FieldKnow.DataCollect.DataCollectActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.NetworkActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.Finance.FinanceActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.Search.SearchActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.Statistics.StatisticsActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.UserRelated.SettingsActivity;
import com.comp1008.MrugalaBartlett.FieldKnow.Web.WebViewActivity;

/**
 * @author Gemma Bartlett and Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 * 
 */
public class MenuActivity extends ListActivity {

	private Intent myIntent;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] MENUITEMS = getResources().getStringArray(R.array.menu_array);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, MENUITEMS);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		switch (position) {
		case 0:
			myIntent = new Intent(MenuActivity.this, WebViewActivity.class);	
			MenuActivity.this.startActivity(myIntent);
			break;
		case 1:
			myIntent = new Intent(MenuActivity.this, DataCollectActivity.class);
			MenuActivity.this.startActivity(myIntent);
			break;
		case 2:
			myIntent = new Intent(MenuActivity.this, SearchActivity.class);
			MenuActivity.this.startActivity(myIntent);
			break;
		case 3:
			myIntent = new Intent(MenuActivity.this, FinanceActivity.class);
			
			MenuActivity.this.startActivity(myIntent);
			break;
		case 4:
			myIntent = new Intent(MenuActivity.this, SettingsActivity.class);
			MenuActivity.this.startActivity(myIntent);
			break;
		case 5:
			myIntent = new Intent(MenuActivity.this, StatisticsActivity.class);
			MenuActivity.this.startActivity(myIntent);
			break;
		case 6:
			AlertDialog dialog;

			final CharSequence[] items = { "Send data by Email", "Send data using JSON" };
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Send data");
			builder.setItems(items, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int pos) {
					switch (pos) {
					case 0:
					{
						 myIntent = new Intent(MenuActivity.this, NetworkActivity.class);
						 myIntent.putExtra("method", 0);
						 MenuActivity.this.startActivity(myIntent);
					}
					break;
					case 1:
					{
						myIntent = new Intent(MenuActivity.this, NetworkActivity.class);
						myIntent.putExtra("method", 1);
						MenuActivity.this.startActivity(myIntent);
					}break;
					}
				}});
			dialog = builder.create();
			dialog.show();
			
			break;
		default:
			myIntent = new Intent(MenuActivity.this, MenuActivity.class);
			MenuActivity.this.startActivity(myIntent);
			break;
		}
	}

}