package org.uengine.codi.mw3.processexplorer;

import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.model.Session;

public class DocumentViewWindow {
	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	String authorId;
		public String getAuthorId() {
			return authorId;
		}
		public void setAuthorId(String authorId) {
			this.authorId = authorId;
		}
	
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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
		
	DocumentNavigatorPanel  documentNavigatorPanel;
		public DocumentNavigatorPanel getDocumentNavigatorPanel() {
			return documentNavigatorPanel;
		}
		public void setDocumentNavigatorPanel(
				DocumentNavigatorPanel documentNavigatorPanel) {
			this.documentNavigatorPanel = documentNavigatorPanel;
		}
		
	DocumentFolderPanel documentFolderPanel;
		public DocumentFolderPanel getDocumentFolderPanel() {
			return documentFolderPanel;
		}
		public void setDocumentFolderPanel(DocumentFolderPanel documentFolderPanel) {
			this.documentFolderPanel = documentFolderPanel;
		}

	DocumentFilePanel documentFilePanel;
		public DocumentFilePanel getDocumentFilePanel() {
			return documentFilePanel;
		}
		public void setDocumentFilePanel(DocumentFilePanel documentFilePanel) {
			this.documentFilePanel = documentFilePanel;
		}
		
	@AutowiredFromClient
	transient public Session session;	
	
	public DocumentViewWindow(){
		documentNavigatorPanel = new DocumentNavigatorPanel();
		documentFolderPanel = new DocumentFolderPanel();
		documentFilePanel = new DocumentFilePanel();
	}
	
	public void load() throws Exception{
		
	}
	
}
