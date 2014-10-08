package com.dauntless.project;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class GalleryActivity extends ActionBarActivity {

	/*private Integer[] pics = {R.drawable.Chrysanthemum, R.drawable.Desert,
			R.drawable.Jellyfish, R.drawable.Koala,
			R.drawable.Lighthouse, R.drawable.Penguins};
	*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gallery, menu);
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
	
	public void activateCamera(View view) {
	    Intent intent = new Intent(this, ActivateCamera.class);
	    //String message = "Settings";
	    //intent.putExtra(MENU, message);
	    startActivity(intent);
	}
}
