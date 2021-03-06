package org.sagark.snapnotify;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import com.google.android.gcm.GCMBaseIntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class GCMIntentService extends GCMBaseIntentService {
	private static int notificationcount = 0;
	
	public GCMIntentService() {
		super("snapnotify");
	}
	
	public void onRegistered(Context context, String regId) {
		Log.e("snap", regId);
		SettingsActivity.storedRegId = regId;
		String requesturl = SettingsActivity.server_location;
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(requesturl);
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("regId", regId));
		pairs.add(new BasicNameValuePair("devicename", SettingsActivity.device_name));
		
		try {
			post.setEntity(new UrlEncodedFormEntity(pairs));
			client.execute(post);
			Log.e("snap", "complete");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onUnregistered(Context context, String regId) {
		//stub
	}
	
	@SuppressWarnings("deprecation")
	public void onMessage(Context context, Intent intent) {
		String ns = Context.NOTIFICATION_SERVICE;
	    NotificationManager notificationManager = (NotificationManager) getSystemService(ns);
	    Notification notification = new Notification(R.drawable.ic_launcher, "Received SnapNotify Message", System.currentTimeMillis());
	    
	    // Hide the notification after it's selected
	    notification.flags |= Notification.FLAG_AUTO_CANCEL;
	    notification.defaults |= Notification.DEFAULT_SOUND;
	    notification.defaults |= Notification.DEFAULT_VIBRATE;

	    PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 0);
	    
	    String title = intent.getExtras().getString("title");
	    String content = intent.getExtras().getString("content");
	    
	    notification.setLatestEventInfo(this, title, content, activity);
	    notification.number += 1;
	    notificationcount += 1;
	    notificationManager.notify(notificationcount, notification);
	}
	
	public void onError(Context context, String errorId) {
		//stub
	}
	
	public boolean onRecoverableError(Context context, String errorId) {
		//stub
		return true;
	}
	
}
