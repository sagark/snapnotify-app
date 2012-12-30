package org.sagark.snapnotify;

import android.os.Bundle;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class MainActivity extends Activity {

    GoogleAnalyticsTracker tracker;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // analytics tracking code
        tracker = GoogleAnalyticsTracker.getInstance();
        tracker.startNewSession("UA-34204437-1", this);
        tracker.trackPageView("/app_home");
        tracker.dispatch();
        
        setContentView(R.layout.activity_main);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		tracker.stopSession();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i;
		ComponentName n;
		
	    switch (item.getItemId()) {
	        case R.id.about_snapnotify:
	            i = new Intent();
	            n = new ComponentName("org.sagark.snapnotify","org.sagark.snapnotify.AboutActivity");
	            break;
	        case R.xml.preferences:
	        	i = new Intent();
	        	n = new ComponentName("org.sagark.snapnotify", "org.sagark.snapnotify.SettingsActivity");
	        	break;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	    
	    i.setComponent(n);
	    startActivity(i);
	    return true;
	}
	
}
