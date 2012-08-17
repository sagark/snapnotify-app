package org.sagark.snapnotify;

import android.os.Bundle;
import com.google.android.gcm.GCMRegistrar;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	//need to make this user-customizable 
    protected static String SENDER_ID = "586572666469";
    protected static String SnapNotifyServer = "http://192.168.1.7:1337/register";
    protected static String storedRegId = ""; //for use by reregister button

	@Override
    public void onCreate(Bundle savedInstanceState) {
    	GCMRegistrar.checkDevice(this);
    	GCMRegistrar.checkManifest(this);
    	final String regId = GCMRegistrar.getRegistrationId(this);
    	if (regId.equals("")) {
    	  GCMRegistrar.register(this, SENDER_ID);
    	} else {
    	  Log.v("snap", "Already registered");
    	}
    	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
