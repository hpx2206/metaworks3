package org.uengine.codi.mw3.processexplorer;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.Documentation;
import org.uengine.codi.mw3.webProcessDesigner.ProcessAttributePanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContainer;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDetailPanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessSummaryPanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewPanel;

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
	ProcessDetailPanel processDetailPanel;
		public ProcessDetailPanel getProcessDetailPanel() {
			return processDetailPanel;
		}
		public void setProcessDetailPanel(ProcessDetailPanel processDetailPanel) {
			this.processDetailPanel = processDetailPanel;
		}
	ProcessSummaryPanel processSummaryPanel;
		public ProcessSummaryPanel getProcessSummaryPanel() {
			return processSummaryPanel;
		}
		public void setProcessSummaryPanel(ProcessSummaryPanel processSummaryPanel) {
			this.processSummaryPanel = processSummaryPanel;
		}

	public ProcessExploreContent(){
		processViewPanel = new ProcessViewPanel();
		processDetailPanel = new ProcessDetailPanel();
		processSummaryPanel = new ProcessSummaryPanel();
	}
	
	public void load() throws Exception{
		processViewPanel.setDefId(defId);
		processViewPanel.setAlias(alias);
		processViewPanel.setViewType("definitionView");
		processViewPanel.load();
		
		ProcessDesignerContainer processDesignerContainer = processViewPanel.processViewer.getProcessDesignerContainer();
		
		processDetailPanel.setDocumentation(processDesignerContainer.getProcessDetailPanel().getDocumentation());
		getProcessDetailPanel().getMetaworksContext().setHow("snsView");
		getProcessDetailPanel().getDocumentation().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		processSummaryPanel.setDetailList(processDesignerContainer.getProcessSummaryPanel().getDetailList());
		getProcessSummaryPanel().getMetaworksContext().setHow("snsView");
		getProcessSummaryPanel().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] startProcess() throws Exception{
		
		return new Object[]{};
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] startPrint() throws Exception{
		
		return new Object[]{};
	}
}
