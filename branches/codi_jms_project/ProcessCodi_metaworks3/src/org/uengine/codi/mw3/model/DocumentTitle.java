package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.WfNode;

public class DocumentTitle implements ContextAware{
	
	@AutowiredFromClient
	transient public Session session;
	
	public DocumentTitle(){
		setMetaworksContext(new MetaworksContext());
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	String id;
		@Hidden
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	@Face(displayName="$DocumentFolderName")
	String name;
		@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT,"SubFolder"})
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}		
	String parentId;
		@Hidden
		public String getParentId() {
			return parentId;
		}
		public void setParentId(String parentId) {
			this.parentId = parentId;
		}

	boolean documentSecuopt;		
		@Hidden
		@Face(displayName="$DocumentSecuopt")
		public boolean isDocumentSecuopt() {
			return documentSecuopt;
		}
		public void setDocumentSecuopt(boolean documentSecuopt) {
			this.documentSecuopt = documentSecuopt;
		}

	public void saveMe() throws Exception{
		WfNode wfNode = new WfNode();
		DocumentNode node = new DocumentNode();
			wfNode.setName(this.getName());
			wfNode.setType("doc");
			wfNode.setSecuopt(documentSecuopt ? "1" : "0");
			
			if("SubFolder".equals(this.getMetaworksContext().getWhen())){
				wfNode.setParentId(this.getId());	
				wfNode.setAuthorId(session.getUser().getUserId());		
				wfNode.setCompanyId(session.getCompany().getComCode());
				wfNode.createMe();
				
				node = new DocumentNode();
				node.setId(wfNode.getId());
				node.setParentId(this.getId());	
				node.setUserId(session.getUser().getUserId());
				node.setUserName(session.getUser().getName());
				node.getMetaworksContext().setWhen(this.getMetaworksContext().getWhen());
				
				node.saveMe();
				
				
				this.setId(wfNode.getId());
			}else if(MetaworksContext.WHEN_NEW.equals(this.getMetaworksContext().getWhen())){
				wfNode.setParentId("Main");	
				wfNode.setAuthorId(session.getUser().getUserId());		
				wfNode.setCompanyId(session.getCompany().getComCode());
				wfNode.createMe();
				
				node = new DocumentNode();
				node.setId(wfNode.getId());
				node.setParentId("Main");	
				node.setUserId(session.getUser().getUserId());
				node.setUserName(session.getUser().getName());
				node.getMetaworksContext().setWhen(this.getMetaworksContext().getWhen());
				
				node.saveMe();
			
				this.setId(wfNode.getId());
		}else{
			wfNode.setId(this.getId());
			wfNode.copyFrom(wfNode.databaseMe());
			wfNode.setName(this.getName());
			wfNode.saveMe();
		}
		
	}
	
	@Face(displayName="$Save")
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] save() throws Exception{
		
		if("SubFolder".equals(this.getMetaworksContext().getWhen())){
			this.saveMe();
		
			DocumentNode documentNode = new DocumentNode();
			documentNode.setId(this.getId());
			documentNode.setName(this.getName());
			documentNode.setParentId(this.getId());	
		
		return new Object[]{new ToAppend(new DocumentPanel(), documentNode), new Refresh(this)};
		}else if(MetaworksContext.WHEN_NEW.equals(this.getMetaworksContext().getWhen())){
			this.saveMe();
			
			DocumentNode documentNode = new DocumentNode();
			documentNode.setId(this.getId());
			documentNode.setName(this.getName());
			documentNode.setParentId("Main");	
			
		return new Object[]{new ToAppend(new DocumentPanel(), documentNode), new Refresh(this)};

	}
		return null;
	}
	@Face(displayName="$Modify")
	@Available(when={MetaworksContext.WHEN_EDIT})
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] modify() throws Exception{
		
		this.saveMe();
		
		DocumentNode documentNode = new DocumentNode();
		documentNode.setId(this.getId());
		documentNode.setName(this.getName());
		
		return new Object[]{new Refresh(documentNode), new Remover(new ModalWindow())};
	}
	
}
