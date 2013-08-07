package org.uengine.codi.mw3.webProcessDesigner;

public class MergeImportProcessPanel {
	String selectedProcessAlias;
		public String getSelectedProcessAlias() {
			return selectedProcessAlias;
		}
		public void setSelectedProcessAlias(String selectedProcessAlias) {
			this.selectedProcessAlias = selectedProcessAlias;
		}
		
	MergeImportProcess mergeImportProcess;
		public MergeImportProcess getMergeImportProcess() {
			return mergeImportProcess;
		}
		public void setMergeImportProcess(MergeImportProcess mergeImportProcess) {
			this.mergeImportProcess = mergeImportProcess;
		}
		
	public MergeImportProcessPanel(){
	}
	
	public void load(){
		mergeImportProcess = new MergeImportProcess();
		mergeImportProcess.setSelectedProcessAlias(selectedProcessAlias);
		mergeImportProcess.load();
	}
}
