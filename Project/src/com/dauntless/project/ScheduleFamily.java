package com.dauntless.project;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ScheduleFamily extends ActionBarActivity {
	public static final String MEETING = "com.dauntless.project.MEETING";
	public String time = "";
	public int counter = 800;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule_family);
		
		Intent intent = getIntent();
		time = intent.getStringExtra(ScheduleTime.TIME);
        
	    Log.i("Time", ""+time);
    
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.schedule_family, menu);
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
	
	public void createMeeting(View view) {
	    Intent intent = new Intent(this, Schedules.class);
	    String message = time + " " + "Dad";
	    intent.putExtra(MEETING, message);
	    startActivity(intent);
	}
}
