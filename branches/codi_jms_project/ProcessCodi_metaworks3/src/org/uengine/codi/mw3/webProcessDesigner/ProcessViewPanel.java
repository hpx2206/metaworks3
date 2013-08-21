package org.uengine.codi.mw3.webProcessDesigner;

import java.io.File;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.metadata.MetadataBundle;
import org.uengine.kernel.GlobalContext;
import org.uengine.util.UEngineUtil;

public class ProcessViewPanel {
		
	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	String viewType;
		public String getViewType() {
			return viewType;
		}
		public void setViewType(String viewType) {
			this.viewType = viewType;
		}
		
	ProcessViewer processViewer;
		public ProcessViewer getProcessViewer() {
			return processViewer;
		}
		public void setProcessViewer(ProcessViewer processViewer) {
			this.processViewer = processViewer;
		}
	public ProcessViewPanel(){
		processViewer = new ProcessViewer();
	}
	public void load(){
		if(UEngineUtil.isNotEmpty(alias)  && UEngineUtil.isNotEmpty(defId)){
			String codebase = GlobalContext.getPropertyString("codebase", "codebase");
			processViewer.setDefId(defId);
			processViewer.setAlias(codebase + File.separatorChar + alias);
			processViewer.setViewType(viewType);
			processViewer.load();
		}
	}
	@AutowiredFromClient
	public ProcessNavigatorPanel processNavigatorPanel;
	
}
