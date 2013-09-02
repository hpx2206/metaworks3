package org.uengine.codi.mw3.processexplorer;

import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.Session;

public class DocumentViewWindow {
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

	String tempId;
		public String getTempId() {
			return tempId;
		}
		public void setTempId(String tempId) {
			this.tempId = tempId;
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
		
		if(id != null){
			documentNavigatorPanel.setId(this.getId());
			documentNavigatorPanel.setParentid(this.getParentId());
			documentNavigatorPanel.setName(this.getName());
			documentNavigatorPanel.load();
			
			documentFolderPanel.setId(this.getId());
			documentFolderPanel.setParentId(this.getParentId());
			documentFolderPanel.load(documentFolderPanel.getParentId());
			
			documentFilePanel.setId(this.getId());
			documentFilePanel.setParentId(this.getTempId());
			documentFilePanel.load(documentFilePanel.getParentId());
		}
		
	}
	
}
