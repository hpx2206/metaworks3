package org.uengine.codi.mw3.ide.compare;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.metadata.MetadataBundle;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.mw3.model.FileImporter;
import org.uengine.codi.mw3.model.Popup;

public class CompareImportFilePanel {
	
	static final String FILE_LOCATION = "target";
	static final String PRESENT_PROCESS = "0";
	static final String ORIGIN_PROCESS = "1";
	public static final String UPLOAD_PATH = "uploaded";
	
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
	MetaworksFile metaworksFile;
		public MetaworksFile getMetaworksFile() {
			return metaworksFile;
		}
		public void setMetaworksFile(MetaworksFile metaworksFile) {
			this.metaworksFile = metaworksFile;
		}
	SelectBox nodeSelect;
		public SelectBox getNodeSelect() {
			return nodeSelect;
		}
		public void setNodeSelect(SelectBox nodeSelect) {
			this.nodeSelect = nodeSelect;
		}
	String uploadPath;
		public String getUploadPath() {
			return uploadPath;
		}
		public void setUploadPath(String uploadPath) {
			this.uploadPath = uploadPath;
		}
	
	@AutowiredFromClient
	public FileComparePanel fileComparePanel;
	
	public CompareImportFilePanel(){
	}
	
	public void load() throws Exception{
		
		nodeSelect = new SelectBox();
		nodeSelect.add("업로드 된 프로세스", "0");
		nodeSelect.add("기존 프로세스", "1");
		nodeSelect.setId("selectNode"); 
		
		compareFileNavigator = new CompareFileNavigator();
		compareFileNavigator.setId(CompareImportFilePanel.FILE_LOCATION);
		compareFileNavigator.loadUpload();
		
		compareImportFile = new CompareImportFile();
		compareImportFile.setSelectedProcessAlias(selectedProcessAlias);
		compareImportFile.load();
	}
	
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Popup importFile() throws Exception{
		
		FileImporter fileImporter = new FileImporter();
		fileImporter.setParentDirectory(fileComparePanel.getFileUploadPath());
		
		Popup popup = new Popup();
		popup.setPanel(fileImporter);
		popup.setMetaworksContext(new MetaworksContext());
		popup.getMetaworksContext().setWhen("edit");
		  
		return popup;
	 }
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] changeFileNavigator() throws Exception {
		
		if (CompareImportFilePanel.PRESENT_PROCESS.equals(this.nodeSelect.getSelected())) {
			this.setUploadPath(fileComparePanel.getFileUploadPath()); 
			compareFileNavigator.setUploadPath(this.getUploadPath());
			compareFileNavigator.setSelectType(CompareImportFilePanel.PRESENT_PROCESS);
			compareFileNavigator.load();
			
			return new Object[] { new Refresh(compareFileNavigator) };
			
		} else if (CompareImportFilePanel.ORIGIN_PROCESS.equals(this.nodeSelect.getSelected())) {
			String projectId = MetadataBundle.getProjectId();
			String mainPath = MetadataBundle.getProjectBasePath(projectId);
			
			compareFileNavigator.setUploadPath(mainPath);
			compareFileNavigator.setSelectType(CompareImportFilePanel.ORIGIN_PROCESS);
			compareFileNavigator.load();
			
			return new Object[] { new Refresh(compareFileNavigator) };
			
		} else {
			
			return null;
		}
		
	}
}
