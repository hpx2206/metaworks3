package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;

public class ReplyOverlayCommentWorkItem  extends OverlayCommentWorkItem{

	final static String TYPE="replyCmnt";
	
	public ReplyOverlayCommentWorkItem(){
		setType(TYPE);
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)		
	public Object saveReply(){
		
		
		return null;
	}
}
