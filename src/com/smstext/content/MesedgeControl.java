package com.smstext.content;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.smstext.R;
import com.smstext.category.Category;

public class MesedgeControl {
	
	Content columeContent;
	LayoutInflater inflater;
	LinearLayout mainViewLayout;
	OnClickListener btnClickListener;
	
	View viewColum;
	ViewId viewId;
	
	int index; 
	Category category = null;
	
	private ArrayList<Mesedge> mesedgeList = new ArrayList<Mesedge>();
	
	public MesedgeControl(Content columeContent, LayoutInflater inflater, LinearLayout mainViewLayout, int index) {
		
		this.columeContent = columeContent;
		this.inflater = inflater;
		this.mainViewLayout = mainViewLayout;
		
		viewId = ViewId.getInstance();
		
		this.index = index;
		
		listener();
		create();	
	}

	public void create() {

		if (columeContent.getCategoryList().size() != 0) {

			category = columeContent.getCategoryList().get(index);

			for (String mesedgeObj : category.getMesedgeList()) {

				viewColum = inflater.inflate(R.layout.mesedge_line, null);
				mesedgeList.add(new Mesedge(mesedgeObj, viewColum, viewId, btnClickListener));
				mainViewLayout.addView(viewColum);
			}

		}
	}
	
	
	public void listener() {
		
		btnClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {

				for (Mesedge MesedgeObj : mesedgeList) 
					MesedgeObj.listener(v.getId());
			}
		};		
	}

}
