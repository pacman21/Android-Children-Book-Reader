package com.example.childrensbookreader;

import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.childrensbookreader.Classes.Books;
import com.example.childrensbookreader.Classes.Records;
import com.example.childrensbookreader.Classes.SendRequest;
import com.example.childrensbookreader.Classes.Users;

public class InviteRecord extends BaseMenu {
	public Books book = new Books(Users.getUserID());
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invite_record);
		setButtonHandlers();
		Log.d("User ID: ", Users.getUserID().toString());
		
		Bundle extras = getIntent().getExtras(); 
		Integer bookID = extras.getInt("bookID");
		String bookTitle = extras.getString("bookTitle");
		Integer pageCount = extras.getInt("pageCount");
		
		book.setBookID(bookID);
		book.setPageCount(pageCount);
		book.setTitle(bookTitle);
	}
	
	private void setButtonHandlers() {
		((Button) findViewById(R.id.btnInvite)).setOnClickListener(invite);
	}
	
	private View.OnClickListener invite = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Records r = new Records(Users.getUserID().toString(), book.getBookID().toString());
			
			EditText email = (EditText) findViewById(R.id.txtEmail);
			if(isValidEmailAddress(email.getText().toString())){
				r.sendRecordRequest("dapacc@yahoo.com");
			}
		}
	};
	
	public boolean isValidEmailAddress(String email) {
		 String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
		 
		 Pattern p = Pattern.compile(regEx);
		 Matcher m = p.matcher(email);
		 
		 if(m.find()){
			 return true;
		 } else {
			 return false;
		 }
	}
}
