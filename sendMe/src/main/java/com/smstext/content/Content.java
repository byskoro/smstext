package com.smstext.content;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.smstext.category.Category;

public class Content {
	
	private ArrayList<Category> categoryList = new ArrayList<Category>();
	Category categoryObj;
	StringReader in;
	
	private boolean parseDataState = false;
	
	public Content(String dataFromServer, SAXBuilder parser) {

		try {
			
			in = new StringReader(dataFromServer);
			Document doc_1 = parser.build(in);
			Element rootNode_1 = doc_1.getRootElement();
			Element Category = rootNode_1.getChild("Category");
			
			List categoryList = Category.getChildren("category");
	        for (Object aCategory :categoryList) {
	        	
	            Element category = (Element) aCategory;

	            String nameCat =  category.getChildText("name");
	            categoryObj = new Category(nameCat, "");
	            Element fieldsElement = category.getChild("fields");
	            List fieldsList = fieldsElement.getChildren("field");

                for (Object aFieldsList : fieldsList) {

                    Element fieldElement = (Element) aFieldsList;

                    String data = fieldElement.getChildText("text");
                    categoryObj.addMessage(data);
                }
		        
		        this.categoryList.add(categoryObj);
	         }
	        
	        parseDataState = true;
			
		} catch (Exception e) {
			parseDataState = false;
		}
		
	}
	
	public ArrayList<Category> getCategoryList() {
		
		return categoryList;
	}
	
	public boolean isParseDataState() {
		
		return parseDataState;
	}
	
}
