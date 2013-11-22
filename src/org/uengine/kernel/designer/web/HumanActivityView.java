package org.uengine.kernel.designer.web;

import org.metaworks.MetaworksContext;

public class HumanActivityView extends ActivityView{
	
	public static final String MAIN_CLASS = "org.uengine.kernel.HumanActivity"; 
	
	public HumanActivityView(){
		setActivityClass(HumanActivityView.MAIN_CLASS);
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
}
