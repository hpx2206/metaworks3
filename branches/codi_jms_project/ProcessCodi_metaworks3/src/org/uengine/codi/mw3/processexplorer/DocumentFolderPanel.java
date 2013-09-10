package org.uengine.codi.mw3.processexplorer;


import java.util.ArrayList;

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

	ArrayList<DocumentNode> documentList;
		public ArrayList<DocumentNode> getDocumentList() {
			return documentList;
		}
		public void setDocumentList(ArrayList<DocumentNode> documentList) {
			this.documentList = documentList;
		}
	DocumentNavigatorPanel documentNavigatorPanel;
		public DocumentNavigatorPanel getDocumentNavigatorPanel() {
			return documentNavigatorPanel;
		}
		public void setDocumentNavigatorPanel(
				DocumentNavigatorPanel documentNavigatorPanel) {
			this.documentNavigatorPanel = documentNavigatorPanel;
		}
		
	public DocumentFolderPanel(){
		documentList = new ArrayList<DocumentNode>();
	}
	
	@ServiceMethod(callByContent=true)
	public void openFolder() throws Exception{
		IDocumentNode documentNode = new DocumentNode();
		documentNode.setMetaworksContext(new MetaworksContext());
		documentNode.getMetaworksContext().setHow("fileView");
		documentNode.setId(this.getId());
		documentNode.openFolderView();
		
	}
	public void load(String id) throws Exception {
		DocumentNode documentNode = new DocumentNode();
		documentNode.setMetaworksContext(new MetaworksContext());
		documentNode.getMetaworksContext().setHow("folderView");
		documentList = documentNode.loadFolderView(id);
	}
	
	
}
