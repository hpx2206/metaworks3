package org.uengine.codi.mw3.ide.compare;


public class CompareOriginFilePanel {
	
	public static final String FILE_LOCATION = "source";   
	
	String selectedProcessAlias;
		public String getSelectedProcessAlias() {
			return selectedProcessAlias;
		}
		public void setSelectedProcessAlias(String selectedProcessAlias) {
			this.selectedProcessAlias = selectedProcessAlias;
		}
	CompareOriginFile compareOriginFile;
		public CompareOriginFile getCompareOriginFile() {
			return compareOriginFile;
		}
		public void setCompareOriginFile(CompareOriginFile compareOriginFile) {
			this.compareOriginFile = compareOriginFile;
		}
	public CompareOriginFilePanel(){
	}
	
	public void load() throws Exception{
		compareOriginFile = new CompareOriginFile();
		if( selectedProcessAlias != null ){
			compareOriginFile.setSelectedProcessAlias(selectedProcessAlias);
			compareOriginFile.load();
		}
	}
	
}
