package com.smstext.category;

import java.util.ArrayList;

import com.smstext.R;
import com.smstext.activity.MainActivity;
import com.smstext.content.ViewId;

import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

public class Category {

	String name;
	View   viewColum;
	ViewId viewId;
	Button columeBtn;
	int    id_Btn;
	String color;
	
	int indexColor[] = new int[]{0xff62b5e1, 0xffb179ba, 0xff9fc136, 0xfffec14c, 0xeff64a4b, 0xff36b986};

	private ArrayList<String> mesedgeList = new ArrayList<String>();

	public Category(String name, String color) {

		this.name = name;
		this.color = color;
	}

	public void addMesedge(String str) {

		mesedgeList.add(str);
	}

	public String getMesedge(int index) {

		return mesedgeList.get(index);
	}
	
	public ArrayList<String> getMesedgeList() {
		
		return mesedgeList;
	}

	public void setViewColum(View viewColum) {

		this.viewColum = viewColum;
	}

	public void create(ViewId viewId, View viewColum, OnClickListener btnClickListener, int index) {

		this.viewId = viewId;
		this.viewColum = viewColum;

		columeBtn = (Button) viewColum.findViewById(R.id.btn_id);
		id_Btn = viewId.getUniqueId();
		columeBtn.setId(id_Btn);
		columeBtn.setOnClickListener(btnClickListener);
		
		columeBtn.setText(name);
		
		LayoutParams params = columeBtn.getLayoutParams();
		params.height = MainActivity.HEIGHT_COLUME;
		columeBtn.setLayoutParams(params);
		columeBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, MainActivity.TEXT_SIZE);
		
		columeBtn.setBackgroundColor(indexColor[index]);
	}
	
	public void listener(int id, int index) {
		
		if(id_Btn == id)	
			MainActivity.callBack.goToActivity(index);			
	}

}
