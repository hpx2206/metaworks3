package org.uengine.codi.mw3.model;


public class ProcessMapPanel {

	public ProcessMapPanel(){
	}
	
	public void load() throws Exception {
		ProcessMapList processMapList = new ProcessMapList();
		processMapList.load();
		
		setProcessMapList(processMapList);
	}
	
	ProcessMapList processMapList;
		public ProcessMapList getProcessMapList() {
			return processMapList;
		}
		public void setProcessMapList(ProcessMapList processMapList) {
			this.processMapList = processMapList;
		}
	
}
