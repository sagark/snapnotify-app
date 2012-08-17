package org.sagark.snapnotify;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.google.android.gcm.GCMBaseIntentService;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class GCMIntentService extends GCMBaseIntentService{
	public GCMIntentService(){
		super("lol");
	}
	public void onRegistered(Context context, String regId){
		//stub
		Log.e("snap", regId);
		String urlParam = regId;
		//adapted from http://www.wikihow.com/Execute-HTTP-POST-Requests-in-Android
		String requesturl = "http://192.168.1.7:1337/register";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(requesturl);
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair(urlParam, ""));
		//pairs.add(new BasicNameValuePair("key2", "value2"));
		try {
			post.setEntity(new UrlEncodedFormEntity(pairs));
			HttpResponse response = client.execute(post);
			Log.e("snap", "complete");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void onUnregistered(Context context, String regId){
		//stub
	}
	
	@SuppressWarnings("deprecation")
	public void onMessage(Context context, Intent intent){
	    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	    Notification notification = new Notification(R.drawable.ic_launcher, "A new notification", System.currentTimeMillis());
	    // Hide the notification after its selected
	    notification.flags |= Notification.FLAG_AUTO_CANCEL;

	    //Intent intent2 = new Intent(this, NotificationReceiver.class);
	    PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 0);
	    
	    notification.setLatestEventInfo(this, "This is the title", "This is the text", activity);
	    notification.number += 1;
	    notificationManager.notify(0, notification);
	}
	public void onError(Context context, String errorId){
		//stub
	}
	public boolean onRecoverableError(Context context, String errorId){
		//stub
		return true;
	}
}
