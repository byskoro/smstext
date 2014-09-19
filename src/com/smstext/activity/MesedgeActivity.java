package com.smstext.activity;

import com.smstext.R;
import com.smstext.content.MainPageControl;
import com.smstext.content.MesedgeControl;

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

public class MesedgeActivity extends Activity implements OnClickListener {

	LayoutInflater inflater;
	MainPageControl mainPageControl;
	
	public static Activity activity;
	public static CallBack CallBack;
	
	String textMesedge = "";
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
        new MesedgeControl(MainActivity.columeContent, inflater, mainViewLayout, MainActivity.indexColume);
	};
	
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
					MesedgeActivity.activity.startActivity(smsIntent);
				} catch (android.content.ActivityNotFoundException ex) { }
			}

			@Override
			public void select(Button textBtn, String data) {
				
				if (currentTextBtn != textBtn) {

					if (currentTextBtn != null)
						currentTextBtn.setBackgroundResource(R.drawable.mess);

					currentTextBtn = textBtn;
					currentTextBtn.setBackgroundResource(R.drawable.mess_actv);

					textMesedge = data;
				}
				else{
					currentTextBtn.setBackgroundResource(R.drawable.mess);
					currentTextBtn = null;
					textMesedge = "";
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
			
			if(!textMesedge.equals("")){
				
				ClipboardManager _clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
				_clipboard.setText(textMesedge);
			      Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.coping), Toast.LENGTH_SHORT); 
			      toast.setGravity(Gravity.CENTER, 0, 0); 
			      toast.show();
			}
			else{
				
			      Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.selectMesedge), Toast.LENGTH_SHORT); 
			      toast.setGravity(Gravity.CENTER, 0, 0); 
			      toast.show();
			}
		}
		
		if( v.getId() == send.getId()){		
			
			if(!textMesedge.equals(""))
				CallBack.sendSMS(textMesedge);
			else{
				
			      Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.selectMesedge), Toast.LENGTH_SHORT); 
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