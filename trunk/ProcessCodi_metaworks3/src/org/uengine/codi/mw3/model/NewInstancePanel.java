package org.uengine.codi.mw3.model;

import java.util.Date;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.dao.MetaworksDAO;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.Choice;
import org.uengine.codi.mw3.knowledge.ITopicNode;


public class NewInstancePanel implements ContextAware {
	
	public NewInstancePanel(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	
	public void load(Session session) throws Exception{
//		unstructuredProcessInstanceStarter = new UnstructuredProcessInstanceStarter();
//		
		processMapPanel = new ProcessMapPanel();		
		processMapPanel.setMetaworksContext(this.getMetaworksContext());
		processMapPanel.load(session);
		
		newInstantiator = new CommentWorkItem();
		newInstantiator.setWriter(session.getUser());		
		newInstantiator.setInstantiation(true);
		newInstantiator.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		if("sns".equals(session.getEmployee().getPreferUX())){
			newInstantiator.getMetaworksContext().setHow("sns");
			this.getMetaworksContext().setHow("sns");
		}
		
		Choice securityLevel = new Choice();
		securityLevel.add("$Privacy.Normal","0");
		securityLevel.add("$Privacy.OnlyFollowers","1");
		securityLevel.add("$Privacy.Public","2");
		
		if("topic".equals(session.getLastPerspecteType())){
			
			StringBuffer sb = new StringBuffer();
			sb.append("select * from bpm_knol knol");
			sb.append(" where 	knol.type = ?type");
			sb.append(" and 		knol.id = ?topicId ");
			
			ITopicNode dao = (ITopicNode)MetaworksDAO.createDAOImpl(TransactionContext.getThreadLocalInstance(), sb.toString(), ITopicNode.class); 
			dao.set("type", "topic");
			dao.set("topicId", session.getLastSelectedItem());
			dao.select();
			if( dao.next() ){
				if( dao.getSecuopt() != null && dao.getSecuopt().equals("1") ){	// 비공개토픽
					securityLevel.add("$Privacy.Topic","3");
					securityLevel.setSelected("3");
				}else{	// 공개 토픽
					securityLevel.setSelected("0");
				}
			}
			
		}else{
			securityLevel.setSelected("0");
		}
		setSecurityLevel(securityLevel);
	}
	
	@AutowiredFromClient
	public Session session;
	
	WorkItem newInstantiator;
		public WorkItem getNewInstantiator() {
			return newInstantiator;
		}
	
		public void setNewInstantiator(WorkItem newInstantiator) {
			this.newInstantiator = newInstantiator;
		}

//	String securityLevel;
//	@Range(options={"$Privacy.Normal", "$Privacy.OnlyFollowers", "$Privacy.Public" , "$Privacy.Topic"}, 
//			values={"0", "1", "2", "3"})	
//		public String getSecurityLevel() {
//			return securityLevel;
//		}
//	
//		public void setSecurityLevel(String securityLevel) {
//			this.securityLevel = securityLevel;
//		}
		
	Choice securityLevel;
		@Face(ejsPath="dwr/metaworks/org/metaworks/widget/ChoiceCombo.ejs")
		public Choice getSecurityLevel() {
			return securityLevel;
		}
		public void setSecurityLevel(Choice securityLevel) {
			this.securityLevel = securityLevel;
		}

		
	InstanceBackground instanceBackground; 

		public InstanceBackground getInstanceBackground() {
			return instanceBackground;
		}
	
		public void setInstanceBackground(InstanceBackground instanceBackground) {
			this.instanceBackground = instanceBackground;
		}

	//	UnstructuredProcessInstanceStarter unstructuredProcessInstanceStarter;		
//	public UnstructuredProcessInstanceStarter getUnstructuredProcessInstanceStarter() {
//		return unstructuredProcessInstanceStarter;
//	}
//
//	public void setUnstructuredProcessInstanceStarter(
//			UnstructuredProcessInstanceStarter unstructuredProcessInstanceStarter) {
//		this.unstructuredProcessInstanceStarter = unstructuredProcessInstanceStarter;
//	}
//	
	ProcessMapPanel processMapPanel;
		public ProcessMapPanel getProcessMapPanel() {
			return processMapPanel;
		}
		public void setProcessMapPanel(ProcessMapPanel processMapPanel) {
			this.processMapPanel = processMapPanel;
		}
		

	String knowledgeNodeId;
		@Hidden
		public String getKnowledgeNodeId() {
			return knowledgeNodeId;
		}
		public void setKnowledgeNodeId(String knowledgeNodeId) {
			this.knowledgeNodeId = knowledgeNodeId;
		}
		
	Date dueDate;
		@Hidden	
		public Date getDueDate() {
			return dueDate;
		}
		public void setDueDate(Date dueDate) {
			this.dueDate = dueDate;
		}
		
	String parentInstanceId;
	@Hidden
		public String getParentInstanceId() {
			return parentInstanceId;
		}
		public void setParentInstanceId(String parentInstanceId) {
			this.parentInstanceId = parentInstanceId;
		}
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	

}
