package com.smstext.content;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.smstext.activity.MessageActivity;
import com.smstext.sendme.R;

public class Message {
	
	String name;
	View viewColumn;
	ViewId viewId;
	int id_Btn;
	Button textBtn;

	public Message(String name, View viewColumn, ViewId viewId, OnClickListener btnClickListener) {

		this.name = name;
		this.viewId = viewId;
		this.viewColumn = viewColumn;
		
		textBtn = (Button) viewColumn.findViewById(R.id.text_id);
		id_Btn = viewId.getUniqueId();
		textBtn.setId(id_Btn);
		textBtn.setOnClickListener(btnClickListener);
		textBtn.setText(name);
	}
	
	public void listener(int id) {
		
		if(id_Btn == id)
			MessageActivity.CallBack.select(textBtn, name);
	}
}
