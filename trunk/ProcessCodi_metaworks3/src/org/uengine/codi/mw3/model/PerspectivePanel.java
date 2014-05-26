package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.uengine.codi.mw3.knowledge.ProjectPerspective;
import org.uengine.codi.mw3.processexplorer.ValuechainPerspective;

public class PerspectivePanel implements ContextAware {
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	PersonalPerspective personalPerspective;
	@Available(condition="personalPerspective")
		public PersonalPerspective getPersonalPerspective() {
			return personalPerspective;
		}
	
		public void setPersonalPerspective(PersonalPerspective personalPerspective) {
			this.personalPerspective = personalPerspective;
		}
	
	DeptPerspective deptPerspective;
	@Available(condition="deptPerspective")
		public DeptPerspective getDeptPerspective() {
			return deptPerspective;
		}
		public void setDeptPerspective(DeptPerspective deptPerspective) {
			this.deptPerspective = deptPerspective;
		}

	RolePerspective rolePerspective;
	@Available(condition="rolePerspective")
		public RolePerspective getRolePerspective() {
			return rolePerspective;
		}
		public void setRolePerspective(RolePerspective rolePerspective) {
			this.rolePerspective = rolePerspective;
		}

	ProcessPerspective processPerspective;
	@Available(condition="processPerspective")
		public ProcessPerspective getProcessPerspective() {
			return processPerspective;
		}
	
		public void setProcessPerspective(ProcessPerspective processPerspective) {
			this.processPerspective = processPerspective;
		}
	
	OrganizationPerspectiveApp appPerspective;
	@Available(condition="appPerspective")
		public OrganizationPerspectiveApp getAppPerspective() {
			return appPerspective;
		}
		
		public void setAppPerspective(OrganizationPerspectiveApp appPerspective) {
			this.appPerspective = appPerspective;
		}
		
	ProjectPerspective projectPerspective;
	@Available(condition="projectPerspective")
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
	@Available(condition="strategicPerspective")
		public StrategicPerspective getStrategicPerspective() {
			return strategicPerspective;
		}
		public void setStrategicPerspective(StrategicPerspective strategicPerspective) {
			this.strategicPerspective = strategicPerspective;
		}
		
	DocumentPerspective documentPerspective;
	@Available(condition="documentPerspective")
		public DocumentPerspective getDocumentPerspective() {
			return documentPerspective;
		}
		public void setDocumentPerspective(DocumentPerspective documentPerspective) {
			this.documentPerspective = documentPerspective;
		}

	TopicPerspective topicPerspective;
	@Available(condition="topicPerspective")
		public TopicPerspective getTopicPerspective() {
			return topicPerspective;
		}
	
		public void setTopicPerspective(TopicPerspective topicPerspective) {
			this.topicPerspective = topicPerspective;
		}
		
	CompetitionPerspective	competitionPerspective;
	@Available(condition="competitionPerspective")
			public CompetitionPerspective getCompetitionPerspective() {
				return competitionPerspective;
			}
			public void setCompetitionPerspective(
					CompetitionPerspective competitionPerspective) {
				this.competitionPerspective = competitionPerspective;
			}
			
	ContactPerspective contactPerspective;
	@Available(condition="contactPerspective")
		public ContactPerspective getContactPerspective() {
			return contactPerspective;
		}
		public void setContactPerspective(ContactPerspective contactPerspective) {
			this.contactPerspective = contactPerspective;
		}

	UpcommingTodoPerspective upcommingTodoPerspective;
	@Available(condition="upcommingTodoPerspective")
		public UpcommingTodoPerspective getUpcommingTodoPerspective() {
			return upcommingTodoPerspective;
		}
		public void setUpcommingTodoPerspective(
				UpcommingTodoPerspective upcommingTodoPerspective) {
			this.upcommingTodoPerspective = upcommingTodoPerspective;
		}
	ValuechainPerspective valuechainPerspective;
	@Available(condition="valuechainPerspective")
		public ValuechainPerspective getValuechainPerspective() {
			return valuechainPerspective;
		}
		public void setValuechainPerspective(ValuechainPerspective valuechainPerspective) {
			this.valuechainPerspective = valuechainPerspective;
		}
	@AutowiredFromClient
	public Session session;
		
	public PerspectivePanel() throws Exception {
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	
	public PerspectivePanel(Session session) throws Exception {
		this();
		this.load(session);
	}
	public void load(Session session) throws Exception {
		if(session != null){
			//개인별
			if("1".equals(Perspective.USE_PERSONAL)){
				personalPerspective = new PersonalPerspective();
				personalPerspective.session = session;
			}
			
			//주제별
			if("1".equals(Perspective.USE_TOPIC)){
				topicPerspective = new TopicPerspective();
			}
				
			//대회별
			if("1".equals(Perspective.USE_COMPETITION)){
				competitionPerspective = new CompetitionPerspective();
			}
			
			if(!SNS.isPhone()){
				if(session.getEmployee().isApproved() && !session.getEmployee().isGuest()){
					//조직도
					if("1".equals(Perspective.USE_GROUP)){
						deptPerspective = new DeptPerspective();
	//					organizationPerspectiveDept.session = session;
	//					organizationPerspectiveDept.select();
					}
					
					//역할
					if("1".equals(Perspective.USE_ROLE)){
						rolePerspective = new RolePerspective();
						
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
						upcommingTodoPerspective = new UpcommingTodoPerspective();
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
					// 벨류체인
					if("1".equals(Perspective.USE_VALUECHAIN)){
						valuechainPerspective = new ValuechainPerspective();
						valuechainPerspective.getMetaworksContext().setHow("snsView");
					}
				}//.isApproved() && .isGuest()
			
				//processStatusPerspective = new ProcessStatusPerspective();
				//지식맵
				if("1".equals(Perspective.USE_PERSPECTIVE_KNOWLEDGE)){
					strategicPerspective = new StrategicPerspective();
				}
				if("1".equals(Perspective.USE_DOCUMENT)){
				     documentPerspective = new DocumentPerspective();
				     MetaworksContext metaworksContext = new MetaworksContext();
				     metaworksContext.setHow("perspectivePanel");
				     
				     documentPerspective.setMetaworksContext(metaworksContext);
				     String tenentId = Employee.extractTenantName(session.getEmployee().getEmail());
				     documentPerspective.setTenentId(tenentId);
				}
			}//SNS.isPhone
		}//session
	}
	
	
}
