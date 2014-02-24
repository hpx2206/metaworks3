package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
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
import org.uengine.codi.mw3.model.IPublicServiceIntroduceItem;
import org.uengine.codi.mw3.model.PublicServiceIntroduce;
import org.uengine.codi.mw3.model.PublicServiceIntroduceCode;
import org.uengine.codi.mw3.model.PublicServiceIntroduceItem;
import org.uengine.codi.mw3.model.PublicServiceIntroduceTabPanel;
import org.uengine.codi.mw3.model.PublicServiceIntroduceViewPanel;
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
		@Available(how={SECTOR, SERVICE, CONTENT, TAB})
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
			PublicServiceIntroduceCode publicServiceIntroduceCode = new PublicServiceIntroduceCode();
			publicServiceIntroduceCode.addTab(this.getContentName());
			publicServiceIntroduceCode.flushDatabaseMe();
			
			// 최신의 tabId를 알아낸다.
			String currentLastTabId = null;
			IPublicServiceIntroduceCode iPublicServiceIntroduceCode = publicServiceIntroduceCode.findCurrentLastTab();
			while(iPublicServiceIntroduceCode.next()) {
				currentLastTabId = iPublicServiceIntroduceCode.getId();
			}
			
			// introduce의 id에 tab이름 셋팅
			PublicServiceIntroduce parentPublicServiceIntroduce = new PublicServiceIntroduce();
			parentPublicServiceIntroduce = parentPublicServiceIntroduce.loadChart(currentLastTabId);
			parentPublicServiceIntroduce.setId(currentLastTabId);
			parentPublicServiceIntroduce.setTabName(this.contentName);
			
			return new Object[] { new Remover(new ModalWindow()), new ToAppend(new PublicServiceIntroduceTabPanel(), parentPublicServiceIntroduce)};
			
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
				
			publicServiceIntroduceItem.setContentName(this.getContentName());
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
	}
	
}