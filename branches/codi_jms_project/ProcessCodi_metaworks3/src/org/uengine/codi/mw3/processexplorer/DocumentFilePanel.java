package org.uengine.codi.mw3.processexplorer;


import org.metaworks.MetaworksContext;
import org.uengine.codi.mw3.model.DocumentNode;
import org.uengine.codi.mw3.model.IDocumentNode;

public class DocumentFilePanel {
	
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
		
	DocumentFileViewPanel documentFileViewPanel;
		public DocumentFileViewPanel getDocumentFileViewPanel() {
			return documentFileViewPanel;
		}
		public void setDocumentFileViewPanel(DocumentFileViewPanel documentFileViewPanel) {
			this.documentFileViewPanel = documentFileViewPanel;
		}
		
	public void loadFolderView() throws Exception{
			
			IDocumentNode documentNode =  DocumentNode.findFile(parentId);	
			setDocumentNode(documentNode);
			
		}

	public void load(String parentId) throws Exception{
		IDocumentNode documentNode =  new DocumentNode();
		documentNode.setMetaworksContext(new MetaworksContext());
		documentNode.getMetaworksContext().setHow("fileView");
		documentNode.loadExplorerView(parentId);
		setDocumentNode(documentNode);
	}
	
	//상세보기
	public void fileDetailView() throws Exception{
		
		
	}
}
