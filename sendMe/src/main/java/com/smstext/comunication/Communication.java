package com.smstext.comunication;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class Communication {

	  public static String ll_askServerControls(String data){

		try {
			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, 2000);
			HttpConnectionParams.setSoTimeout(httpParameters, 2000);
			
            DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
			HttpGet httpPost = new HttpGet(data);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();

			return EntityUtils.toString(httpEntity);
		}
		catch(Exception e){  }
			
		return "N/A";
	  } 
	
	
	
}
