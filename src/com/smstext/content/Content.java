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
	
	private boolean parceDataState = false;
	
	public Content(String dataFromServer, SAXBuilder parser) {

		try {
			
			in = new StringReader(dataFromServer);
			Document doc_1 = parser.build(in);
			Element rootNode_1 = doc_1.getRootElement();
			Element Category = (Element) rootNode_1.getChild("Category");	
			
			List categorysList = Category.getChildren("category");
	        for (int i = 0; i < categorysList.size(); i++) {
	        	
	            Element category = (Element) categorysList.get(i);
	            String nameCat =  category.getChildText("name");
	            categoryObj = new Category(nameCat, "#ffff0000");
	            Element fieldsElement = (Element) category.getChild("fields");
	            List fieldsList = fieldsElement.getChildren("field");
	            
		        for (int k = 0; k < fieldsList.size(); k++) {
		        	
		            Element fieldElement = (Element) fieldsList.get(k);
     
		           String data = fieldElement.getChildText("text");
		           categoryObj.addMesedge(data);
		         }
		        
		        categoryList.add(categoryObj);
	         }
	        
	        parceDataState = true;
			
		} catch (Exception e) {
			parceDataState = false;
		}
		
	}
	
	public ArrayList<Category> getCategoryList() {
		
		return categoryList;
	}
	
	public boolean isParceDataState() {
		
		return parceDataState;
	}
	
}
