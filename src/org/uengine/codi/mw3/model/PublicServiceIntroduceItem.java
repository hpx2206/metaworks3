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

	String contentName;
		public String getContentName() {
			return contentName;
		}
		public void setContentName(String contentName) {
			this.contentName = contentName;
		}

	String contentDescription;
		public String getContentDescription() {
			return contentDescription;
		}
		public void setContentDescription(String contentDescription) {
			this.contentDescription = contentDescription;
		}
		
	String govExpectedEffect;
		public String getGovExpectedEffect() {
			return govExpectedEffect;
		}
		public void setGovExpectedEffect(String govExpectedEffect) {
			this.govExpectedEffect = govExpectedEffect;
		}
		
	String priExpectedEffect;
		public String getPriExpectedEffect() {
			return priExpectedEffect;
		}
		public void setPriExpectedEffect(String priExpectedEffect) {
			this.priExpectedEffect = priExpectedEffect;
		}

	String businessMotivation;
		public String getBusinessMotivation() {
			return businessMotivation;
		}
		public void setBusinessMotivation(String businessMotivation) {
			this.businessMotivation = businessMotivation;
		}
	
	String modelConcept;
		public String getModelConcept() {
			return modelConcept;
		}
		public void setModelConcept(String modelConcept) {
			this.modelConcept = modelConcept;
		}

	String CSF;
		public String getCSF() {
			return CSF;
		}
		public void setCSF(String cSF) {
			CSF = cSF;
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
	
	public IPublicServiceIntroduceItem loadSelectedItem(String codeId, String sectorId, String serviceId, String contentName) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from public_introduce_item");
		sb.append(" where tab=?tab");
		sb.append(" and sectorId=?sectorId");
		sb.append(" and serviceId=?serviceId");
		sb.append(" and contentName=?contentName");
		
		IPublicServiceIntroduceItem item = (IPublicServiceIntroduceItem) sql(IPublicServiceIntroduceItem.class, sb.toString());
		item.setTab(codeId);
		item.setSectorId(sectorId);
		item.setServiceId(serviceId);
		item.setContentName(contentName);
		item.select();
		
		return item;
	}
	
	public void addContent() throws Exception{
		this.createDatabaseMe();
	}
	
	@ServiceMethod(callByContent=true, mouseBinding=ServiceMethodContext.MOUSEBINDING_LEFTCLICK, bindingHidden=true, target=ServiceMethodContext.TARGET_POPUP)
	@Hidden
	public Object[] showDetail() throws Exception{
		ModalWindow modalWindow = new ModalWindow();
		PublicServiceIntroduceViewPanel publicServiceIntroduceViewPanel = new PublicServiceIntroduceViewPanel();
		// showDetail을 부르자 마자는 우선 view mode
		publicServiceIntroduceViewPanel.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		// viewPanel이 객체 단위가 아니므로 DB에 연결하여 각각의 값을 알아내어 set
		IPublicServiceIntroduceItem iPublicServiceIntroduceItem = new PublicServiceIntroduceItem();
		iPublicServiceIntroduceItem = loadSelectedItem(this.getTab(), this.getSectorId(), this.getServiceId(), this.getContentName());
		
		while(iPublicServiceIntroduceItem.next()) {
			publicServiceIntroduceViewPanel.setItemId(iPublicServiceIntroduceItem.getItemId());
			publicServiceIntroduceViewPanel.setItemSectorId(iPublicServiceIntroduceItem.getSectorId());
			publicServiceIntroduceViewPanel.setItemServiceId(iPublicServiceIntroduceItem.getServiceId());
			publicServiceIntroduceViewPanel.setItemTabId(iPublicServiceIntroduceItem.getTab());
			publicServiceIntroduceViewPanel.setItemName(iPublicServiceIntroduceItem.getContentName());
			publicServiceIntroduceViewPanel.setItemProfileNo(iPublicServiceIntroduceItem.getProfileNo());
			// null 이면 MetaworksContext edit 이 안먹는다.. "" 주어서 해결하자.
			if(iPublicServiceIntroduceItem.getContentDescription() == null) { 
				publicServiceIntroduceViewPanel.setItemDescription("");
				
			} else {
				publicServiceIntroduceViewPanel.setItemDescription(iPublicServiceIntroduceItem.getContentDescription());
			
			}
			// motivation도 마찬가지.
			if(iPublicServiceIntroduceItem.getBusinessMotivation() == null) {
				publicServiceIntroduceViewPanel.setItemBusinessMotivation("");
			
			} else {
				publicServiceIntroduceViewPanel.setItemBusinessMotivation(iPublicServiceIntroduceItem.getBusinessMotivation());
			
			}
			// effected도 마찬가지.
			if(iPublicServiceIntroduceItem.getGovExpectedEffect() == null) {
				publicServiceIntroduceViewPanel.setItemGovExpectedEffect("");
			
			} else {
				publicServiceIntroduceViewPanel.setItemGovExpectedEffect(iPublicServiceIntroduceItem.getGovExpectedEffect());
			
			}
			
			if(iPublicServiceIntroduceItem.getPriExpectedEffect() == null) {
				publicServiceIntroduceViewPanel.setItemPriExpectedEffect("");
				
			} else {
				publicServiceIntroduceViewPanel.setItemPriExpectedEffect(iPublicServiceIntroduceItem.getPriExpectedEffect());
				
			}
			// CSF도 마찬가지.
			if(iPublicServiceIntroduceItem.getCSF() == null) {
				publicServiceIntroduceViewPanel.setItemCSF("");
			
			} else {
				publicServiceIntroduceViewPanel.setItemCSF(iPublicServiceIntroduceItem.getCSF());
			
			}
			
			// concept는 webEditor에다가 set
			if(iPublicServiceIntroduceItem.getModelConcept() == null) {
				publicServiceIntroduceViewPanel.setItemModelConcept("");
				
			} else {
				publicServiceIntroduceViewPanel.getWebEditor().setContents(iPublicServiceIntroduceItem.getModelConcept());
			}
			
		}	
		
		modalWindow.setTitle(this.getContentName());
		modalWindow.setWidth(1200);
		modalWindow.setHeight(700);
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
		sb.append("select contentName");
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
			contentNameList.add(contentNames.getContentName());
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
