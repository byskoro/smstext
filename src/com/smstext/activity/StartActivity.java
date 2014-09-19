package com.smstext.activity;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jdom.input.SAXBuilder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.smstext.R;
import com.smstext.activity.MainActivity.CallBack;
import com.smstext.comunication.Comunication;
import com.smstext.content.Content;

public class StartActivity extends Activity {
	
	public static Content content;

	Thread serverThread;
	Comunication comunication;
	public String dataFromServer = "N/A";
	
	ProgressBar statusBar;
	
	SAXBuilder parser;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_lay);
        
        statusBar = (ProgressBar) findViewById(R.id.statusBar);
        
        comunication = new Comunication();
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
    	parser = new SAXBuilder(); 
        new dawnloadDataFromServer().execute();    	
    }

	class dawnloadDataFromServer extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			statusBar.setVisibility(ProgressBar.VISIBLE);
		}

		@Override
		protected Void doInBackground(Void... params) {

			dataFromServer = comunication.ll_askServerControls("http://sendme.esy.es/func.php?action=all&lang=ru");
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) { }
			
			content = new Content(dataFromServer, parser);
			
			if(content.isParceDataState() == true){
				
				try {
				    FileOutputStream fos = openFileOutput("DayTwentyTwoFile", Context.MODE_PRIVATE);
				    fos.write(dataFromServer.getBytes());
				    fos.close();
				} catch (Exception e) { }
			
			}
			else{
				
				try {
				    BufferedReader inputReader = new BufferedReader(new InputStreamReader(openFileInput("DayTwentyTwoFile")));
				    String inputString;
				    StringBuffer stringBuffer = new StringBuffer();                
				    while ((inputString = inputReader.readLine()) != null) {
				        stringBuffer.append(inputString + "\n");
				    }
				    dataFromServer = stringBuffer.toString();
				} catch (IOException e) {
				    e.printStackTrace();
				}
			}
			
	        content = new Content(dataFromServer, parser);
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			statusBar.setVisibility(ProgressBar.INVISIBLE);
			
	        if( content.isParceDataState() == false ){
	        	
			      Toast toast = Toast.makeText(getApplicationContext(),  getString(R.string.erroreInternet), Toast.LENGTH_SHORT); 
			      toast.setGravity(Gravity.CENTER, 0, 0); 
			      toast.show();
			}
	        else{
	        	
		        startActivity(new Intent(StartActivity.this, MainActivity.class));
		        overridePendingTransition(R.anim.right_in, R.anim.left_out);
		        finish();
	        }
		}
	}
    
}
