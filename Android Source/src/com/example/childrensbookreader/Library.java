package com.example.childrensbookreader;

import java.util.ArrayList;
import com.example.childrensbookreader.Classes.Books;
import com.example.childrensbookreader.Classes.Encryption;
import com.example.childrensbookreader.Classes.SendRequest;
import com.example.childrensbookreader.Classes.Users;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Library extends BaseMenu {
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_books);
		
		Encryption e = new Encryption();
		
		SendRequest sr = new SendRequest();
		
		Books book = new Books(Users.getUserID());
		
		ArrayList<Books> books = book.getOwnedBooks();
		
		int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics());
		int width  = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics());
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height);
		lp.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
		Integer number = 0;
		
		LinearLayout lColumn = (LinearLayout) findViewById(R.id.leftColumn);
		LinearLayout rColumn = (LinearLayout) findViewById(R.id.rightColumn);
		
		if(book.error != ""){
			Toast.makeText(getApplicationContext(), book.error, Toast.LENGTH_SHORT).show();
			return;
		}
		
		for(Books b : books){
			//buttons
			Button b1 = new Button(this);
			b1.setBackgroundDrawable(getResources().getDrawable(R.drawable.book_icon));
			b1.setText(b.getTitle());
			b1.setTextColor(Color.parseColor("#FFFFFF"));
			b1.setLayoutParams(lp);
			
			b1.setId(b.getBookID());
			b1.setTag(b);
			
			b1.setOnClickListener(butttonClicks);
			
			if ( number == 0){
				lColumn.addView(b1);
				number=1;
			} else {
				rColumn.addView(b1);
				number = 0;
			}
		}
	}
	
	public View.OnClickListener butttonClicks = new View.OnClickListener(){
		@Override
		public void onClick(View v) {
			Books b = (Books) v.getTag();
			
			Intent i = new Intent(getApplicationContext(), MainActivity.class);
			i.putExtra("bookTitle", b.getTitle());
			i.putExtra("bookID", b.getBookID());
			i.putExtra("pageCount", b.getPageCount());
			startActivity(i);  
		}
	};
}
