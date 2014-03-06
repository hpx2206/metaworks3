package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;


public class ConsultingServiceChoiceList {
	
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
		
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow loadSelfTest() throws Exception {
		ModalWindow modalWindow = new ModalWindow();
		
		SelfTestSurveyPanel selfTestSurveyPanel = new SelfTestSurveyPanel();
		selfTestSurveyPanel.session = session;
		selfTestSurveyPanel.load();
		
		modalWindow.setTitle("셀프 테스트 설문조사");
		modalWindow.setWidth(900);
		modalWindow.setHeight(600);
		modalWindow.setPanel(selfTestSurveyPanel);
		
		return modalWindow;
			
	}	
		
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow loadRegionMatching() {
		ModalWindow modalWindow = new ModalWindow();
		
		RegionMatchingChart	regionMatchingChart = new RegionMatchingChart();
		modalWindow.setTitle("국가 매칭 서비스");
		modalWindow.setWidth(900);
		modalWindow.setHeight(600);
		modalWindow.setPanel(regionMatchingChart);
		
		return modalWindow;
			
	}
	
}
