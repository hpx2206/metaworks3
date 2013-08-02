package org.uengine.kernel.designer.web;

import java.io.Serializable;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;

public class HumanActivityView extends ActivityView implements Serializable , ContextAware{
	MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}
	public static final String MAIN_CLASS = "org.uengine.kernel.HumanActivity"; 
	
	public HumanActivityView(){
		setActivityClass(HumanActivityView.MAIN_CLASS);
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
}
