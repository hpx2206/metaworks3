package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.annotation.AutowiredFromClient;

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
	
	public void load(){
		processViewer = new ProcessViewer();
		processViewer.setDefId(defId);
		processViewer.setAlias(alias);
		processViewer.setViewType(viewType);
		processViewer.load();
	}
	@AutowiredFromClient
	public ProcessNavigatorPanel processNavigatorPanel;
	
}
