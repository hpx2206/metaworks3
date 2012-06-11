package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Hidden;
import org.metaworks.website.MetaworksFile;

public class GenericWorkItem extends WorkItem{
	
	
	public GenericWorkItem(){
		setType("generic");
	}

	@Hidden(on=false)
	public GenericWorkItemHandler getGenericWorkItemHandler() {
		// TODO Auto-generated method stub
		return super.getGenericWorkItemHandler();
	}

}
