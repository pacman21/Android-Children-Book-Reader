package com.example.childrensbookreader.Classes;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;
import org.json.JSONObject;

public class Users {
	private static Integer userID;
	private static String email;
	
	public Users(){
	}
	
	public boolean createUser(String login, String password){
		SendRequest sr = new SendRequest();
		
		String argument = login + ":-" + password;
		
		try {
			String st = sr.execute("Users", "createUser", argument).get(35, TimeUnit.SECONDS).toString();
			JSONObject obj1 = new JSONObject(st);
			String valid = obj1.getString("valid");
			
			if(valid.contains("true")){
				String uid = obj1.getString("user_id");
				Users.setEmail(login);
				Users.setUserID(Integer.parseInt(uid));
				return true;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return false;
	}
	
	public boolean checkValidUser(String login, String password){
		
		SendRequest sr = new SendRequest();
		String argument = login + ":-" + password;
		
		try {
			String st = sr.execute("Users", "checkUser", argument).get(35, TimeUnit.SECONDS).toString();
			JSONObject obj1 = new JSONObject(st);
			String valid = obj1.getString("valid");
			
			if(valid.contains("true")){
				String uid = obj1.getString("user_id");
				Users.setEmail(login);				
				Users.setUserID(Integer.parseInt(uid));
				return true;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		
		return false;
	}
	
	public static Integer getUserID() {
		return userID;
	}

	public static void setUserID(Integer userID) {
		Users.userID = userID;
	}

	public static String getEmail() {
		return email;
	}

	public static void setEmail(String email) {
		Users.email = email;
	}
	
	
}
