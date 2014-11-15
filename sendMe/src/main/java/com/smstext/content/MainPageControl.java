package com.smstext.content;

import com.smstext.category.Category;
import com.smstext.sendme.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MainPageControl {

	Content columnContent;
	LayoutInflater inflater;
	LinearLayout mainViewLayout;
	OnClickListener btnClickListener;
	
	View viewColumn;
	
	ViewId viewId;
	int index = 0;
	
	public MainPageControl( Content columnContent, LayoutInflater inflater, LinearLayout mainViewLayout) {
		
		this.columnContent = columnContent;
		this.inflater = inflater;
		this.mainViewLayout = mainViewLayout;
		
		viewId = ViewId.getInstance();
		
		listener();
		create();	
	}

	public void create() {
		
		int index = 0;

		if (columnContent.getCategoryList().size() != 0)
			for (Category categoryObj : columnContent.getCategoryList()) {

				viewColumn = inflater.inflate(R.layout.title_line, null);
				categoryObj.create(viewId, viewColumn, btnClickListener, index);
				mainViewLayout.addView(viewColumn);
				index++;
				if(index == 6) index = 0;
			}
	}
	
	
	public void listener() {

		btnClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				index = 0;

				if (columnContent.getCategoryList().size() != 0)
					for (Category categoryObj : columnContent.getCategoryList()){
						categoryObj.listener(v.getId(), index);
						index++;
					}
			}
		};
	}
	
}
