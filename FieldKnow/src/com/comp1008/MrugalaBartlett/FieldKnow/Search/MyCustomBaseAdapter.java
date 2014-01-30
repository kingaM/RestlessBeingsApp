/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 * some of the code is from http://geekswithblogs.net/bosuch/archive/2011/01/31/android---create-a-custom-multi-line-listview-bound-to-an.aspx
 */

package com.comp1008.MrugalaBartlett.FieldKnow.Search;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.comp1008.MrugalaBartlett.FieldKnow.R;
import com.comp1008.MrugalaBartlett.FieldKnow.DataManager.Person;

public class MyCustomBaseAdapter extends BaseAdapter {
	static class ViewHolder {
		TextView txtName;
		TextView txtAge;
		TextView txtNameF;
		TextView txtNameM;
	}

	private static ArrayList<Person> searchArrayList;

	private LayoutInflater mInflater;

	public MyCustomBaseAdapter(Context context, ArrayList<Person> results) {
		searchArrayList = results;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return searchArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return searchArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.custom_row, null);
			holder = new ViewHolder();
			holder.txtName = (TextView) convertView
					.findViewById(R.id.nameSearch);
			holder.txtAge = (TextView) convertView.findViewById(R.id.ageSearch);
			holder.txtNameF = (TextView) convertView
					.findViewById(R.id.nameFSearch);
			holder.txtNameM = (TextView) convertView
					.findViewById(R.id.nameMSearch);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtName.setText("Full name: "
				+ searchArrayList.get(position).getFullName());
		holder.txtAge.setText("Age: " + searchArrayList.get(position).getAge());
		holder.txtNameF.setText("Father name: "
				+ searchArrayList.get(position).getNameF());
		holder.txtNameM.setText("Mother name: "
				+ searchArrayList.get(position).getNameM());

		return convertView;
	}
}