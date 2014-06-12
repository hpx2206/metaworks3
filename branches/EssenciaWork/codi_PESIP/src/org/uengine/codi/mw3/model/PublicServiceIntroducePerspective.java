package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class PublicServiceIntroducePerspective {
	
	@AutowiredFromClient
	public static Session session;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] select() throws Exception {
		PublicServiceIntroducePanel publicServiceIntroducePanel = new PublicServiceIntroducePanel();
		publicServiceIntroducePanel.load();
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setWidth(0);
		modalWindow.setHeight(0);
		modalWindow.setTitle("$publicServiceIntroduce");
		modalWindow.setPanel(publicServiceIntroducePanel);
		
		return new Object[]{modalWindow};
	}
}
