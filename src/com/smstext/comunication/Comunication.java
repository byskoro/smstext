package com.smstext.comunication;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;


public class Comunication {

	public Comunication() {

	}	  
	  //"http://" + serverIP + ":80/xml_file.xml"
	  public String ll_askServerControls(final String data){

		try {
			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, 2000);
			HttpConnectionParams.setSoTimeout(httpParameters, 2000);
			
            DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
			HttpGet httpPost = new HttpGet(data);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			String xmlStr = EntityUtils.toString(httpEntity);

			return xmlStr;
		}
		catch(Exception e){  }
			
		return "N/A";
	  } 
	
	
	
}
