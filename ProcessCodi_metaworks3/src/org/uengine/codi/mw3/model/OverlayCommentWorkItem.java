package org.uengine.codi.mw3.model;

import org.metaworks.ToAppend;
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
	
	public Object[] add() throws Exception {
		boolean isOwnReturn = false;
		if( WHEN_EDIT.equals(getMetaworksContext().getWhen())){
			isOwnReturn = true;
		}else{
			isOwnReturn = false;
		}
		
		Object[] returnObjects = super.add();
		if( !isOwnReturn ){
			WorkItem parentWorkItem = new WorkItem();
			parentWorkItem.setTaskId(getOverlayCommentOption().getParentTaskId());
			
			returnObjects = new Object[]{new ToAppend(parentWorkItem, this)};
		}
		return returnObjects;
	}
}
