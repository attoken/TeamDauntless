package com.dauntless.project;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateFamilyActivity extends ActionBarActivity {
	EditText nameEdit;
	EditText surnameEdit;
	EditText positionEdit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_family);
		
		
		final Button button = (Button) findViewById(R.id.createFamilyDetailBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// take the strings 
            	// make into an object to pass back to families circle
            	nameEdit = (EditText)findViewById(R.id.createFamilyNameEdit);
            	surnameEdit = (EditText)findViewById(R.id.createFamilySurnameEdit);
            	positionEdit = (EditText)findViewById(R.id.createFamilyPositionEdit);
            	
            	//TODO no validation     			        	
            	Intent intent = new Intent(CreateFamilyActivity.this, FamiliesCircleActivity.class);
            	//Intent intent = new Intent(CreateFamilyActivity.this, TestActivity.class);
            	intent.putExtra("familyAdmin", new FamilyAdmin(nameEdit.getText().toString(), surnameEdit.getText().toString(), positionEdit.getText().toString()));
        	    startActivity(intent);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_family, menu);
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
