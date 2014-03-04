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
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow loadPastOrderInformation() throws Exception{
		// db 에서 과거의 발주정보를 전부 찾아온다.
		OrderInformation orderInformation = new OrderInformation();
		IOrderInformation iOrderInformation = orderInformation.loadPastOrderInfo();
		
		// 그리고 worldChart에 set
		WorldMapChart worldMapChart = new WorldMapChart();
		worldMapChart.load(iOrderInformation);
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setWidth(900);
		modalWindow.setHeight(600);
		modalWindow.setPanel(worldMapChart);
		
		return modalWindow;
			
	}		
}
