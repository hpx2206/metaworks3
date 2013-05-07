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
import org.springframework.beans.factory.annotation.Autowired;
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
			String vmDb = (String)((Serializable)processManager.getProcessVariable(instId.toString(), "", "vm_db"));
			String vmIp = (String)((Serializable)processManager.getProcessVariable(instId.toString(), "", "vm_ip"));
			String projectName = (String)((Serializable)processManager.getProcessVariable(instId.toString(), "", "projectName"));
			String projectId = (String)((Serializable)processManager.getProcessVariable(instId.toString(), "", "projectId"));
			
			
			JschCommand jschServerBehaviour = new JschCommand();
			
			//create SVN
			String command = GlobalContext.getPropertyString("vm.svn.createProject") + " \"" + projectName + "\"";
			jschServerBehaviour.runCommand(command);
			
			//SVN setting
			command = GlobalContext.getPropertyString("vm.svn.setting") + " \"" + projectName + "\"";
			jschServerBehaviour.runCommand(command);
			
			TopicMapping tp = new TopicMapping();
			tp.setTopicId(projectId);
			
			ITopicMapping participateUsers = tp.findUsers();
			
			RowSet rs = participateUsers.getImplementationObject().getRowSet();
			
			while(rs.next()) {
				
				Employee employee = new Employee();
				employee.setEmpCode(rs.getString("userId"));
				
				String password =  employee.findMe().getPassword();
				
				//vm 유저 추가
				command = GlobalContext.getPropertyString("vm.svn.createUser") + " \"" + projectName + "\" \"" + rs.getString("userId") + "\" \"" + password + "\"";
				jschServerBehaviour.runCommand(command);
				
				//올챙이 류저 추가
				String parameter = "?db=" + vmDb + "&email=" + rs.getString("userId") + "&url=" + vmIp + "&name=" + projectName;
				createDatabase(parameter);
				
			}
			
			//svn프로젝트와 동일한 hudson job생성
			command = GlobalContext.getPropertyString("vm.hudson.createJob") + " \"" + projectName + "\"";
			jschServerBehaviour.runCommand(command);
			
			command = GlobalContext.getPropertyString("vm.hudson.setting") + " \"" + projectName + "\"" + " \"" + GlobalContext.getPropertyString("vm.server.ip") + "\"";
			jschServerBehaviour.runCommand(command);
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
