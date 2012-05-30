package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;


public class ProcessMapList {

	public ProcessMapList(){
	}
	
	public void load() throws Exception {
		IProcessMap processMap = ProcessMap.loadList();
		processMap.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		setProcessMapList(processMap);		
	}
	
	IProcessMap processMapList;
		public IProcessMap getProcessMapList() {
			return processMapList;
		}	
		public void setProcessMapList(IProcessMap processMapList) {
			this.processMapList = processMapList;
		}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP, callByContent=true)
	public Popup append() {
		IProcessMap processMap = new ProcessMap();
		processMap.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		
		Popup popup = new Popup(560, 800);
		popup.setPanel(processMap);
		
		return popup;
	}
}
