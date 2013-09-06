package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Name;
import org.springframework.beans.factory.annotation.Autowired;

@Face(ejsPath="genericfaces/Window.ejs",
	  displayName="InstanceViewContent",
	  options={"hideLabels", "maximize"}, 
	  values={"true", "true"})
public class InstanceViewContent extends ContentWindow {
	
	public Session session;

	@Autowired
	@Name
	public InstanceView instanceView;			
		public InstanceView getInstanceView() {
			return instanceView;
		}
	
		public void setInstanceView(InstanceView instanceView) {
			this.instanceView = instanceView;
		}
	
	DocumentViewer documentViewer;
		public DocumentViewer getDocumentViewer() {
			return documentViewer;
		}
	
		public void setDocumentViewer(DocumentViewer documentViewer) {
			this.documentViewer = documentViewer;
		}

	String folderId;
		public String getFolderId() {
			return folderId;
		}
	
		public void setFolderId(String folderId) {
			this.folderId = folderId;
		}

	String instanceName;
	@Name
	@Hidden
		public String getInstanceName() {
			return instanceName;
		}
		public void setInstanceName(String instanceName) {
			this.instanceName = instanceName;
		}

	public InstanceViewContent(){
		
	}
	
	public void load(IInstance instance) throws Exception{
		this.setInstanceName(instance.getName());
		
		instanceView.session = session;
		instanceView.setMetaworksContext(getMetaworksContext());
		instanceView.load(instance);
		
		this.setInstanceView(instanceView);
		
	}	

	public void loadDocument() throws Exception{
		documentViewer = new DocumentViewer();
		documentViewer.setFolderId(getFolderId());
		documentViewer.session = session;
		documentViewer.setMetaworksContext(getMetaworksContext());
		documentViewer.loadDocument();
		
		this.setDocumentViewer(documentViewer);
		
		
	}
}
