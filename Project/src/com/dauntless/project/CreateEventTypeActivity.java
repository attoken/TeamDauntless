package com.dauntless.project;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class CreateEventTypeActivity extends ActionBarActivity {
	
	public static final String EVENT = "com.dauntless.project.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event_type);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.schedule_event, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void eventFood(View view) {
	    Intent intent = new Intent(this, CreateEventDateActivity.class);
	    String message = "Food";
	    intent.putExtra(EVENT, message);
	    startActivity(intent);
	}
	
	public void eventShopping(View view) {
	    Intent intent = new Intent(this, CreateEventDateActivity.class);
	    String message = "Shopping";
	    intent.putExtra(EVENT, message);
	    startActivity(intent);
	}
	
	public void eventZoo(View view) {
	    Intent intent = new Intent(this, CreateEventDateActivity.class);
	    String message = "Zoo";
	    intent.putExtra(EVENT, message);
	    startActivity(intent);
	}
	
	public void eventOther(View view) {
	    Intent intent = new Intent(this, CreateEventDateActivity.class);
	    String message = "Other";
	    intent.putExtra(EVENT, message);
	    startActivity(intent);
	}
}
