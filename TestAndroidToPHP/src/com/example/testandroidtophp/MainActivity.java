package com.example.testandroidtophp;

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

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

	HttpClient httpclient;
	HttpGet request;
	HttpResponse response;
	String url;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// URL of PHP Script

		url = "http://172.22.126.40:80/LeFamilyController.php/";
		Log.i("test","hello");
		new MyTask().execute();
		
	}
	private class MyTask extends AsyncTask<Void, Void, Void>
	{
		
		 TextView result = (TextView) findViewById(R.id.tvResult);
		 EditText test = (EditText) findViewById(R.id.editText1);

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
				    
					response = httpclient.execute(httppost);				
					
					json = EntityUtils.toString(response.getEntity());
					
					indexStart = json.indexOf("{");
					indexEnd = json.indexOf("}");
					json = json.substring(indexStart, indexEnd+1);
					
					JSONObject jobj = new JSONObject(json);
					Log.i("jsonClass", json.getClass().toString());
					Log.i("error",json);
					test.setText(json);

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