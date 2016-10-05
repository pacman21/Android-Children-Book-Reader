package com.example.childrensbookreader;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import com.example.childrensbookreader.Classes.Books;
import com.example.childrensbookreader.Classes.SendRequest;
import com.example.childrensbookreader.Classes.Users;

public class MainActivity extends BaseMenu {
	public Books book = new Books(Users.getUserID());
	public WebView wv;
	String fileString;
	MediaPlayer mp = new MediaPlayer();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setButtonHandlers();
		
		Button b = (Button) findViewById(R.id.btnPlay);
		b.setText("|>");
		
		Log.d("User ID: ", Users.getUserID().toString());
		
		Bundle extras = getIntent().getExtras(); 
		Integer bookID = extras.getInt("bookID");
		String bookTitle = extras.getString("bookTitle");
		Integer pageCount = extras.getInt("pageCount");
		
		wv = (WebView) findViewById(R.id.webView1);
		
		book.setBookID(bookID);
		book.setPageNum(1);
		book.setTitle(bookTitle);
		book.setPageCount(pageCount);
		book.showPage(wv);
		String audioUrl = book.getAudioURL();
		
		if(book.isHasAudio()){
			Log.d("Audio:", "There is audio");
			audioPlayer(audioUrl);
		} else {
			Log.d("Audio:", "There is no audio");
		}
	}

	private void setButtonHandlers() {
		((Button) findViewById(R.id.next)).setOnClickListener(changePage);
		((Button) findViewById(R.id.prev)).setOnClickListener(changePage);
		((Button) findViewById(R.id.btnPlay)).setOnClickListener(handleMusic);
		((Button) findViewById(R.id.btnInvite)).setOnClickListener(inviteRecorder);
	}
	
	private View.OnClickListener inviteRecorder = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(getApplicationContext(), InviteRecord.class);
			i.putExtra("bookTitle", book.getTitle());
			i.putExtra("bookID", book.getBookID());
			i.putExtra("pageCount", book.getPageCount());
			i.putExtra("UserID", Users.getUserID().toString());
			startActivity(i);
		}
	};
	
	private View.OnClickListener handleMusic = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.btnPlay: {
					Button b = (Button) findViewById(R.id.btnPlay);
					String bString = b.getText().toString();
					
					if(bString == "|>"){
						b.setText("[]");
						b.setBackgroundResource(R.drawable.stop_button);
						mp.start();
					} else {
						b.setText("|>");
						b.setBackgroundResource(R.drawable.play_button);
						mp.stop();
						try {
							mp.prepare();
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	};

	private View.OnClickListener changePage = new View.OnClickListener() {
	@Override
	public void onClick(View v) {
	        switch (v.getId()) {
	          case R.id.next: {
	        	 Button b = (Button) findViewById(R.id.btnPlay);
	      		 b.setText("|>");
	        	 //goes to the next page
	             book.nextPage(wv, getApplicationContext());
	             //stops the audio
	             mp.stop();
	             String audioUrl = book.getAudioURL();
				 if(book.isHasAudio()){
					 Log.d("Audio:", "There is audio");
					 audioPlayer(audioUrl);
				 } else {
					 Log.d("Audio:", "There is no audio");
				 }
	             break;
	          }
	          case R.id.prev: {
	        	 Button b = (Button) findViewById(R.id.btnPlay);
	        	 b.setText("|>");
	        	 //goes to a previous page
	             book.previousPage(wv, getApplicationContext());
	             //stops the audio
	             mp.stop();
	             
	             String audioUrl = book.getAudioURL();
				 if(book.isHasAudio()){
					 Log.d("Audio:", "There is audio");
					 audioPlayer(audioUrl);
				 } else {
					 Log.d("Audio:", "There is no audio");
				 }
	             break;
	          }
	       }
	    }
	};

	public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
	
	@Override
	public void onBackPressed() {
		mp.stop();
		finish();
	}
	
	 public void audioPlayer(String fileName){
	    //set up MediaPlayer    
	    try {
	        mp.setDataSource(fileName);
	        mp.prepare();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
