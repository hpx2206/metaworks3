package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;

public class ConsultingServiceChoicePanel {
	
	ConsultingServiceChoiceList serviceChoicePanelList;
		public ConsultingServiceChoiceList getServiceChoicePanelList() {
			return serviceChoicePanelList;
		}
		public void setServiceChoicePanelList(
				ConsultingServiceChoiceList serviceChoicePanelList) {
			this.serviceChoicePanelList = serviceChoicePanelList;
		}

	@AutowiredFromClient
	public Session session;
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public void load() {
	
		if(serviceChoicePanelList == null) {
			serviceChoicePanelList = new ConsultingServiceChoiceList();
		}
	
		serviceChoicePanelList.session = session;
		serviceChoicePanelList.setSelfTest("셀프 테스트 서비스");
		serviceChoicePanelList.setRegionMatching("국가 매칭 서비스");
	
	}
}
