package com.dauntless.project;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	public static final String MENU = "com.dauntless.project.MENU";
	public String message = "";
	
	HttpClient httpclient;
	HttpGet request;
	HttpResponse response;
	String url;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url = "http://172.22.95.123:80/LeFamilyController.php/";
		Log.i("test","hello");
		new MyTask().execute();
		
		
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
    public void schedule(View view) {
	    Intent intent = new Intent(this, CreateEventTypeActivity.class);
	    //EditText editText = (EditText) findViewById(R.id.edit_message);
	    //String message = editText.getText().toString();
	    //intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}
    
    public void meetings(View view) {
	    Intent intent = new Intent(this, EventsActivity.class);
	    String message = "Dauntless";
	    intent.putExtra(MENU, message);
	    startActivity(intent);
	}
    
    public void settings(View view) {
	    Intent intent = new Intent(this, SettingsActivity.class);
	    //String message = "Settings";
	    //intent.putExtra(MENU, message);
	    startActivity(intent);
	}
    
    public void gallery(View view) {
	    Intent intent = new Intent(this, GalleryActivity.class);
	    //String message = "Settings";
	    //intent.putExtra(MENU, message);
	    startActivity(intent);
	}
    
    public void families(View view) {
	    Intent intent = new Intent(this, FamiliesCircleActivity.class);
	    //String message = "Settings";
	    //intent.putExtra(MENU, message);
	    startActivity(intent);
	}
    
    public void petMenu(View view) {
	    Intent intent = new Intent(this, PetMenuActivity.class);
	    //String message = "Settings";
	    //intent.putExtra(MENU, message);
	    startActivity(intent);
	}

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    
    private class MyTask extends AsyncTask<Void, Void, Void>
	{
		
		 //TextView result = (TextView) findViewById(R.id.tvResult);
		 //EditText test = (EditText) findViewById(R.id.editText1);

		 int statusCode;
		 String line;
		 String text;
		 String json;
		 int indexStart;
			int indexEnd;
			StringBuilder builder = new StringBuilder();
		    @Override
		    protected Void doInBackground(Void... params) {
		     
		    	try {
		    		
					httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost(url);
					
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				    nameValuePairs.add(new BasicNameValuePair("phoneNumber", "0"));
				    nameValuePairs.add(new BasicNameValuePair("functionCall", "1"));
				    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				    
				    Log.i("bernard", "WOOHOO");
					response = httpclient.execute(httppost);
					Log.i("bernard2", "WOOHOO");
					
					
					json = EntityUtils.toString(response.getEntity());
					
					indexStart = json.indexOf("{");
					indexEnd = json.indexOf("}");
					json = json.substring(indexStart, indexEnd+1);
					
					JSONObject jobj = new JSONObject(json);
					Log.i("jsonClass", json.getClass().toString());
					Log.i("error",json);
					//test.setText(json);

				}

				catch (Exception e) {
					// Code to handle exception
					
				}

		     return null;
		     
		    }
		    
		    @Override
		    protected void onPostExecute(Void result) {
		     
		    	try {
					
				} catch (Exception e) {
					// Code to handle exception
				} 
		     
		     super.onPostExecute(result);   
		    }

		   }
}
