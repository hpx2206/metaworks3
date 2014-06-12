package org.uengine.codi.mw3.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class WorldMap {
	
	// API를 선택하는건데 그에 해당하는 국가 코드를 넘겨줘야 하는데 저장할 곳이 없다.
	// 해결책을 모르겠다. worldMap에서 임시로 책임진다.
	String selectedRegion;
		public String getSelectedRegion() {
			return selectedRegion;
		}
		public void setSelectedRegion(String selectedRegion) {
			this.selectedRegion = selectedRegion;
		}

	// key String 국가 code, value Integer 발주정보 횟수
	HashMap<String, Integer> countryOrderInfo;
		public HashMap<String, Integer> getCountryOrderInfo() {
			return countryOrderInfo;
		}
		public void setCountryOrderInfo(HashMap<String, Integer> countryOrderInfo) {
			this.countryOrderInfo = countryOrderInfo;
		}

	// key String 국가 code, value 국가 alpha2 (ex : ru, us)
	HashMap<String, String> countryCodeInfo;
		public HashMap<String, String> getCountryCodeInfo() {
			return countryCodeInfo;
		}
		public void setCountryCodeInfo(HashMap<String, String> countryCodeInfo) {
			this.countryCodeInfo = countryCodeInfo;
		}
	
	public void load() throws Exception {
		// order Info setting
		PseipBidding pseipBidding = new PseipBidding();
		IPseipBidding iPseipBidding = pseipBidding.loadCountryOrderInfo();
		if(countryOrderInfo == null)
			countryOrderInfo = new HashMap<String, Integer>();
			
		while(iPseipBidding.next()) {
			countryOrderInfo.put(iPseipBidding.getCountryCode(), iPseipBidding.getCount());
		}
		
		// code Info setting
		PseipCountry pseipCountry = new PseipCountry();
		IPseipCountry iPseipCountry = pseipCountry.loadCountryCodeInfo();
		if(countryCodeInfo == null) 
			countryCodeInfo = new HashMap<String, String>();
		
		while(iPseipCountry.next()) {
			countryCodeInfo.put(iPseipCountry.getAlpha2(), iPseipCountry.getCountryCode());
		}
		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object selectRegion() throws Exception {
		PseipBidding pseipBidding = new PseipBidding();
		IPseipBidding iPseipBidding = pseipBidding.loadOrderInformation(this.getSelectedRegion(), SelectPage.OFF_SET, SelectPage.FIND_COUNT);
		
		ArrayList<OrderInformation> orderInformationList = new ArrayList<OrderInformation>();
		// 객체에 번호를 붙여주기 위한 지역변수
		int orderNo = 0;
		// orderInformation 객체에 region이 가지고 있는 발주정보를 모두 가져온다.
		while(iPseipBidding.next()) {
			++orderNo;
			OrderInformation orderInformation = new OrderInformation();
			orderInformation.setNo(orderNo);
			orderInformation.setSelectedRegion(this.getSelectedRegion());
			orderInformation.setProjectName(iPseipBidding.getProjectName());
			orderInformation.setIndustryCategory(iPseipBidding.getIndustryCategory());
			orderInformation.setServiceCategory(iPseipBidding.getServiceCategory());
			orderInformation.setProjectStatus(iPseipBidding.getProjectStatus());
			orderInformation.setRegDate(iPseipBidding.getRegDate());
			orderInformation.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			
			orderInformationList.add(orderInformation);
		}
		
		int infoSize = pseipBidding.loadRegionOrderInformationSize(this.getSelectedRegion());
		int pageLength;
		
		if(infoSize % SelectPage.FIND_COUNT == 0 )
			pageLength = infoSize / SelectPage.FIND_COUNT;
		else 
			pageLength = (infoSize / SelectPage.FIND_COUNT) + 1;
		
		OrderInformationViewPanel orderInformationViewPanel = new OrderInformationViewPanel();
		orderInformationViewPanel.setId("view");
		orderInformationViewPanel.load(orderInformationList, this.getSelectedRegion(), pageLength, orderInformationViewPanel.getId());
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setTitle("국가");
		modalWindow.setWidth(0);
		modalWindow.setHeight(0);
		modalWindow.setPanel(orderInformationViewPanel);
		
		return modalWindow;
	}
	
}
