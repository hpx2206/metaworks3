package org.uengine.codi.mw3.ide.compare;

public class CompareImportFilePanel {
	
	static final String FILE_LOCATION = "target";   
	
	String selectedProcessAlias;
		public String getSelectedProcessAlias() {
			return selectedProcessAlias;
		}
		public void setSelectedProcessAlias(String selectedProcessAlias) {
			this.selectedProcessAlias = selectedProcessAlias;
		}
		
	CompareFileNavigator compareFileNavigator; 
		public CompareFileNavigator getCompareFileNavigator() {
			return compareFileNavigator;
		}
		public void setCompareFileNavigator(CompareFileNavigator compareFileNavigator) {
			this.compareFileNavigator = compareFileNavigator;
		}
		
	CompareImportFile compareImportFile;
		public CompareImportFile getCompareImportFile() {
			return compareImportFile;
		}
		public void setCompareImportFile(CompareImportFile compareImportFile) {
			this.compareImportFile = compareImportFile;
		}
	public CompareImportFilePanel(){
	}
	
	public void load() throws Exception{
		compareFileNavigator = new CompareFileNavigator();
		compareFileNavigator.setId(CompareImportFilePanel.FILE_LOCATION);
		compareFileNavigator.loadUpload();
		
		compareImportFile = new CompareImportFile();
		compareImportFile.setSelectedProcessAlias(selectedProcessAlias);
		compareImportFile.load();
	}
}
