package com.smstext.content;

import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.smstext.category.Category;
import com.smstext.sendme.R;

public class MessageControl {
	
	Content columnContent;
	LayoutInflater inflater;
	LinearLayout mainViewLayout;
	OnClickListener btnClickListener;
	
	View viewColumn;
	ViewId viewId;
	
	int index; 
	Category category = null;
	
	private ArrayList<Message> messageList = new ArrayList<Message>();
	
	public MessageControl(Content columnContent, LayoutInflater inflater, LinearLayout mainViewLayout, int index) {
		
		this.columnContent = columnContent;
		this.inflater = inflater;
		this.mainViewLayout = mainViewLayout;
		
		viewId = ViewId.getInstance();
		
		this.index = index;
		
		listener();
		create();	
	}

	public void create() {

		if (columnContent.getCategoryList().size() != 0) {

			category = columnContent.getCategoryList().get(index);

			for (String messageObj : category.getMessageList()) {

				viewColumn = inflater.inflate(R.layout.mesedge_line, null);
				messageList.add(new Message(messageObj, viewColumn, viewId, btnClickListener));
				mainViewLayout.addView(viewColumn);
			}

		}
	}
	
	
	public void listener() {
		
		btnClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {

				for (Message messageObj : messageList)
					messageObj.listener(v.getId());
			}
		};		
	}

}
