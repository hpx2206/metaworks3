package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.processexplorer.DocumentViewWindow;

@Face(
		ejsPathMappingByContext=
	{
		"{how: 'explorer', face: 'dwr/metaworks/org/uengine/codi/mw3/model/DocumentPanel_explorer.ejs'}",
		"{how: 'perspectivePanel', face: 'dwr/metaworks/org/uengine/codi/mw3/model/DocumentPanel.ejs'}",
	})
public class DocumentPanel implements ContextAware {

	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}	
	
	public DocumentPanel(){
		setMetaworksContext(new MetaworksContext());
	}
	@AutowiredFromClient
	transient public Session session;
	
	IDocumentNode documentNode;
		public IDocumentNode getDocumentNode() {
			return documentNode;
		}
		public void setDocumentNode(IDocumentNode documentNode) {
			this.documentNode = documentNode;
		}

	DocumentViewWindow documentViewWindow;	 
		public DocumentViewWindow getDocumentViewWindow() {
			return documentViewWindow;
		}
		public void setDocumentViewWindow(DocumentViewWindow documentViewWindow) {
			this.documentViewWindow = documentViewWindow;
		}

	
	public void load() throws Exception{

		DocumentNode Node = new DocumentNode();
		Node.session = session;
		Node.setId(session.getCompany().getComCode());
		
		documentNode = Node.loadDocumentList();
		documentNode.setMetaworksContext(this.getMetaworksContext());
		documentNode.getMetaworksContext().setWhen("onlyView");
		setDocumentNode(documentNode);
	}
//	@Override
//	public WfNode makeNewNode(){
//		WfNode node = new KnowlegeNode();
//		node.session = session;
//		node.setType(this.getId());
//		if(session != null)
//			node.setAuthorId(session.getUser().getUserId());
//		
//		return node;
//		
//	}
}
