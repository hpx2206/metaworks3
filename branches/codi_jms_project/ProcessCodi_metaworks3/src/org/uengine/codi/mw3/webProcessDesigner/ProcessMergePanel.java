package org.uengine.codi.mw3.webProcessDesigner;

public class ProcessMergePanel {
	String selectedProcessAlias;
		public String getSelectedProcessAlias() {
			return selectedProcessAlias;
		}
		public void setSelectedProcessAlias(String selectedProcessAlias) {
			this.selectedProcessAlias = selectedProcessAlias;
		}
	MergeOriginProcessPanel mergeOriginProcessPanel;
		public MergeOriginProcessPanel getMergeOriginProcessPanel() {
			return mergeOriginProcessPanel;
		}
		public void setMergeOriginProcessPanel(
				MergeOriginProcessPanel mergeOriginProcessPanel) {
			this.mergeOriginProcessPanel = mergeOriginProcessPanel;
		}
	MergeImportProcessPanel mergeImportProcessPanel;
		public MergeImportProcessPanel getMergeImportProcessPanel() {
			return mergeImportProcessPanel;
		}
		public void setMergeImportProcessPanel(
				MergeImportProcessPanel mergeImportProcessPanel) {
			this.mergeImportProcessPanel = mergeImportProcessPanel;
		}
	public ProcessMergePanel(){
		
	}
	public void load(){
		mergeOriginProcessPanel = new MergeOriginProcessPanel();
		mergeOriginProcessPanel.setSelectedProcessAlias("D:/codi/codebase/codi/root/ccc.wpd");
		mergeOriginProcessPanel.load();
		
		mergeImportProcessPanel = new MergeImportProcessPanel();
		mergeImportProcessPanel.setSelectedProcessAlias("D:/codi/codebase/codi/root/efe.process");
		mergeImportProcessPanel.load();
		 
		// TODO
		
	}
}
