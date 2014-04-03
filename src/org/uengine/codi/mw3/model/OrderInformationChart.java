package org.uengine.codi.mw3.model;

import java.util.HashMap;


public class OrderInformationChart {
	
	// key String 국가 code, value Integer 발주정보 횟수
	HashMap<String, Integer> countryOrderInfo;
		public HashMap<String, Integer> countryOrderInfo() {
			return countryOrderInfo;
		}
		public void setCountryOrderInfo(HashMap<String, Integer> countryOrderInfo) {
			this.countryOrderInfo = countryOrderInfo;
		}

//	ArrayList<OrderInformation> allOrderInformationList;
//		public ArrayList<OrderInformation> getAllOrderInformationList() {
//			return allOrderInformationList;
//		}
//		public void setAllOrderInformationList(
//				ArrayList<OrderInformation> allOrderInformationList) {
//			this.allOrderInformationList = allOrderInformationList;
//		}

	public void load() throws Exception {
		PseipBidding pseipBidding = new PseipBidding();
		IPseipBidding iPseipBidding = pseipBidding.loadAllOrderInformation();
		
		if(countryOrderInfo == null) {
			countryOrderInfo = new HashMap<String, Integer>();
		}
		iPseipBidding = pseipBidding.loadCountryOrderInfo();
		while(iPseipBidding.next()) {
			countryOrderInfo.put(pseipBidding.getCountryCode(), pseipBidding.getCount());
		}
		
	}

}
