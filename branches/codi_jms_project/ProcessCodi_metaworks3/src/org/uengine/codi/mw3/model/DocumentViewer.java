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
	
	Long taskId;
		public Long getTaskId() {
			return taskId;
		}
		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}
	DocumentContentView documentContentView;
		public DocumentContentView getDocumentContentView() {
			return documentContentView;
		}
		public void setDocumentContentView(DocumentContentView documentContentView) {
			this.documentContentView = documentContentView;
		}
    public DocumentViewer(){
    }
	public void loadDocument() throws Exception {
		
		documentContentView = new DocumentContentView();
		documentContentView.setTaskId(this.getTaskId());
		documentContentView.load();
		
		
	} 	
}
