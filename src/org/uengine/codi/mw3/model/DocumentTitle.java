package org.uengine.codi.mw3.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import org.metaworks.dao.DAOFactory;
import org.metaworks.dao.KeyGeneratorDAO;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.TopicMapping;



@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
			options={"fieldOrder"}, values={"name,description"})
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
		@Face(displayName="$Name")
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
		@Face(displayName="$description")
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
		
		TopicMapping tm = new TopicMapping();
		tm.setTopicId(node.getId());
		tm.setUserId(session.getUser().getUserId());
		tm.setUserName(session.getUser().getName());
		tm.setAssigntype(0);
		
		Map options = new HashMap();
		options.put("useTableNameHeader", "false");
		options.put("onlySequenceTable", "true");
		KeyGeneratorDAO kg = DAOFactory.getInstance(TransactionContext.getThreadLocalInstance()).createKeyGenerator("bpm_topicmapping", options);
		kg.select();
		kg.next();
		
		Number number = kg.getKeyNumber();
		
		tm.setTopicMappingId(number.longValue());
		tm.createDatabaseMe();
		
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
		
		
		TopicMapping tm = new TopicMapping();
		tm.setTopicId(node.getId());
		tm.setUserId(session.getUser().getUserId());
		tm.setUserName(session.getUser().getName());
		tm.setAssigntype(0);
		
		Map options = new HashMap();
		options.put("useTableNameHeader", "false");
		options.put("onlySequenceTable", "true");
		KeyGeneratorDAO kg = DAOFactory.getInstance(TransactionContext.getThreadLocalInstance()).createKeyGenerator("bpm_topicmapping", options);
		kg.select();
		kg.next();
		
		Number number = kg.getKeyNumber();
		
		tm.setTopicMappingId(number.longValue());
		tm.createDatabaseMe();
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
		}
		
		return null;
	}
	@Face(displayName="$Save")
	@Available(when={MetaworksContext.WHEN_EDIT})
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] modify() throws Exception{
		DocumentNode node = new DocumentNode();
		node.setId(this.getId());
		node.setName(this.getName());
		node.setDescription(this.getDescription());
		node.setAuthorId(session.getUser().getUserId());
		node.setCompanyId(session.getCompany().getComCode());
		node.setParentId(this.getParentId());
		node.setType(DocumentNode.TYPE_DOC);
		node.setSecuopt(documentSecuopt ? "1" : "0");
		node.saveMe();
		return new Object[]{ new Refresh(node), new Remover(new ModalWindow())};
	}
	
}
