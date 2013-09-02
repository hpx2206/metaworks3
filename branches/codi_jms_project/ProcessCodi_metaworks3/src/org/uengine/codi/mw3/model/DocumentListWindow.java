package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;

@Face(ejsPath="dwr/metaworks/genericfaces/Window.ejs",
displayName="DocumentList Window",
options={"hideLabels", "maximize"},
values={"true", "true"})
public class DocumentListWindow extends ContentWindow  {

	String parentId;
		@Hidden
		public String getParentId() {
			return parentId;
		}
	
		public void setParentId(String parentId) {
			this.parentId = parentId;
		}
	DocumentListView documentListView;
		public DocumentListView getDocumentListView() {
			return documentListView;
		}
		public void setDocumentListView(DocumentListView documentListView) {
			this.documentListView = documentListView;
		}	
	
	public DocumentListWindow() throws Exception{
		setTitle("$DocumentListWindow");
	}
	
	public void load() throws Exception{
		documentListView = new DocumentListView();
		documentListView.setParentId(this.getParentId());
		documentListView.load(this.getParentId());
		
	}
}
