package org.uengine.codi.mw3.webProcessDesigner;

public class MergeOriginProcessPanel {
	
	String selectedProcessAlias;
		public String getSelectedProcessAlias() {
			return selectedProcessAlias;
		}
		public void setSelectedProcessAlias(String selectedProcessAlias) {
			this.selectedProcessAlias = selectedProcessAlias;
		}
		
	MergeOriginProcess mergeOriginProcess;
		public MergeOriginProcess getMergeOriginProcess() {
			return mergeOriginProcess;
		}
		public void setMergeOriginProcess(MergeOriginProcess mergeOriginProcess) {
			this.mergeOriginProcess = mergeOriginProcess;
		}
	
	public MergeOriginProcessPanel(){
	}
	
	public void load(){
		
		mergeOriginProcess = new MergeOriginProcess();
		mergeOriginProcess.setSelectedProcessAlias(selectedProcessAlias);
		mergeOriginProcess.load();
		
	}
}
