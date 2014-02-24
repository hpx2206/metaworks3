package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.CategoryTitle;

// wiki class
public class PublicServiceIntroduce {
	
	final static String SECTOR = "sector";
	final static String SERVICE = "service";
	
	final static String TABLE = "table";
	
	IPublicServiceIntroduceCode services;
		public IPublicServiceIntroduceCode getServices() {
			return services;
		}
		public void setServices(IPublicServiceIntroduceCode services) {
			this.services = services;
		}

	IPublicServiceIntroduceCode sectors;
		public IPublicServiceIntroduceCode getSectors() {
			return sectors;
		}
		public void setSectors(IPublicServiceIntroduceCode sectors) {
			this.sectors = sectors;
		}
	
	IPublicServiceIntroduceItem item;
		public IPublicServiceIntroduceItem getItem() {
			return item;
		}
		public void setItem(IPublicServiceIntroduceItem item) {
			this.item = item;
		}
		
	String tabName;
		public String getTabName() {
			return tabName;
		}
		public void setTabName(String tabName) {
			this.tabName = tabName;
		}
		
	String id;
		@Id
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	public PublicServiceIntroduce loadChart(String codeId) throws Exception {
		//sector, service load
		PublicServiceIntroduceCode publicServiceIntroduceCode = new PublicServiceIntroduceCode();
		this.setSectors(publicServiceIntroduceCode.loadSectorsByTab(codeId));
		this.setServices(publicServiceIntroduceCode.loadServicesByTab(codeId));
		
		//item load
		PublicServiceIntroduceItem publicServiceIntroduceItem = new PublicServiceIntroduceItem();
		this.setItem(publicServiceIntroduceItem.loadItem(codeId));
		this.getItem().getMetaworksContext().setWhen(TABLE);
		
		return this;
	}
	
	// row 는 sector
	@ServiceMethod(callByContent=true, inContextMenu=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="$addRow")
	public ModalWindow addRow() throws Exception{
		CategoryTitle categoryTitle = new CategoryTitle();
		categoryTitle.setMetaworksContext(new MetaworksContext());
		categoryTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		categoryTitle.getMetaworksContext().setHow(SECTOR);
		categoryTitle.setTab(this.getId());
		
		return new ModalWindow(categoryTitle , 500, 200,  "$addRow");
	}
	
	// column 은 service
	@ServiceMethod(callByContent=true, inContextMenu=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="$addColumn")
	public ModalWindow addColumn() throws Exception{
		CategoryTitle categoryTitle = new CategoryTitle();
		categoryTitle.setMetaworksContext(new MetaworksContext());
		categoryTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		categoryTitle.getMetaworksContext().setHow(SERVICE);
		categoryTitle.setTab(this.getId());
		
		return new ModalWindow(categoryTitle , 500, 200,  "$addColumn");
	}
}
