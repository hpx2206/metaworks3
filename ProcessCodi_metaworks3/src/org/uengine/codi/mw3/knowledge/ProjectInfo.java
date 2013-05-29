
package org.uengine.codi.mw3.knowledge;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;

import org.directwebremoting.io.FileTransfer;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.Download;
import org.metaworks.website.OpenBrowser;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.hudson.HudsonJobApi;
import org.uengine.codi.hudson.HudsonJobDDTO;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.project.VMRequest;
import org.uengine.codi.vm.JschCommand;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", options={"fieldOrder", "methodOrder"}, values={"projectName,description,ip,templateName,hudson,svn", "eclipseDownload,devSendbox,vmDownload,deploy"})
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
	
	@Hidden
	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}

	@Face(displayName="$ProjectName")
	String projectName;
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
	
	@Hidden
	String os;
		public String getOs() {
			return os;
		}
		public void setOs(String os) {
			this.os = os;
		}
		
	@Hidden
	String db;
		public String getDb() {
			return db;
		}
		public void setDb(String db) {
			this.db = db;
		}
			
	@Hidden
	String was;
		public String getWas() {
			return was;
		}
		public void setWas(String was) {
			this.was = was;
		}
		
	@Hidden
	String vm;
		public String getVm() {
			return vm;
		}
		public void setVm(String vm) {
			this.vm = vm;
		}
		
	@Face(displayName="$Svn")
	String svn;
		public String getSvn() {
			return svn;
		}
		public void setSvn(String svn) {
			this.svn = svn;
		}

	@Hidden
	String ci;
		public String getCi() {
			return ci;
		}
		public void setCi(String ci) {
			this.ci = ci;
		}
	
	@Face(displayName="$ProjectDescription")
	String description;
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	
	@Face(displayName="@Hudson")
	String hudson;
		public String getHudson() {
			return hudson;
		}
		public void setHudson(String hudson) {
			this.hudson = hudson;
		}
	
	@Face(displayName="@TemplateName")
	String templateName;
		public String getTemplateName() {
			return templateName;
		}
		public void setTemplateName(String templateName) {
			this.templateName = templateName;
		}
		
	@Face(displayName="$Ip")
	String ip;
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
	
	@Hidden
	String vmDouwnUrl;
		public String getVmDouwnUrl() {
			return vmDouwnUrl;
		}
		public void setVmDouwnUrl(String vmDouwnUrl) {
			this.vmDouwnUrl = vmDouwnUrl;
		}
		
		
	public void load() throws Exception {
		
		String projectId = session.getLastSelectedItem();
		
		
/*		IWfNode wfNode = (IWfNode)Database.sql(IWfNode.class, 
				"select * from bpm_knol where id = ?id");
		wfNode.set("id", projectId);
		wfNode.select();*/
		
		WfNode wfNode = new WfNode();
		wfNode.setId(projectId);
		wfNode.copyFrom(wfNode.databaseMe());
		
			if(wfNode.getLinkedInstId() != null){
				String linkedId = String.valueOf(wfNode.getLinkedInstId());
				
				this.projectName = wfNode.getName();
				this.description = wfNode.getDescription();
	
				Serializable serial = null;
				serial = processManager.getProcessVariable(linkedId, "", "VMRequest");
				if(serial instanceof VMRequest) {
					VMRequest vmRequest = (VMRequest)serial;
					this.templateName = vmRequest.getVmImageCombo().getSelectedText();
				}
				
				this.ip = (String)(Serializable)processManager.getProcessVariable(linkedId, "", "vm_ip");
				
				this.svn = "svn://" + GlobalContext.getPropertyString("vm.manager.ip") + "/" + this.projectName;
				this.hudson = GlobalContext.getPropertyString("vm.hudson.url");
			}
		
	}
	
	/*
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
	*/
	
	@Face(displayName="$Deploy")
	@ServiceMethod(callByContent=true)
	public Object deploy() throws Exception{
		
//		callURL(hudsonReload());
//		callURL(hudsonBuild());
		
		String host = GlobalContext.getPropertyString("vm.manager.ip");
		String userId = GlobalContext.getPropertyString("vm.manager.user");
		String passwd = GlobalContext.getPropertyString("vm.manager.password");

		String targetUserId = GlobalContext.getPropertyString("vm.target.user");
		String targetPassword= GlobalContext.getPropertyString("vm.target.password");

		JschCommand jschServerBehaviour = new JschCommand();
		jschServerBehaviour.sessionLogin(host, userId, passwd);
		
		String scriptHudsonSetting = GlobalContext.getPropertyString("vm.hudson.setting");
		String scriptHudsonBuild = GlobalContext.getPropertyString("vm.hudson.build");
		String scriptFilePermission = GlobalContext.getPropertyString("vm.permission");
		String scriptTargetStartUp = GlobalContext.getPropertyString("vm.target.startup");
		
		String paramProjectName = "\"" + this.getProjectName() + "\"";
		String paramIP = "\"" + this.getIp() + "\"";
		
		String hudsonURL = GlobalContext.getPropertyString("vm.hudson.url");
		String nextBuilderNumber = null;
		String builderResult = null;
		
		long timeoutTime = 200000;
		long sleepTime = 5000;
		long tryTime = 0;
		
		/*
		this.setProjectName("paastest6");
		this.setIp("192.168.212.64");
 
		//<!-- temp work
		jschServerBehaviour.runCommand(GlobalContext.getPropertyString("vm.svn.createProject") + " " + paramProjectName);
		jschServerBehaviour.runCommand(GlobalContext.getPropertyString("vm.svn.setting") + " " + paramProjectName);
		jschServerBehaviour.runCommand(GlobalContext.getPropertyString("vm.svn.createUser") + " " + paramProjectName + " " + "paasManager admin");
		jschServerBehaviour.runCommand(GlobalContext.getPropertyString("vm.hudson.createJob") + " " + paramProjectName);
		//--> temp work
		
		if(true)
			return new Remover(this);

		*/
		
		jschServerBehaviour.runCommand(scriptHudsonSetting + " " +paramProjectName + " " + paramIP);
		
		HudsonJobApi hudsonJobApi = new HudsonJobApi();

		while(nextBuilderNumber == null){
			HudsonJobDDTO hudsonJobDDTO = hudsonJobApi.hudsonJobApiXmlParser(hudsonURL, this.getProjectName());
			
			nextBuilderNumber = hudsonJobDDTO.getNextBuilderNumber();
			System.out.println("nextBuilderNumber :" + nextBuilderNumber);
			try {
				tryTime += sleepTime;
				Thread.sleep(sleepTime);
			} catch (Exception e) {
			}
			
			if(tryTime > timeoutTime)
				break;
		}
		
		// build hudson
		jschServerBehaviour.runCommand(scriptHudsonBuild + " " + paramProjectName);
		
		while(builderResult == null){
			HudsonJobDDTO hudsonJobDDTO = hudsonJobApi.hudsonJobApiXmlParser(hudsonURL, this.getProjectName());
			
			if(nextBuilderNumber.equals(hudsonJobDDTO.getLastSuccessfulBuild().getNumber()))
				builderResult = "SUCCESS";
			else if(nextBuilderNumber.equals(hudsonJobDDTO.getLastUnSuccessfulBuild().getNumber()))
				builderResult = "UNSUCCESS";
			else if(nextBuilderNumber.equals(hudsonJobDDTO.getLastFailedBuild().getNumber()))
				builderResult = "FAILED";
			
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
			}
		}

		if(jschServerBehaviour.getJschSession() != null)
			jschServerBehaviour.getJschSession().disconnect();

		
		if(builderResult.equals("SUCCESS")){
			
			jschServerBehaviour.sessionLogin(this.getIp(), targetUserId, targetPassword);
			
			jschServerBehaviour.runCommand(scriptFilePermission);
			jschServerBehaviour.runCommand(scriptTargetStartUp + " " + this.getProjectName());
			
			if(jschServerBehaviour.getJschSession() != null)
				jschServerBehaviour.getJschSession().disconnect();
		}
		
		return new Remover(this);
		
	}
	
	@Face(displayName="$OpenDevSandBox")
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Download devSendbox() throws Exception{
		String fileSystemPath = GlobalContext.getPropertyString("filesystem.path",".");
		String sendboxPath = fileSystemPath + "/resource/sandbox_final.ova";
		
		return new Download(new FileTransfer(new String("sandbox_final.ova".getBytes("UTF-8"),"ISO8859_1"), null,  new FileInputStream(sendboxPath)));		
	}
	
	@Face(displayName="$DownEclipse")
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Download eclipseDownload() throws Exception{
		String fileSystemPath = GlobalContext.getPropertyString("filesystem.path",".");
		String sendboxPath = fileSystemPath + "/resource/govFramEclpse64.zip";
		
		return new Download(new FileTransfer(new String("govFramEclpse64.zip".getBytes("UTF-8"),"ISO8859_1"), null,  new FileInputStream(sendboxPath)));		
	}
	
	@Face(displayName="$VMDown")
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public OpenBrowser vmDownload() throws Exception{
		
		String url =  GlobalContext.getPropertyString("vm.download.url");
		
		return new OpenBrowser(url);
        
	}

	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	@Autowired
	public InstanceViewContent instanceViewContent;
	
	public String hudsonReload() throws Exception {
		return "http://192.168.12.212:8080/hudson/reload";
	}
	
	public String hudsonBuild() throws Exception {
		return "http://192.168.12.212:8080/hudson/job/" + this.getProjectName() + "/build";
	}
	
	public void callURL(String sUrl) throws Exception {
		URL url;
		URLConnection connection;
		InputStream is;
		InputStreamReader isr;
		BufferedReader br;
		try{
			
			url = new URL(sUrl);
			connection = url.openConnection();
			
			is = connection.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			String buf = null;
			
			while(true){
				buf = br.readLine();
				if(buf == null) break;
			}
			
		}catch(IOException ioe){
			System.err.println("IOException " + ioe);
			ioe.printStackTrace();
		}
	}
	
//	public static void main(String args[]){
//		
//		String urlString = "http://192.168.12.212:8080/hudson/reload";
//		
//		HttpURLConnection conn = null;
//		
//		 try {
//			   URL connectURI = new URL(urlString);
//			   
//			   conn = (HttpURLConnection) connectURI.openConnection();   
//			   conn.setDoInput(true);
//			   conn.setDoOutput(false);
//			   conn.setRequestMethod("GET");
//			   conn.connect();   
//			   
//			   if(conn.getResponseCode() != HttpURLConnection.HTTP_OK){
//				   throw new RuntimeException("Faild / HTTP error code : "+conn.getResponseCode());
//			   }else{
//				   System.out.println("success");
//			   }
//		 }catch(Exception e){
//			 e.printStackTrace();
//		 }
//			
//	}

}
