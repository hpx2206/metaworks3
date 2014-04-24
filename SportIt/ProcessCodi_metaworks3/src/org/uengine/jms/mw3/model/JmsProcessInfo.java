package org.uengine.jms.mw3.model;

import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.InstanceListPanel;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.NewInstancePanel;
import org.uengine.codi.mw3.model.ProcessInfo;
import org.uengine.codi.mw3.model.ProcessMap;
import org.uengine.codi.mw3.model.RoleMappingPanel;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.IProcessTopicMapping;
import org.uengine.codi.mw3.webProcessDesigner.ProcessTopicMapping;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewer;
import org.uengine.processmanager.ProcessManagerRemote;

public class JmsProcessInfo extends ProcessInfo{
	
	@Autowired
	public InstanceViewContent instanceViewContent;
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
		
	ProcessViewer processViewer;
		public ProcessViewer getProcessViewer() {
			return processViewer;
		}
		public void setProcessViewer(ProcessViewer processViewer) {
			this.processViewer = processViewer;
		}
	
	public JmsProcessInfo(){
		
	}
	
	public JmsProcessInfo(Session session)  throws Exception{
		super(session);
		this.setProcessViewer(new ProcessViewer());
	}

	public void load() throws Exception{
		
		ProcessTopicMapping ptm = new ProcessTopicMapping();
		IProcessTopicMapping findptm = null;
		ptm.setProcessPath(this.getDefId());
		findptm = ptm.findByProcess(this.getId() , "process");
		
		if(findptm==null){
			throw new Exception("wrong access");
		}
		
		processViewer.setDefId(this.getTitle());
		processViewer.setAlias(findptm.getProcessPath());
		processViewer.setViewType("definitionView");
		processViewer.setUseClassLoader(true);
		processViewer.load();

	}
	
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="$processinfo.showdetail")
	public ModalWindow action() throws Exception{
		
		ProcessTopicMapping ptm = new ProcessTopicMapping();
		ptm.setProcessPath(session.getLastSelectedItem());
		IProcessTopicMapping findptm = ptm.findByProcessPath();
		if(findptm==null){
			throw new Exception("wrong access");
		}
		
		ProcessExploreContent processExploreContent = new ProcessExploreContent();
		processExploreContent.setAlias(findptm.getProcessPath());
		processExploreContent.setDefId(findptm.getProcessPath());
		processExploreContent.session = session;
		processExploreContent.load();
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setPanel(processExploreContent);
		modalWindow.setWidth(700);
		modalWindow.setHeight(500);
		modalWindow.setTitle(this.getTitle());
		
		return modalWindow;
		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	@Face(displayName="$processinfo.execute")
	public Object[] startProcess() throws Exception{
		ProcessTopicMapping ptm = new ProcessTopicMapping();
		ptm.setProcessPath(session.getLastSelectedItem());
		IProcessTopicMapping findptm = ptm.findByProcessPath();
		if(findptm==null){
			throw new Exception("wrong access");
		}
		
		instanceViewContent.session = session;
		
		ProcessMap processMap = new ProcessMap();
		processMap.processManager = processManager;
		processMap.session = session;
		processMap.instanceView = instanceViewContent;
		processMap.setDefId(findptm.getProcessPath());
		
		String instId = processMap.initializeProcess();
				
		RoleMappingPanel roleMappingPanel = new RoleMappingPanel(processManager, findptm.getProcessPath(), session);
		roleMappingPanel.putRoleMappings(processManager, instId);
		processManager.executeProcess(instId);
		processManager.applyChanges();
		
		Instance instance = new Instance();
		instance.setInstId(new Long(instId));
		instance.databaseMe();
		
		instance.databaseMe().setInitiator(session.getUser());
		instance.databaseMe().setInitComCd(session.getCompany().getComCode());
		
		if( instanceViewContent == null ){
			instanceViewContent = new InstanceViewContent();
		}
		instanceViewContent.session = session;
		instanceViewContent.load(instance);

		return new Object[]{ new Refresh(instanceViewContent)};
	}

}
