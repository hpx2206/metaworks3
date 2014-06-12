package org.uengine.codi.mw3.processexplorer;

import java.util.ArrayList;

import org.metaworks.annotation.Hidden;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.mw3.webProcessDesigner.Documentation;
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
		setDefId(defId);
	}
	public void load( ProcessDesignerContainer processDesignerContainer ) throws Exception {
	}
}
