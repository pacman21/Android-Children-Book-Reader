package com.example.childrensbookreader;

import java.io.File;
import java.io.IOException;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.childrensbookreader.Classes.Books;
import com.example.childrensbookreader.Classes.Records;
import com.example.childrensbookreader.Classes.SendRequest;
import com.example.childrensbookreader.Classes.UploadFileToServer;
import com.example.childrensbookreader.Classes.Users;

public class RecordAudioInvited extends BaseMenu {
	private static final String AUDIO_RECORDER_FOLDER = "AudioRecorder";
	private MediaRecorder recorder = null;
	public Books book;
	public WebView wv;
	String fileString;
	MediaPlayer mp = new MediaPlayer();
	LinearLayout bookControls;
	LinearLayout preRecord;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record_audio);
		setButtonHandlers();
		Button b = (Button) findViewById(R.id.btnStart);
		b.setText(" ");
		
		bookControls = (LinearLayout)findViewById(R.id.bookControls);
		preRecord = (LinearLayout)findViewById(R.id.preRecordControls);
		wv = (WebView) findViewById(R.id.webView1);
		
		Log.d("User ID: ", Users.getUserID().toString());
	}

	private void setButtonHandlers() {
		((Button) findViewById(R.id.next)).setOnClickListener(changePage);
		((Button) findViewById(R.id.prev)).setOnClickListener(changePage);
	    ((Button) findViewById(R.id.btnStart)).setOnClickListener(btnClick);
		((Button) findViewById(R.id.checkCode)).setOnClickListener(codeCheck);
	}
	
	private View.OnClickListener codeCheck = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
				EditText recordKey = (EditText)findViewById(R.id.txtRecordKey);
				String recKey = recordKey.getText().toString();
				
		        Records r = new Records("0", "0");
		        Books b = r.checkRecordRequest(recKey);
		        
		        Log.d("Book ID", "" + b.getBookID());
		        Log.d("Sent By UserID", "" + b.getUserID());
		        Log.d("This UserID", "" + Users.getUserID());
		        Log.d("Book Erro", "" +b.error);
		        if(b.error == ""){
		        	book = new Books(b.getUserID());
		        	book.setBookID(b.getBookID());
		        	book.setPageCount(b.getPageCount());
		        	book.setTitle(b.getTitle());
		        	
		        	Toast.makeText(getApplicationContext(), "Good Job!", Toast.LENGTH_SHORT).show();
		        	preRecord.setVisibility(View.GONE);
		        	bookControls.setVisibility(View.VISIBLE);
		        	b.showPage(wv);
		        } else {
		        	Toast.makeText(getApplicationContext(), "Wrong code FOOOLL!", Toast.LENGTH_SHORT).show();
		        }
		    }
		};
		
	private View.OnClickListener changePage = new View.OnClickListener() {
	@Override
	public void onClick(View v) {
	        switch (v.getId()) {
	          case R.id.next: {
	             book.nextPage(wv, getApplicationContext());
	             break;
	          }
	          case R.id.prev: {
	             book.previousPage(wv, getApplicationContext());
	             break;
	          }
	       }
	    }
	};
	
	private View.OnClickListener btnClick = new View.OnClickListener() {
	@Override
	public void onClick(View v) {
    	 Button b = (Button) findViewById(R.id.btnStart);
    	 String txt = b.getText().toString();
    	 Resources r = getResources();
    	 
    	 if(txt == " "){
    		 b.setBackgroundResource(R.drawable.stop_button);
	 		 Toast.makeText(RecordAudioInvited.this, "Start Recording", Toast.LENGTH_SHORT).show();
			 startRecording();
			 txt = "  ";
    	 } else if(txt == "  ") {
    		 b.setBackgroundResource(R.drawable.record);
			 Toast.makeText(RecordAudioInvited.this, "Stop Recording", Toast.LENGTH_SHORT).show();
			 stopRecording();
			 txt = " ";
    	 }
    	 b.setText(txt);
      }
	};
	
	private void enableButton(int id, boolean isEnable) {
		((Button) findViewById(id)).setEnabled(isEnable);
	}

	private String getFilename() {
		String filepath = Environment.getExternalStorageDirectory().getPath();
		File file = new File(filepath, AUDIO_RECORDER_FOLDER);
		if (!file.exists()) {
		file.mkdirs();
		}
		Toast.makeText(getApplicationContext(), file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".mp4", Toast.LENGTH_LONG).show();
		fileString = file.getAbsolutePath() + "/" + book.getBookID() + book.getPageNum() + ".3gp";
		return (fileString);
	
	}

	private void startRecording() {
		recorder = new MediaRecorder();
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
		recorder.setOutputFile(getFilename());
		recorder.setOnErrorListener(errorListener);
		recorder.setOnInfoListener(infoListener);
		try {
			recorder.prepare();
			recorder.start();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void stopRecording() {
	  if (null != recorder) {
		recorder.stop();
		recorder.reset();
		recorder.release();
		recorder = null;
		
		// Set your file path here
		if(isOnline() == true){
			UploadFileToServer uf = new UploadFileToServer(book);
			String resp = uf.execute(fileString).toString();
			Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getApplicationContext(), "No Internet Connection Available", 
					Toast.LENGTH_SHORT).show();
		}
	  }
	}

	private MediaRecorder.OnErrorListener errorListener = new MediaRecorder.OnErrorListener() {
	  @Override
	public void onError(MediaRecorder mr, int what, int extra) {
		Toast.makeText(RecordAudioInvited.this, "Error: " + what + ", " + extra, Toast.LENGTH_SHORT).show();
		    }
	};

	private MediaRecorder.OnInfoListener infoListener = new MediaRecorder.OnInfoListener() {
		@Override
		public void onInfo(MediaRecorder mr, int what, int extra) {
			Toast.makeText(RecordAudioInvited.this, "Warning: " + what + ", " + extra, Toast.LENGTH_SHORT).show();
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
	        mp.start();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
