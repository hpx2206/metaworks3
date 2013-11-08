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
	
	IDocumentNode idocumentNode;
		public IDocumentNode getIdocumentNode() {
			return idocumentNode;
		}
		public void setIdocumentNode(IDocumentNode idocumentNode) {
			this.idocumentNode = idocumentNode;
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
		DocumentNode dnode = new DocumentNode();
		dnode.session = session;
		dnode.setCompanyId(session.getCompany().getComCode());
		
		idocumentNode = dnode.loadDocumentList();
		idocumentNode.setMetaworksContext(this.getMetaworksContext());
		idocumentNode.getMetaworksContext().setWhen("onlyView");
		setIdocumentNode(idocumentNode);
	}
}
