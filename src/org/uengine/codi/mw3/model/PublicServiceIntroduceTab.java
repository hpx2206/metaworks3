package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.CategoryTitle;


public class PublicServiceIntroduceTab {
	
	public final static String TAB_MODIFY = "tab_modify";
	
	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	// tab 수정
	@ServiceMethod(callByContent=true, inContextMenu=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="$Modify")
	public ModalWindow modify() throws Exception{
		CategoryTitle categoryTitle = new CategoryTitle();
		categoryTitle.setMetaworksContext(new MetaworksContext());
		categoryTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		categoryTitle.getMetaworksContext().setHow(TAB_MODIFY);
		categoryTitle.setTab(this.getId());
		categoryTitle.setContentName(this.getName());
		
		return new ModalWindow(categoryTitle , 500, 200,  "$Modify");
	}
	
	// tab 삭제
	@ServiceMethod(callByContent=true, inContextMenu=true, needToConfirm=true, target=ServiceMethodContext.TARGET_APPEND) 
	@Face(displayName="$Delete")
	public Object delete() throws Exception{
		// tab은 순서대로 지워야한다. 가장 작은 Item -> Code -> Tab 순으로
		// item도 지워야 한다. tab을 기반으로 item을 삭제하므로
		PublicServiceIntroduceItem publicServiceIntroduceItem = new PublicServiceIntroduceItem();
		publicServiceIntroduceItem.deleteByTab(this.getId());
		
		// code도 지우고
		PublicServiceIntroduceCode publicServiceIntroduceCode = new PublicServiceIntroduceCode();
		publicServiceIntroduceCode.deleteByTab(this.getId());
		
		// tab도 지운다.
		publicServiceIntroduceCode.setId(this.getId());
		publicServiceIntroduceCode.deleteTab();
		
		// viewPanel에 적용(이는 곧 refresh)
		PublicServiceIntroducePanel publicServiceIntroducePanel = new PublicServiceIntroducePanel();
		publicServiceIntroducePanel.load();
		
		return new Refresh(publicServiceIntroducePanel);
	}
}
