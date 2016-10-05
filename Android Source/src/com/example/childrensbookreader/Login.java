package com.example.childrensbookreader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.childrensbookreader.Classes.Users;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		Button btnLogin = (Button) findViewById(R.id.btnLogin);
		Button btnRegister = (Button) findViewById(R.id.btnRegister);
		final EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
		final EditText txtPassword = (EditText) findViewById(R.id.txtPassword);
		final Users u = new Users();
		
		btnLogin.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Boolean b = u.checkValidUser(txtEmail.getText().toString(), 
								txtPassword.getText().toString());
				
				if(b){
					Toast.makeText(getApplicationContext(), "Logged in", Toast.LENGTH_SHORT).show();
					Intent i = new Intent(getApplicationContext(), Library.class);
					startActivity(i);
				} else {
					Toast.makeText(getApplicationContext(), "There was an error logging in.", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		btnRegister.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String email = txtEmail.getText().toString();
				String password = txtPassword.getText().toString();
				
				if(!validate(email, "email")){
					Toast.makeText(getApplicationContext(), "You have an invalid email address", Toast.LENGTH_SHORT).show();
					return;
				}
				
				if(!validate(password, "")){
					Toast.makeText(getApplicationContext(), "You have an invalid password", Toast.LENGTH_SHORT).show();
					return;
				}
				Boolean b = u.createUser(email, password);
				
				if(b){
					Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
					Intent i = new Intent(getApplicationContext(), Library.class);
					startActivity(i);
				} else {
					Toast.makeText(getApplicationContext(), "There was an error registering.", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	public boolean validate(String val, String type){
		if(type == "email"){
			//Check valid email
		}
		
		//Check length
		if(val.length() < 6){
			return false;
		}
		
		Pattern pattern = Pattern.compile("[*%{}<>^]");
	    Matcher matcher = pattern.matcher(val);
	    
	    if(!matcher.find()){
	    	return false;
	    }
	    
		return true;
	}
}
