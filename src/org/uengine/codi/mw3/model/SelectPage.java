package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;

public class SelectPage {
	
	public final static int OFF_SET = 0;
	public final static int FIND_COUNT = 10;
	
	String pageId;
		public String getPageId() {
			return pageId;
		}
		public void setPageId(String pageId) {
			this.pageId = pageId;
		}

	String selectedRegion;
		public String getSelectedRegion() {
			return selectedRegion;
		}
		public void setSelectedRegion(String selectedRegion) {
			this.selectedRegion = selectedRegion;
		}
		
	int selectedPage;
		public int getSelectedPage() {
			return selectedPage;
		}
		public void setSelectedPage(int selectedPage) {
			this.selectedPage = selectedPage;
		}
		
	int pageLength;
		public int getPageLength() {
			return pageLength;
		}
		public void setPageLength(int pageLength) {
			this.pageLength = pageLength;
		}
		
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object loadView() throws Exception {
		String parseOffset = (this.getSelectedPage() - 1) + "" + OFF_SET;
		int resultOffset = Integer.parseInt(parseOffset);
		
		PseipBidding pseipBidding = new PseipBidding();
		IPseipBidding iPseipBidding = pseipBidding.loadOrderInformation(this.getSelectedRegion(), resultOffset, FIND_COUNT);

		ArrayList<OrderInformation> orderInformationList = new ArrayList<OrderInformation>();
		// 객체에 번호를 붙여주기 위한 지역변수
		int orderNo = resultOffset;
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
		
		OrderInformationViewPanel orderInformationViewPanel = new OrderInformationViewPanel();
		orderInformationViewPanel.setId("view");
		orderInformationViewPanel.load(orderInformationList, this.getSelectedRegion(), this.getPageLength(), orderInformationViewPanel.getId());
		
		return new Refresh(orderInformationViewPanel);
			
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object loadSelectView() throws Exception {
		String parseOffset = (this.getSelectedPage() - 1) + "" + OFF_SET;
		int resultOffset = Integer.parseInt(parseOffset);
		
		PseipBidding pseipBidding = new PseipBidding();
		IPseipBidding iPseipBidding = pseipBidding.loadOrderInformation(this.getSelectedRegion(), resultOffset, FIND_COUNT);

		ArrayList<OrderInformation> orderInformationList = new ArrayList<OrderInformation>();
		// 객체에 번호를 붙여주기 위한 지역변수
		int orderNo = resultOffset;
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
			orderInformation.getMetaworksContext().setWhen("select_view");
			
			orderInformationList.add(orderInformation);
		}
		
		OrderInformationViewPanel orderInformationViewPanel = new OrderInformationViewPanel();
		orderInformationViewPanel.setId("select_view");
		orderInformationViewPanel.load(orderInformationList, this.getSelectedRegion(), this.getPageLength(), orderInformationViewPanel.getId());
		
		return new Refresh(orderInformationViewPanel);
		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object loadListView() throws Exception {
		String parseOffset = (this.getSelectedPage() - 1) + "" + OFF_SET;
		int resultOffset = Integer.parseInt(parseOffset);
		
		PseipBidding pseipBidding = new PseipBidding();
		IPseipBidding iPseipBidding = pseipBidding.loadAllOrderInformation(resultOffset, SelectPage.FIND_COUNT);
		ArrayList<OrderInformation> allOrderInformationList = new ArrayList<OrderInformation>();
		// 객체에 번호를 붙여주기 위한 지역변수
		int allOrderNo = resultOffset;
		// orderInformation 객체에 region이 가지고 있는 발주정보를 모두 가져온다.
		while(iPseipBidding.next()) {
			++allOrderNo;
			OrderInformation orderInformation = new OrderInformation();
			orderInformation.setNo(allOrderNo);
			orderInformation.setKoName(iPseipBidding.getCountryCode());
			orderInformation.setProjectName(iPseipBidding.getProjectName());
			orderInformation.setIndustryCategory(iPseipBidding.getIndustryCategory());
			orderInformation.setServiceCategory(iPseipBidding.getServiceCategory());
			orderInformation.setProjectStatus(iPseipBidding.getProjectStatus());
			orderInformation.setRegDate(iPseipBidding.getRegDate());
			orderInformation.getMetaworksContext().setWhen("list");
			
			allOrderInformationList.add(orderInformation);
		}
		
		OrderInformationViewPanel orderInformationViewPanel = new OrderInformationViewPanel();
		orderInformationViewPanel.setId("list");
		orderInformationViewPanel.load(allOrderInformationList, this.getSelectedRegion(), this.getPageLength(), orderInformationViewPanel.getId());
		
		return new Refresh(orderInformationViewPanel);
	}
}
