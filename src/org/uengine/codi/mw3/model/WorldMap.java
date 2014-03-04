package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class WorldMap {
	
	String countrycode;
		public String getCountrycode() {
			return countrycode;
		}
		
		public void setCountrycode(String countrycode) {
			this.countrycode = countrycode;
		}
		
	String countryname;
		public String getCountryname() {
			return countryname;
		}
		
		public void setCountryname(String countryname) {
			this.countryname = countryname;
		}
	
	
//	IOrderInformation orderInformation;
//		public IOrderInformation getOrderInformation() {
//			return orderInformation;
//		}
//	
//		public void setOrderInformation(IOrderInformation orderInformation) {
//			this.orderInformation = orderInformation;
//		}

	IOrderInformation distinctOrderInformation;
		public IOrderInformation getDistinctOrderInformation() {
			return distinctOrderInformation;
		}
	
		public void setDistinctOrderInformation(
				IOrderInformation distinctOrderInformation) {
			this.distinctOrderInformation = distinctOrderInformation;
		}

		WorldMapChart	worldMapChart;
		public WorldMapChart getWorldMapChart() {
			return worldMapChart;
		}
	
		public void setWorldMapChart(WorldMapChart worldMapChart) {
			this.worldMapChart = worldMapChart;
		}

	public void loadMapInfo() throws Exception {
//		if(orderInformation == null) {
//			orderInformation = new OrderInformation();
//		}
		if(distinctOrderInformation == null) {
			distinctOrderInformation = new OrderInformation();
			
		}
//		this.setOrderInformation(orderInformation.findOrderInfo());
		this.setDistinctOrderInformation(distinctOrderInformation.findDistinctOrderInfo());
	}
	
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] loadViewPanel() throws Exception {

		
		// 패널이 가지고 있는 IOrderInformation에 set.
		OrderInformationViewPanel orderInformationViewPanel = new OrderInformationViewPanel();
		orderInformationViewPanel.load(this.getCountrycode());
		
		Popup popup = new Popup();
		popup.setWidth(870);
		popup.setHeight(500);
		popup.setName("발주정보 :  " + this.getCountryname());
		popup.setPanel(orderInformationViewPanel);
		
		return new Object[] {popup};
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow loadRegionMatching() {
		ModalWindow modalWindow = new ModalWindow();
		if(worldMapChart == null){
			worldMapChart = new WorldMapChart();
		}
		
		
		modalWindow.setWidth(900);
		modalWindow.setHeight(600);
		modalWindow.setPanel(worldMapChart);
		return modalWindow;
			
	}		
}
