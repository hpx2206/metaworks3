package org.uengine.codi.mw3.model;

import java.util.ArrayList;
import java.util.Date;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class OrderInformation {
	
	public final static String SELECT_VIEW = "select_view";
	public final static String LIST = "list";
	
	public OrderInformation() {
		metaworksContext = new MetaworksContext();
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	int no;
		@Id
		public int getNo() {
			return no;
		}
		public void setNo(int no) {
			this.no = no;
		}
		
	String selectedRegion;
		public String getSelectedRegion() {
			return selectedRegion;
		}
		public void setSelectedRegion(String selectedRegion) {
			this.selectedRegion = selectedRegion;
		} 
		
	String koName;
		@Available(when=LIST)
		public String getKoName() {
			return koName;
		}
		public void setKoName(String koName) {
			this.koName = koName;
		}

	String projectName;
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

	String industryCategory;
		public String getIndustryCategory() {
			return industryCategory;
		}
		public void setIndustryCategory(String industryCategory) {
			this.industryCategory = industryCategory;
		}

	String serviceCategory;
		public String getServiceCategory() {
			return serviceCategory;
		}
		public void setServiceCategory(String serviceCategory) {
			this.serviceCategory = serviceCategory;
		}

	String projectStatus;
		public String getProjectStatus() {
			return projectStatus;
		}
		public void setProjectStatus(String projectStatus) {
			this.projectStatus = projectStatus;
		}

	Date regDate;
		public Date getRegDate() {
			return regDate;
		}
		public void setRegDate(Date regDate) {
			this.regDate = regDate;
		}
		
	@AutowiredFromClient
	public static OrderInformationViewPanel orderInformationViewPanel;
		
	@Available(when=MetaworksContext.WHEN_VIEW)
	@ServiceMethod(callByContent=true, mouseBinding=ServiceMethodContext.MOUSEBINDING_LEFTCLICK, target=ServiceMethodContext.TARGET_POPUP)
	public Object showDetail() throws Exception {
		// left current list 구성하기
		ArrayList<OrderInformation> currentOrderInformationList = orderInformationViewPanel.getOrderInformationDetailPanel().getOrderInformationList();
		for(int i = 0; i < currentOrderInformationList.size(); i++) {
			currentOrderInformationList.get(i).getMetaworksContext().setWhen(SELECT_VIEW);
		}
		
		OrderInformationViewPanel currentOrderInformationViewPanel = new OrderInformationViewPanel();
		currentOrderInformationViewPanel.setId(SELECT_VIEW);
		currentOrderInformationViewPanel.load(currentOrderInformationList, this.getSelectedRegion(), orderInformationViewPanel.getOrderInformationDetailPanel().getSelectPage().getPageLength(), currentOrderInformationViewPanel.getId());
		
		// center all list 구성하기
		PseipBidding pseipBidding = new PseipBidding();
		IPseipBidding iPseipBidding = pseipBidding.loadAllOrderInformation(SelectPage.OFF_SET, SelectPage.FIND_COUNT);
		ArrayList<OrderInformation> allOrderInformationList = new ArrayList<OrderInformation>();
		// 객체에 번호를 붙여주기 위한 지역변수
		int allOrderNo = 0;
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
			orderInformation.getMetaworksContext().setWhen(LIST);
			
			allOrderInformationList.add(orderInformation);
		}
		
		// length 및 size logic 알고리즘
		int infoSize = pseipBidding.loadAllRegionOrderInformationSize();
		int pageLength;
		if(infoSize % SelectPage.FIND_COUNT == 0 )
			pageLength = infoSize / SelectPage.FIND_COUNT;
		else 
			pageLength = (infoSize / SelectPage.FIND_COUNT) + 1;
		
		
		// rigth all list 를 위한 make view panel 후에 multi view에 add
		OrderInformationViewPanel allOrderInformationViewPanel = new OrderInformationViewPanel();
		allOrderInformationViewPanel.setId("list");
		allOrderInformationViewPanel.load(allOrderInformationList, this.getSelectedRegion(), pageLength, allOrderInformationViewPanel.getId());
		
		// chart load and set
		OrderInformationChart orderInformationChart = new OrderInformationChart();
		orderInformationChart.load();
		
		
		// multi view panel
		OrderInformationMultiViewPanel orderInformationMultiViewPanel = new OrderInformationMultiViewPanel();
		orderInformationMultiViewPanel.loadViewPanel(currentOrderInformationViewPanel, allOrderInformationViewPanel, orderInformationChart);
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setTitle("상세 발주 정보");
		modalWindow.setWidth(0);
		modalWindow.setHeight(0);
		modalWindow.setPanel(orderInformationMultiViewPanel);
		
		return modalWindow;
	}
	
}