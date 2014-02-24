package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.CategoryTitle;

public class PublicServiceIntroduceTabPanel {
	
	final static String TAB = "tab";
	
	ArrayList<PublicServiceIntroduce> introList;
		public ArrayList<PublicServiceIntroduce> getIntroList() {
			return introList;
		}
		public void setIntroList(ArrayList<PublicServiceIntroduce> introList) {
			this.introList = introList;
		}
		
	public void load() throws Exception {
		
		IPublicServiceIntroduceCode iPublicServiceIntroduceCode = new PublicServiceIntroduceCode();
		iPublicServiceIntroduceCode = iPublicServiceIntroduceCode.loadTabs();
		
		if(introList == null)
			introList = new ArrayList<PublicServiceIntroduce>();
		
		while(iPublicServiceIntroduceCode.next()) {
			PublicServiceIntroduce publicServiceIntroduce = new PublicServiceIntroduce();
			publicServiceIntroduce.setTabName(iPublicServiceIntroduceCode.getName());
			publicServiceIntroduce.setId(iPublicServiceIntroduceCode.getId());
			this.getIntroList().add(publicServiceIntroduce.loadChart(iPublicServiceIntroduceCode.getId()));
			
		}
	}
	
	// TablePanel은 Tab 추가 메서드가 존재해야 한다.
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="$addTab")
	public ModalWindow addTab() throws Exception{
		CategoryTitle categoryTitle = new CategoryTitle();
		categoryTitle.setMetaworksContext(new MetaworksContext());
		categoryTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		categoryTitle.getMetaworksContext().setHow(TAB);
		
		return new ModalWindow(categoryTitle , 500, 200,  "$addTab");
	}
}
