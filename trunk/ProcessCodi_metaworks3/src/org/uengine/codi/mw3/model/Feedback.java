package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;


public class Feedback {
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow popupFeedback(){
		return new ModalWindow(new ContactUs(),800,570,"피드백");
	}


}
