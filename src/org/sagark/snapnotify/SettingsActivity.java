package org.sagark.snapnotify;

import com.google.android.gcm.GCMRegistrar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.Toast;

public class SettingsActivity extends Activity {
	
    protected static String storedRegId; //for use by reregister button
    protected static String sender_id;
    protected static String server_location;
    protected static String device_name;

	
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	registerGCM();
    }
    
    
	protected void registerGCM() {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		sender_id = sharedPref.getString("sender_id", "0000");
		server_location = sharedPref.getString("server_address", "http://");
		device_name = sharedPref.getString("device_name", "Device1");
		
		if (!sender_id.equals("0000") && !server_location.equals("http://")) {
	    	GCMRegistrar.checkDevice(this);
	    	GCMRegistrar.checkManifest(this);
	    	storedRegId = GCMRegistrar.getRegistrationId(this);
	    	GCMRegistrar.register(this, sender_id);
		} else {
			//toast here instructing user to enter prefs
			Context context = getApplicationContext();
			CharSequence text = "Enter your server address / sender id, then press set prefs and reregister.";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
	}

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
    
}