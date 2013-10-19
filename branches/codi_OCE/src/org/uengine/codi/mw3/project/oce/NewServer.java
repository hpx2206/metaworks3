package org.uengine.codi.mw3.project.oce;

import java.util.Date;

import org.metaworks.EventContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToEvent;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.ide.Templete;
import org.uengine.codi.mw3.knowledge.CloudInfo;
import org.uengine.codi.mw3.knowledge.ProjectInfo;
import org.uengine.codi.mw3.model.Instance;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.ProcessMap;
import org.uengine.codi.mw3.model.RoleMappingPanel;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.KeyedParameter;
import org.uengine.kernel.ResultPayload;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(displayName="$project.server.add", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs")
public class NewServer extends Templete{
	
	@AutowiredFromClient
	transient public KtProjectServers ktProjectServers;
	
	@Autowired
	transient public ProcessManagerRemote processManager;

	@Autowired
	public InstanceViewContent instanceViewContent;
	
	@AutowiredFromClient
	transient public Session session;
	
	
	String projectId;
	@Hidden
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
		
	String projectName;
	@Face(displayName="$project.server.name")	
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

	String osType;
	@Face(displayName="$project.server.ostype", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", options={"리눅스(LINUX)","유닉스(UNIX)","Window NT"}, values={"LINUX", "UNIX", "Window"})
		public String getOsType() {
			return osType;
		}
		public void setOsType(String osType) {
			this.osType = osType;
		}
	String wasType;
	@Face(displayName="$project.server.wastype", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", options={"서버엔진 사용안함","Jboss 6.0","Tomcat 7.0"}, values={"사용안함", "Jboss", "Tomcat"})
		public String getWasType() {
			return wasType;
		}
		public void setWasType(String wasType) {
			this.wasType = wasType;
		}
	String dbType;
	@Face(displayName="$project.server.dbtype", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", options={"데이터베이스 사용안함","Cubrid","Mysql","Oracle"}, values={"사용안함", "Cubrid", "Mysql","Oracle"})
		public String getDbType() {
			return dbType;
		}
		public void setDbType(String dbType) {
			this.dbType = dbType;
		}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] finish() throws Exception {
		
		try {
			String defId = "projectProcess/projectCre2.process";
			
			ProcessMap processMap = new ProcessMap();
			processMap.processManager = processManager;
			processMap.session = session;
			processMap.instanceView = instanceViewContent;
			processMap.setDefId(defId);
			
			String instId = processMap.initializeProcess();
					
			ProjectCreate projectCreate = new ProjectCreate();
			projectCreate.setProjectId(this.getProjectId());
			projectCreate.setOsSelect(osType);
			projectCreate.setWasSelect(wasType);
			projectCreate.setDbSelect(dbType);
			
			ResultPayload rp = new ResultPayload();
			rp.setProcessVariableChange(new KeyedParameter("ProjectCreate", projectCreate));
			
			RoleMappingPanel roleMappingPanel = new RoleMappingPanel(processManager, processMap.getDefId(), session);
			roleMappingPanel.putRoleMappings(processManager, instId);
			
			processManager.executeProcess(instId);
//			processManager.getProcessInstance(instId).setBeanProperty("ProjectCreate", projectCreate);
			processManager.executeProcessByWorkitem(instId.toString(), rp);
			processManager.applyChanges();
			
			Instance instance = new Instance();
			instance.setInstId(new Long(instId));
			instance.databaseMe().setTopicId(this.getProjectId());
			instance.databaseMe().setName("개발환경요청" + " : " + this.getProjectName());
			instance.flushDatabaseMe();
			
			CloudInfo cloudInfo = new CloudInfo();
			cloudInfo.setId(cloudInfo.createNewId());
			cloudInfo.setProjectId(projectId);
			cloudInfo.setServerName(this.getProjectName());
			cloudInfo.setOsType(osType);
			cloudInfo.setWasType(wasType);
			cloudInfo.setDbType(dbType);
			cloudInfo.setModdate(new Date());
			cloudInfo.createDatabaseMe();
			
			cloudInfo.flushDatabaseMe();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ktProjectServers.loadOceServer();
		
		return new Object[]{new Remover(new ModalWindow()),  new Refresh(ktProjectServers) };
	}

	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Remover cancel(){
		return new Remover(new ModalWindow());
	}
}
