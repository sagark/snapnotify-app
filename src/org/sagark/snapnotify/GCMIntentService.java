package org.sagark.snapnotify;

import com.google.android.gcm.GCMBaseIntentService;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class GCMIntentService extends GCMBaseIntentService{
	public GCMIntentService(){
		super("Test");
	}
	public void onRegistered(Context context, String regId){
		//stub
		Log.e("snap", regId);
	}
	public void onUnregistered(Context context, String regId){
		//stub
	}
	public void onMessage(Context context, Intent intent){
		//stub
	}
	public void onError(Context context, String errorId){
		//stub
	}
	public boolean onRecoverableError(Context context, String errorId){
		//stub
		return true;
	}
}
