package org.sagark.snapnotify;

import android.os.Bundle;
import com.google.android.gcm.GCMRegistrar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	//need to make this user-customizable 
    protected static String SENDER_ID = "586572666469";
    protected static String SnapNotifyServer = "http://192.168.1.7:1337/register";
    protected static String storedRegId; //for use by reregister button
    public static final String PREFS_NAME = "SnapPrefsFile";

    Button mButton;
    Button mButton2;
    EditText mEdit;
    EditText mEdit2;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        //load preferences from file
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SENDER_ID = settings.getString("SENDER_ID", "0000");
		SnapNotifyServer = settings.getString("SnapNotifyServer", "http://localhost");
		
    	//register to GCM Server
		registerGCM();

        //set button listener to set prefs
        mButton = (Button)findViewById(R.id.button1);
        mButton2 = (Button)findViewById(R.id.button2);
        mEdit = (EditText)findViewById(R.id.editText1);
        mEdit2 = (EditText)findViewById(R.id.editText2);
        mEdit.setText(SnapNotifyServer); //set stored value in prefs
        mEdit2.setText(SENDER_ID); //set stored value in prefs
        
        mButton.setOnClickListener(
        		new View.OnClickListener() {
        			public void onClick(View view){
        				MainActivity.SnapNotifyServer = mEdit.getText().toString();
        				MainActivity.SENDER_ID = mEdit2.getText().toString();
        			}
        		});
        		
        mButton2.setOnClickListener(
        		new View.OnClickListener() {
        			public void onClick(View view){
        				registerGCM();
        			}
        		});
    }
	
	@Override
	protected void onStop(){
		super.onStop();
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("SnapNotifyServer", SnapNotifyServer);
		editor.putString("SENDER_ID", SENDER_ID);
		editor.commit();
		
	}
	
	protected void registerGCM(){
    	GCMRegistrar.checkDevice(this);
    	GCMRegistrar.checkManifest(this);
    	storedRegId = GCMRegistrar.getRegistrationId(this);
    	if (storedRegId.equals("")) {
    	  GCMRegistrar.register(this, SENDER_ID);
    	} else {
    	  Log.v("snap", "Already registered");
    	}
	}
}
