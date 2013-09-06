package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Window.ejs",
	displayName="DocumentViewer",
	options={"hideLabels", "maximize"}, 
	values={"true", "true"})
public class DocumentViewer extends ContentWindow {

	@AutowiredFromClient
	transient public Session session;
	
	String folderId;
		public String getFolderId() {
			return folderId;
		}
		public void setFolderId(String folderId) {
			this.folderId = folderId;
		}
		
	public void loadDocument() throws Exception {
		

		DocumentContentView documentContentView = new DocumentContentView();
		documentContentView.setId(this.getFolderId());
		documentContentView.load();
		
		
	} 	
}
