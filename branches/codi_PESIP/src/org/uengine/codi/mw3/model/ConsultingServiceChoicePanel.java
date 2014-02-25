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
	
	// 나중에 로드할 것이 잇다면 여기서 로드해야함
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public void load() {
	
		if(serviceChoicePanelList == null) {
			serviceChoicePanelList = new ConsultingServiceChoiceList();
		}
	
		serviceChoicePanelList.setSelfTest("aa");
		serviceChoicePanelList.setRegionMatching("bb");
	
	}
}
