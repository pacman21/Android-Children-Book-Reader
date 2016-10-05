package com.example.childrensbookreader.Classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

public class SendRequest extends AsyncTask<String, Void, String> {
	public String doInBackground(String... url){
    // Creating HTTP client
		String passedUrl = "http://livingagame.com/android/rtyk/call_api.php";
		String object = url[0];
		String function = url[1];
		String arguments = url[2];
		Encryption enc = new Encryption();
		
		try {
			object = enc.bytesToHex(enc.encrypt(object));
			function = enc.bytesToHex(enc.encrypt(function));
			arguments = enc.bytesToHex(enc.encrypt(arguments));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Log.d("Object: ", object);
		Log.d("Function: ", function);
		
		String time = this.getTime();
		String secret = "Fq216w2to5PdS5GFl5iL473Tsc08Qa";
		String hash = md5(secret + time + function);
		
		String newUrl = passedUrl + "?object=" + object + "&function=" + function +
				"&dateTime=" + time + "&hashCode=" + hash + "&arguments=" + arguments;
	    HttpClient httpClient = new DefaultHttpClient();
	    // Creating HTTP Post
	    HttpPost httpPost = new HttpPost(newUrl);
	    String retString = "ERROR";

	    // Making HTTP Request
	    try {
	        HttpResponse response = httpClient.execute(httpPost);
	        HttpEntity entity = response.getEntity();
	        InputStream is = entity.getContent();
	        
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	        }
	        is.close();
	        retString = sb.toString();
	        Log.d("Return:", retString);
	    } catch (ClientProtocolException e) {
	        // writing exception to log
	    	retString = e.toString();
	    } catch (IOException e) {
	        // writing exception to log
	    	retString = e.toString();
	    }
	    
	    return retString;
	}
	
	@Override
	protected void onPostExecute(String result) {
	    super.onPostExecute(result);
	}
	
	public String getTime(){
	      Calendar lCDateTime = Calendar.getInstance();
	      lCDateTime.setTimeZone(TimeZone.getTimeZone("America/New_York"));
	      Long timeInMilli = (long) Math.round(lCDateTime.getTimeInMillis() / 1000);
	      Log.d("Time: ", timeInMilli.toString());
	    
	    return timeInMilli.toString();
	}
	
	public static final String md5(final String s) {
	    try {
	        // Create MD5 Hash
	        MessageDigest digest = java.security.MessageDigest
	                .getInstance("MD5");
	        digest.update(s.getBytes());
	        byte messageDigest[] = digest.digest();

	        // Create Hex String
	        StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < messageDigest.length; i++) {
	            String h = Integer.toHexString(0xFF & messageDigest[i]);
	            while (h.length() < 2)
	                h = "0" + h;
	            hexString.append(h);
	        }
	        return hexString.toString();

	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return "";
	}

}
