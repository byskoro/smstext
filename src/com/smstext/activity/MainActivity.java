package com.smstext.activity;

import com.smstext.R;
import com.smstext.content.Content;
import com.smstext.content.MainPageControl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.LinearLayout;


public class MainActivity extends Activity {
	
	LayoutInflater inflater;
	MainPageControl mainPageControl;
	
	public static int HEIGHT_COLUME;
	public static int TEXT_SIZE;
	public static Content  columeContent;
	public Activity activity;
	public static CallBack callBack;
	public static int indexColume = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_lay);
        
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout mainViewLayout  = (LinearLayout) findViewById(R.id.layView);
        
        findScreenSize();
        
        activity = this;
        columeContent = StartActivity.content;
        mainPageControl = new MainPageControl(columeContent, inflater, mainViewLayout);
        initCallBack();
    }
    
    void findScreenSize() {

		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		float tmpHeight = display.getHeight();
		HEIGHT_COLUME = (int)(tmpHeight/7);
		TEXT_SIZE = HEIGHT_COLUME/4;
    }
    
    int getPixels(int unit, float size) {
    	
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return (int)TypedValue.applyDimension(unit, size, metrics);
    }
    
   void initCallBack(){
    	
    	callBack = new CallBack(){
			@Override
			public void goToActivity(int index) {
				
				indexColume = index;
				activity.startActivity(new Intent(activity, MesedgeActivity.class));
				overridePendingTransition(R.anim.right_in, R.anim.left_out);
			}
    	};
    }
    
    
    public interface CallBack{    	
    public void goToActivity(int index);
    }

}
