package com.example.childrensbookreader.Classes;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Records {
	public String error;
	public String inviterID;
	public String bookID;
	
	public Records(String extUserID, String extBookID){
		this.error = "";
		this.bookID = extBookID;
		this.inviterID = extUserID;
	}
	
	public boolean sendRecordRequest(String invitedID){
		String argument =  this.inviterID + ":-" + invitedID + ":-" + this.bookID;
		
		SendRequest sr = new SendRequest();
		
		try {
			String st = sr.execute("Record", "sendRecordInvite", argument).get(35, TimeUnit.SECONDS).toString();

			if(st.contains("true")){
				return true;
			} else {
				return false;
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
		}
	}
	
	public Books checkRecordRequest(String requestCode){
		String retString = "false";
		String argument =  requestCode;
		
		SendRequest sr = new SendRequest();
		
		Books tempBook = new Books(0);
		
		try {
			String st = sr.execute("Record", "checkCode", argument).get(35, TimeUnit.SECONDS).toString();
			
			if(st.contains("recording_invite_id")){
				JSONObject obj1 = new JSONObject(st);
				Books tempBook2 = new Books(Integer.parseInt(obj1.getString("invite_sent_by")));
				tempBook2.setBookID(Integer.parseInt(obj1.getString("book_id")));
				tempBook2.setTitle(obj1.getString("title"));
				tempBook2.setPageCount(obj1.getInt("page_count"));
				return tempBook2;
			} else {
				tempBook.error = "Record code not found!";
				return tempBook;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tempBook.error = "Record code not found!";
			return tempBook;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tempBook.error = "Record code not found!";
			return tempBook;
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tempBook.error = "Record code not found!";
			return tempBook;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tempBook.error = "Record code not found!";
			return tempBook;
		}
	}
}
