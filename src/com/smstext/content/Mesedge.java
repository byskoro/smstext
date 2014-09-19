package com.smstext.content;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.smstext.R;
import com.smstext.activity.MesedgeActivity;

public class Mesedge {
	
	String name;
	View viewColum;
	ViewId viewId;
	ImageButton columeBtn;
	int id_Btn;
	Button textBtn;


	public Mesedge(String name, View viewColum, ViewId viewId, OnClickListener btnClickListener) {

		this.name = name;
		this.viewId = viewId;
		this.viewColum = viewColum;
		
		textBtn = (Button) viewColum.findViewById(R.id.text_id);		
		id_Btn = viewId.getUniqueId();
		textBtn.setId(id_Btn);
		textBtn.setOnClickListener(btnClickListener);
		textBtn.setText(name);
	}
	
	public void listener(int id) {
		
		if(id_Btn == id)
			MesedgeActivity.CallBack.select(textBtn, name);
	}
}
