package com.example.childrensbookreader.Classes;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;


import android.os.AsyncTask;
import android.util.Log;

public class UploadFileToServer  extends AsyncTask<String, Void, String> {
	Books b;
	
	public UploadFileToServer(Books book) {
		this.b = book;
	}

	@Override
	protected String doInBackground(String... fileToSend) {
		String foto = fileToSend[0];

		File file = new File(foto);
		
        DefaultHttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost("http://www.livingagame.com/android/rtyk/upload_audio.php");

        MultipartEntity mpEntity = new MultipartEntity(
                HttpMultipartMode.BROWSER_COMPATIBLE);
        mpEntity.addPart("form_file", new FileBody(file, "image/3gp"));
        try {
			mpEntity.addPart("book_id", new StringBody("" + b.getBookID()));
			mpEntity.addPart("page_num", new StringBody("" + b.getPageNum()));
			mpEntity.addPart("user_id", new StringBody("" + b.getUserID()));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        httppost.setEntity(mpEntity);

        HttpResponse response;
        try {

            response = httpclient.execute(httppost);
            
            HttpEntity resEntity = response.getEntity();
            
            if (resEntity != null) {

            }
            if (resEntity != null) {
                resEntity.consumeContent();
            }
            
            Log.d("Response: ", response.toString());
            return response.toString();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

	    return null;
	}

}
