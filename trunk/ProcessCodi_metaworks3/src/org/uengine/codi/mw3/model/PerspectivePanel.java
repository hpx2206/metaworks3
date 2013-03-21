package org.uengine.codi.mw3.model;

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
	
	//ProcessStatusPerspective processStatusPerspective;
	//
	//	public ProcessStatusPerspective getProcessStatusPerspective() {
	//		return processStatusPerspective;
	//	}
	//
	//	public void setProcessStatusPerspective(
	//			ProcessStatusPerspective processStatusPerspective) {
	//		this.processStatusPerspective = processStatusPerspective;
	//	}
	

	//	StrategicPerspective strategicPerspective;
//		public StrategicPerspective getStrategicPerspective() {
//			return strategicPerspective;
//		}
//	
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
	
	public PerspectivePanel() throws Exception {
		this(null);
	}
	
	public PerspectivePanel(Session session) throws Exception {
		if(session != null){
			personalPerspective = new PersonalPerspective();
			personalPerspective.session = session;
			personalPerspective.select();
						
			if(session.getEmployee().isApproved()){
				topicPerspective = new TopicPerspective();
				topicPerspective.session = session;
				topicPerspective.select();
				
				organizationPerspectiveDept = new OrganizationPerspectiveDept();
				organizationPerspectiveRole = new OrganizationPerspectiveRole();
				processPerspective = new ProcessPerspective();
				appPerspective = new OrganizationPerspectiveApp();
				
			}
			
			//processStatusPerspective = new ProcessStatusPerspective();
			
			//strategicPerspective = new StrategicPerspective();
		}
	}
}
