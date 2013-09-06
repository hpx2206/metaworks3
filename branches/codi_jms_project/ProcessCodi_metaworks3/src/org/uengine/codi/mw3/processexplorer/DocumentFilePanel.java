package org.uengine.codi.mw3.processexplorer;


import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.uengine.codi.mw3.model.DocumentNode;
import org.uengine.codi.mw3.model.IDocumentNode;

public class DocumentFilePanel implements ContextAware{
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
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
	String conntype;
		public String getConntype() {
			return conntype;
		}
		public void setConntype(String conntype) {
			this.conntype = conntype;
		}	
		
	IDocumentNode documentNode;
		public IDocumentNode getDocumentNode() {
			return documentNode;
		}
		public void setDocumentNode(IDocumentNode documentNode) {
			this.documentNode = documentNode;
		}	
		
		
	public void loadDetailView() throws Exception{
		IDocumentNode documentNode =  new DocumentNode();
		documentNode.findDetail(this.getId());
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
