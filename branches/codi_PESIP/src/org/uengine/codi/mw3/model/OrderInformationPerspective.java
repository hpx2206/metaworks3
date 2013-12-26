package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.IRegionNode;

public class OrderInformationPerspective {
	
	@AutowiredFromClient
	public static Session session;
	
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
		orderInformationPanel.load();
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setWidth(1100);
		modalWindow.setHeight(725);
		modalWindow.setTitle("$OrderInformation");
		modalWindow.setPanel(orderInformationPanel);
		
		return new Object[]{modalWindow};
	}

}
