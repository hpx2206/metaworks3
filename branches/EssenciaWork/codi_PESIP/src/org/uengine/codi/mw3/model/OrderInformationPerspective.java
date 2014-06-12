package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class OrderInformationPerspective {
	
	@AutowiredFromClient
	public static Session session;
	
		
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] select() throws Exception {
		OrderInformationPanel orderInformationPanel = new OrderInformationPanel();
		orderInformationPanel.load();
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setWidth(0);
		modalWindow.setHeight(0);
		modalWindow.setTitle("$OrderInformation");
		modalWindow.setPanel(orderInformationPanel);
		
		return new Object[]{modalWindow};
	}

}
