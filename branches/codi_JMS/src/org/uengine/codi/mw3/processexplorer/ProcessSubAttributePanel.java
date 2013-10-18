package org.uengine.codi.mw3.processexplorer;

import java.util.ArrayList;

import org.metaworks.annotation.Hidden;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.mw3.webProcessDesigner.Documentation;
import org.uengine.codi.mw3.webProcessDesigner.DocumentationSub;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContainer;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewPanel;

public class ProcessSubAttributePanel {
	String defId;
	@Hidden
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
	ArrayList<MetaworksFile> fileList;
		public ArrayList<MetaworksFile> getFileList() {
			return fileList;
		}
		public void setFileList(ArrayList<MetaworksFile> fileList) {
			this.fileList = fileList;
		}
	DocumentationSub documentationSub;
	public DocumentationSub getDocumentationSub() {
		return documentationSub;
	}
	public void setDocumentationSub(DocumentationSub documentationSub) {
		this.documentationSub = documentationSub;
	}
	ProcessViewPanel processViewPanel;
		@Hidden
		public ProcessViewPanel getProcessViewPanel() {
			return processViewPanel;
		}
		public void setProcessViewPanel(ProcessViewPanel processViewPanel) {
			this.processViewPanel = processViewPanel;
		}
			
	public ProcessSubAttributePanel(){
		processViewPanel = new ProcessViewPanel();
		setDocumentationSub(null);
		setDefId(defId);
	}
	public void load( ProcessDesignerContainer processDesignerContainer ) throws Exception {
		/* 
		 * 파일을 뿌리게 되면.. ProcessViewWindow 를  AutowiredFromClient를 하였을때, marshalling data error 발생
		if( processDesignerContainer != null && processDesignerContainer.getActivityList() != null ){
			if( fileList == null ){
				fileList = new ArrayList<MetaworksFile>();
			}
			ArrayList<Activity> activityList = processDesignerContainer.getActivityList();
			for(Activity act : activityList){
				Documentation doc = act.getDocumentation();
				MetaworksFile file1 = doc.getAttachfile1();
				if (file1 != null && file1.getUploadedPath() != null
						&& file1.getFilename() != null){
					file1.setFileTransfer(new FileTransfer(new String(file1.getFilename().getBytes("UTF-8"),"ISO8859_1"), file1.getMimeType(), new FileInputStream(file1.overrideUploadPathPrefix() + "/" + file1.getUploadedPath())));
					fileList.add(file1);
				}
				MetaworksFile file2 = doc.getAttachfile2();
				if (file2 != null && file2.getUploadedPath() != null
						&& file2.getFilename() != null){
					file2.setFileTransfer(new FileTransfer(new String(file2.getFilename().getBytes("UTF-8"),"ISO8859_1"), file2.getMimeType(), new FileInputStream(file2.overrideUploadPathPrefix() + "/" + file2.getUploadedPath())));
					fileList.add(file2);
				}
				MetaworksFile file3 = doc.getAttachfile3();
				if (file3 != null && file3.getUploadedPath() != null
						&& file3.getFilename() != null){
					file3.setFileTransfer(new FileTransfer(new String(file3.getFilename().getBytes("UTF-8"),"ISO8859_1"), file3.getMimeType(), new FileInputStream(file3.overrideUploadPathPrefix() + "/" + file3.getUploadedPath())));
					fileList.add(file3);
				}
			}
		}
		*/
	}
}
