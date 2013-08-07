package org.uengine.codi.mw3.model;

import org.uengine.codi.mw3.knowledge.ProjectPerspective;
import org.uengine.kernel.GlobalContext;

public class PerspectivePanel {

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
//
//	StrategicPerspective strategicPerspective;
//		public StrategicPerspective getStrategicPerspective() {
//			return strategicPerspective;
//		}
//		public void setStrategicPerspective(StrategicPerspective strategicPerspective) {
//			this.strategicPerspective = strategicPerspective;
//		}
		
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

	public PerspectivePanel() throws Exception {
		this(null);
	}
	
	public PerspectivePanel(Session session) throws Exception {
		
		if(session != null){
			//개인별
			personalPerspective = new PersonalPerspective();
			personalPerspective.session = session;
			personalPerspective.select();
			
			if(session.getEmployee().isApproved() && !session.getEmployee().isGuest()){
				
				//주제별
				topicPerspective = new TopicPerspective();
				topicPerspective.session = session;
				topicPerspective.select();
				
				//조직도
				organizationPerspectiveDept = new OrganizationPerspectiveDept();
//				organizationPerspectiveDept.select();
				
				//역할
				organizationPerspectiveRole = new OrganizationPerspectiveRole();
//				organizationPerspectiveRole.select();
				
				//프로세스별
				processPerspective = new ProcessPerspective();
//				processPerspective.select();
				
				//앱
				if("1".equals(GlobalContext.getPropertyString("app.use", "1"))){
					appPerspective = new OrganizationPerspectiveApp();
				}
				if("1".equals(GlobalContext.getPropertyString("project.use", "1"))){
					//프로젝트
					projectPerspective = new ProjectPerspective();
//						projectPerspective.select();
				}
				if("1".equals(GlobalContext.getPropertyString("contact.use", "1"))){
					//친구
					contactPerspective = new ContactPerspective();
					contactPerspective.session = session;
					contactPerspective.select();
				}
				
			}
			//processStatusPerspective = new ProcessStatusPerspective();
			//strategicPerspective = new StrategicPerspective();
		}
	}
}
