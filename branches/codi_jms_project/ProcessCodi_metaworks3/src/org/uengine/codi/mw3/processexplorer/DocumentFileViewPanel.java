package org.uengine.codi.mw3.processexplorer;

import java.util.Date;

import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.model.IUser;

public class DocumentFileViewPanel {
	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	String title;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}		
	IUser writer;
		public IUser getWriter() {
			return writer;
		}
		public void setWriter(IUser writer) {
			this.writer = writer;
		}	
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}	
		
	Date CreatedDate;
		public Date getCreatedDate() {
			return CreatedDate;
		}
		public void setCreatedDate(Date createdDate) {
			CreatedDate = createdDate;
		}
	
	Date ModifiedDate;
		public Date getModifiedDate() {
			return ModifiedDate;
		}
		public void setModifiedDate(Date modifiedDate) {
			ModifiedDate = modifiedDate;
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
		
	
	public void load(){
		
	}
	
}
