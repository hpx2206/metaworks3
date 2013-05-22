package org.uengine.codi.mw3.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.sql.RowSet;

import org.metaworks.annotation.Face;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.knowledge.ITopicMapping;
import org.uengine.codi.mw3.knowledge.TopicMapping;
import org.uengine.codi.mw3.model.Employee;
import org.uengine.codi.vm.JschCommand;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs")
public class ApprovalComplete implements ITool  {

	String serverName;	
		public String getServerName() {
			return serverName;
		}
		public void setServerName(String serverName) {
			this.serverName = serverName;
		}

	String serverIp;
		public String getServerIp() {
			return serverIp;
		}
		public void setServerIp(String serverIp) {
			this.serverIp = serverIp;
		}

	String realmId;
		public String getRealmId() {
			return realmId;
		}
		public void setRealmId(String realmId) {
			this.realmId = realmId;
		}
	
	String imageId;
		public String getImageId() {
			return imageId;
		}
		public void setImageId(String imageId) {
			this.imageId = imageId;
		}
	
	String hardwareProfileId;
		public String getHardwareProfileId() {
			return hardwareProfileId;
		}
		public void setHardwareProfileId(String hardwareProfileId) {
			this.hardwareProfileId = hardwareProfileId;
		}
	
	@Override
	public void onLoad() throws Exception {
		
	}

	@Override
	public void beforeComplete() throws Exception {
		
		Map map = (Map)TransactionContext.getThreadLocalInstance().getSharedContext(ITOOL_MAP_KEY);
		ProcessManagerRemote processManager = (ProcessManagerRemote)map.get(ITOOL_PROCESS_MANAGER_KEY);
		
		String instId = (String)map.get(ITOOL_INSTANCEID_KEY);
		
		
		if(processManager != null && processManager.getProcessVariable(instId.toString(), "", "vm_ip") != null){
			String host = GlobalContext.getPropertyString("vm.manager.ip");
			String userId = GlobalContext.getPropertyString("vm.manager.user");
			String passwd = GlobalContext.getPropertyString("vm.manager.password");
			
			String vmDb = (String)((Serializable)processManager.getProcessVariable(instId.toString(), "", "vm_db"));
			String vmIp = (String)((Serializable)processManager.getProcessVariable(instId.toString(), "", "vm_ip"));
			String projectName = (String)((Serializable)processManager.getProcessVariable(instId.toString(), "", "projectName"));
			String projectId = (String)((Serializable)processManager.getProcessVariable(instId.toString(), "", "projectId"));
			
			String scriptSvnCreateProject = GlobalContext.getPropertyString("vm.svn.createProject");
			String scriptSvnSetting = GlobalContext.getPropertyString("vm.svn.setting");
			String scriptSvnInsertUser = GlobalContext.getPropertyString("vm.svn.createUser");
			String scriptHudsonCreateJob = GlobalContext.getPropertyString("vm.hudson.createJob");
			
			String paramProjectName = "\"" + projectName + "\"";
			

			
			JschCommand jschServerBehaviour = new JschCommand();
			jschServerBehaviour.sessionLogin(host, userId, passwd);
			
			//create SVN
			jschServerBehaviour.runCommand(scriptSvnCreateProject + " " + paramProjectName);
			//SVN setting
			jschServerBehaviour.runCommand(scriptSvnSetting + " " + paramProjectName);
			//svn프로젝트와 동일한 hudson job생성
			jschServerBehaviour.runCommand(scriptHudsonCreateJob + " " + paramProjectName);
			
			TopicMapping tp = new TopicMapping();
			tp.setTopicId(projectId);
			
			ITopicMapping participateUsers = tp.findUsers();
			while(participateUsers.next()){
				Employee employee = new Employee();
				employee.setEmpCode(participateUsers.getString("userId"));
				
				String password =  employee.findMe().getPassword();
				
				//vm 유저 추가
				String paramInsertUserId = "\"" + participateUsers.getString("userId") + "\"";
				String paramInsertPassword = "\"" + password + "\"";
				
				jschServerBehaviour.runCommand(scriptSvnInsertUser + " " + paramProjectName + " " + paramInsertUserId + " " + paramInsertPassword);
				
				//올챙이 류저 추가
				String parameter = "?db=" + vmDb + "&email=" + participateUsers.getString("userId") + "&url=" + vmIp + "&name=" + projectName;
				createDatabase(parameter);
			}
			
			jschServerBehaviour.getJschSession().disconnect();
			
			
			
			
//			command = GlobalContext.getPropertyString("vm.hudson.setting") + " \"" + projectName + "\"" + " \"" + GlobalContext.getPropertyString("vm.server.ip") + "\"";
//			jschServerBehaviour.runCommand(command);
			
			//filecommand롭 보내고
			//해당파일 실행하고 => commend만 날리면됨.
			//put-append local-path [remote-path]
			
			/*
			String[] commands = new String[3];
			commands[0] = GlobalContext.getPropertyString("vm.add.permission");			//전송된 파일에 권한 부여하기
			commands[1] = GlobalContext.getPropertyString("vm.remote.filepath") + GlobalContext.getPropertyString("vm.setting.adjustEnv");		//환경세팅 스크립트 파일 실행하기
			commands[2] = GlobalContext.getPropertyString("vm.remote.filepath") + GlobalContext.getPropertyString("vm.setting.adjustHosts");
			
			if(commands.length != 0){
				for(int i=0; i<commands.length; i++){
					jschServerBehaviour.runCommand(commands[i]);
				}
			}
			*/
			
		}
	}

	@Override
	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}

	protected void createDatabase(String parameter) {
		
		String ip = GlobalContext.getPropertyString("pole.call.ip");
		String port = GlobalContext.getPropertyString("pole.call.port");
		String db  = GlobalContext.getPropertyString("pole.call.db");
		
		String sUrl = "http://" + ip + ":" + port + db + "/createDatabase" + parameter;
		
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

}
