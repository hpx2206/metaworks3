package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.widget.layout.Layout;


public class OrderInformationMultiViewPanel {
	
	Layout layout;
		@Id
		public Layout getLayout() {
			return layout;
		}
		public void setLayout(Layout layout) {
			this.layout = layout;
		}
		
	public void loadViewPanel(OrderInformationViewPanel currentListPanel, OrderInformationViewPanel allListPanel, OrderInformationChart orderInformationChart) {
		this.loadLayout(currentListPanel, allListPanel, orderInformationChart);
			
	}
	
	public void loadLayout(OrderInformationViewPanel currentListPanel, OrderInformationViewPanel allListPanel, OrderInformationChart orderInformationChart) {
		Layout layout = new Layout();
		layout.setWest(currentListPanel);
		layout.setCenter(allListPanel);
		layout.setSouth(orderInformationChart);
		layout.setOptions("togglerLength_open:0, spacing_open:0, spacing_closed:0, west__spacing_open:5, west__size:'50%'");
		this.setLayout(layout);
		
	}
		
}
