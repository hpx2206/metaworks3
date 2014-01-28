package org.uengine.codi.mw3.model;

import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;


public class Feedback {
	
	@ServiceMethod
	public ModalWindow popupFeedback(){
		return new ModalWindow(new ContactUs(),800,550,"피드백");
	}


}
