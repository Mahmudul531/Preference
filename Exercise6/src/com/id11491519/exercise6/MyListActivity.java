package com.id11491519.exercise6;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MyListActivity extends Activity {
	MyDatabaseHelper db;
	private String KEY_ID = "id";
	private String KEY_FIRST_NAME = "f_name";
	private String KEY_LAST_NAME = "l_name";
	private String KEY_GENDER = "gender";
	private String KEY_BIRTHDAY = "bday";
	private String KEY_HOBBY = "hobby";
	ArrayList<HashMap<String, String>> dataHash;
	SimpleAdapter nAdapter;
	ListView lv;
	String MonthData[] = new String[] { "January", "February", "March",
			"April", "May", "June", "July", "August", "September", "October",
			"November", "December" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.famou_list_activity);

		init();// initialize

		db.open(); // database initialize and fetch data from database

		/* Index of database column */
		Cursor c = db.getAllData();
		int ids = c.getColumnIndex(KEY_ID);
		int iFirstName = c.getColumnIndex(KEY_FIRST_NAME);
		int iLastName = c.getColumnIndex(KEY_LAST_NAME);
		int iGender = c.getColumnIndex(KEY_GENDER);
		int iDate = c.getColumnIndex(KEY_BIRTHDAY);
		try {
			// Fetch database using cursor
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("FirstLast",
						c.getString(iFirstName) + " " + c.getString(iLastName));
				map.put("Gender", c.getInt(iGender) == 0 ? "Male" : "Female");
				map.put("Birdhday",
						"Birthdate : " + dateCalculation(c.getLong(iDate)));
				dataHash.add(map);

			}
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(getApplicationContext(), e.toString(),
					Toast.LENGTH_LONG).show();
		}

		/* Use simple adapter for listview */
		
		String[] fromfavourite = { "FirstLast", "Gender", "Birdhday" };
		int[] tofavourite = { R.id.textViewHighCenter, R.id.textViewdialogTime,
				R.id.textViewdialogDate };
		nAdapter = new SimpleAdapter(getApplicationContext(), dataHash,
				R.layout.famous_list_inflater, fromfavourite, tofavourite);
		lv.setAdapter(nAdapter);
		
		/*-----------------------------------------------------*/

	}

	// Calculation date from long value
	public String dateCalculation(long date) {

		String newDate = Long.toString(date);
		String Day = "", Month = "", Year = "";
		for (int i = 0; i < newDate.length(); i++) {
			if (i == 0 || i == 1) {
				Day += (Character.toString(newDate.charAt(i)));
			} else if (i == 2 || i == 3) {
				Month += (Character.toString(newDate.charAt(i)));
				if (i == 3) {
					Month = MonthData[Integer.parseInt(Month) - 1];
				}
			} else {
				Year += (Character.toString(newDate.charAt(i)));
			}

		}
		return Day + " " + Month + " " + Year;
	}

	// Initialize xml object
	public void init() {
		db = new MyDatabaseHelper(MyListActivity.this);
		dataHash = new ArrayList<HashMap<String, String>>();
		lv = (ListView) findViewById(R.id.listView1);
	}

}
