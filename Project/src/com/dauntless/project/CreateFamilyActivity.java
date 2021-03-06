package com.dauntless.project;

import java.util.ArrayList;
import java.util.HashMap;
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
import android.telephony.TelephonyManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateFamilyActivity extends ActionBarActivity {
	EditText nameEdit;
	EditText surnameEdit;
	EditText positionEdit;
	JSONObject joAdminDetails;
	
	HttpClient httpclient;
	HttpGet request;
	HttpResponse response;
	String url;
	
	HashMap<String, String> adminDetails = new HashMap<String, String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_family);
		
		
		ConnectionClient cc = new ConnectionClient();
        url = cc.getUrl();
        
        //TelephonyManager telemanager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		//final String getSimSerialNumber = telemanager.getSimSerialNumber();
		
		final Button button = (Button) findViewById(R.id.createFamilyDetailBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	// take the strings 
            	// make into an object to pass back to families circle
            	nameEdit = (EditText)findViewById(R.id.createFamilyNameEdit);
            	surnameEdit = (EditText)findViewById(R.id.createFamilySurnameEdit);
            	positionEdit = (EditText)findViewById(R.id.createFamilyPositionEdit);
            	
            	//TODO change the phone number to dynamic
            	adminDetails.put("phoneNumber", "9172174900000000000");
            	adminDetails.put("name", nameEdit.getText().toString());
            	adminDetails.put("familyName", surnameEdit.getText().toString());
            	adminDetails.put("position", positionEdit.getText().toString());
            	adminDetails.put("admin", "true");
            	
            	joAdminDetails = new JSONObject(adminDetails);
            	
            	new MyTask().execute();
            	
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
		    		System.setProperty("http.keepAlive", "false");
					httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost(url);
					
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				    nameValuePairs.add(new BasicNameValuePair("adminDetails", joAdminDetails.toString()));
				    nameValuePairs.add(new BasicNameValuePair("functionCall", "2"));
				    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				    
					response = httpclient.execute(httppost);
					Log.v("response code", response.getStatusLine()
                            .getStatusCode() + ""); 
					Log.i("response", "Sucessful!");	
					
					json = EntityUtils.toString(response.getEntity());
					
					indexStart = json.indexOf("{");
					indexEnd = json.indexOf("}");
					json = json.substring(indexStart, indexEnd+1);
					
					JSONObject jobj = new JSONObject(json);
					Log.i("jsonClass", json.getClass().toString());
					Log.i("error",json);					

				}

				catch (Exception e) {
					Log.e("error", e.toString());	
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
