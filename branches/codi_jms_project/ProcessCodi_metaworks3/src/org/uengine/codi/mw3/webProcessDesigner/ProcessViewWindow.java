package org.uengine.codi.mw3.webProcessDesigner;

public class ProcessViewWindow {

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
	ProcessNavigatorPanel processNavigatorPanel;
		public ProcessNavigatorPanel getProcessNavigatorPanel() {
			return processNavigatorPanel;
		}
		public void setProcessNavigatorPanel(ProcessNavigatorPanel processNavigatorPanel) {
			this.processNavigatorPanel = processNavigatorPanel;
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
		
	//DefinitionViewWindow의 Layout Setting
	public void load() throws Exception{
		processNavigatorPanel = new ProcessNavigatorPanel();
		processNavigatorPanel.setDefId(defId);
		processNavigatorPanel.setAlias(alias);
		processNavigatorPanel.load();
		
		processViewPanel = new ProcessViewPanel();
		processViewPanel.setDefId(defId);
		processViewPanel.setAlias(alias);
		processViewPanel.setViewType("definitionView");
		processViewPanel.load();
		
		processAttributePanel = new ProcessAttributePanel();
		processAttributePanel.setDocumentation(null);
		processAttributePanel.setDefId(defId);
		if( processViewPanel.processViewer != null ){
			processAttributePanel.load(processViewPanel.processViewer.getProcessDesignerContainer());
		}
	}
	
	public void loadByProcess(HistoryItem historyItem) throws Exception{
		
		processNavigatorPanel.historyList.add(historyItem);
		
		processViewPanel = new ProcessViewPanel();
		processViewPanel.setDefId(historyItem.getDefId());
		processViewPanel.setAlias(historyItem.getAlias());
		processViewPanel.setViewType("definitionView");
		processViewPanel.load();
		
		processAttributePanel.setDocumentation(null);
		processAttributePanel.setDefId(historyItem.getDefId());
		if( processViewPanel.processViewer != null ){
			processAttributePanel.load(processViewPanel.processViewer.getProcessDesignerContainer());
		}
		
	}

}
