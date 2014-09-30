package com.dauntless.project;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EventsActivity extends ActionBarActivity {
	public String meeting = "";
	private static TextView allMeetings;
	public static int counter = 0;
	//public ImageButton[] meetingList = new ImageButton[40];
	
	private void setUpViews() {
		allMeetings = (TextView) findViewById(R.id.meetingResult);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);
		
		setUpViews();
		
		RelativeLayout list = (RelativeLayout) findViewById(R.id.rls);
		
		
		Intent intent = getIntent();
		meeting = intent.getStringExtra(CreateEventSelectFamilyActivity.MEETING);
		Log.i("Meeting", ""+meeting);
        if(meeting != null)
        {
        	allMeetings.append(String.valueOf(counter) + ": " + meeting + " created!");
        	/*
        	RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		    lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);	    
			
			ImageButton newMeeting = new ImageButton(this);
			newMeeting.setImageResource(R.drawable.ic_launcher);
			newMeeting.setLayoutParams(lp);
			//meetingList[counter].setOnClickListener(mGreenBallOnClickListener);
			newMeeting.setBackgroundColor(Color.TRANSPARENT); 
			
			newMeeting.setTag(counter);
			newMeeting.setId(counter);

		    list.addView(newMeeting);
		    */
		    counter++;
		    //setContentView(list, lp);
		    Log.i("Created", " "+counter);
		    
		    //onSaveInstanceState(savedInstanceState);
        }
        else
        {
        	// not working
        	allMeetings.getText();
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.schedules, menu);
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
	
	public void backToHome(View view) {
	    Intent intent = new Intent(this, MainActivity.class);
	    startActivity(intent);
	    //finish();
	}
}
