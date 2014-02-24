package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class ServiceChoicePanel {
	
	String selfTest;
		public String getSelfTest() {
			return selfTest;
		}
		public void setSelfTest(String selfTest) {
			this.selfTest = selfTest;
		}

	String regionMatching;
		public String getRegionMatching() {
			return regionMatching;
		}
		public void setRegionMatching(String regionMatching) {
			this.regionMatching = regionMatching;
		}

	@AutowiredFromClient
	public Session session;
	
	// 나중에 로드할 것이 잇다면 여기서 로드해야함
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object load() {
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setTitle("서비스 준비중입니다");
		modalWindow.setWidth(250);
		modalWindow.setHeight(50);
		
		return modalWindow;
	}
}
