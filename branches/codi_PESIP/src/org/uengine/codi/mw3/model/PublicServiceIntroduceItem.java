package org.uengine.codi.mw3.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.DAOFactory;
import org.metaworks.dao.Database;
import org.metaworks.dao.KeyGeneratorDAO;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.CategoryTitle;

public class PublicServiceIntroduceItem extends Database<IPublicServiceIntroduceItem> implements IPublicServiceIntroduceItem{
	
	final static String CONTENT = "content";
	
	Long itemId;
		@Id
		public Long getItemId() {
			return itemId;
		}
		public void setItemId(Long itemId) {
			this.itemId = itemId;
		}

	String tab;
		public String getTab() {
			return tab;
		}
		public void setTab(String tab) {
			this.tab = tab;
		}
		
	String serviceId;
		public String getServiceId() {
			return serviceId;
		}
		public void setServiceId(String serviceId) {
			this.serviceId = serviceId;
		}
		
	String sectorId;
		public String getSectorId() {
			return sectorId;
		}
		public void setSectorId(String sectorId) {
			this.sectorId = sectorId;
		}
		
	Long profileNo;
		public Long getProfileNo() {
			return profileNo;
		}
		public void setProfileNo(Long profileNo) {
			this.profileNo = profileNo;
		}
		
	String serviceName;
		public String getServiceName() {
			return serviceName;
		}
		public void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}
		
	String cutServiceName;
		public String getCutServiceName() {
			return cutServiceName;
		}
		public void setCutServiceName(String cutServiceName) {
			this.cutServiceName = cutServiceName;
		}

	String serviceIntroduce;
		public String getServiceIntroduce() {
			return serviceIntroduce;
		}
		public void setServiceIntroduce(String serviceIntroduce) {
			this.serviceIntroduce = serviceIntroduce;
		}

	String constructTerm;
		public String getConstructTerm() {
			return constructTerm;
		}
		public void setConstructTerm(String constructTerm) {
			this.constructTerm = constructTerm;
		}

	String budget;
		public String getBudget() {
			return budget;
		}
		public void setBudget(String budget) {
			this.budget = budget;
		}

	String serviceSummary;
		public String getServiceSummary() {
			return serviceSummary;
		}
		public void setServiceSummary(String serviceSummary) {
			this.serviceSummary = serviceSummary;
		}
		
	String servicePurpose;
		public String getServicePurpose() {
			return servicePurpose;
		}
		public void setServicePurpose(String servicePurpose) {
			this.servicePurpose = servicePurpose;
		}
		
	String publicExpectation;
		public String getPublicExpectation() {
			return publicExpectation;
		}
		public void setPublicExpectation(String publicExpectation) {
			this.publicExpectation = publicExpectation;
		}

	String civilianExpectation;
		public String getCivilianExpectation() {
			return civilianExpectation;
		}
		public void setCivilianExpectation(String civilianExpectation) {
			this.civilianExpectation = civilianExpectation;
		}

	String managerAttachOrganization;
		public String getManagerAttachOrganization() {
			return managerAttachOrganization;
		}
		public void setManagerAttachOrganization(String managerAttachOrganization) {
			this.managerAttachOrganization = managerAttachOrganization;
		}

	String managerName;
		public String getManagerName() {
			return managerName;
		}
		public void setManagerName(String managerName) {
			this.managerName = managerName;
		}

	String managerNumber;
		public String getManagerNumber() {
			return managerNumber;
		}
		public void setManagerNumber(String managerNumber) {
			this.managerNumber = managerNumber;
		}

	String managerEmail;
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
		
	public IPublicServiceIntroduceItem loadItem(String codeId) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from public_introduce_item");
		sb.append(" where tab=?tab");
		
		IPublicServiceIntroduceItem items = (IPublicServiceIntroduceItem) sql(IPublicServiceIntroduceItem.class, sb.toString());
		items.setTab(codeId);
		items.select();
		
		return items;
	}
	
	public IPublicServiceIntroduceItem loadSelectedItem(String codeId, String sectorId, String serviceId, Long itemId) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from public_introduce_item");
		sb.append(" where tab=?tab");
		sb.append(" and sectorId=?sectorId");
		sb.append(" and serviceId=?serviceId");
		sb.append(" and itemId=?itemId");
		
		IPublicServiceIntroduceItem item = (IPublicServiceIntroduceItem) sql(IPublicServiceIntroduceItem.class, sb.toString());
		item.setTab(codeId);
		item.setSectorId(sectorId);
		item.setServiceId(serviceId);
		item.setItemId(itemId);
		item.select();
		
		return item;
	}
	
	public void addContent() throws Exception{
		this.createDatabaseMe();
	}
	
	@ServiceMethod(callByContent=true, mouseBinding=ServiceMethodContext.MOUSEBINDING_LEFTCLICK, bindingHidden=true, target=ServiceMethodContext.TARGET_POPUP)
	@Hidden
	public Object[] showDetail() throws Exception{
		PublicServiceIntroduceViewPanel publicServiceIntroduceViewPanel = new PublicServiceIntroduceViewPanel();
		// showDetail을 부르자 마자는 우선 view mode
		publicServiceIntroduceViewPanel.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		// viewPanel이 객체 단위가 아니므로 DB에 연결하여 각각의 값을 알아내어 set
		IPublicServiceIntroduceItem iPublicServiceIntroduceItem = new PublicServiceIntroduceItem();
		iPublicServiceIntroduceItem = loadSelectedItem(this.getTab(), this.getSectorId(), this.getServiceId(), this.getItemId());

		if(publicServiceIntroduceViewPanel.getPublicServiceIntroduceItem() == null) {
			publicServiceIntroduceViewPanel.setPublicServiceIntroduceItem(new PublicServiceIntroduceItem());
		}
		
		while(iPublicServiceIntroduceItem.next()) {
			PublicServiceIntroduceItem item = publicServiceIntroduceViewPanel.getPublicServiceIntroduceItem();
			item.copyFrom(iPublicServiceIntroduceItem);
			publicServiceIntroduceViewPanel.setPublicServiceIntroduceItem(item);
		}
		
		// detail 부분 아래 서비스랑 구축부분 불러오는 부분.
		PublicServiceIntroduceService publicServiceIntroduceService = new PublicServiceIntroduceService();
		IPublicServiceIntroduceService iPublicServiceIntroduceService = publicServiceIntroduceService.loadService(this.getItemId());
		
	    PublicServiceIntroduceDetailList publicServiceIntroduceDetailList = new PublicServiceIntroduceDetailList();
		
		if(iPublicServiceIntroduceService.size() == 0) {
			publicServiceIntroduceDetailList.setServiceList(new ArrayList<PublicServiceIntroduceService>());
			publicServiceIntroduceDetailList.getServiceList().add(new PublicServiceIntroduceService());
			
		} else {
			publicServiceIntroduceDetailList.setServiceList(new ArrayList<PublicServiceIntroduceService>());
			while(iPublicServiceIntroduceService.next()) {
				PublicServiceIntroduceService service = new PublicServiceIntroduceService();
				service.copyFrom(iPublicServiceIntroduceService);
				publicServiceIntroduceDetailList.getServiceList().add(service);
			}
		}
		
		PublicServiceIntroduceConstruct publicServiceIntroduceConstruct = new PublicServiceIntroduceConstruct();
		IPublicServiceIntroduceConstruct iPublicServiceIntroduceConstruct = publicServiceIntroduceConstruct.loadConstruct(this.getItemId()); 
		
		if(iPublicServiceIntroduceConstruct.size() == 0) {
			publicServiceIntroduceDetailList.setConstructList(new ArrayList<PublicServiceIntroduceConstruct>());
			publicServiceIntroduceDetailList.getConstructList().add(new PublicServiceIntroduceConstruct());
			
		} else {
			while(iPublicServiceIntroduceConstruct.next()) {
				PublicServiceIntroduceConstruct construct = new PublicServiceIntroduceConstruct();
				construct.copyFrom(iPublicServiceIntroduceConstruct);
				publicServiceIntroduceDetailList.getConstructList().add(construct);
			}
		}
		
		publicServiceIntroduceViewPanel.load(publicServiceIntroduceDetailList);
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setTitle(this.getServiceName());
		modalWindow.setWidth(0);
		modalWindow.setHeight(0);
		modalWindow.setPanel(publicServiceIntroduceViewPanel);
		
		return new Object[] { modalWindow };
	}
	
	// sequence table에 생성하여 id 값을 +1씩 시켜주는 함수
	public int createNewNotiSettingId() throws Exception{
		Map options = new HashMap();
		options.put("useTableNameHeader", "false");
		options.put("onlySequenceTable", "true");
		  
		KeyGeneratorDAO kg = DAOFactory.getInstance(TransactionContext.getThreadLocalInstance()).createKeyGenerator("publicServiceItem", options);
		kg.select();
		kg.next();
		  
		Number number = kg.getKeyNumber();
		  
		return number.intValue();
	 }
	
	// content 는 item 그리고 Hidden이어야 한다. 보이진 않아야 함
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="$addContent")
	@Hidden
	public ModalWindow addItem() throws Exception{
		CategoryTitle categoryTitle = new CategoryTitle();
		categoryTitle.setMetaworksContext(new MetaworksContext());
		categoryTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		categoryTitle.getMetaworksContext().setHow(CONTENT);
		categoryTitle.setSectorId(this.getSectorId());
		categoryTitle.setServiceId(this.getServiceId());
		categoryTitle.setTab(this.getTab());
		
		return new ModalWindow(categoryTitle , 500, 200,  "$addContent");
	}
	
	public void save() throws Exception {
		this.syncToDatabaseMe();
	}
	
	public ArrayList<String> findContentName(String codeId, String sectorId, String serviceId) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select serviceName");
		sb.append(" from public_introduce_item");
		sb.append(" where tab=?tab");
		sb.append(" and sectorId=?sectorId");
		sb.append(" and serviceId=?serviceId");
		
		IPublicServiceIntroduceItem contentNames = (IPublicServiceIntroduceItem) sql(IPublicServiceIntroduceItem.class, sb.toString());
		contentNames.setTab(codeId);
		contentNames.setSectorId(sectorId);
		contentNames.setServiceId(serviceId);
		contentNames.select();
		
		ArrayList<String> contentNameList = new ArrayList<String>();
		
		while(contentNames.next()) {
			contentNameList.add(contentNames.getServiceName());
		}
		
		return contentNameList;
	}
	
	public void delete() throws Exception {
		this.deleteDatabaseMe();
	}
	
	public void deleteByTab(String tabId) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("delete");
		sb.append(" from public_introduce_item");
		sb.append("	where tab = ?tab");
		
		IPublicServiceIntroduceItem items = (IPublicServiceIntroduceItem) sql(IPublicServiceIntroduceItem.class, sb.toString());
		items.setTab(tabId);
		items.update();
	}
	
	public void deleteBySector(String sectorId) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("delete");
		sb.append(" from public_introduce_item");
		sb.append("	where sectorId = ?sectorId");
		
		IPublicServiceIntroduceItem items = (IPublicServiceIntroduceItem) sql(IPublicServiceIntroduceItem.class, sb.toString());
		items.setSectorId(sectorId);
		items.update();
	}
	
	public void deleteByService(String serviceId) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("delete");
		sb.append(" from public_introduce_item");
		sb.append("	where serviceId = ?serviceId");
		
		IPublicServiceIntroduceItem items = (IPublicServiceIntroduceItem) sql(IPublicServiceIntroduceItem.class, sb.toString());
		items.setSectorId(serviceId);
		items.update();
	}
	
}
