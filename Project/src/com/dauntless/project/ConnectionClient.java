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

import android.os.AsyncTask;
import android.util.Log;

public class ConnectionClient {
	public String url = "http://172.20.95.38:80/LeFamilyController.php/";
	HttpClient httpclient;
	HttpGet request;
	HttpResponse response;
	
	public String getUrl()
	{
		return url;
	}

}
