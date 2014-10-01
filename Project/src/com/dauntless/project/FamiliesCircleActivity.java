package com.dauntless.project;

import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class FamiliesCircleActivity extends ActionBarActivity {
	boolean hasCreated = false;
	FamilyAdmin familyAdmin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_families);
		
		
		Bundle data = getIntent().getExtras();
		if(data == null)
		{
			Log.i("Data", "NULL");
		}
		else
		{
			familyAdmin = (FamilyAdmin) data.getParcelable("familyAdmin");
			Log.i("Family Admin", familyAdmin.getName());
			
			//TODO GIVE BERNARD THESE 3 DETAILS FROM THE ACTIVITY BEFORE THIS TOGETHER WITH
			// THE PHONE NUMBER**
			hasCreated = true;
		}
		
		
		
		final ActionBar actionBar = getActionBar();
		// Specify that tabs should be displayed in the action bar.
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	    // Create a tab listener that is called when the user changes tabs.
	    final ActionBar.TabListener tabListener = new ActionBar.TabListener() {
	        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
	            // show the given tab
	        

			}

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
	    };

	    /*/ Add 3 tabs, specifying the tab's text and TabListener
	    for (int i = 0; i < 3; i++) {
	        actionBar.addTab(
	                actionBar.newTab()
	                        //.setText("Tab " + (i + 1))
	                        .setText("Low's Family")
	                		.setTabListener(tabListener));
	    }*/
	    
	    if(hasCreated)
	    {
    	actionBar.addTab(
                actionBar.newTab().setText(familyAdmin.getName() + " Family").setTabListener(tabListener));            		
	    }

        final Button button = (Button) findViewById(R.id.createFamilyBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	           	
            	Intent intent = new Intent(FamiliesCircleActivity.this, CreateFamilyActivity.class);
        	    startActivity(intent);
        	    
        	    
            }
        });


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.families, menu);
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
}
