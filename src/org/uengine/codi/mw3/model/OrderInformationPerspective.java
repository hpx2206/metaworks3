package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.IRegionNode;

public class OrderInformationPerspective {
	
	OrderInformationPanel orderInformationPanel;
		public OrderInformationPanel getOrderInformationPanel() {
			return orderInformationPanel;
		}
		public void setOrderInformationPanel(OrderInformationPanel orderInformationPanel) {
			this.orderInformationPanel = orderInformationPanel;
		}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] select() throws Exception {
		if(orderInformationPanel == null) {
			orderInformationPanel = new OrderInformationPanel();
		}
		orderInformationPanel.loadWorldMap();
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setWidth(1050);
		modalWindow.setHeight(650);
		modalWindow.setTitle("발주 정보");
		modalWindow.setPanel(orderInformationPanel);
		
		return new Object[]{modalWindow};
	}

}
