package org.uengine.codi.mw3.processexplorer;

import java.util.Date;

import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.DocumentNode;
import org.uengine.codi.mw3.model.IDocumentNode;

public class DocumentFileViewPanel {
	
	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	String parentId;			
		public String getParentId() {
			return parentId;
		}
		public void setParentId(String parentId) {
			this.parentId = parentId;
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
	IDocumentNode documentNode;
		public IDocumentNode getDocumentNode() {
			return documentNode;
		}
		public void setDocumentNode(IDocumentNode documentNode) {
			this.documentNode = documentNode;
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
		
	@ServiceMethod(callByContent=true)
	public void load() throws Exception{
		IDocumentNode documentNode =  DocumentNode.findDetail(this.getId());	
		setDocumentNode(documentNode);
	}
	
}
