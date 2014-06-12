package org.uengine.codi.mw3.ide.compare;

import org.uengine.codi.mw3.ide.editor.process.ProcessMergeEditor;

public class FileComparePanel {
	String selectedProcessAlias;
		public String getSelectedProcessAlias() {
			return selectedProcessAlias;
		}
		public void setSelectedProcessAlias(String selectedProcessAlias) {
			this.selectedProcessAlias = selectedProcessAlias;
		}
	CompareNavigator compareNavigator;
		public CompareNavigator getCompareNavigator() {
			return compareNavigator;
		}
		public void setCompareNavigator(CompareNavigator compareNavigator) {
			this.compareNavigator = compareNavigator;
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
	
	public void load() throws Exception{
		compareNavigator = new CompareNavigator();
		compareNavigator.load();
		
		compareOriginFilePanel = new CompareOriginFilePanel();
		compareOriginFilePanel.setSelectedProcessAlias(selectedProcessAlias);
		compareOriginFilePanel.load();
		
		compareImportFilePanel = new CompareImportFilePanel();
		compareImportFilePanel.load();
		
	}
	
	public void saveMe(ProcessMergeEditor processMergeEditor) throws Exception{
		compareOriginFilePanel.getCompareOriginFile().save(processMergeEditor);
	}
}
