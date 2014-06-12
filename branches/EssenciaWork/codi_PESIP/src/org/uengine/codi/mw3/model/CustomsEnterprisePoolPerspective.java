package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class CustomsEnterprisePoolPerspective {
	
	@AutowiredFromClient
	public static Session session;
	
	
	CustomsEnterprisePoolPanel customsEnterprisePoolPanel;
		public CustomsEnterprisePoolPanel getCustomsEnterprisePoolPanel() {
			return customsEnterprisePoolPanel;
		}
		public void setCustomsEnterprisePoolPanel(
				CustomsEnterprisePoolPanel customsEnterprisePoolPanel) {
			this.customsEnterprisePoolPanel = customsEnterprisePoolPanel;
		}


	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] select() throws Exception {
		CustomsEnterprisePoolPanel customsEnterprisePoolPanel = new CustomsEnterprisePoolPanel();
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setWidth(1000);
		modalWindow.setHeight(600);
		modalWindow.setTitle("수출기업 네트워크");
		modalWindow.setPanel(customsEnterprisePoolPanel);
		
		return new Object[]{modalWindow};
	}
}
