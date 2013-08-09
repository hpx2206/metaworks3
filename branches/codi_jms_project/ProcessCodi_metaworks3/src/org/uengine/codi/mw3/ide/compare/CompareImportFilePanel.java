package org.uengine.codi.mw3.ide.compare;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.metadata.MetadataBundle;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.mw3.model.FileImporter;
import org.uengine.codi.mw3.model.Popup;

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
	MetaworksFile metaworksFile;
		public MetaworksFile getMetaworksFile() {
			return metaworksFile;
		}
		public void setMetaworksFile(MetaworksFile metaworksFile) {
			this.metaworksFile = metaworksFile;
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
	
	
	@ServiceMethod(inContextMenu=true, target=ServiceMethodContext.TARGET_POPUP)
	public Popup importFile() throws Exception{
		
		String projectId = MetadataBundle.getProjectId();
		String mainPath = MetadataBundle.getProjectBasePath(projectId);
		
		FileImporter fileImporter = new FileImporter();
		fileImporter.setParentDirectory(mainPath);
	  
		Popup popup = new Popup();
		popup.setPanel(fileImporter);
		popup.setMetaworksContext(new MetaworksContext());
		popup.getMetaworksContext().setWhen("edit");
		  
		return popup;
	 }
}
