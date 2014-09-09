package com.dauntless.project;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ScheduleDay extends ActionBarActivity {
	public static final String DAY = "com.dauntless.project.DAY";
	public String event = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule_day);
		
		Intent intent = getIntent();
	    event = intent.getStringExtra(ScheduleEvent.EVENT);

	    Log.i("Event", ""+event);
	    // Create the text view
	    //TextView textView = new TextView(this);
	    //textView.setTextSize(40);
	    //textView.setText(message);

	    // Set the text view as the activity layout
	    //setContentView(textView);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.schedule_day, menu);
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
	
	public void dayMonday(View view) {
	    Intent intent = new Intent(this, ScheduleTime.class);
	    String message = event + " Monday";
	    intent.putExtra(DAY, message);
	    startActivity(intent);
	}
	
	public void dayTuesday(View view) {
	    Intent intent = new Intent(this, ScheduleTime.class);
	    String message = event + " Tuesday";
	    intent.putExtra(DAY, message);
	    startActivity(intent);
	}
	
	public void dayWednesday(View view) {
	    Intent intent = new Intent(this, ScheduleTime.class);
	    String message = event + " Wednesday";
	    intent.putExtra(DAY, message);
	    startActivity(intent);
	}
	
	public void dayThursday(View view) {
	    Intent intent = new Intent(this, ScheduleTime.class);
	    String message = event + " Thursday";
	    intent.putExtra(DAY, message);
	    startActivity(intent);
	}
	
	public void dayFriday(View view) {
	    Intent intent = new Intent(this, ScheduleTime.class);
	    String message = event + " Friday";
	    intent.putExtra(DAY, message);
	    startActivity(intent);
	}
	
	public void daySaturday(View view) {
	    Intent intent = new Intent(this, ScheduleTime.class);
	    String message = event + " Saturday";
	    intent.putExtra(DAY, message);
	    startActivity(intent);
	}
	
	public void daySunday(View view) {
	    Intent intent = new Intent(this, ScheduleTime.class);
	    String message = event + " Sunday";
	    intent.putExtra(DAY, message);
	    startActivity(intent);
	}
	
}