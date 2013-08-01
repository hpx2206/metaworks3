package org.uengine.codi.mw3.model;

import org.metaworks.annotation.ServiceMethod;


public class OverlayCommentWorkItem extends WorkItem{
	
	final static String TYPE="ovryCmnt";
	
	public OverlayCommentWorkItem(){
		setType(TYPE);
	}
	
	@ServiceMethod(inContextMenu=false)
	public void edit() throws Exception{
	}
}
