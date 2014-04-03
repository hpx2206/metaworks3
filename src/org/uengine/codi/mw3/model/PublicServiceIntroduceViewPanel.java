package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.admin.WebEditor;

public class PublicServiceIntroduceViewPanel {
	
	public PublicServiceIntroduceViewPanel() {
		metaworksContext = new MetaworksContext();
		
		serviceSummary = new WebEditor();
		serviceSummary.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
	}
	
	PublicServiceIntroduceItem publicServiceIntroduceItem;
		public PublicServiceIntroduceItem getPublicServiceIntroduceItem() {
			return publicServiceIntroduceItem;
		}
		public void setPublicServiceIntroduceItem(
				PublicServiceIntroduceItem publicServiceIntroduceItem) {
			this.publicServiceIntroduceItem = publicServiceIntroduceItem;
		}

	String serviceName;
	@Face(ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"1", "100"})
		public String getServiceName() {
			return serviceName;
		}
		public void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}
	
	String serviceIntroduce;
	@Face(ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"1", "100"})
		public String getServiceIntroduce() {
			return serviceIntroduce;
		}
		public void setServiceIntroduce(String serviceIntroduce) {
			this.serviceIntroduce = serviceIntroduce;
		}
	
	String constructTerm;
	@Face(ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"3", "30"})
		public String getConstructTerm() {
			return constructTerm;
		}
		public void setConstructTerm(String constructTerm) {
			this.constructTerm = constructTerm;
		}

	String budget;
	@Face(ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"3", "22"})
		public String getBudget() {
			return budget;
		}
		public void setBudget(String budget) {
			this.budget = budget;
		}

	String servicePurpose;
	@Face(ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"3", "22"})
		public String getServicePurpose() {
			return servicePurpose;
		}
		public void setServicePurpose(String servicePurpose) {
			this.servicePurpose = servicePurpose;
		}
		
	String publicExpectation;
	@Face(ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"3", "22"})
		public String getPublicExpectation() {
			return publicExpectation;
		}
		public void setPublicExpectation(String publicExpectation) {
			this.publicExpectation = publicExpectation;
		}
		
	String civilianExpectation;
	@Face(ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"3", "22"})
		public String getCivilianExpectation() {
			return civilianExpectation;
		}
		public void setCivilianExpectation(String civilianExpectation) {
			this.civilianExpectation = civilianExpectation;
		}
	
	String managerAttachOrganization;
	@Face(ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"3", "22"})
		public String getManagerAttachOrganization() {
			return managerAttachOrganization;
		}
		public void setManagerAttachOrganization(String managerAttachOrganization) {
			this.managerAttachOrganization = managerAttachOrganization;
		}
		
	String managerName;
	@Face(ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"3", "22"})
		public String getManagerName() {
			return managerName;
		}
		public void setManagerName(String managerName) {
			this.managerName = managerName;
		}
	
	String managerNumber;
	@Face(ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"3", "22"})
		public String getManagerNumber() {
			return managerNumber;
		}
		public void setManagerNumber(String managerNumber) {
			this.managerNumber = managerNumber;
		}
	
	String managerEmail;
	@Face(ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"3", "22"})
		public String getManagerEmail() {
			return managerEmail;
		}
		public void setManagerEmail(String managerEmail) {
			this.managerEmail = managerEmail;
		}
	
	String constructCareer;
		public String getConstructCareer() {
			return constructCareer;
		}
		public void setConstructCareer(String constructCareer) {
			this.constructCareer = constructCareer;
		}

	String relationService;
		public String getRelationService() {
			return relationService;
		}
		public void setRelationService(String relationService) {
			this.relationService = relationService;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	WebEditor serviceSummary;
		public WebEditor getServiceSummary() {
			return serviceSummary;
		}
		public void setServiceSummary(WebEditor serviceSummary) {
			this.serviceSummary = serviceSummary;
		}
		
	PublicServiceIntroduceDetailList publicServiceIntroduceDetailList;
		public PublicServiceIntroduceDetailList getPublicServiceIntroduceDetailList() {
			return publicServiceIntroduceDetailList;
		}
		public void setPublicServiceIntroduceDetailList(
				PublicServiceIntroduceDetailList publicServiceIntroduceDetailList) {
			this.publicServiceIntroduceDetailList = publicServiceIntroduceDetailList;
		}
		
	// 수정은 MetaworksContext를 view -> edit 로 바꾸는 작업
	// viewPanel 자체도 수정모드로 변경되어야 하고
	@ServiceMethod(callByContent=true)
	public void modify() {
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		this.getServiceSummary().getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
	// MetaworksContext가 edit 상태라면 Save를 한다.
	// 그리고 다시 메타웍스컨텍스트를 view 상태로..
	@ServiceMethod(callByContent=true, needToConfirm=true) 
	public void save() throws Exception {
		this.getPublicServiceIntroduceItem().setServiceName(this.getServiceName());
		this.getPublicServiceIntroduceItem().setServiceIntroduce(this.getServiceIntroduce());
		this.getPublicServiceIntroduceItem().setConstructTerm(this.getConstructTerm());
		this.getPublicServiceIntroduceItem().setBudget(this.getBudget());
		this.getPublicServiceIntroduceItem().setServiceSummary(this.getServiceSummary().getContents());
		this.getPublicServiceIntroduceItem().setServicePurpose(this.getServicePurpose());
		this.getPublicServiceIntroduceItem().setCivilianExpectation(this.getCivilianExpectation());
		this.getPublicServiceIntroduceItem().setPublicExpectation(this.getPublicExpectation());
		this.getPublicServiceIntroduceItem().setManagerAttachOrganization(this.getManagerAttachOrganization());
		this.getPublicServiceIntroduceItem().setManagerName(this.getManagerName());
		this.getPublicServiceIntroduceItem().setManagerNumber(this.getManagerNumber());
		this.getPublicServiceIntroduceItem().setManagerEmail(this.getManagerEmail());
		this.getPublicServiceIntroduceItem().syncToDatabaseMe();
		
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		this.getServiceSummary().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	
	@ServiceMethod(callByContent=true, needToConfirm=true, target=ServiceMethodContext.TARGET_APPEND) 
	public Object[] apply() throws Exception{
		// 표의 전체 아이템을 다시 가져와서 뿌려주고 닫는다.
		PublicServiceIntroduceTabInfo publicServiceIntroduceTabInfo = new PublicServiceIntroduceTabInfo();
		publicServiceIntroduceTabInfo.load();
		
		String thisTabId = this.getPublicServiceIntroduceItem().getTab();
		String tempArray[]  = thisTabId.split("_");
		// tab의 id는 2인데 어레이는 0부터 들어가니 - 1
		int parseTabId = Integer.parseInt(tempArray[1]) - 1;
		
		return new Object[] {new Remover(new ModalWindow()), new Refresh(publicServiceIntroduceTabInfo.getIntroList().get(parseTabId)) };
		
	}
	
	@ServiceMethod(callByContent=true, needToConfirm=true, target=ServiceMethodContext.TARGET_APPEND) 
	public Object[] delete() throws Exception{
		PublicServiceIntroduceItem publicServiceIntroduceItem = new PublicServiceIntroduceItem();
		publicServiceIntroduceItem.setItemId(this.getPublicServiceIntroduceItem().getItemId());
		publicServiceIntroduceItem.delete();
		
		// 삭제하고 바로 적용 해야한다.
		PublicServiceIntroduceTabInfo publicServiceIntroduceTabInfo = new PublicServiceIntroduceTabInfo();
		publicServiceIntroduceTabInfo.load();
		
		String thisTabId = this.getPublicServiceIntroduceItem().getTab();
		String tempArray[]  = thisTabId.split("_");
		// tab의 id는 2인데 어레이는 0부터 들어가니 - 1
		int parseTabId = Integer.parseInt(tempArray[1]) - 1;
		
		return new Object[] {new Remover(new ModalWindow()), new Refresh(publicServiceIntroduceTabInfo.getIntroList().get(parseTabId)) };
		
	}
	
	public void load(PublicServiceIntroduceDetailList list) {
		this.setServiceName(this.getPublicServiceIntroduceItem().getServiceName());
		this.setServiceIntroduce(this.getPublicServiceIntroduceItem().getServiceIntroduce());
		this.setConstructTerm(this.getPublicServiceIntroduceItem().getConstructTerm());
		this.setBudget(this.getPublicServiceIntroduceItem().getBudget());
		this.getServiceSummary().setContents(this.getPublicServiceIntroduceItem().getServiceSummary());
		this.setServicePurpose(this.getPublicServiceIntroduceItem().getServicePurpose());
		this.setCivilianExpectation(this.getPublicServiceIntroduceItem().getCivilianExpectation());
		this.setPublicExpectation(this.getPublicServiceIntroduceItem().getPublicExpectation());
		this.setManagerAttachOrganization(this.getPublicServiceIntroduceItem().getManagerAttachOrganization());
		this.setManagerName(this.getPublicServiceIntroduceItem().getManagerName());
		this.setManagerNumber(this.getPublicServiceIntroduceItem().getManagerNumber());
		this.setManagerEmail(this.getPublicServiceIntroduceItem().getManagerEmail());
		this.setPublicServiceIntroduceDetailList(list);
	}
}
