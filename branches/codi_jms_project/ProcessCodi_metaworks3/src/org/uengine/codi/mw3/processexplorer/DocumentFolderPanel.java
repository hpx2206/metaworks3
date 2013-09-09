package org.uengine.codi.mw3.processexplorer;


import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.DocumentNode;
import org.uengine.codi.mw3.model.IDocumentNode;

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
	String parentId;
		public String getParentId() {
			return parentId;
		}
		public void setParentId(String parentId) {
			this.parentId = parentId;
		}

	IDocumentNode documentNode;
		public IDocumentNode getDocumentNode() {
			return documentNode;
		}
		public void setDocumentNode(IDocumentNode documentNode) {
			this.documentNode = documentNode;
		}		
		
	DocumentNavigatorPanel documentNavigatorPanel;
		public DocumentNavigatorPanel getDocumentNavigatorPanel() {
			return documentNavigatorPanel;
		}
		public void setDocumentNavigatorPanel(
				DocumentNavigatorPanel documentNavigatorPanel) {
			this.documentNavigatorPanel = documentNavigatorPanel;
		}
	
	@ServiceMethod(callByContent=true)
	public void openFolder() throws Exception{
		IDocumentNode documentNode = new DocumentNode();
		documentNode.setMetaworksContext(new MetaworksContext());
		documentNode.getMetaworksContext().setHow("fileView");
		documentNode.setId(this.getId());
		documentNode.loadFolderView();
		
	}
	public void load(String id) throws Exception {
		IDocumentNode documentNode = new DocumentNode();
		documentNode.setMetaworksContext(new MetaworksContext());
		documentNode.getMetaworksContext().setHow("folderView");
		documentNode.loadFolderView(id);
		setDocumentNode(documentNode);
	}
	
	
}
