package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class CustomsEnterprisePoolPerspective {
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
		if(customsEnterprisePoolPanel == null) {
			customsEnterprisePoolPanel = new CustomsEnterprisePoolPanel();
		}
		customsEnterprisePoolPanel.loadWorldMap();
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setWidth(1050);
		modalWindow.setHeight(650);
		modalWindow.setTitle("국내외 수출입 Pool ");
		modalWindow.setPanel(customsEnterprisePoolPanel);
		
		return new Object[]{modalWindow};
	}
}
