package org.uengine.codi.mw3.model;

import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToEvent;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.admin.WebEditor;
import org.uengine.kernel.UEngineException;

public class PublicServiceIntroduceViewPanel {
	
	public PublicServiceIntroduceViewPanel() {
		metaworksContext = new MetaworksContext();
		
		webEditor = new WebEditor();
		webEditor.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
	}
	
	Long itemId;
		public Long getItemId() {
			return itemId;
		}
		public void setItemId(Long itemId) {
			this.itemId = itemId;
		}
		
	String itemSectorId;
		public String getItemSectorId() {
			return itemSectorId;
		}
		public void setItemSectorId(String itemSectorId) {
			this.itemSectorId = itemSectorId;
		}

	String itemServiceId;
		public String getItemServiceId() {
			return itemServiceId;
		}
		public void setItemServiceId(String itemServiceId) {
			this.itemServiceId = itemServiceId;
		}
		
	String itemTabId;
		public String getItemTabId() {
			return itemTabId;
		}
		public void setItemTabId(String itemTabId) {
			this.itemTabId = itemTabId;
		}

	String itemName;
	@Face(ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"1", "60"})
		public String getItemName() {
			return itemName;
		}
		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

	String itemDescription;
	@Face(ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"7", "60"})
		public String getItemDescription() {
			return itemDescription;
		}
		public void setItemDescription(String itemDescription) {
			this.itemDescription = itemDescription;
		}

	Long itemProfileNo;
	@Face(ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"1", "20"})
		public Long getItemProfileNo() {
			return itemProfileNo;
		}
		public void setItemProfileNo(Long itemProfileNo) {
			this.itemProfileNo = itemProfileNo;
		}
		
	String itemBusinessMotivation;
	@Face(ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"7", "60"})
		public String getItemBusinessMotivation() {
			return itemBusinessMotivation;
		}
		public void setItemBusinessMotivation(String itemBusinessMotivation) {
			this.itemBusinessMotivation = itemBusinessMotivation;
		}

	String itemGovExpectedEffect;
	@Face(ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"7", "60"})
		public String getItemGovExpectedEffect() {
			return itemGovExpectedEffect;
		}
		public void setItemGovExpectedEffect(String itemGovExpectedEffect) {
			this.itemGovExpectedEffect = itemGovExpectedEffect;
		}
		
	String itemPriExpectedEffect;
	@Face(ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"7", "60"})
		public String getItemPriExpectedEffect() {
			return itemPriExpectedEffect;
		}
		public void setItemPriExpectedEffect(String itemPriExpectedEffect) {
			this.itemPriExpectedEffect = itemPriExpectedEffect;
		}

	String itemCSF;
	@Face(ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"7", "60"})
		public String getItemCSF() {
			return itemCSF;
		}
		public void setItemCSF(String itemCSF) {
			this.itemCSF = itemCSF;
		}
		
	String itemModelConcept;
		public String getItemModelConcept() {
			return itemModelConcept;
		}
		public void setItemModelConcept(String itemModelConcept) {
			this.itemModelConcept = itemModelConcept;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	WebEditor webEditor;
		public WebEditor getWebEditor() {
			return webEditor;
		}
		public void setWebEditor(WebEditor webEditor) {
			this.webEditor = webEditor;
		}
		
		
	// 수정은 MetaworksContext를 view -> edit 로 바꾸는 작업
	// viewPanel 자체도 수정모드로 변경되어야 하고
	@ServiceMethod(callByContent=true)
	public void modify() {
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		this.getWebEditor().getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
	// MetaworksContext가 edit 상태라면 Save를 한다.
	// 그리고 다시 메타웍스컨텍스트를 view 상태로..
	@ServiceMethod(callByContent=true) 
	public void save() throws Exception {
		PublicServiceIntroduceItem publicServiceIntroduceItem = new PublicServiceIntroduceItem();
		publicServiceIntroduceItem.setItemId(this.getItemId());
		publicServiceIntroduceItem.setTab(this.getItemTabId());
		publicServiceIntroduceItem.setSectorId(this.getItemSectorId());
		publicServiceIntroduceItem.setServiceId(this.getItemServiceId());
		publicServiceIntroduceItem.setContentName(this.getItemName());
		publicServiceIntroduceItem.setContentDescription(this.getItemDescription());
		publicServiceIntroduceItem.setProfileNo(this.getItemProfileNo());
		// Model Concept webEditor의 내용이기 때문에 webEditor의 내용을 가져와서 itemModelConcept 넣는다.
		if(webEditor != null) {
			this.setItemModelConcept(webEditor.getContents());
			publicServiceIntroduceItem.setModelConcept(this.getItemModelConcept());
			
		} else {
			new UEngineException("WebEditor ERROR!!");
		}
		
		publicServiceIntroduceItem.setBusinessMotivation(this.getItemBusinessMotivation());
		publicServiceIntroduceItem.setGovExpectedEffect(this.getItemGovExpectedEffect());
		publicServiceIntroduceItem.setPriExpectedEffect(this.getItemPriExpectedEffect());
		publicServiceIntroduceItem.setCSF(this.getItemCSF());
		publicServiceIntroduceItem.save();
		
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		this.getWebEditor().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND) 
	public Object[] apply() throws Exception{
		// 표의 전체 아이템을 다시 가져와서 뿌려주고 닫는다.
		PublicServiceIntroduceTabInfo publicServiceIntroduceTabInfo = new PublicServiceIntroduceTabInfo();
		publicServiceIntroduceTabInfo.load();
		
		String thisTabId = this.getItemTabId();
		String tempArray[]  = thisTabId.split("_");
		// tab의 id는 2인데 어레이는 0부터 들어가니 - 1
		int parseTabId = Integer.parseInt(tempArray[1]) - 1;
		
		return new Object[] {new Remover(new ModalWindow()), new Refresh(publicServiceIntroduceTabInfo.getIntroList().get(parseTabId)) };
		
	}
	
	@ServiceMethod(callByContent=true, needToConfirm=true, target=ServiceMethodContext.TARGET_APPEND) 
	public Object[] delete() throws Exception{
		PublicServiceIntroduceItem publicServiceIntroduceItem = new PublicServiceIntroduceItem();
		publicServiceIntroduceItem.setItemId(this.getItemId());
		publicServiceIntroduceItem.delete();
		
		// 삭제하고 바로 적용 해야한다.
		PublicServiceIntroduceTabInfo publicServiceIntroduceTabInfo = new PublicServiceIntroduceTabInfo();
		publicServiceIntroduceTabInfo.load();
		
		String thisTabId = this.getItemTabId();
		String tempArray[]  = thisTabId.split("_");
		// tab의 id는 2인데 어레이는 0부터 들어가니 - 1
		int parseTabId = Integer.parseInt(tempArray[1]) - 1;
		
		
		return new Object[] {new Remover(new ModalWindow()), new Refresh(publicServiceIntroduceTabInfo.getIntroList().get(parseTabId)) };
		
	}
}
