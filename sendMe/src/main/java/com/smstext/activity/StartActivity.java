package com.smstext.activity;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.jdom.input.SAXBuilder;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.smstext.comunication.Communication;
import com.smstext.content.Content;
import com.smstext.sendme.R;

public class StartActivity extends Activity {
	
	public static Content content;
	ProgressBar statusBar;
	SAXBuilder parser;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_lay);
        
        statusBar = (ProgressBar) findViewById(R.id.statusBar);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
    	parser = new SAXBuilder(); 
        new downloadDataFromServer().execute();
    }

	class downloadDataFromServer extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			statusBar.setVisibility(ProgressBar.VISIBLE);
		}

		@Override
		protected Void doInBackground(Void... params) {

            String dataFromServer = Communication.ll_askServerControls(getString(R.string.url));
			
			content = new Content(dataFromServer, parser);
			
			if(content.isParseDataState()){
				
				try {
				    FileOutputStream fos = openFileOutput(getString(R.string.fileName), Context.MODE_PRIVATE);
				    fos.write(dataFromServer.getBytes());
				    fos.close();
				} catch (Exception e) { }
			}
			else{
				
				try {
				    BufferedReader inputReader = new BufferedReader(new InputStreamReader(openFileInput(getString(R.string.fileName))));
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
			
	        if( !content.isParseDataState() ){
	        	
			      Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.errorInternet), Toast.LENGTH_SHORT);
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
