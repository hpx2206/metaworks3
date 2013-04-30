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
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	
	@Override
	public void onLoad() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeComplete() throws Exception {
		
		Map map = (Map)TransactionContext.getThreadLocalInstance().getSharedContext(ITOOL_MAP_KEY);
		processManager = (ProcessManagerRemote)map.get(ITOOL_PROCESS_MANAGER_KEY);
		
		String instId = (String)map.get(ITOOL_INSTANCEID_KEY);
		
		
		
		
		if(processManager != null && processManager.getProcessVariable(instId.toString(), "", "vm_ip") != null){
			String vmDb = (String)((Serializable)processManager.getProcessVariable(instId.toString(), "", "vm_db"));
			String vmIp = (String)((Serializable)processManager.getProcessVariable(instId.toString(), "", "vm_ip"));
			String projectName = (String)((Serializable)processManager.getProcessVariable(instId.toString(), "", "projectName"));
			String projectId = (String)((Serializable)processManager.getProcessVariable(instId.toString(), "", "projectId"));
			
			TopicMapping tp = new TopicMapping();
			tp.setTopicId(projectId);
			
			ITopicMapping participateUsers = tp.findUsers();
			
			RowSet rs = participateUsers.getImplementationObject().getRowSet();
			
			
			while(rs.next()) {
				String parameter = "?db=" + vmDb + "&email=" + rs.getString("userId") + "&url=" + vmIp + "&name=" + projectName;
				
				createDatabase(parameter);
			}
			
		}
		
	}

	@Override
	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	/*String approvedUrl;
		@Face(displayName="승인 : ")
		public String getApprovedUrl() {
			return approvedUrl;
		}
		public void setApprovedUrl(String approvedUrl) {
			String url = "승인된 URL : http://192.168.0.225:8080/realcloud/ ";
			this.approvedUrl = url;
		}
		
	String name;
		@NonLoadable
		public String getName() {
			return name;
		}	
		public void setName(String name) {
			this.name = name;
		}
		
	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}	
	
	@Autowired
	public ProcessManagerRemote processManager;

	@Override
	public void onLoad() throws Exception {
		Map map = (Map)TransactionContext.getThreadLocalInstance().getSharedContext(ITOOL_MAP_KEY);
		
		processManager = (ProcessManagerRemote)map.get(ITOOL_PROCESS_MANAGER_KEY);
		
		String projectId = this.getProjectId();
		
		IWfNode wfNode = (IWfNode)Database.sql(IWfNode.class, "select * from bpm_knol where id = ?id");
		wfNode.set("id", projectId);
		wfNode.select();
		
		
		String os = null;
		String db = null;
		String was = null;
		
		if(wfNode.size() > 0) {
			wfNode.next();
			
			String linkedId = String.valueOf(wfNode.getLinkedInstId());
			
			Serializable serial = null;
			
			serial = processManager.getProcessVariable(linkedId, "", "VMRequest");
			if(serial instanceof VMRequest) {
				VMRequest vmRequest = (VMRequest)serial;
				
				os = vmRequest.getOsSelect().getSelectedText();
				db = vmRequest.getDbSelect().getSelectedText();
				was = vmRequest.getWasSelect().getSelectedText();
			}
			
		}
		
		if(os != null && db != null && was != null) {
			createDatabase(db);
		}
	}

	@Override
	public void beforeComplete() throws Exception {
		
		
	}
		
	public void afterComplete() throws Exception {
		
	}
	*/
	
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
