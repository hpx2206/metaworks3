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
		compareImportFile = new CompareImportFile();
		if( selectedProcessAlias != null ){
			compareImportFile.setSelectedProcessAlias(selectedProcessAlias);
			compareImportFile.load();
		}
		
	}
	
}
