package org.uengine.codi.mw3.processexplorer;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.ProcessAttributePanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewPanel;

@Face(displayName="운영절차")
public class ProcessExploreContent{
	
	@AutowiredFromClient
	transient public Session session;
	
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
	String path;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}

	ProcessViewPanel processViewPanel;
		public ProcessViewPanel getProcessViewPanel() {
			return processViewPanel;
		}
		public void setProcessViewPanel(ProcessViewPanel processViewPanel) {
			this.processViewPanel = processViewPanel;
		}
	
	ProcessAttributePanel processAttributePanel;
		public ProcessAttributePanel getProcessAttributePanel() {
			return processAttributePanel;
		}
		public void setProcessAttributePanel(ProcessAttributePanel processAttributePanel) {
			this.processAttributePanel = processAttributePanel;
		}
		
	ProcessSubAttributePanel  processSubAttributePanel;
		public ProcessSubAttributePanel getProcessSubAttributePanel() {
			return processSubAttributePanel;
		}
		public void setProcessSubAttributePanel(
				ProcessSubAttributePanel processSubAttributePanel) {
			this.processSubAttributePanel = processSubAttributePanel;
		}

	ProcessFormPanel processFormPanel;
		public ProcessFormPanel getProcessFormPanel() {
			return processFormPanel;
		}
		public void setProcessFormPanel(ProcessFormPanel processFormPanel) {
			this.processFormPanel = processFormPanel;
		}
		
	public ProcessExploreContent(){
		processViewPanel = new ProcessViewPanel();
		processAttributePanel = new ProcessAttributePanel();
		processSubAttributePanel = new ProcessSubAttributePanel();
		processFormPanel = new ProcessFormPanel();
	}
	
	
	public void load() throws Exception{
		processViewPanel.setDefId(defId);
		processViewPanel.setAlias(alias);
		processViewPanel.setViewType("definitionView");
		processViewPanel.load();
		
//			ProcessFormPanel processFormPanel = new ProcessFormPanel();
		
		processAttributePanel.setDocumentation(null);
		processAttributePanel.setDefId(defId);
		if( processViewPanel.processViewer != null ){
			processAttributePanel.load(processViewPanel.processViewer.getProcessDesignerContainer());
		}
		
		processSubAttributePanel.setDocumentationSub(null);
		processSubAttributePanel.setDefId(defId);
		if( processViewPanel.processViewer != null ){
			processSubAttributePanel.load(processViewPanel.processViewer.getProcessDesignerContainer());
		}
		
		
	}
}
