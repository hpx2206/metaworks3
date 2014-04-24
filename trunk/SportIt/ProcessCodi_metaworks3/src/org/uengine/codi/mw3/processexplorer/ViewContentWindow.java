package org.uengine.codi.mw3.processexplorer;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.ProcessAttributePanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewPanel;

@Face(ejsPath="genericfaces/Window.ejs",
displayName="ViewContentWindow",
options={"hideLabels", "minimize"}, 
values={"true", "true"})
public class ViewContentWindow extends ContentWindow {
	String defId;
		@Hidden
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
		
	Long taskId;
		@Hidden
		public Long getTaskId() {
			return taskId;
		}
		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}

	String alias;
		@Hidden	
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	@AutowiredFromClient
	transient public Session session;
	
	ProcessAttributePanel processAttributePanel;
		public ProcessAttributePanel getProcessAttributePanel() {
			return processAttributePanel;
		}
		public void setProcessAttributePanel(ProcessAttributePanel processAttributePanel) {
			this.processAttributePanel = processAttributePanel;
		}
	ProcessViewPanel processViewPanel;
		@Hidden
		public ProcessViewPanel getProcessViewPanel() {
			return processViewPanel;
		}
		public void setProcessViewPanel(ProcessViewPanel processViewPanel) {
			this.processViewPanel = processViewPanel;
		}
		
		
	public ViewContentWindow() throws Exception{
		processAttributePanel = new ProcessAttributePanel();
		processViewPanel = new ProcessViewPanel();
	}
	
	public void load() throws Exception{
		processAttributePanel.setDocumentation(null);
		processAttributePanel.setDefId(defId);
		
		
		if( processViewPanel.processViewer != null ){
			processAttributePanel.load(processViewPanel.processViewer.getProcessDesignerContainer());
		}
	}
	
}
