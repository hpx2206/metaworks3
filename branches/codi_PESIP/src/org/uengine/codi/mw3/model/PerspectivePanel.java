package org.uengine.codi.mw3.model;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
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
		
	// 공공 서비스 과제 발주정보
	OrderInformationPerspective orderInformationPerspective;
		public OrderInformationPerspective getOrderInformationPerspective() {
			return orderInformationPerspective;
		}
		public void setOrderInformationPerspective(
				OrderInformationPerspective orderInformationPerspective) {
			this.orderInformationPerspective = orderInformationPerspective;
		}
		
	// 공공 서비스 과제 지식마켓
	KnowledgeMarketPerspective knowledgeMarketPerspective;
		public KnowledgeMarketPerspective getKnowledgeMarketPerspective() {
			return knowledgeMarketPerspective;
		}
		public void setKnowledgeMarketPerspective(
				KnowledgeMarketPerspective knowledgeMarketPerspective) {
			this.knowledgeMarketPerspective = knowledgeMarketPerspective;
		}
	// 공공 서비스 지역
	RegionPerspective regionPerspective;
		public RegionPerspective getRegionPerspective() {
			return regionPerspective;
		}
		public void setRegionPerspective(RegionPerspective regionPerspective) {
			this.regionPerspective = regionPerspective;
		}
		
	// 공공 서비스 국내외 수출입 기업 Pool
	CustomsEnterprisePoolPerspective customsEnterprisePoolPerspective;
		public CustomsEnterprisePoolPerspective getCustomsEnterprisePoolPerspective() {
			return customsEnterprisePoolPerspective;
		}
		public void setCustomsEnterprisePoolPerspective(
				CustomsEnterprisePoolPerspective customsEnterprisePoolPerspective) {
			this.customsEnterprisePoolPerspective = customsEnterprisePoolPerspective;
		}
	
	// 한국형 공공서비스 소개
	PublicServiceIntroducePerspective publicServiceIntroducePerspective;
		public PublicServiceIntroducePerspective getPublicServiceIntroducePerspective() {
			return publicServiceIntroducePerspective;
		}
		public void setPublicServiceIntroducePerspective(
				PublicServiceIntroducePerspective publicServiceIntroducePerspective) {
			this.publicServiceIntroducePerspective = publicServiceIntroducePerspective;
		}

	@AutowiredFromClient
	public Session session;
		
	public PerspectivePanel() throws Exception {
		this(null);
	}
	
	public PerspectivePanel(Session session) throws Exception {
		if(session != null){
			//개인별
			if("1".equals(GlobalContext.getPropertyString("personal.use", "1"))){
				personalPerspective = new PersonalPerspective();
				personalPerspective.session = session;
				personalPerspective.select();
			}
			
			if(session.getEmployee().isApproved() && !session.getEmployee().isGuest()){
				//주제별
				if("1".equals(GlobalContext.getPropertyString("topic.use", "1"))){
					topicPerspective = new TopicPerspective();
					topicPerspective.session = session;
					topicPerspective.select();
				}
				
				//조직도
				if("1".equals(GlobalContext.getPropertyString("organization.use", "1"))){
					organizationPerspectiveDept = new OrganizationPerspectiveDept();
//					organizationPerspectiveDept.session = session;
//					organizationPerspectiveDept.select();
				}
				
				//역할
				if("1".equals(GlobalContext.getPropertyString("role.use", "1"))){
					organizationPerspectiveRole = new OrganizationPerspectiveRole();
					organizationPerspectiveRole.session = session;
					organizationPerspectiveRole.select();
				}
				
				//프로세스별
				if("1".equals(GlobalContext.getPropertyString("process.use", "1"))){
					processPerspective = new ProcessPerspective();
//					processPerspective.select();
				}
				
				//문서
				documentPerspective = new DocumentPerspective();
				setMetaworksContext(new MetaworksContext());
				this.getMetaworksContext().setHow("perspectivePanel");
				documentPerspective.setMetaworksContext(this.getMetaworksContext());
				
				//친구
				if("1".equals(GlobalContext.getPropertyString("contact.use", "1"))){
					contactPerspective = new ContactPerspective();
					contactPerspective.session = session;
					contactPerspective.select();
				}
				if("1".equals(GlobalContext.getPropertyString("commingTodo.use", "1"))){
					commingTodoPerspective = new CommingTodoPerspective();
					commingTodoPerspective.session = session;
					commingTodoPerspective.select();
				}
				//앱
				if("1".equals(GlobalContext.getPropertyString("app.use", "1"))){
					appPerspective = new OrganizationPerspectiveApp();
					appPerspective.getMetaworksContext().setHow("dashboard");
					appPerspective.getMetaworksContext().setWhere("oce_perspective");
					appPerspective.session = session;
					appPerspective.select();
				}
				//프로젝트
				if("1".equals(GlobalContext.getPropertyString("project.use", "1"))){
					projectPerspective = new ProjectPerspective();
					projectPerspective.session = session;
					projectPerspective.select();
				}
				
				// 여기서부터 공공서비스 코드
				// 발주정보 
				if("1".equals(GlobalContext.getPropertyString("orderInfo.use", "1"))) {
					orderInformationPerspective = new OrderInformationPerspective();
					orderInformationPerspective.session = session;
				}
				
				// 지식 마켓
				if("1".equals(GlobalContext.getPropertyString("knolMarket","1"))) {
					knowledgeMarketPerspective = new KnowledgeMarketPerspective();
					knowledgeMarketPerspective.session = session;
					knowledgeMarketPerspective.select();
				}
				
				// 지역별
				if("1".equals(GlobalContext.getPropertyString("region","1"))) {
					regionPerspective = new RegionPerspective();
					regionPerspective.session = session;
					regionPerspective.select();
				}
				
				// 수출입 pool
				if("1".equals(GlobalContext.getPropertyString("customsPool","1"))) {
					customsEnterprisePoolPerspective = new CustomsEnterprisePoolPerspective();
					customsEnterprisePoolPerspective.session = session;
				}
				
				// 한국형 공공서비스 소개
				if("1".equals(GlobalContext.getPropertyString("publicServiceIntro","1"))) {
					publicServiceIntroducePerspective = new PublicServiceIntroducePerspective();
					publicServiceIntroducePerspective.session = session;
				}
			}
			//processStatusPerspective = new ProcessStatusPerspective();
			//지식맵
			if("1".equals(GlobalContext.getPropertyString("perspective.knowledge.use", "1"))){
				strategicPerspective = new StrategicPerspective();
				strategicPerspective.session = session;
			}
		}//session
	}
	
}
