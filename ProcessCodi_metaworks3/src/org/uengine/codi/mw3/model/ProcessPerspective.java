package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;

public class ProcessPerspective extends Perspective{
	
	public ProcessPerspective(){
		setLabel("Process");
		
	}
	
//	폴터를 뿌리는 부분 수정수정
//	@ServiceMethod
//	public Object[] select() throws Exception {
//		
//		if(!isSelected()){
//			
//			
//			processDefinitions = new ResourceFile();
//			processDefinitions.setMetaworksContext(new MetaworksContext());	
//			processDefinitions.getMetaworksContext().setWhere("navigation");
//			processDefinitions.setObjType("folder");
//			processDefinitions.setFolder(true);
//			processDefinitions.setAlias("");
//			processDefinitions.setName("/");
//			processDefinitions.drillDown();
//			
//		}
//		
//		return super.select();
//	}
	
	protected void loadChildren() throws Exception {
		
			IProcessMap processMap = ProcessMap.loadList(session);
			processMap.getMetaworksContext().setWhen("filter");
			
			setProcessMapList(processMap);
	}

	protected void unloadChildren() throws Exception {
		setProcessMapList(null);
	}
	
	IProcessMap processMapList;
		public IProcessMap getProcessMapList() {
			return processMapList;
		}	
		public void setProcessMapList(IProcessMap processMapList) {
			this.processMapList = processMapList;
		}
	
//	ResourceFile processDefinitions;
//	
//		public ResourceFile getProcessDefinitions() {
//			return processDefinitions;
//		}
//	
//		public void setProcessDefinitions(ResourceFile processDefinitions) {
//			this.processDefinitions = processDefinitions;
//		}
	
//	@ServiceMethod
//	public ContentWindow open() throws Exception {
//		ContentWindow window = new ContentWindow();
//		UserPickupTool picker = new UserPickupTool();
//		picker.onLoad();
//		window.setPanel(picker);
//		return window;
//	}

}
