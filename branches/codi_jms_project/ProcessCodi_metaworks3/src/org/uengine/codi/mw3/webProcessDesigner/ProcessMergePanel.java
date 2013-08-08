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
	MergeTopMenu mergeTopMenu;
		public MergeTopMenu getMergeTopMenu() {
			return mergeTopMenu;
		}
		public void setMergeTopMenu(MergeTopMenu mergeTopMenu) {
			this.mergeTopMenu = mergeTopMenu;
		}
	public ProcessMergePanel(){
		
	}
	public void load(){
		mergeOriginProcessPanel = new MergeOriginProcessPanel();
		mergeOriginProcessPanel.setSelectedProcessAlias(this.selectedProcessAlias);
		mergeOriginProcessPanel.load();
				
		// import 부분은 따로 processMap을 부르기 때문에 Origin 부터 작업.
		mergeImportProcessPanel = new MergeImportProcessPanel();
		mergeImportProcessPanel.setSelectedProcessAlias("D:/codi/codebase/codi/root/efe.process");
		mergeImportProcessPanel.load();
		
		// TODO
	}
	
}
