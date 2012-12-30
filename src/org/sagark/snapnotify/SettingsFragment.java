package org.sagark.snapnotify;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.preference.PreferenceFragment;

@SuppressLint("NewApi")
public class SettingsFragment extends PreferenceFragment {
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		// Load the preferences from and XML resource
		addPreferencesFromResource(R.xml.preferences);
	}
}