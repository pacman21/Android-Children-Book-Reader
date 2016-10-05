package com.example.childrensbookreader.Classes;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.content.Context;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

public class Books {
	private Integer bookID;
	private String title;
	private Integer pageCount;
	private Integer pageNum;
	private Double cost;
	private String description;
	private boolean hasAudio;
	private Integer userID;
	public String error;
	
	public Books(Integer extUser){ 
		this.error = "";
		this.userID = extUser;
		this.pageNum = 1;
	}
	
	public void nextPage(WebView wv, Context c){
		Log.d("Next Page Count: ", "" + this.pageCount);
		if((this.pageNum+1) <= this.pageCount){
			this.pageNum+=1;
			this.showPage(wv);
			Toast.makeText(c, "Going to next page.", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(c, "You are already on the last page.", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void previousPage(WebView wv, Context c){
		if(this.pageNum > 1){
			this.pageNum-=1;
			this.showPage(wv);
			Toast.makeText(c, "You are on page 1.", Toast.LENGTH_SHORT).show();
		}
	}
	
	private String getPageURL(){
		Integer bookID = this.bookID;
		Integer pageNum = this.pageNum;
		
		if(bookID != null && pageNum != null){
			return String.format("http://www.livingagame.com/android/rtyk/Books/%s-%s.jpg",
					bookID, pageNum);
		} else {
			return null;
		}
	}
	
	public String getAudioURL(){
		Integer bookID = this.bookID;
		Integer pageNum = this.pageNum;
		Integer userID = this.getUserID();
		
		if(bookID != null && pageNum != null){
			SendRequest sr = new SendRequest();
			String argument = bookID + "-" + pageNum + "-" + userID;
				
			try {
				String st = sr.execute("Books", "checkAudio", argument).get(35, TimeUnit.SECONDS).toString();
				
				JSONObject obj1 = new JSONObject(st);
				String valid = obj1.getString("valid");
				String sql = obj1.getString("sql");
				
				Log.d("SQL:", sql);
				
				if(valid.contains("true")){
					this.setHasAudio(true);
					return String.format("http://www.livingagame.com/android/rtyk/uploads/%s-%s-%s.3gp",
							bookID, pageNum, userID);
				} else {
					this.setHasAudio(false);
					return null;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.setHasAudio(false);
				return null;
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.setHasAudio(false);
				return null;
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.setHasAudio(false);
				return null;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			this.setHasAudio(false);
			return null;
		}
	}
	
	public void showPage(WebView wv){
		wv.getSettings().setBuiltInZoomControls(true);
		wv.getSettings().setLoadWithOverviewMode(true);
		wv.getSettings().setUseWideViewPort(true);
		
		wv.loadData("<div style='margin-left: auto; margin-right: auto'> " +
						"<img src='" + this.getPageURL() + "' /> " +
					"</div>", "text/html", "UTF-8");
		wv.getSettings().setBuiltInZoomControls(true);
	}
	
	public boolean buyBook(){
		String argument =  this.bookID + ":-" + this.userID;
		SendRequest sr = new SendRequest();
		
		try {
			String st = sr.execute("Books", "buyBook", argument).get(35, TimeUnit.SECONDS).toString();

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
	
	public ArrayList<Books> getOwnedBooks(){
		ArrayList<Books> book = new ArrayList<Books>();
		SendRequest sr = new SendRequest();
		
		//pass the object, the function that you want to invoke on object
		//and the arguments to pass
		try {
			String st = sr.execute("Books", "getOwnedBooks", this.userID.toString()).get(35, TimeUnit.SECONDS).toString();
			Log.d("Response: ", st);
			
			if(st.contains("incorrect values")){
				this.error = "There was an error. Please refresh the page!";
				return book;
			}
			
			JSONArray jsonArray = new JSONArray(st);
			
			Log.d("Length: ", "" + jsonArray.length());
			
			for (int i = 0; i < jsonArray.length(); i++) {
				Books tempBook = new Books(this.userID);
				JSONObject obj1 = jsonArray.getJSONObject(i);
				tempBook.setBookID(Integer.parseInt(obj1.getString("book_id")));
				tempBook.setDescription(obj1.getString("description"));
				tempBook.setTitle(obj1.getString("title"));
				tempBook.setPageCount(obj1.getInt("page_count"));
				book.add(tempBook);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return book;
	}
	
	public ArrayList<Books> getAvailableBooks(){
		ArrayList<Books> book = new ArrayList<Books>();
		SendRequest sr = new SendRequest();
		
		//pass the object, the function that you want to invoke on object
		//and the arguments to pass
		try {
			String st = sr.execute("Books", "getAvailableBooks", this.userID.toString()).get(35, TimeUnit.SECONDS).toString();
			Log.d("Response: ", st);
			
			if(st.contains("incorrect values")){
				this.error = "There was an error. Please refresh the page!";
				return book;
			}
			
			JSONArray jsonArray = new JSONArray(st);
			
			Log.d("Length: ", "" + jsonArray.length());
			
			for (int i = 0; i < jsonArray.length(); i++) {
				Books tempBook = new Books(this.userID);
				JSONObject obj1 = jsonArray.getJSONObject(i);
				tempBook.setBookID(Integer.parseInt(obj1.getString("book_id")));
				tempBook.setCost(Double.parseDouble(obj1.getString("current_cost")));
				tempBook.setDescription(obj1.getString("description"));
				tempBook.setTitle(obj1.getString("title"));
				tempBook.setPageCount(obj1.getInt("page_count"));
				book.add(tempBook);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return book;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setBookID(Integer extBookID){
		this.bookID = extBookID;
	}
	
	public Integer getBookID(){
		return this.bookID;
	}
	
	public void setPageNum(Integer extPageNum){
		this.pageNum = extPageNum;
	}
	
	public Integer getPageNum(){
		return this.pageNum;
	}

	public Integer getPageCount() {
		return this.pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public String getDescription() {
		return this.description;
	}
	
	public Integer getUserID(){
		return this.userID;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public boolean isHasAudio() {
		return hasAudio;
	}

	public void setHasAudio(boolean hasAudio) {
		this.hasAudio = hasAudio;
	}
}
