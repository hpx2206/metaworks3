package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.IFrame;
import org.metaworks.widget.ModalWindow;


public class OrderInformationViewPanel {
	
	final static String SRC= "src=";
	final static String END_TAG = ">";
	final static String WIKI_URL = "http://ko.wikipedia.org/wiki/";
	
	String id;
		@Id
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

	OrderInformationDetailPanel orderInformationDetailPanel;
		public OrderInformationDetailPanel getOrderInformationDetailPanel() {
			return orderInformationDetailPanel;
		}
		public void setOrderInformationDetailPanel(
				OrderInformationDetailPanel orderInformationDetailPanel) {
			this.orderInformationDetailPanel = orderInformationDetailPanel;
		}
		
	String regionKoName;
		public String getRegionKoName() {
			return regionKoName;
		}
		public void setRegionKoName(String regionKoName) {
			this.regionKoName = regionKoName;
		}
		
	String regionUrl;
		public String getRegionUrl() {
			return regionUrl;
		}
		public void setRegionUrl(String regionUrl) {
			this.regionUrl = regionUrl;
		}
		
	@Available(when=MetaworksContext.WHEN_VIEW)
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object showRegionInfo() throws Exception{
		// html parsing (하드코딩. 일단 바쁘니)
		IFrame iFrame = new IFrame(WIKI_URL + this.getRegionKoName());
		iFrame.setWidth("1150");
		iFrame.setHeight("750");
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setTitle("WIKI");
		modalWindow.setWidth(0);
		modalWindow.setHeight(0);
		modalWindow.setPanel(iFrame);
		
		return modalWindow;
	}
		
	@Available(when=MetaworksContext.WHEN_VIEW)
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object showGoogleMap() throws Exception{
		// html parsing (하드코딩. 일단 바쁘니)
		String parseUrl[] = this.getRegionUrl().split(SRC);
		String resultUrl[] = parseUrl[1].split(END_TAG);
		String result = resultUrl[0].replaceAll("\"", "");
		
		IFrame iFrame = new IFrame();
		iFrame.setSrc(result);
		iFrame.setWidth("1150");
		iFrame.setHeight("750");
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setTitle("GoogleMAP");
		modalWindow.setWidth(0);
		modalWindow.setHeight(0);
		modalWindow.setPanel(iFrame);
		
		return modalWindow;
	}
	
	public void load(ArrayList<OrderInformation> list, String selectedRegion, int pageLength, String status) throws Exception {
		// viewpanel은 detail과 국가정보, 지도 3가지 정보를 가지고 있다.
		PseipCountry pseipCountry = new PseipCountry();
		IPseipCountry iPseipCountry = pseipCountry.loadCountryDetailInfo(selectedRegion);
		// 국가 정보 wiki에 붙일 한글 국가 이름 지역변수와 구글 지도를 보여주게 할 url
		while(iPseipCountry.next()) {
			this.setRegionKoName(iPseipCountry.getNameKo());
			this.setRegionUrl(iPseipCountry.getUrl());
			
		}
		
		OrderInformationDetailPanel orderInformationDetailPanel = new OrderInformationDetailPanel();
		orderInformationDetailPanel.load(list, selectedRegion, pageLength, status);
		this.setOrderInformationDetailPanel(orderInformationDetailPanel);
		
	}
}
