package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;

public class ProcessPerspective extends Perspective{
	
	public ProcessPerspective(){
		setLabel("Process");
		
	}

	@ServiceMethod
	public Object[] select() throws Exception {
		
		if(!isSelected()){
			processDefinitions = new ResourceFile();
			processDefinitions.setMetaworksContext(new MetaworksContext());	
			processDefinitions.getMetaworksContext().setWhere("navigation");
			processDefinitions.setObjType("folder");
			processDefinitions.setFolder(true);
			processDefinitions.setAlias("");
			processDefinitions.setName("/");
			processDefinitions.drillDown();
		}
		
		return super.select();
	}
	
	ResourceFile processDefinitions;
	
		public ResourceFile getProcessDefinitions() {
			return processDefinitions;
		}
	
		public void setProcessDefinitions(ResourceFile processDefinitions) {
			this.processDefinitions = processDefinitions;
		}
	
//	@ServiceMethod
//	public ContentWindow open() throws Exception {
//		ContentWindow window = new ContentWindow();
//		UserPickupTool picker = new UserPickupTool();
//		picker.onLoad();
//		window.setPanel(picker);
//		return window;
//	}

}
