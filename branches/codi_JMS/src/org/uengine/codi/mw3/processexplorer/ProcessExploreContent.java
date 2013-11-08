package org.uengine.codi.mw3.processexplorer;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.InstanceListPanel;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.NewInstancePanel;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.ProcessMap;
import org.uengine.codi.mw3.model.RoleMappingPanel;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDesignerContainer;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDetailPanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessSummaryPanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewPanel;
import org.uengine.processmanager.ProcessManagerRemote;

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
	
	@Autowired
	public ProcessManagerRemote processManager;

	@AutowiredFromClient
	public NewInstancePanel newInstancePanel;
	
	@Autowired
	public InstanceViewContent instanceViewContent;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] startProcess() throws Exception{
		instanceViewContent.session = session;
		
		ProcessMap processMap = new ProcessMap();
		processMap.processManager = processManager;
		processMap.session = session;
		processMap.instanceView = instanceViewContent;
		processMap.setDefId(alias);
		
		String instId = processMap.initializeProcess();
				
		RoleMappingPanel roleMappingPanel = new RoleMappingPanel(processManager, alias, session);
		roleMappingPanel.putRoleMappings(processManager, instId);
		processManager.executeProcess(instId);
		processManager.applyChanges();
		
		Instance instance = new Instance();
		instance.setInstId(new Long(instId));
		instance.databaseMe();
		
		if( instanceViewContent == null ){
			instanceViewContent = new InstanceViewContent();
		}
		instanceViewContent.session = session;
		instanceViewContent.load(instance);
		
		NewInstancePanel newInstancePanel =  new NewInstancePanel();
		newInstancePanel.session = session;
		newInstancePanel.load(session);
		
		InstanceListPanel instanceListPanel = new InstanceListPanel(session); //should return instanceListPanel not the instanceList only since there're one or more instanceList object in the client-side
		instanceListPanel.session = session;
		instanceListPanel.setNewInstancePanel(newInstancePanel);
		instanceListPanel.getInstanceList().load();

		if("sns".equals(session.getEmployee().getPreferUX())){
			return new Object[]{new Remover(new ModalWindow() , true) , new Refresh(instanceListPanel)};
		}else{
			return new Object[]{new Remover(new ModalWindow() , true) , new Remover(new Popup() , true), new Refresh(instanceListPanel), new Refresh(instanceViewContent)};
		}
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] startPrint() throws Exception{
		
		return new Object[]{};
	}
}
