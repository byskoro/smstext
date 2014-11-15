package com.smstext.category;

import java.util.ArrayList;

import com.smstext.activity.MainActivity;
import com.smstext.content.ViewId;
import com.smstext.sendme.R;

import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

public class Category {

	String name;
	View viewColumn;
	ViewId viewId;
	Button columnBtn;
	int    id_Btn;
	String color;
	
	int indexColor[] = new int[]{0xff62b5e1, 0xffb179ba, 0xff9fc136, 0xfffec14c, 0xeff64a4b, 0xff36b986};

	private ArrayList<String> messageList = new ArrayList<String>();

	public Category(String name, String color) {

		this.name = name;
		this.color = color;
	}

	public void addMessage(String str) {

		messageList.add(str);
	}
	
	public ArrayList<String> getMessageList() {
		
		return messageList;
	}

	public void create(ViewId viewId, View viewColumn, OnClickListener btnClickListener, int index) {

		this.viewId = viewId;
		this.viewColumn = viewColumn;

		columnBtn = (Button) viewColumn.findViewById(R.id.btn_id);
		id_Btn = viewId.getUniqueId();
		columnBtn.setId(id_Btn);
		columnBtn.setOnClickListener(btnClickListener);
		
		columnBtn.setText(name);
		
		LayoutParams params = columnBtn.getLayoutParams();
		params.height = MainActivity.heightColumn;
		columnBtn.setLayoutParams(params);
		columnBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, MainActivity.textSize);
		
		columnBtn.setBackgroundColor(indexColor[index]);
	}
	
	public void listener(int id, int index) {
		
		if(id_Btn == id)	
			MainActivity.callBack.goToActivity(index);			
	}

}
