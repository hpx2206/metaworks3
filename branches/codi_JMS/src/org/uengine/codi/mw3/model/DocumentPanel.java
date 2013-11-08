package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
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
	
	IDocumentNode documentNode;
		public IDocumentNode getDocumentNode() {
			return documentNode;
		}
		public void setDocumentNode(IDocumentNode documentNode) {
			this.documentNode = documentNode;
		}
		
	public DocumentPanel(){
		setMetaworksContext(new MetaworksContext());
	}
	@AutowiredFromClient
	transient public Session session;
	

	@ServiceMethod
	public Object[] loadUnformed() throws Exception{

		String title = "도큐멘트";
		Object[] returnObject = Perspective.loadDocumentListPanel(session, "UnlabeledDocument", "Main", title);
		
		return returnObject;
	}

	public void load() throws Exception{
		DocumentNode node = new DocumentNode();
		node.session = session;
		node.setCompanyId(session.getCompany().getComCode());
		
		documentNode = node.loadDocumentList();
		documentNode.setMetaworksContext(this.getMetaworksContext());
		documentNode.getMetaworksContext().setWhen("onlyView");
		setDocumentNode(documentNode);
	}
}
