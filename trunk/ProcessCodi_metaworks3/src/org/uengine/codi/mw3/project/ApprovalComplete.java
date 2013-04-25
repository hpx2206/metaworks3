package org.uengine.codi.mw3.project;

import org.metaworks.annotation.Face;
import org.uengine.codi.ITool;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
		
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
*/		
}
