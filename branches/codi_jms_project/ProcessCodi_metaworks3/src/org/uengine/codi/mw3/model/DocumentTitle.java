package org.uengine.codi.mw3.model;

import java.util.Calendar;
import java.util.Date;

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
		
	String name;
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
	String description;
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	Date startDate;
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
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

	public void saveMain() throws Exception{
		DocumentNode node = new DocumentNode();
		node.setName(this.getName());
		node.setId(node.getId());
		node.setDescription(this.getDescription());
		node.setType(DocumentNode.TYPE_DOC);
		node.setSecuopt(documentSecuopt ? "1" : "0");
		node.setStartDate(Calendar.getInstance().getTime());		
		node.setParentId("Main");	
		node.setAuthorId(session.getUser().getUserId());		
		node.setCompanyId(session.getCompany().getComCode());
		node.getMetaworksContext().setWhen(this.getMetaworksContext().getWhen());
		node.createMe();
	}
	DocumentNode node;
		public DocumentNode getNode() {
			return node;
		}
		public void setNode(DocumentNode node) {
			this.node = node;
		}
	public void saveSub() throws Exception{
		DocumentNode node = new DocumentNode();
		node.setId(node.getId());
		node.setName(this.getName());
		node.setDescription(this.getDescription());
		node.setType(DocumentNode.TYPE_DOC);
		node.setSecuopt(documentSecuopt ? "1" : "0");
		node.setStartDate(Calendar.getInstance().getTime());
		node.setParentId(this.getId());	
		node.setAuthorId(session.getUser().getUserId());		
		node.setCompanyId(session.getCompany().getComCode());
		node.getMetaworksContext().setWhen(this.getMetaworksContext().getWhen());
		node.createMe();
		
		
		this.setId(node.getId());
//}else{
//	wfNode.setId(this.getId());
//	wfNode.copyFrom(wfNode.databaseMe());
//	wfNode.setName(this.getName());
//	wfNode.saveMe();
////}
	}
	
	@Face(displayName="$Save")
	@Available(when={MetaworksContext.WHEN_NEW})
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] save() throws Exception{
		
		if(MetaworksContext.WHEN_NEW.equals(this.getMetaworksContext().getWhen()) && 
				"sub".equals(this.getMetaworksContext().getHow())){
			
			this.saveSub();
			DocumentNode documentNode = new DocumentNode();
			documentNode.setId(this.getId());
			
			return new Object[]{new ToAppend(new DocumentPanel(), documentNode), new Refresh(this), new Remover(new ModalWindow())};
		}else if(MetaworksContext.WHEN_NEW.equals(this.getMetaworksContext().getWhen())){
			
			this.saveMain();
			DocumentNode documentNode = new DocumentNode();
			documentNode.setId(this.getId());
			
			return new Object[]{new ToAppend(new DocumentPanel(), documentNode), new Refresh(this), new Remover(new ModalWindow())};
		}else if(MetaworksContext.WHEN_EDIT.equals(this.getMetaworksContext().getWhen())){
			
			this.modify();
			DocumentNode documentNode = new DocumentNode();
			return new Object[]{new Remover(new ModalWindow()), new Refresh(documentNode)};
		}
		
		return null;
	}
	
	
	public void modify() throws Exception{
		
		DocumentNode node = new DocumentNode();
		node.setId(this.getId());
		node.setName(this.getName());
		node.setDescription(this.getDescription());
		node.setParentId(this.getParentId());
		node.setType(DocumentNode.TYPE_DOC);
		node.setSecuopt(documentSecuopt ? "1" : "0");
		node.saveMe();
	}
	
}
