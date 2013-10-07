package org.uengine.codi.mw3.ide.compare;

import java.io.File;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.metadata.MetadataBundle;
import org.uengine.codi.mw3.model.FileImporter;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.kernel.GlobalContext;

public class CompareNavigator {
	
	public static final String SERVER_PATH = "server";
	public static final String UPLOAD_PATH = "uploaded";

	CompareFileNavigator originFileNavigator; 
		public CompareFileNavigator getOriginFileNavigator() {
			return originFileNavigator;
		}
		public void setOriginFileNavigator(CompareFileNavigator originFileNavigator) {
			this.originFileNavigator = originFileNavigator;
		}

	CompareFileNavigator importFileNavigator; 
		public CompareFileNavigator getImportFileNavigator() {
			return importFileNavigator;
		}
		public void setImportFileNavigator(CompareFileNavigator importFileNavigator) {
			this.importFileNavigator = importFileNavigator;
		}

	SelectBox nodeSelect;
		public SelectBox getNodeSelect() {
			return nodeSelect;
		}
		public void setNodeSelect(SelectBox nodeSelect) {
			this.nodeSelect = nodeSelect;
		}
	String fileUploadPath;
		public String getFileUploadPath() {
			return fileUploadPath;
		}
		public void setFileUploadPath(String fileUploadPath) {
			this.fileUploadPath = fileUploadPath;
		}
		
	public void load() throws Exception{
		
		String fileSystemPath = GlobalContext.getPropertyString("filesystem.path", "filesystem.path");
		String uploadedDirectory = fileSystemPath + CompareNavigator.UPLOAD_PATH;
		File directory = new File(uploadedDirectory);
		if(!directory.isDirectory()) {
			directory.mkdirs();
		}
		this.fileUploadPath = uploadedDirectory;
		
		originFileNavigator = new CompareFileNavigator();
		originFileNavigator.setId(CompareOriginFilePanel.FILE_LOCATION);
		originFileNavigator.load();
		
		
		nodeSelect = new SelectBox();
		nodeSelect.add("업로드 폴더", CompareNavigator.UPLOAD_PATH);
		nodeSelect.add("서버 폴더", CompareNavigator.SERVER_PATH);
		nodeSelect.setId("selectNode"); 
		
		importFileNavigator = new CompareFileNavigator();
		importFileNavigator.setId(CompareImportFilePanel.FILE_LOCATION);
		importFileNavigator.setSelectType(CompareNavigator.UPLOAD_PATH);
		importFileNavigator.setUploadPath(uploadedDirectory);
		importFileNavigator.load();
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Popup importFile() throws Exception{
		
		FileImporter fileImporter = new FileImporter();
		fileImporter.setFileUploadPath(this.getFileUploadPath());
		
		Popup popup = new Popup();
		popup.setPanel(fileImporter);
		popup.setMetaworksContext(new MetaworksContext());
		popup.getMetaworksContext().setWhen("edit");
		  
		return popup;
	 }
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] changeFileNavigator() throws Exception {
		if (CompareNavigator.UPLOAD_PATH.equals(this.nodeSelect.getSelected())) {
			importFileNavigator.setUploadPath(this.getFileUploadPath());
		} else{
			String projectId = MetadataBundle.getProjectId();
			String mainPath = MetadataBundle.getProjectBasePath(projectId);
			importFileNavigator.setUploadPath(mainPath);
		}
		importFileNavigator.setSelectType(this.nodeSelect.getSelected());
		importFileNavigator.load();
		
		return new Object[] { new Refresh(importFileNavigator) };
	}
}
