package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
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
		
	RegionMatchingChart regionMatchingChart;
		public RegionMatchingChart getRegionMatchingChart() {
			return regionMatchingChart;
		}
		public void setRegionMatchingChart(RegionMatchingChart regionMatchingChart) {
			this.regionMatchingChart = regionMatchingChart;
		}

	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object loadSelfTest() {
			return regionMatching;
			
	}
		
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow loadRegionMatching() {
		ModalWindow modalWindow = new ModalWindow();
		if(regionMatchingChart == null){
			regionMatchingChart = new RegionMatchingChart();
		}
		
//		ModalWindow modalWindow = new ModalWindow(regionMatchingChart, 700, 400, "RegionMatching");
		
		modalWindow.setWidth(900);
		modalWindow.setHeight(600);
		modalWindow.setPanel(regionMatchingChart);
		return modalWindow;
			
	}		
}
