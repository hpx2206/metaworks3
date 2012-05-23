package org.uengine.codi.mw3.model;

import org.metaworks.annotation.ServiceMethod;

public class ProcessPerspective extends Perspective{
	
	public ProcessPerspective(){
		setLabel("Process");
	}

	@ServiceMethod
	public Object[] select() throws Exception {
		
		if(!isSelected()){
		
			ProcessDefinition root = new ProcessDefinition();
			root.setParentFolder("-1");
			processDefinitions = root.findAll();
		}
		
		return super.select();
	}
	
	IProcessDefinition processDefinitions;
	
		public IProcessDefinition getProcessDefinitions() {
			return processDefinitions;
		}
	
		public void setProcessDefinitions(IProcessDefinition processDefinitions) {
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
