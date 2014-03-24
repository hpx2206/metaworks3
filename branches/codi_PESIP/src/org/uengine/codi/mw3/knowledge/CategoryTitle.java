package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.IPublicServiceIntroduceCode;
import org.uengine.codi.mw3.model.PublicServiceIntroduce;
import org.uengine.codi.mw3.model.PublicServiceIntroduceCode;
import org.uengine.codi.mw3.model.PublicServiceIntroduceItem;
import org.uengine.codi.mw3.model.PublicServiceIntroduceTab;
import org.uengine.codi.mw3.model.PublicServiceIntroduceTabInfo;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.UEngineException;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
options={"fieldOrder"}, values={"contentName"})
public class CategoryTitle  implements ContextAware{
	
	final static String SECTOR = "sector";
	final static String SERVICE = "service";
	final static String CONTENT = "content";
	final static String TAB = "tab";
	
	final static String ADD = "add";
	final static String TAB_MODIFY = "tab_modify";
	final static String CODE_MODIFY = "code_modify";
	
	public CategoryTitle(){
		setMetaworksContext(new MetaworksContext());
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	String serviceId;
		@Hidden
		public String getServiceId() {
			return serviceId;
		}
		public void setServiceId(String serviceId) {
			this.serviceId = serviceId;
		}
		
	String sectorId;
		@Hidden
		public String getSectorId() {
			return sectorId;
		}
		public void setSectorId(String sectorId) {
			this.sectorId = sectorId;
		}

	String contentName;
		@Face(displayName="$contentName")
		@Available(how={SECTOR, SERVICE, CONTENT, TAB, TAB_MODIFY, CODE_MODIFY})
		public String getContentName() {
			return contentName;
		}
		public void setContentName(String contentName) {
			this.contentName = contentName;
		}
		
	String tab;
		public String getTab() {
			return tab;
		}
		public void setTab(String tab) {
			this.tab = tab;
		}

	@AutowiredFromClient
	transient public Session session;
	
	@Face(displayName="$Create")
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] save() throws Exception{
		
		if(MetaworksContext.WHEN_NEW.equals(this.getMetaworksContext().getWhen())) {
			// sector save시 service의 size가 필요함. 항상 Tab에 의존해야하므로 인자로 전부 Tab이 들어가서 id가 된다.
			if(SECTOR.equals(this.getMetaworksContext().getHow())) {
				PublicServiceIntroduceCode publicServiceIntroduceCode = new PublicServiceIntroduceCode();
				publicServiceIntroduceCode.addService(this.getContentName(), this.getTab());
				
				PublicServiceIntroduce parentPublicServiceIntroduce = new PublicServiceIntroduce();
				parentPublicServiceIntroduce.setId(this.getTab());
				
				return new Object[] { new Remover(new ModalWindow()), new ToAppend(parentPublicServiceIntroduce, publicServiceIntroduceCode)};
				
			// service save시 sector의 size가 필요함. 항상 Tab에 의존해야하므로 인자로 전부 Tab이 들어가서 id가 된다.
			} else if (SERVICE.equals(this.getMetaworksContext().getHow())){
				PublicServiceIntroduceCode publicServiceIntroduceCode = new PublicServiceIntroduceCode();
				publicServiceIntroduceCode.addSector(this.getContentName(), this.getTab());
				
				PublicServiceIntroduce parentPublicServiceIntroduce = new PublicServiceIntroduce();
				parentPublicServiceIntroduce.setId(this.getTab());
				
				return new Object[] { new Remover(new ModalWindow()), new ToAppend(parentPublicServiceIntroduce, publicServiceIntroduceCode)};
			
			// tab도 추가되어야 하며 tab이 추가되면 tab 안의 내용들을 DB 에서 가져와야 한다.
			// 그리고 tab 자체를 toAppend
			// 그런데 toAppend 할 것이 두개다. code와 introduce
			} else if(TAB.equals(this.getMetaworksContext().getHow())) {
				// tab이 추가되기 전에 중복된 이름의 탭은 exception
				PublicServiceIntroduceCode publicServiceIntroduceCode = new PublicServiceIntroduceCode();
				IPublicServiceIntroduceCode iPublicServiceIntroduceCode = publicServiceIntroduceCode.loadTabs();
				
				while(iPublicServiceIntroduceCode.next()) {
					if(this.getContentName().equals(iPublicServiceIntroduceCode.getName())) {
						throw new UEngineException("$tabError");
					}
				}
				// 그 후에 addTab 하고 flush				
				publicServiceIntroduceCode.addTab(this.getContentName());
				publicServiceIntroduceCode.flushDatabaseMe();
				// 최신의 tabId를 알아낸다. 위에서 flush를 했기때문에 마지막에 추가된 tab의 id를 알아내는 과정이다.
				String currentLastTabId = null;
				iPublicServiceIntroduceCode = publicServiceIntroduceCode.findCurrentLastTab();
				while(iPublicServiceIntroduceCode.next()) {
					currentLastTabId = iPublicServiceIntroduceCode.getId();
				}
				// tab li를 그려주기 위한 작업.
				PublicServiceIntroduceTab publicServiceIntroduceTab = new PublicServiceIntroduceTab();
				publicServiceIntroduceTab.setId(currentLastTabId);
				publicServiceIntroduceTab.setName(this.getContentName());
				
				// introduce div를 그려주기 위한 작업. introduce의 id에 tab이름 셋팅
				PublicServiceIntroduce publicServiceIntroduce = new PublicServiceIntroduce();
				publicServiceIntroduce = publicServiceIntroduce.loadChart(currentLastTabId);
				publicServiceIntroduce.setId(currentLastTabId);
				publicServiceIntroduce.setTabName(this.getContentName());
				
				PublicServiceIntroduceTabInfo publicServiceIntroduceTabInfo = new PublicServiceIntroduceTabInfo();
				publicServiceIntroduceTabInfo.load();
				
				// 반드시 순서를 지켜야 한다. 
				Object[] returnObject = new Object[3];
				returnObject[0] = new Remover(new ModalWindow());
				// 진짜 tab만 붙이는 객체 ul의 li
				returnObject[1] = new ToAppend(publicServiceIntroduceTabInfo, publicServiceIntroduceTab);
				// tab이랑 연결된 진짜 객체 ArrayList<publicServiceIntroduce> 에 넣을 publicServiceIntroduce 객체 div
				returnObject[2] = new ToAppend(publicServiceIntroduceTabInfo, publicServiceIntroduce);
				
				return returnObject;
				
			// 섹터도 아니고 서비스도 아니고 탭도 아니고.. 즉 Content 내용 추가 일 때 (item)
			} else {
				PublicServiceIntroduceItem publicServiceIntroduceItem = new PublicServiceIntroduceItem();
				
				publicServiceIntroduceItem.setItemId(Long.valueOf(publicServiceIntroduceItem.createNewNotiSettingId()));
				// contentName은 중복될 수 없다. contentName을 전부 불러와서 비교하면서 체크
				ArrayList<String> contentNameList = new ArrayList<String>();
				contentNameList = publicServiceIntroduceItem.findContentName(this.getTab(), this.getSectorId(), this.getServiceId());
				
				// 검사하여 contentName을 save하자.
				for(int i = 0; i < contentNameList.size(); i++) {
					// contentNameList의 i번째가 null 이거나 this의 컨텐트 이름과 i번째의 컨텐트 이름이 같다면 exception 
					if( (contentNameList.get(i) == null) || (this.getContentName().equals(contentNameList.get(i))))
						throw new UEngineException("$contentError");
				}
					
				publicServiceIntroduceItem.setServiceName(this.getContentName());
				publicServiceIntroduceItem.setSectorId(this.getSectorId());
				publicServiceIntroduceItem.setServiceId(this.getServiceId());
				// tab id를 통해 item 불러와야 해서 여기도 getTab에 의존..
				publicServiceIntroduceItem.setTab(this.getTab());
				publicServiceIntroduceItem.setProfileNo(Long.valueOf(publicServiceIntroduceItem.loadItem(this.getTab()).size() + 1));
				publicServiceIntroduceItem.getMetaworksContext().setWhen(ADD);
				
				publicServiceIntroduceItem.addContent();
	
				PublicServiceIntroduce parentPublicServiceIntroduce = new PublicServiceIntroduce();
				parentPublicServiceIntroduce.setId(this.getTab());
	
				return new Object[] { new Remover(new ModalWindow()), new ToAppend(parentPublicServiceIntroduce, publicServiceIntroduceItem)};
			}
		
		// meatworksContenxt가 new가 아니면. 즉 edit (tab의 수정에만 쓰임 수정의 메타웍스컨텍스트.when.how는 MODIFY)
		// tab name을 db를 통해 수정하고 화면을 다시 그림
		} else {
			
			// tab modify
			if(PublicServiceIntroduceTab.TAB_MODIFY.equals(this.getMetaworksContext().getHow())) {
				PublicServiceIntroduceCode publicServiceIntroduceCode = new PublicServiceIntroduceCode();
				publicServiceIntroduceCode.setId(this.getTab());
				
				IPublicServiceIntroduceCode iPublicServiceIntroduceCode = publicServiceIntroduceCode.findTabInfo();
				while(iPublicServiceIntroduceCode.next()) {
					publicServiceIntroduceCode.setName(this.getContentName());
					publicServiceIntroduceCode.setParentId(iPublicServiceIntroduceCode.getParentId());
					publicServiceIntroduceCode.setSequence(iPublicServiceIntroduceCode.getSequence());
				}
				publicServiceIntroduceCode.modifyTab();
				publicServiceIntroduceCode.flushDatabaseMe();
				
				PublicServiceIntroduceTabInfo publicServiceIntroduceTabInfo = new PublicServiceIntroduceTabInfo();
				publicServiceIntroduceTabInfo.load();
				
				String thisTabId = this.getTab();
				String tempArray[]  = thisTabId.split("_");
				// tab의 id는 2인데 어레이는 0부터 들어가니 - 1
				int parseTabId = Integer.parseInt(tempArray[1]) - 1;
				
				PublicServiceIntroduceTab publicServiceIntroduceTab = new PublicServiceIntroduceTab();
				publicServiceIntroduceTab = publicServiceIntroduceTabInfo.getPublicServiceIntroduceTab().get(parseTabId);
				
				return new Object[] { new Remover(new ModalWindow()), new Refresh(publicServiceIntroduceTab)};
				
			// code modify
			} else {
				PublicServiceIntroduceCode publicServiceIntroduceCode = new PublicServiceIntroduceCode();
				
				if(this.getSectorId() != null) {
					publicServiceIntroduceCode.setId(this.getSectorId());
					IPublicServiceIntroduceCode iPublicServiceIntroduceCode = publicServiceIntroduceCode.findSector();
					
					while(iPublicServiceIntroduceCode.next()) {
						publicServiceIntroduceCode.setName(this.getContentName());
						publicServiceIntroduceCode.setParentId(iPublicServiceIntroduceCode.getParentId());
						publicServiceIntroduceCode.setSequence(iPublicServiceIntroduceCode.getSequence());
						publicServiceIntroduceCode.setTab(this.getTab());
					}
					
				} else {
					publicServiceIntroduceCode.setId(this.getServiceId());
					IPublicServiceIntroduceCode iPublicServiceIntroduceCode = publicServiceIntroduceCode.findService();
					
					while(iPublicServiceIntroduceCode.next()) {
						publicServiceIntroduceCode.setName(this.getContentName());
						publicServiceIntroduceCode.setParentId(iPublicServiceIntroduceCode.getParentId());
						publicServiceIntroduceCode.setSequence(iPublicServiceIntroduceCode.getSequence());
						publicServiceIntroduceCode.setTab(this.getTab());
					}
					
				}
				
				publicServiceIntroduceCode.syncToDatabaseMe();
				publicServiceIntroduceCode.flushDatabaseMe();
				
				PublicServiceIntroduce publicServiceIntroduce = new PublicServiceIntroduce();
				publicServiceIntroduce.setId(this.getTab());
				publicServiceIntroduce.loadChart(this.getTab());
				
				return new Object[] { new Remover(new ModalWindow()), new Refresh(publicServiceIntroduce)};
				
			}
				
			
		}
		
	}
	
}