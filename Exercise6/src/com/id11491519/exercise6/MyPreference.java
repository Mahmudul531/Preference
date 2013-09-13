package com.id11491519.exercise6;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.widget.Toast;

public class MyPreference extends PreferenceActivity implements
		OnSharedPreferenceChangeListener {
	CheckBoxPreference check;
	ListPreference listLanguage;
	EditTextPreference url;
	RingtonePreference ring;
	String LanguageSummary;
	SharedPreferences sp;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Initialized the preference
		addPreferencesFromResource(R.xml.preferences);

		// Initialize preference xml object
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		sp.registerOnSharedPreferenceChangeListener(this);
		check = (CheckBoxPreference) getPreferenceScreen().findPreference(
				"preference_voice_activated");

		/* checked previous settings and update current preference */

		if (check.isChecked())
			check.setSummary("Locked");
		else {
			check.setSummary("Unlock");
		}
		listLanguage = (ListPreference) getPreferenceScreen().findPreference(
				"preference_language");
		LanguageSummary = sp.getString("preference_language",
				"Select a language");
		listLanguage.setSummary(LanguageSummary);

		url = (EditTextPreference) getPreferenceScreen().findPreference(
				"prefUsername");

		url.setSummary("Set the Home Page URL");
		ring = (RingtonePreference) getPreferenceScreen().findPreference(
				"ringtone");
		/*-----------------------------------------------------------*/

	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {

		// Let's do something when a preference value changes

		if (key.equals("preference_voice_activated")) {
			try {

				if (check.isChecked()) {
					// Toast.makeText(getApplicationContext(),key,
					// Toast.LENGTH_SHORT).show();
					check.setSummary("Locked");
				} else {
					check.setSummary("Unlocked");
				}
			} catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(getApplicationContext(), e.toString(),
						Toast.LENGTH_SHORT).show();
			}
		} else if (key.equals("preference_language")) {
			listLanguage.setSummary(listLanguage.getEntry());
		} else if (key.equals("prefUsername")) {
			url.setSummary(url.getText());
		} else if (key.equals("ringtone")) {
			ring.setSummary("Ringtone selected");
		}

	}

}
