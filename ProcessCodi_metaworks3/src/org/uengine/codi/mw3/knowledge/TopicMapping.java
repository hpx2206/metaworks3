package org.uengine.codi.mw3.knowledge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.metaworks.annotation.NonLoadable;
import org.metaworks.dao.DAOFactory;
import org.metaworks.dao.Database;
import org.metaworks.dao.KeyGeneratorDAO;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.IDept;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

public class TopicMapping extends Database<ITopicMapping> implements ITopicMapping {
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	String topicId;
		public String getTopicId() {
			return topicId;
		}
		public void setTopicId(String topicId) {
			this.topicId = topicId;
		}
		
	Long topicMappingId;
		@Override
		public Long getTopicMappingId() {
			return topicMappingId;
		}
		@Override
		public void setTopicMappingId(Long topicMappingId) {
			this.topicMappingId = topicMappingId;
		}
		
	String userId;
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		
	String userName;
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
	
	int assigntype;
		public int getAssigntype() {
			return assigntype;
		}
		public void setAssigntype(int assigntype) {
			this.assigntype = assigntype;
		}

	String vmIp;
		@NonLoadable
		public String getVmIp() {
			return vmIp;
		}
		public void setVmIp(String vmIp) {
			this.vmIp = vmIp;
		}
		
	String vmDB;
		@NonLoadable
		public String getVmDB() {
			return vmDB;
		}
		public void setVmDB(String vmDB) {
			this.vmDB = vmDB;
		}

	
	public ITopicMapping saveMe() throws Exception {

		Map options = new HashMap();
		options.put("useTableNameHeader", "false");
		options.put("onlySequenceTable", "true");
		
		KeyGeneratorDAO kg = DAOFactory.getInstance(TransactionContext.getThreadLocalInstance()).createKeyGenerator("bpm_topicmapping", options);
		kg.select();
		kg.next();
		
		Number number = kg.getKeyNumber();
		
		setTopicMappingId(number.longValue());
		
		WfNode node = new WfNode();
		node.setId(this.getTopicId());
		Long instId = node.databaseMe().getLinkedInstId();

		
		final String DBTYPE_CUBRID = "cubrid";
		final String DBTYPE_ORACLE = "oracle";
		final String DBTYPE_MYSQL = "mysql";
		final String DBTYPE_MSSQL = "mssql";
		final String DBTYPE_MONGODB = "mongo";
		

		if(processManager != null && processManager.getProcessVariable(instId.toString(), "", "vm_ip") != null){
			String vmIp = (String)((Serializable)processManager.getProcessVariable(instId.toString(), "", "vm_ip"));
			String vmDb = (String)((Serializable)processManager.getProcessVariable(instId.toString(), "", "vm_db"));
			
			this.setVmIp(vmIp);
			this.setVmDB(vmDb);
			
			createDatabaseToTadpole();
			
		}
		
		return createDatabaseMe();
	}
	
	public ITopicMapping findUsers() throws Exception {
		
		StringBuffer selectbf = new StringBuffer();
		selectbf.append("select * from bpm_topicmapping where topicid=?topicId");
		
		ITopicMapping dao = (ITopicMapping) sql(ITopicMapping.class, selectbf.toString());
		dao.setTopicId( this.getTopicId());
		dao.select();
		
		return dao;
	}
	
	public ITopicMapping findByUser() throws Exception {
		StringBuffer selectbf = new StringBuffer();
		selectbf.append("select * from BPM_TOPICMAPPING ");
		selectbf.append(" where topicid = ?topicid");
		selectbf.append(" and userid = ?userid");
		
		ITopicMapping dao = (ITopicMapping) sql(ITopicMapping.class,	selectbf.toString());
		dao.setTopicId( this.getTopicId());
		dao.setUserId( this.getUserId());
		dao.select();
		
		return dao;
	}
	
	public IUser findUser() throws Exception {
		IUser users = (IUser) Database.sql(IUser.class, "select  userId, userName name from BPM_TOPICMAPPING  where topicId=?topicId and assigntype=?assigntype ");
		
		users.set("topicId", this.getTopicId() );
		users.set("assigntype", 0);
		users.select();
		
		return users;
	}
	public IDept findDept() throws Exception {
		IDept dept = (IDept) Database.sql(IDept.class, "select  userId as PARTCODE , userName as  PARTNAME  from BPM_TOPICMAPPING  where topicId=?topicId and assigntype=?assigntype ");
		
		dept.set("topicId", this.getTopicId() );
		dept.set("assigntype", 2);
		dept.select();
		
		return dept;
	}
	
	public void remove() throws Exception {
		deleteDatabaseMe();
	}
	
	protected void createDatabaseToTadpole() {
		
		String ip = GlobalContext.getPropertyString("pole.call.ip");
		String port = GlobalContext.getPropertyString("pole.call.port");
		String db  = GlobalContext.getPropertyString("pole.call.db");
		
		String parameter = "?db=" + this.getVmDB()
							+ "&email=" + this.getUserId()
							+ "&url=" + this.getVmIp()
							+ "&name=" + "aaaa";
		
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
				System.out.println(buf);
			}
			
		}catch(IOException ioe){
			System.err.println("IOException " + ioe);
			ioe.printStackTrace();
		}
	}
}
