package com.smstext.content;

import com.smstext.R;
import com.smstext.category.Category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MainPageControl {

	Content columeContent;
	LayoutInflater inflater;
	LinearLayout mainViewLayout;
	OnClickListener btnClickListener;
	
	View viewColum;
	
	ViewId viewId;
	int index = 0;
	
	public MainPageControl( Content columeContent, LayoutInflater inflater, LinearLayout mainViewLayout) {
		
		this.columeContent = columeContent;
		this.inflater = inflater;
		this.mainViewLayout = mainViewLayout;
		
		viewId = ViewId.getInstance();
		
		listener();
		create();	
	}

	public void create() {
		
		int index = 0;

		if (columeContent.getCategoryList().size() != 0)
			for (Category categoryObj : columeContent.getCategoryList()) {

				viewColum = inflater.inflate(R.layout.title_line, null);
				categoryObj.create(viewId, viewColum, btnClickListener, index);
				mainViewLayout.addView(viewColum);
				index++;
				if(index == 6) index = 0;
			}
	}
	
	
	public void listener() {

		btnClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				index = 0;

				if (columeContent.getCategoryList().size() != 0)
					for (Category categoryObj : columeContent.getCategoryList()){
						categoryObj.listener(v.getId(), index);
						index++;
					}
			}
		};
	}
	
}
