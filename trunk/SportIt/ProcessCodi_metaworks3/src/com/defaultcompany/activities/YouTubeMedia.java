package com.defaultcompany.activities;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.metaworks.FieldDescriptor;
import org.metaworks.Type;
import org.metaworks.inputter.SelectInput;
import org.uengine.util.UEngineUtil;

public class YouTubeMedia implements Serializable {
	
	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;
	
	@SuppressWarnings("unchecked")
	public static void metaworksCallback_changeMetadata(Type type) {
		type.setFieldOrder(new String[] { "Title", "Description", "Category", "KeyWords" });
		
		FieldDescriptor categoryFd = type.getFieldDescriptor("Category");
		
		try {
			SAXBuilder builder = new SAXBuilder(false);
			Document document = builder.build("http://gdata.youtube.com/schemas/2007/categories.cat?hl=" + Locale.getDefault().getLanguage());
			List<Element> children = document.getRootElement().getChildren();
			
			Object[] selections = new Object[children.size()];
			Object[] values = new Object[children.size()];
			
			for (int i = 0; i < children.size(); i++) {
				Element c = children.get(i);
				selections[i] = c.getAttributeValue("label");
				values[i] = c.getAttributeValue("term");
			}
			categoryFd.setInputter(new SelectInput(selections, values));
			
		} catch (Exception e) {
			e.printStackTrace();
			
			categoryFd.setInputter(new SelectInput());
		}
	}
	
	private String title;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}

	private String description;
		public String getDescription() {
			if (!UEngineUtil.isNotEmpty(description)) {
				return title;
			}
			return description;
		}	
		public void setDescription(String description) {
			this.description = description;
		}
	
	private String category;
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}

	private String keyWords;
		public String getKeyWords() {
			return keyWords;
		}
		public void setKeyWords(String keyWords) {
			this.keyWords = keyWords;
		}
		
}
