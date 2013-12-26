package org.uengine.codi.mw3.model;

public class OrderInformationViewPanel {
	
	IOrderInformation orderInformation;
		public IOrderInformation getOrderInformation() {
			return orderInformation;
		}
		public void setOrderInformation(IOrderInformation orderInformation) {
			this.orderInformation = orderInformation;
		}
		
	
	public void load(String countryCode) throws Exception {
	
		OrderInformation loadOrderInfo = new OrderInformation();
		loadOrderInfo.setCountrycode(countryCode);
		
		this.setOrderInformation(loadOrderInfo.loadDetailOrderInfo());
	}
	
}
