package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.annotation.AutowiredFromClient;
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
	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}	
	public ProcessViewer processViewer;
		public ProcessViewer getProcessViewer() {
			return processViewer;
		}
		public void setProcessViewer(ProcessViewer processViewer) {
			this.processViewer = processViewer;
		}
		
	boolean useClassLoader;
		public boolean isUseClassLoader() {
			return useClassLoader;
		}
		public void setUseClassLoader(boolean useClassLoader) {
			this.useClassLoader = useClassLoader;
		}
		
	public ProcessViewPanel() throws Exception{
		processViewer = new ProcessViewer();
	}
	public void load(){
		if(UEngineUtil.isNotEmpty(alias)  && UEngineUtil.isNotEmpty(defId)){
			processViewer.setDefId(defId);
			processViewer.setAlias(alias);
			processViewer.setProjectId(projectId);
//			processViewer.setAlias(codebase + File.separatorChar + alias);
			processViewer.setViewType(viewType);
			processViewer.setUseClassLoader(useClassLoader);
			processViewer.load();
		}
	}
	@AutowiredFromClient
	public ProcessNavigatorPanel processNavigatorPanel;
	
}
