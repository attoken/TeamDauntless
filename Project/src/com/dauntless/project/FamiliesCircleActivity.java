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
import org.json.JSONArray;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TextView;

public class FamiliesCircleActivity extends ActionBarActivity {
	boolean hasCreated = false;
	boolean fetch = true;
	boolean populate = true;
	FamilyAdmin familyAdmin;
	
	HttpClient httpclient;
	HttpGet request;
	HttpResponse response;
	String url;
	String phoneNumber;
	String selectedFamily;
	List<String> nameList = new ArrayList<String>();
	List<String> idList = new ArrayList<String>();
	List<String> memberList = new ArrayList<String>();

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_families);
		ConnectionClient cc = new ConnectionClient();
        url = cc.getUrl();
		Log.i("test","FamiliesCircleActivity");
        
        //setContentView(R.layout.activity_families);
		
        
        //TODO Hardcoded value of phone number
		phoneNumber = "9172174900000000000";
		
		final ActionBar actionBar = getActionBar();
		// Specify that tabs should be displayed in the action bar.
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

	    // Create a tab listener that is called when the user changes tabs.
	    final ActionBar.TabListener tabListener = new ActionBar.TabListener() {
	        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
	            // show the given tab
		        Intent intent = new Intent(FamiliesCircleActivity.this, CreateFamilyActivity.class);
	        	startActivity(intent);
			}

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FamiliesCircleActivity.this, CreateFamilyActivity.class);
        	    startActivity(intent);
			}
	    };
	    
	 // Create a tab listener that is called when the user changes tabs.
	    final ActionBar.TabListener tabListener2 = new ActionBar.TabListener() {
	        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
	        	
	            // show the given tab
	        	selectedFamily = idList.get(tab.getPosition()).toString();
				new getMembers().execute();
				ImageView image = (ImageView)findViewById(R.id.createFamilyImgV1);
				ImageView image2 = (ImageView)findViewById(R.id.createFamilyImgV2);
				ImageView image3 = (ImageView)findViewById(R.id.createFamilyImgV3);
				ImageView image4 = (ImageView)findViewById(R.id.createFamilyImgV4);
				ImageView image5 = (ImageView)findViewById(R.id.createFamilyImgV5);
				ImageView image6 = (ImageView)findViewById(R.id.createFamilyImgV6);
				ImageView image7 = (ImageView)findViewById(R.id.createFamilyImgV7);
				
				image.setImageDrawable(null);
				image2.setImageDrawable(null);
				image3.setImageDrawable(null);
				image4.setImageDrawable(null);
				image5.setImageDrawable(null);
				image6.setImageDrawable(null);
				image7.setImageDrawable(null);
				
				TextView text = (TextView)findViewById(R.id.createFamilyTxt1);
				TextView text2 = (TextView)findViewById(R.id.createFamilyTxt2);
				TextView text3 = (TextView)findViewById(R.id.createFamilyTxt3);
				TextView text4 = (TextView)findViewById(R.id.createFamilyTxt4);
				TextView text5 = (TextView)findViewById(R.id.createFamilyTxt5);
				TextView text6 = (TextView)findViewById(R.id.createFamilyTxt6);
				TextView text7 = (TextView)findViewById(R.id.createFamilyTxt7);
				
				text.setText("");
				text2.setText("");
				text3.setText("");
				text4.setText("");
				text5.setText("");
				text6.setText("");
				text7.setText("");
				
				while(populate);
				for(int i = 0; i < memberList.size(); i++)
				{
					switch(i) {
					case 0:					
						image.setImageResource(R.drawable.man);						
						text.setText(memberList.get(i));
						break;
					case 1:
						image2.setImageResource(R.drawable.man);						
						text2.setText(memberList.get(i));
						text2.setGravity(Gravity.CENTER);
						break;
					case 2:						
						image3.setImageResource(R.drawable.man);						
						text3.setText(memberList.get(i));
						text3.setGravity(Gravity.CENTER);
						break;
					case 3:						
						image4.setImageResource(R.drawable.man);						
						text4.setText(memberList.get(i));
						text4.setGravity(Gravity.CENTER);
						break;
					case 4:						
						image5.setImageResource(R.drawable.man);						
						text5.setText(memberList.get(i));
						text5.setGravity(Gravity.CENTER);
						break;
					case 5:						
						image6.setImageResource(R.drawable.man);						
						text6.setText(memberList.get(i));
						text6.setGravity(Gravity.CENTER);
						break;
					case 6:
						image7.setImageResource(R.drawable.man);
						text7.setText(memberList.get(i));
						text7.setGravity(Gravity.CENTER);
						break;
					default:
						break;
					}
            
					Log.i("Member", memberList.get(i));
				}
				memberList.clear();
			}

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				populate = true;
			}

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
	        	
				
			}
	    };
		
		
		new getFamilies().execute();
		
		Bundle data = getIntent().getExtras();
		while(fetch);
		// Add tabs, specifying the tab's text and TabListener
	    for (int i = 0; i < nameList.size(); i++) {
	        actionBar.addTab(
	                actionBar.newTab()
	                        
	                        .setText(nameList.get(i) + " Family")
	                		.setTabListener(tabListener2));
	    }
	    
	    actionBar.addTab(
                actionBar.newTab()                        
                        .setText("+")
                		.setTabListener(tabListener));
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
	
	private class getFamilies extends AsyncTask<Void, Void, Void>
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
				    nameValuePairs.add(new BasicNameValuePair("phoneNumber", phoneNumber));
				    nameValuePairs.add(new BasicNameValuePair("functionCall", "3"));
				    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

					response = httpclient.execute(httppost);
					Log.v("response code", response.getStatusLine()
                            .getStatusCode() + ""); 
					Log.i("retrieval status", "Successful!");				
					json = EntityUtils.toString(response.getEntity());
					json += "~";
		
					indexStart = json.indexOf("</pre>[");
					indexEnd = json.indexOf("~");
					json = json.substring(indexStart+6, indexEnd);
					json = json.replace("[", "");
					json = json.replace("]", "");
					json = "[" + json + "]";
					Log.i("error222",json);
					
					JSONArray jarr = new JSONArray(json);
					JSONObject jobj;
					
					for(int i = 0; i< jarr.length(); i++)
					{
						jobj = jarr.getJSONObject(i);
						nameList.add(jobj.getString("familyName"));
						idList.add("00" + jobj.getString("familyID"));
					}
				
				}

				catch (Exception e) {
					Log.e("error", e.toString());
					
				}

		     fetch = false;
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
	
	private class getMembers extends AsyncTask<Void, Void, Void>
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
				    nameValuePairs.add(new BasicNameValuePair("familyID", selectedFamily));
				    nameValuePairs.add(new BasicNameValuePair("functionCall", "4"));
				    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

					response = httpclient.execute(httppost);
					Log.v("response code", response.getStatusLine()
                            .getStatusCode() + ""); 
					Log.i("retrieval status", "Successful!");				
					json = EntityUtils.toString(response.getEntity());
					json += "~";
		
					indexStart = json.indexOf("</pre>[");
					indexEnd = json.indexOf("~");
					json = json.substring(indexStart+6, indexEnd);
					json = json.replace("[", "");
					json = json.replace("]", "");
					json = "[" + json + "]";
					Log.i("error222",json);
					
					JSONArray jarr = new JSONArray(json);
					JSONObject jobj;
					
					for(int i = 0; i< jarr.length(); i++)
					{
						jobj = jarr.getJSONObject(i);
						if(jobj.getString("family_userAdminFlag").equals("1"))
						{
							memberList.add(jobj.getString("family_userName") + " (Admin)");
						}
						else
						{
							memberList.add(jobj.getString("family_userName"));
						}
					}
			
				}

				catch (Exception e) {
					Log.e("error", e.toString());
					
				}

		     populate = false;
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
