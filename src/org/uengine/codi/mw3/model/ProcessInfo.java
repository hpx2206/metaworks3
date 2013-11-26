package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.codi.mw3.processexplorer.ProcessExploreContent;
import org.uengine.codi.mw3.webProcessDesigner.IProcessTopicMapping;
import org.uengine.codi.mw3.webProcessDesigner.ProcessTopicMapping;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewPanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewer;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewerPanel;
import org.uengine.processmanager.ProcessManagerRemote;

public class ProcessInfo extends GroupInfo{

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
	public ProcessInfo(){
		
	}
	
	public ProcessInfo(Session session){
		this.session = session;
		this.setProcessViewer(new ProcessViewer());
		this.setId(session.getLastSelectedItem());
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	

	@Override
	public void load() throws Exception {
		this.followersLoad();
		WfNode wfNode = new WfNode();
		wfNode.setId(this.getId());
		
		try {
			wfNode.copyFrom(wfNode.databaseMe());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setName(wfNode.getName());
		
		MetaworksFile logoFile = new MetaworksFile();
		logoFile.setUploadedPath(wfNode.getUrl());
		logoFile.setFilename(wfNode.getThumbnail());
		this.setLogoFile(logoFile);
		
		ProcessTopicMapping ptm = new ProcessTopicMapping();
		ptm.setTopicId(this.getId());
		IProcessTopicMapping findptm = ptm.findByTopicId();
		if(findptm==null){
			throw new Exception("wrong access");
		}
	
		processViewer.setDefId(this.getTitle());
		processViewer.setAlias(findptm.getProcessPath());
		processViewer.setViewType("definitionView");
		processViewer.load();

	}
	
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="$processinfo.showdetail")
	public ModalWindow action() throws Exception{
		
		ProcessTopicMapping ptm = new ProcessTopicMapping();
		ptm.setTopicId(this.getId());
		IProcessTopicMapping findptm = ptm.findByTopicId();
		if(findptm==null){
			throw new Exception("wrong access");
		}
		
		ProcessExploreContent processExploreContent = new ProcessExploreContent();
		processExploreContent.setAlias(findptm.getProcessPath());
		processExploreContent.setDefId(this.getTitle());
//		processExploreContent.setPath(this.getPath());
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
		ptm.setTopicId(this.getId());
		IProcessTopicMapping findptm = ptm.findByTopicId();
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
			return new Object[]{ new Refresh(instanceViewContent)};
		}
	}
	
}
