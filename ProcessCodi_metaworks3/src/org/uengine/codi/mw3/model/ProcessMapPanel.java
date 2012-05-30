package org.uengine.codi.mw3.model;

public class ProcessMapPanel {

	public ProcessMapPanel(){
		setProcessMapList(new ProcessMapList());
	}
	
	ProcessMapList processMapList;
		public ProcessMapList getProcessMapList() {
			return processMapList;
		}
		public void setProcessMapList(ProcessMapList processMapList) {
			this.processMapList = processMapList;
		}
	
}
