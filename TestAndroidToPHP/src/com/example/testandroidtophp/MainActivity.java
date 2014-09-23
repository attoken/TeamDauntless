package com.example.testandroidtophp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONTokener;
import org.json.JSONArray;
import org.json.JSONObject;


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

		url = "http://10.0.2.2/LeFamilyController.php/";


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

					JSONObject jobj = new JSONObject();
					
					json = EntityUtils.toString(response.getEntity());

					indexStart = json.indexOf("{");
					indexEnd = json.indexOf("}");
					json = json.substring(indexStart, indexEnd+1);
					Log.d("jsonClass", json.getClass().toString());
					Log.d("error",json);
					//test.setText(json);

				}

				catch (Exception e) {
					// Code to handle exception
					test.setText(e.toString());
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