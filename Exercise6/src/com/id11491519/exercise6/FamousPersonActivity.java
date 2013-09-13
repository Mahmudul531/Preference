package com.id11491519.exercise6;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FamousPersonActivity extends Activity {
	EditText firstName, lastName, hobby;
	DatePicker datepikerBirthday;
	Spinner spinnerGender;
	MyDatabaseHelper db;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.famous_person_xml);

		/* Initialize the variable */
		init();

	}

	public void init() {
		firstName = (EditText) findViewById(R.id.editTextFirstName);
		lastName = (EditText) findViewById(R.id.EditTextLastName);
		hobby = (EditText) findViewById(R.id.EditTextLastName);
		datepikerBirthday = (DatePicker) findViewById(R.id.datePickerBirthday);
		spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
		db = new MyDatabaseHelper(FamousPersonActivity.this);
	}

	/* Add person button click listener is here */

	public void Add(View v) {
		if (firstName.getText().length() != 0
				&& lastName.getText().length() != 0) {
			String DayString = null, MonthString = null, Date = "";
			int day = datepikerBirthday.getDayOfMonth();
			int month = datepikerBirthday.getMonth() + 1;
			int year = datepikerBirthday.getYear();
			DayString = Integer.toString(day);
			MonthString = Integer.toString(month);

			/*
			 * Calculation for Day if day below 10 then add extra 0 first for
			 * future use
			 */
			if (day < 10) {
				DayString = "0" + Integer.toString(day);
			}
			if (month < 10) {
				MonthString = "0" + Integer.toString(month);
			}

			Date = DayString + MonthString + Integer.toString(year);

			try {
				/* Created database for saving person information */

				db.open();

				// please see the original method for more clear about this
				// method
				db.CreatePersonalInfo(firstName.getText().toString(), lastName
						.getText().toString(), Integer.toString(+spinnerGender
						.getSelectedItemPosition()), Date, hobby.getText()
						.toString().length() > 0 ? hobby.getText().toString()
						: " ");
				db.close();

				/* First name Last name field going refresh */
				firstName.setText("");
				lastName.setText("");
				//Showing successful toast
				Toast.makeText(FamousPersonActivity.this,
						"Person added successfully", Toast.LENGTH_SHORT).show();
				
				
			} catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(FamousPersonActivity.this, e.toString(),
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(getApplicationContext(), "Check", Toast.LENGTH_SHORT)
					.show();
		}
	}

	/* Call list xml button click listener */

	public void List(View v) {
		try {
			db.open();
			Cursor c = db.getAllData();
			int count = c.getCount();
			db.close();
			if (count > 0) {
				// If database have list then it will call this intent
				Intent i = new Intent(getApplicationContext(),
						MyListActivity.class);
				startActivity(i);

			} else {
				Toast.makeText(getApplicationContext(), "List Empty",
						Toast.LENGTH_SHORT).show();
			}

		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(getApplicationContext(), e.toString(),
					Toast.LENGTH_SHORT).show();
		}
	}

}
