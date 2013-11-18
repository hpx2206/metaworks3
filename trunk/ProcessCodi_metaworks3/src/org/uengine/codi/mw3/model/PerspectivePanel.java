package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.knowledge.ProjectPerspective;
import org.uengine.kernel.GlobalContext;

public class PerspectivePanel  implements ContextAware {
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}		

	PersonalPerspective personalPerspective;
		public PersonalPerspective getPersonalPerspective() {
			return personalPerspective;
		}
	
		public void setPersonalPerspective(PersonalPerspective personalPerspective) {
			this.personalPerspective = personalPerspective;
		}
	
	OrganizationPerspectiveDept organizationPerspectiveDept;
		public OrganizationPerspectiveDept getOrganizationPerspectiveDept() {
			return organizationPerspectiveDept;
		}
		public void setOrganizationPerspectiveDept(
				OrganizationPerspectiveDept organizationPerspectiveDept) {
			this.organizationPerspectiveDept = organizationPerspectiveDept;
		}
	
	OrganizationPerspectiveRole organizationPerspectiveRole;
		public OrganizationPerspectiveRole getOrganizationPerspectiveRole() {
			return organizationPerspectiveRole;
		}
	
		public void setOrganizationPerspectiveRole(
				OrganizationPerspectiveRole organizationPerspectiveRole) {
			this.organizationPerspectiveRole = organizationPerspectiveRole;
		}
	
	ProcessPerspective processPerspective;
		public ProcessPerspective getProcessPerspective() {
			return processPerspective;
		}
	
		public void setProcessPerspective(ProcessPerspective processPerspective) {
			this.processPerspective = processPerspective;
		}
	
	OrganizationPerspectiveApp appPerspective;
		public OrganizationPerspectiveApp getAppPerspective() {
			return appPerspective;
		}
		
		public void setAppPerspective(OrganizationPerspectiveApp appPerspective) {
			this.appPerspective = appPerspective;
		}
		
	ProjectPerspective projectPerspective;
		public ProjectPerspective getProjectPerspective() {
			return projectPerspective;
		}	
		public void setProjectPerspective(ProjectPerspective projectPerspective) {
			this.projectPerspective = projectPerspective;
		}
		
//	ProcessStatusPerspective processStatusPerspective;
//		public ProcessStatusPerspective getProcessStatusPerspective() {
//			return processStatusPerspective;
//		}
//		public void setProcessStatusPerspective(
//				ProcessStatusPerspective processStatusPerspective) {
//			this.processStatusPerspective = processStatusPerspective;
//		}
		
	StrategicPerspective strategicPerspective;
		public StrategicPerspective getStrategicPerspective() {
			return strategicPerspective;
		}
		public void setStrategicPerspective(StrategicPerspective strategicPerspective) {
			this.strategicPerspective = strategicPerspective;
		}
		
	DocumentPerspective documentPerspective;
		public DocumentPerspective getDocumentPerspective() {
			return documentPerspective;
		}
		public void setDocumentPerspective(DocumentPerspective documentPerspective) {
			this.documentPerspective = documentPerspective;
		}

	TopicPerspective topicPerspective;
		public TopicPerspective getTopicPerspective() {
			return topicPerspective;
		}
	
		public void setTopicPerspective(TopicPerspective topicPerspective) {
			this.topicPerspective = topicPerspective;
		}
		
	ContactPerspective contactPerspective;
		public ContactPerspective getContactPerspective() {
			return contactPerspective;
		}
		public void setContactPerspective(ContactPerspective contactPerspective) {
			this.contactPerspective = contactPerspective;
		}

	CommingTodoPerspective commingTodoPerspective;
		public CommingTodoPerspective getCommingTodoPerspective() {
			return commingTodoPerspective;
		}
		public void setCommingTodoPerspective(
				CommingTodoPerspective commingTodoPerspective) {
			this.commingTodoPerspective = commingTodoPerspective;
		}
		
	@AutowiredFromClient
	public Session session;
		
	public PerspectivePanel() throws Exception {
		this(null);
	}
	
	public PerspectivePanel(Session session) throws Exception {
		if(session != null){
			//개인별
			if("1".equals(Perspective.USE_PERSONAL)){
				personalPerspective = new PersonalPerspective();
				personalPerspective.session = session;
				personalPerspective.select();
			}
			
			if(session.getEmployee().isApproved() && !session.getEmployee().isGuest()){
				//주제별
				if("1".equals(Perspective.USE_TOPIC)){
					topicPerspective = new TopicPerspective();
				}
				
				//조직도
				if("1".equals(Perspective.USE_GROUP)){
					organizationPerspectiveDept = new OrganizationPerspectiveDept();
//					organizationPerspectiveDept.session = session;
//					organizationPerspectiveDept.select();
				}
				
				//역할
				if("1".equals(Perspective.USE_ROLE)){
					organizationPerspectiveRole = new OrganizationPerspectiveRole();
//					organizationPerspectiveRole.session = session;
//					organizationPerspectiveRole.select();
				}
				
				//프로세스별
				if("1".equals(Perspective.USE_PROCESS)){
					processPerspective = new ProcessPerspective();
//					processPerspective.select();
				}
				
				//친구
				if("1".equals(Perspective.USE_CONTACT)){
					contactPerspective = new ContactPerspective();
				}
				if("1".equals(Perspective.USE_COMMINGTODO)){
					commingTodoPerspective = new CommingTodoPerspective();
				}
				
				//앱
				if("1".equals(Perspective.USE_APP)){
					appPerspective = new OrganizationPerspectiveApp();
					appPerspective.session = session;
					if(!("goSns".equals(session.getLastSelectedItem()))){
						appPerspective.getMetaworksContext().setHow("dashboard");
						appPerspective.getMetaworksContext().setWhere("oce_perspective");
						appPerspective.select();
					}
				}
				//프로젝트	
				if("1".equals(Perspective.USE_PROJECT)){
					projectPerspective = new ProjectPerspective();
					projectPerspective.session = session;
					projectPerspective.select();
				}
			}
			//processStatusPerspective = new ProcessStatusPerspective();
			//지식맵
			if("1".equals(Perspective.USE_PERSPECTIVE_KNOWLEDGE)){
				strategicPerspective = new StrategicPerspective();
			}
			if("1".equals(Perspective.USE_DOCUMENT)){
			     documentPerspective = new DocumentPerspective();
			     setMetaworksContext(new MetaworksContext());
			     this.getMetaworksContext().setHow("perspectivePanel");
			     documentPerspective.setMetaworksContext(this.getMetaworksContext());
			}
		}//session
	}
	
	
}
