package com.example.childrensbookreader;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

abstract class BaseMenu extends Activity
{
	/// Creates the menu items
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.library:
            	startActivity(new Intent(this, Library.class));
                return true;
            case R.id.buy:
            	startActivity(new Intent(this, BuyBook.class));;
                return true;
            case R.id.audio_invited:
            	startActivity(new Intent(this, RecordAudioInvited.class));;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}