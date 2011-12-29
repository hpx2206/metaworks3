package com.defaultcompany.activities;

import java.io.Serializable;

import org.metaworks.FieldDescriptor;
import org.metaworks.Type;
import org.metaworks.inputter.TextAreaInput;

public class MetaWeblogPost implements Serializable {
	
	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;
	
	public static void metaworksCallback_changeMetadata(Type type) {
		type.setFieldOrder(new String[] { "Title", "Description", "Tags" });
		
		FieldDescriptor fd = type.getFieldDescriptor("Description");
		fd.setInputter(new TextAreaInput(30, 20));
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
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}

	private String tags;
		public String getTags() {
			return tags;
		}
		public void setTags(String tags) {
			this.tags = tags;
		}

}
