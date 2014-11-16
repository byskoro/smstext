package com.smstext.activity;

import com.smstext.content.MessageControl;
import com.smstext.sendme.R;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MessageActivity extends Activity implements OnClickListener {

	LayoutInflater inflater;
	
	public static Activity activity;
	public static CallBack CallBack;
	
	String textMessage = "";
	Button currentTextBtn;
	
	Button copy, send;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messedge_lay);
		
		copy = (Button) findViewById(R.id.copy_id);
		send = (Button) findViewById(R.id.send_id);
		copy.setOnClickListener(this);
		send.setOnClickListener(this);
		
        activity = this;

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout mainViewLayout  = (LinearLayout) findViewById(R.id.layView);
        
        createCallBack();
        new MessageControl(MainActivity.columnContent, inflater, mainViewLayout, MainActivity.indexColumn);
	}
	
	void createCallBack() {

		CallBack = new CallBack() {

			@Override
			public void sendSMS(String data) {

				Intent smsIntent = new Intent(Intent.ACTION_VIEW);
				smsIntent.setData(Uri.parse("smsto:"));
				smsIntent.setType("vnd.android-dir/mms-sms");

				smsIntent.putExtra("address", "");
				smsIntent.putExtra("sms_body", data);
				try {
					MessageActivity.activity.startActivity(smsIntent);
				} catch (Exception e) { }
			}

			@Override
			public void select(Button textBtn, String data) {
				
				if (currentTextBtn != textBtn) {

					if (currentTextBtn != null)
						currentTextBtn.setBackgroundResource(R.drawable.mess);

					currentTextBtn = textBtn;
					currentTextBtn.setBackgroundResource(R.drawable.mess_actv);

					textMessage = data;
				}
				else{
					currentTextBtn.setBackgroundResource(R.drawable.mess);
					currentTextBtn = null;
					textMessage = "";
				}
			}
		};
	}
	
    public interface CallBack{    
    	
    public void sendSMS(String data);
    public void select(Button textBtn, String data);
    }

    
	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		
		if( v.getId() == copy.getId()){
			
			if(!textMessage.equals("")){
				
				ClipboardManager _clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
				_clipboard.setText(textMessage);
			      Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.coping), Toast.LENGTH_SHORT); 
			      toast.setGravity(Gravity.CENTER, 0, 0); 
			      toast.show();
			}
			else{
				
			      Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.selectMessage), Toast.LENGTH_SHORT);
			      toast.setGravity(Gravity.CENTER, 0, 0); 
			      toast.show();
			}
		}
		
		if( v.getId() == send.getId()){		
			
			if(!textMessage.equals(""))
				CallBack.sendSMS(textMessage);
			else{
				
			      Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.selectMessage), Toast.LENGTH_SHORT);
			      toast.setGravity(Gravity.CENTER, 0, 0); 
			      toast.show();
			}
		}
	}
    
	@Override
	public void onBackPressed() {
		super.onBackPressed();

	    overridePendingTransition(R.anim.left_in, R.anim.right_out);
	}

}