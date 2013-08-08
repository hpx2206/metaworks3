package org.uengine.codi.mw3.ide.compare;

public class FileComparePanel {
	String selectedProcessAlias;
		public String getSelectedProcessAlias() {
			return selectedProcessAlias;
		}
		public void setSelectedProcessAlias(String selectedProcessAlias) {
			this.selectedProcessAlias = selectedProcessAlias;
		}
	CompareOriginFilePanel compareOriginFilePanel;
		public CompareOriginFilePanel getCompareOriginFilePanel() {
			return compareOriginFilePanel;
		}
		public void setCompareOriginFilePanel(
				CompareOriginFilePanel compareOriginFilePanel) {
			this.compareOriginFilePanel = compareOriginFilePanel;
		}
	CompareImportFilePanel compareImportFilePanel;
		public CompareImportFilePanel getCompareImportFilePanel() {
			return compareImportFilePanel;
		}
		public void setCompareImportFilePanel(
				CompareImportFilePanel compareImportFilePanel) {
			this.compareImportFilePanel = compareImportFilePanel;
		}
	public FileComparePanel(){
		
	}
	public void load(){
		compareOriginFilePanel = new CompareOriginFilePanel();
		compareOriginFilePanel.setSelectedProcessAlias("D:/codi/codebase/codi/root/originProcess2.process");
		compareOriginFilePanel.load();
		
		compareImportFilePanel = new CompareImportFilePanel();
		compareImportFilePanel.setSelectedProcessAlias("D:/codi/codebase/codi/root/originProcess1.process");
		compareImportFilePanel.load();
		 
		// TODO
		
	}
}
