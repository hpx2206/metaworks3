package org.uengine.kernel.designer.web;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.webProcessDesigner.Documentation;
import org.uengine.codi.mw3.webProcessDesigner.ProcessAttributePanel;

public class HumanActivityView extends ActivityView {
	
	public static final String MAIN_CLASS = "org.uengine.kernel.HumanActivity"; 
	
	public HumanActivityView(){
		setActivityClass(HumanActivityView.MAIN_CLASS);
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
	
}
