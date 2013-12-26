package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class PublicServiceIntroducePerspective {
	
	@AutowiredFromClient
	public static Session session;
	
	PublicServiceIntroducePanel publicServiceIntroducePanel;
		public PublicServiceIntroducePanel getPublicServiceIntroducePanel() {
			return publicServiceIntroducePanel;
		}
	
		public void setPublicServiceIntroducePanel(
				PublicServiceIntroducePanel publicServiceIntroducePanel) {
			this.publicServiceIntroducePanel = publicServiceIntroducePanel;
		}

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] select() throws Exception {
		if(publicServiceIntroducePanel == null) {
			publicServiceIntroducePanel = new PublicServiceIntroducePanel();
		}
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setWidth(1100);
		modalWindow.setHeight(725);
		modalWindow.setTitle("$publicServiceIntroduce");
		modalWindow.setPanel(publicServiceIntroducePanel);
		
		return new Object[]{modalWindow};
	}
}
