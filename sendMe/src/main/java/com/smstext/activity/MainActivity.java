package com.smstext.activity;

import com.smstext.content.Content;
import com.smstext.content.MainPageControl;
import com.smstext.sendme.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	
	LayoutInflater inflater;
	MainPageControl mainPageControl;
	
	public static int heightColumn;
	public static int textSize;
	public static Content columnContent;
	public Activity activity;
	public static CallBack callBack;
	public static int indexColumn = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_lay);
        
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout mainViewLayout  = (LinearLayout) findViewById(R.id.layView);
        
        findScreenSize();
        
        activity = this;
        columnContent = StartActivity.content;
        mainPageControl = new MainPageControl(columnContent, inflater, mainViewLayout);
        initCallBack();
    }
    
    void findScreenSize() {

		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		float tmpHeight = display.getHeight();
		heightColumn = (int)(tmpHeight/7);
		textSize = heightColumn /4;
    }
    
   void initCallBack(){
    	
    	callBack = new CallBack(){
			@Override
			public void goToActivity(int index) {
				
				indexColumn = index;
				activity.startActivity(new Intent(activity, MessageActivity.class));
				overridePendingTransition(R.anim.right_in, R.anim.left_out);
			}
        };
    }
    
    public interface CallBack{    	
    public void goToActivity(int index);
    }

}
