package org.uengine.codi.mw3.processexplorer;

import java.util.Date;

import org.metaworks.annotation.ServiceMethod;

public class DocumentFolderPanel {
	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}	
	
	Date endDate;
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
	
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
	String description;
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}	
		
		
	public void loadFolderView(){
		
	}
	
	@ServiceMethod(callByContent=true)
	public Object[] openFolder() throws Exception{
	
		return null;
	}
	
	
}
