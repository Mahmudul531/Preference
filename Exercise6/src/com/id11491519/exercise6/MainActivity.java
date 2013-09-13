package com.id11491519.exercise6;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	TextView language, screenRotation, homePage, ringTone;
	SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init(); // Initialize the xml object code
		try {
			getSharedData(); // getting current sharedpreference data value

		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(getApplicationContext(), e.toString(),
					Toast.LENGTH_SHORT).show();
		}
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.myPreference:
			startActivity(new Intent(this, MyPreference.class));
			break;
		}
		return true;
	}

	// Initialize the xml object code
	public void init() {
		language = (TextView) findViewById(R.id.languageHome);
		screenRotation = (TextView) findViewById(R.id.unlockRotationHome);
		homePage = (TextView) findViewById(R.id.HomePageHome);
		ringTone = (TextView) findViewById(R.id.RingtoneHome);
		sp = PreferenceManager.getDefaultSharedPreferences(this);
	}

	/* Update preference when it finished */

	public void getSharedData() {
		String langugeString = sp.getString("preference_language", "Not set");
		boolean screenRotationString = sp.getBoolean(
				"preference_voice_activated", false);
		String homePageString = sp.getString("prefUsername", "Not set");
		String ringToneString = sp.getString("ringtone", "Not set");

		language.setText(langugeString);
		if (screenRotationString)
			screenRotation.setText("Locked");
		else
			screenRotation.setText("Unlocked");
		homePage.setText(homePageString);
		ringTone.setText(ringToneString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */

	/* Famous button in main screen called here */
	public void AddFamous(View v) {
		Intent i = new Intent(getApplicationContext(),
				FamousPersonActivity.class);
		startActivity(i);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		/* Update preference when it finished */
		getSharedData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
