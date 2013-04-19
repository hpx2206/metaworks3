package org.uengine.codi.mw3.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.knowledge.IWfNode;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

public class ApprovalComplete implements ITool  {

	String approvedUrl;
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
	
	protected void createDatabase(String createDbInfo) {
		
		String ip = GlobalContext.getPropertyString("pole.call.ip");
		String port = GlobalContext.getPropertyString("pole.call.port");
		String db  = GlobalContext.getPropertyString("pole.call.db");
		
		String parameter = "?db=" + createDbInfo;
		
		String sUrl = "http://" + ip + ":" + port + db + "/createDatabase" + parameter;
		
		URL url;					//URL 주소 객체
		URLConnection connection;	//URL접속을 가지는 객체
		InputStream is;				//URL접속에서 내용을 읽기위한 Stream
		InputStreamReader isr;
		BufferedReader br;
		
		try{
			
			//URL객체를 생성하고 해당 URL로 접속한다..
			url = new URL(sUrl);
			connection = url.openConnection();
			
			//내용을 읽어오기위한 InputStream객체를 생성한다..
			is = connection.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			//내용을 읽어서 화면에 출력한다..
			String buf = null;
			
			while(true){
				buf = br.readLine();
				if(buf == null) break;
				System.out.println(buf);
			}
			
		}catch(IOException ioe){
			System.err.println("IOException " + ioe);
			ioe.printStackTrace();
			System.exit(1);
		}
	}
		
}
