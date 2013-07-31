package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

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
		
	@AutowiredFromClient
	public ProcessNavigatorPanel processNavigatorPanel;
	
	// 자기 defId를 가지고 자신을 refresh 한다.
	public Object[] refresh(String defId, String defName) {
		this.setDefId(defId);
//		processNavigatorPanel.add(defId, defName);
		
		ProcessAttributePanel processAttributePanel = new ProcessAttributePanel();
		processAttributePanel.setDefId(defId);
		processAttributePanel.load();
		
		return new Object[]{new Refresh(this) ,new Refresh(processNavigatorPanel) ,new Refresh(processAttributePanel)};
		
	}
	
	public void load(){
		processViewer = new ProcessViewer();
		processViewer.setDefId(defId);
		processViewer.setAlias(alias);
		processViewer.setViewType(viewType);
		processViewer.load();
	}
	
}
