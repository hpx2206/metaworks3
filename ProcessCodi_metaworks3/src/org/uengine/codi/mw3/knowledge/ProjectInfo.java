package org.uengine.codi.mw3.knowledge;

import java.io.Serializable;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.InstanceListPanel;
import org.uengine.codi.mw3.model.InstanceView;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.Perspective;
import org.uengine.codi.mw3.model.ProcessMap;
import org.uengine.codi.mw3.model.RoleMappingPanel;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.project.ProjectCreate;
import org.uengine.codi.mw3.project.SVNEdit;
import org.uengine.codi.mw3.project.VMRequest;
import org.uengine.kernel.KeyedParameter;
import org.uengine.kernel.ResultPayload;
import org.uengine.processmanager.ProcessManagerRemote;

public class ProjectInfo implements ContextAware {
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	public ProjectInfo() {
		this.setMetaworksContext(new MetaworksContext());
	}
	
	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}

	String projectName;
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
		
	String os;
		public String getOs() {
			return os;
		}
		public void setOs(String os) {
			this.os = os;
		}
		
	String db;
		public String getDb() {
			return db;
		}
		public void setDb(String db) {
			this.db = db;
		}
			
	String was;
		public String getWas() {
			return was;
		}
		public void setWas(String was) {
			this.was = was;
		}
		
	String vm;
		public String getVm() {
			return vm;
		}
		public void setVm(String vm) {
			this.vm = vm;
		}
		
	String svn;
		public String getSvn() {
			return svn;
		}
		public void setSvn(String svn) {
			this.svn = svn;
		}

	String ci;
		public String getCi() {
			return ci;
		}
		public void setCi(String ci) {
			this.ci = ci;
		}
		
	public void load() throws Exception {
		
		this.projectName = "";
		this.os = "";
		this.db = "";
		this.was = "";
		this.vm = "http://192.168.0.225:8080/realcloud/";
//		this.svn = "";
//		this.ci = "";
		
		String projectId = session.getLastSelectedItem();
		
		IWfNode wfNode = (IWfNode)Database.sql(IWfNode.class, 
				"select * from bpm_knol where id = ?id");
		wfNode.set("id", projectId);
		wfNode.select();
		
		if(wfNode.size() > 0) {
			wfNode.next();
			
			String linkedId = String.valueOf(wfNode.getLinkedInstId());
			
			Serializable serial = null;
			
			serial = processManager.getProcessVariable(linkedId, "", "ProjectCreate");
			if(serial instanceof ProjectCreate) {
				ProjectCreate projectCreate = (ProjectCreate)serial;
				this.projectName = projectCreate.getName();
			}
			
			serial = processManager.getProcessVariable(linkedId, "", "VMRequest");
/*			if(serial instanceof VMRequest) {
				VMRequest vmRequest = (VMRequest)serial;
				this.os = vmRequest.getOsSelect().getSelectedText();
				this.db = vmRequest.getDbSelect().getSelectedText();
				this.was = vmRequest.getWasSelect().getSelectedText();
			}*/
			
//			serial = processManager.getProcessVariable(linkedId, "", "VMCreate");
//			if(serial instanceof VMCreate) {
//				VMCreate vmCreate = (VMCreate)serial;
//				this.vm = vmCreate.getIp();
//			}
//			
//			serial = processManager.getProcessVariable(linkedId, "", "SVNSetting");
//			if(serial instanceof SVNSetting) {
//				SVNSetting svnSetting = (SVNSetting)serial;
//				this.svn = svnSetting.getSvnUrl();
//				
//				metaworksContext.setHow("isSVNEdit");
//			}
//			
//			serial = processManager.getProcessVariable(linkedId, "", "CICreate");
//			if(serial instanceof CICreate) {
//				CICreate ciCreate = (CICreate)serial;
//				this.ci = ciCreate.getCiUrl();
//			}
		}
		
	}
	
	@Face(displayName="SVN수정")
//	@Available(when={MetaworksContext.WHEN_VIEW})
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] svnEdit() throws Exception{
		
		String defId = "SVNEdit.process";
		
		ProcessMap processMap = new ProcessMap();
		processMap.processManager = processManager;
		processMap.session = session;
		processMap.instanceView = instanceViewContent;
		processMap.setDefId(defId);
		
		String instId = processMap.initializeProcess();
		
		SVNEdit svnEdit = new SVNEdit();
		svnEdit.setInstId(instId);
		
		ResultPayload rp = new ResultPayload();
		rp.setProcessVariableChange(new KeyedParameter("SVNEdit", svnEdit));
		
		RoleMappingPanel roleMappingPanel = new RoleMappingPanel(processMap, session);
		roleMappingPanel.putRoleMappings(processManager, instId);
		
		processManager.executeProcess(instId);
		processManager.applyChanges();
		
		Instance instance = new Instance();
		instance.setInstId(new Long(instId));
		instance.databaseMe().setTopicId(session.getLastSelectedItem());
//		instance.databaseMe().setName(projectName);
		instance.databaseMe().setLastCmnt("SVN수정");
		instance.flushDatabaseMe();			
		
//		String title = "프로젝트: " + this.getTopicTitle();
		Object[] returnObj = Perspective.loadInstanceListPanel(session, "topic", session.getLastSelectedItem(), projectName);
		Object[] returnObject = new Object[ returnObj.length + 1];
		
		for (int i = 0; i < returnObj.length; i++) {
			if( returnObj[i] instanceof InstanceListPanel){
				returnObj[i] = new Refresh(returnObj[i]);
			}else{
				returnObj[i] = new Refresh(returnObj[i]);
			}			
		}
	
		InstanceView instanceView = instanceViewContent.instanceView;
		instanceView.session = session;

		instanceView.load(instance);
		
		returnObject[returnObj.length] = new Refresh(instanceViewContent);
		
		return returnObject;
	}

	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	@Autowired
	public InstanceViewContent instanceViewContent;

}
