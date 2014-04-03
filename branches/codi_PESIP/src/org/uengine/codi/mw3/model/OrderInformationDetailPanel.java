package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;

public class OrderInformationDetailPanel {
	
	//list
	ArrayList<OrderInformation> orderInformationList;
		public ArrayList<OrderInformation> getOrderInformationList() {
			return orderInformationList;
		}
		public void setOrderInformationList(
				ArrayList<OrderInformation> orderInformationList) {
			this.orderInformationList = orderInformationList;
		}
		
	// page
	SelectPage selectPage;
		public SelectPage getSelectPage() {
			return selectPage;
		}
		public void setSelectPage(SelectPage selectPage) {
			this.selectPage = selectPage;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	public void load(ArrayList<OrderInformation> list, String selectedRegion, int pageLength, String status) {
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(status);
		this.setOrderInformationList(list);
		this.loadSelectPage(selectedRegion, pageLength);
	}

	public void loadSelectPage(String selectedRegion, int pageLength) {
		SelectPage selectPage = new SelectPage();
		selectPage.setPageId(this.getMetaworksContext().getWhen());
		selectPage.setSelectedRegion(selectedRegion);
		selectPage.setPageLength(pageLength);
		this.setSelectPage(selectPage);
	}
	
}
