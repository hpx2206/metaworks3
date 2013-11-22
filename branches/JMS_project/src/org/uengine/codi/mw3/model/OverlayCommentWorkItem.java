package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Hidden;

public class OverlayCommentWorkItem extends WorkItem{
	
	final static String TYPE="ovryCmnt";
	
	public OverlayCommentWorkItem(){
		setType(TYPE);
	}
	
	@Hidden//(on=false)
	public OverlayCommentWorkItem comment() throws Exception {	
		return null;
	}
}
