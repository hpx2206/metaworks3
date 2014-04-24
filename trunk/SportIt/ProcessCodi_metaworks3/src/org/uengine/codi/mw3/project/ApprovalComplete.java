package org.uengine.codi.mw3.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.metaworks.annotation.Face;
import org.uengine.codi.ITool;
import org.uengine.codi.vm.JschCommand;
import org.uengine.kernel.GlobalContext;

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
		
/*		Map map = (Map)TransactionContext.getThreadLocalInstance().getSharedContext(ITOOL_MAP_KEY);
		ProcessManagerRemote processManager = (ProcessManagerRemote)map.get(ITOOL_PROCESS_MANAGER_KEY);
		
		String instId = (String)map.get(ITOOL_INSTANCEID_KEY);
		
		
		if(processManager != null && processManager.getProcessVariable(instId.toString(), "", "vm_ip") != null){
			Instance instance = new Instance();
			instance.setInstId(Long.parseLong(instId));
			String topicId = instance.databaseMe().getTopicId();
			
			WfNode wfNode = new WfNode();
			wfNode.setId(topicId);
			String projectName = wfNode.databaseMe().getName();
			
			String host = GlobalContext.getPropertyString("vm.manager.ip","localhost");
			String userId = GlobalContext.getPropertyString("vm.manager.user","root");
			String passwd = GlobalContext.getPropertyString("vm.manager.password","root");
			
			String vmName = (String)((Serializable)processManager.getProcessVariable(instId.toString(), "", "vm_name"));

			String paramProjectName = "\"" + projectName + "_" + vmName + "\"";
			String scriptHudsonCreateJob = GlobalContext.getPropertyString("vm.hudson.createJob","/home/hudson/script/hudsonMakeJob.sh");
			String scriptHudsonSetSvnInfo = GlobalContext.getPropertyString("vm.hudson.setting","/home/hudson/script/hudsonSetting.sh");*/
			
			// TODO: KIAT 에서 활성화
			/*
			JschCommand jschServerBehaviour = new JschCommand();
			jschServerBehaviour.sessionLogin(host, userId, passwd);			
			jschServerBehaviour.runCommand(scriptHudsonCreateJob + " " + paramProjectName);		// hudson job 생성
			jschServerBehaviour.getJschSession().disconnect();
			
			jschServerBehaviour.runCommand(scriptHudsonSetSvnInfo + " \"" + paramProjectName +  "\"");	// hudson job 에 svn 정보 설정

			*/
			
			
			
//			command = GlobalContext.getPropertyString("vm.hudson.setting","/home/hudson/script/hudsonSetting.sh") + " \"" + projectName + "\"" + " \"" + GlobalContext.getPropertyString("vm.server.ip") + "\"";
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
			
		//}
	}

	@Override
	public void afterComplete() throws Exception {
		
		String targetUserId = GlobalContext.getPropertyString("vm.target.user","root");
		String targetPassword= GlobalContext.getPropertyString("vm.target.password","root");
		
		JschCommand jschServerBehaviour = new JschCommand();
		jschServerBehaviour.sessionLogin("192.168.212.77", targetUserId, targetPassword);		
		
		String command = "/ssw/data/haproxy.sh add_backend.sh https " + this.getServerName() + " "+ this.getServerIp() + " 8080";
		
	}

	protected void createDatabase(String parameter) {
		
		String ip = GlobalContext.getPropertyString("pole.call.ip","localhost");
		String port = GlobalContext.getPropertyString("pole.call.port","80");
		String db  = GlobalContext.getPropertyString("pole.call.db","/root");
		
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
