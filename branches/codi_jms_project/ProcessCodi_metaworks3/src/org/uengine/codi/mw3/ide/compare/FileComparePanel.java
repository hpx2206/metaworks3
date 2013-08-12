package org.uengine.codi.mw3.ide.compare;

import java.io.File;

import org.uengine.kernel.GlobalContext;

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
	String fileUploadPath;
		public String getFileUploadPath() {
			return fileUploadPath;
		}
		public void setFileUploadPath(String fileUploadPath) {
			this.fileUploadPath = fileUploadPath;
		}
	public FileComparePanel(){
		
	}
	public void load() throws Exception{
		
		
		
		compareOriginFilePanel = new CompareOriginFilePanel();
		compareOriginFilePanel.setSelectedProcessAlias("D:/codi/codebase/codi/root/originProcess2.process");
		compareOriginFilePanel.load();
		
		compareImportFilePanel = new CompareImportFilePanel();
		compareImportFilePanel.setSelectedProcessAlias("D:/codi/codebase/codi/root/originProcess1.process");
		compareImportFilePanel.load();
		 
		String fileSystemPath = GlobalContext.getPropertyString("filesystem.path", "filesystem.path");
		String uploadedDirectory = fileSystemPath + compareImportFilePanel.UPLOAD_PATH;
		File directory = new File(uploadedDirectory);
		if(!directory.isDirectory()) {
			directory.mkdirs();
		}
		this.fileUploadPath = uploadedDirectory;
		
	}
}
