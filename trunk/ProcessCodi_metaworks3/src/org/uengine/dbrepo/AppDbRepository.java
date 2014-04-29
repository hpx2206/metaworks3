package org.uengine.dbrepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.metaworks.ContextAware;
import org.metaworks.dao.DAOFactory;
import org.metaworks.dao.Database;
import org.metaworks.dao.KeyGeneratorDAO;
import org.metaworks.dao.MetaworksDAO;
import org.metaworks.dao.TransactionContext;
import org.metaworks.spring.SpringConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.MetaworksUEngineSpringConnectionAdapter;
import org.uengine.codi.mw3.model.IUser;

public class AppDbRepository extends Database<IAppDbRepository> implements IAppDbRepository, ContextAware {
	
	Long iddatabase;
		@Override
		public Long getIddatabase() {
			return iddatabase;
		}
		@Override
		public void setIddatabase(Long iddatabase) {
			this.iddatabase = iddatabase;
		}

	String dbname;
		@Override
		public String getDbname() {
			return dbname;
		}
		@Override
		public void setDbname(String dbname) {
			this.dbname = dbname;
		}

	String user;
		@Override
		public String getUser() {
			return user;
		}
		@Override
		public void setUser(String user) {
			this.user = user;
		}

	String password;
		@Override
		public String getPassword() {
			return password;
		}
		@Override
		public void setPassword(String password) {
			this.password = password;
		}

	int dbtype;
		@Override
		public int getDbtype() {
			return dbtype;
		}
		@Override
		public void setDbtype(int dbtype) {
			this.dbtype = dbtype;
		}

	String role;
		@Override
		public String getRole() {
			return role;
		}
		@Override
		public void setRole(String role) {
			this.role = role;
		}
	
	String appid;
		@Override
		public String getAppid() {
			return appid;
		}
		@Override
		public void setAppid(String appid) {
			this.appid = appid;
		}
		
	int mode;
		@Override
		public int getMode() {
			return mode;
		}
		@Override
		public void setMode(int mode) {
			this.mode = mode;
		}
		
	String dburl;
		@Override
		public String getDburl() {
			return dburl;
		}
		@Override
		public void setDburl(String dburl) {
			this.dburl = dburl;
		}
	
	public void IAppDbRepository(){
	}
		
		
	public IAppDbRepository saveMe() throws Exception {

		Map options = new HashMap();
		options.put("useTableNameHeader", "false");
		options.put("onlySequenceTable", "true");
		
		KeyGeneratorDAO kg = DAOFactory.getInstance(TransactionContext.getThreadLocalInstance()).createKeyGenerator("appdatabase", options);
		kg.select();
		kg.next();
		
		Number number = kg.getKeyNumber();
		setIddatabase(number.longValue());
		
		IAppDbRepository result = createDatabaseMe();
		flushDatabaseMe();
		
		return result;
	}
	
	
	/**
	 * @param mode
	 * @param wfNodeName
	 * @param wfNodeId
	 * @throws Exception
	 */
	public static void addDbRepository(int mode, String wfNodeName, String wfNodeId) throws Exception{
		
		DatabaseManager dbm = new MySqlConnector();
		
		String ip = mode == 0 ? BaseConnetor.devIp : BaseConnetor.prodIp;
		String port = mode == 0 ? BaseConnetor.devPort : BaseConnetor.prodPort;
		
		
		QueryResult result  = dbm.addDbRepository(mode, wfNodeId);
		ArrayList<HashMap<String, String>> listResult = (ArrayList)result.listResult;
		HashMap<String, String> mapResult = listResult.get(0);

		AppDbRepository adr = new AppDbRepository();
		adr.setAppid(wfNodeName);
		
		adr.setDbname((String)mapResult.get("dbname"));
		adr.setDburl("jdbc:mysql://" + ip + ":" + port + "/" +  (String)mapResult.get("dbname"));
		adr.setUser((String)mapResult.get("user"));
		adr.setPassword((String)mapResult.get("password"));
		adr.setDbtype(1);	//1:mysql, 2:oracle, 3:mssql
		adr.setRole("");
		adr.setMode(mode);
		adr.saveMe();
		adr.flushDatabaseMe(); 
	}
	
	
	@Autowired
	public SpringConnectionFactory connectionFactory;
	
	public AppDbRepository findDbRepo(int mode, String appName) throws Exception {
	
		AppDbRepository appDbRepo = null;
		
		StringBuffer selectbf = new StringBuffer();
		selectbf.append("select * from appdatabase where appid=?appid and mode=?mode");
		
		IAppDbRepository dao = (IAppDbRepository) sql(selectbf.toString());
		dao.setAppid(appName);
		dao.setMode(mode);
		dao.select();
		if(dao.next()){
			appDbRepo = new AppDbRepository();
			appDbRepo.copyFrom(dao);
		}
		
		return appDbRepo;
	}
	
	
//	public IAppDbRepository findDbRepo(int mode, String appName) throws Exception{
//		StringBuffer sb = new StringBuffer();
//		sb.append("SELECT * FROM appdatabase where appid=?appname and mode=?mode");
//		
////		TransactionContext tx = new TransactionContext(); //once a TransactionContext is created, it would be cached by ThreadLocal.set, so, we need to remove this after the request processing. 
////        
////		tx.setManagedTransaction(false);
////		tx.setAutoCloseConnection(true);
////		
////		tx.setRequest(TransactionContext.getThreadLocalInstance().getRequest());
////		tx.setResponse(TransactionContext.getThreadLocalInstance().getResponse());
////
////		if(connectionFactory!=null)
////			tx.setConnectionFactory(connectionFactory);
////		select * from appdatabase where appid='" + appName + "' and mode='0'"
////		IAppDbRepository dao = (IAppDbRepository)MetaworksDAO.createDAOImpl(TransactionContext.getThreadLocalInstance(),
////	   			 sb.toString(), 
////	   			IAppDbRepository.class);
//		
////		selectbf.append("select * from appdatabase where appid=?appid");
//		
//		IAppDbRepository dao = (IAppDbRepository) sql(sb.toString());
//		dao.set("appname", appName);
//		dao.set("mode", mode);
//		dao.select();
////		if(dao.next()){
////			dao = new AppDbRepository();
////			dao.copyFrom(dao);
////		}
////		
////		return appDbRepo;
//		
//		
////		IAppDbRepository dao = sql(sb.toString());
////		dao.set("appname", appName);
////		dao.set("mode", mode);
////		dao.select();
//
//		return dao;
//		
////		AppDbRepository adr = new AppDbRepository();
////		
////		HttpSession httpSession = TransactionContext.getThreadLocalInstance().getRequest().getSession();
////		String projectId = (String)httpSession.getAttribute("currprojectId");
////		
////		adr.setAppid(projectId);
////		IAppDbRepository appDbRepo = adr.findDatabase();
//////		String dbUser = appDbRepo.getUser();
////		String dbName = "jdbc:mysql://localhost:3306/" + appDbRepo.getDbname() + "?useUnicode=true&amp;characterEncoding=UTF8";
////		String user = appDbRepo.getUser();
////		String paaswd = appDbRepo.getPassword();
////		this.setDbname(dbName);
////		this.setUser(user);
////		this.setPassword(paaswd);
//	}
	
	
//	public String getDbUrl() throws Exception {
////		session.getClipboard();
////		ResourceNode test = (ResourceNode) session.getClipboard();
////		test.getParentId();
////		Session session = (Session)session.getClipboard();
//		AppDbRepository adr = new AppDbRepository();
//		
//		HttpSession httpSession = TransactionContext.getThreadLocalInstance().getRequest().getSession();
//		String projectId = (String)httpSession.getAttribute("currprojectId");
//		
//		adr.setAppid(projectId);
//		IAppDbRepository appDbRepo = adr.findDatabase();
////		String dbUser = appDbRepo.getUser();
//	String dbName = appDbRepo.getDbname();
////		httpsession.getAttribute("projectId");
//		return dbName;
//	} 
	 
	
	
//	public AppDbRepository findDatabase() throws Exception {
//			
//		AppDbRepository appDbRepo = null;
//		
//		StringBuffer selectbf = new StringBuffer();
//		selectbf.append("select * from appdatabase where appid=?appid");
//		
//		IAppDbRepository dao = (IAppDbRepository) sql(selectbf.toString());
//		dao.setAppid(this.getAppid());
//		dao.select();
//		if(dao.next()){
//			appDbRepo = new AppDbRepository();
//			appDbRepo.copyFrom(dao);
//		}
//		
//		return appDbRepo;
//	}
//	
//	public ITopicMapping findByUser() throws Exception {
//		StringBuffer selectbf = new StringBuffer();
//		selectbf.append("select * from bpm_topicmapping ");
//		selectbf.append(" where topicid = ?topicid");
//		selectbf.append(" and userid = ?userid");
//		
//		ITopicMapping dao = (ITopicMapping) sql(ITopicMapping.class,	selectbf.toString());
//		dao.setTopicId( this.getTopicId());
//		dao.setUserId( this.getUserId());
//		dao.select();
//		
//		return dao;
//	}
//	
//	public IUser findUser() throws Exception {
//		IUser users = (IUser) Database.sql(IUser.class, "select  userId, userName name from bpm_topicmapping  where topicId=?topicId and assigntype=?assigntype ");
//		
//		users.set("topicId", this.getTopicId() );
//		users.set("assigntype", 0);
//		users.select();
//		
//		return users;
//	}
//	public IDept findDept() throws Exception {
//		IDept dept = (IDept) Database.sql(IDept.class, "select  userId as PARTCODE , userName as  PARTNAME  from bpm_topicmapping  where topicId=?topicId and assigntype=?assigntype ");
//		
//		dept.set("topicId", this.getTopicId() );
//		dept.set("assigntype", 2);
//		dept.select();
//		
//		return dept;
//	}
//	
//	public IFollower findFollowers() throws Exception{
//		
//		StringBuffer sql = new StringBuffer();
//		sql.append("SELECT distinct tm.userId endpoint, ifnull(e.empname,tm.userName) resname, tm.assigntype, tm.topicId parentId, '" + Follower.TYPE_TOPIC + "' parentType");
//		sql.append("  FROM bpm_topicmapping tm,  emptable e");
//		sql.append(" WHERE tm.userId = e.empcode and tm.assigntype = 0");
//		sql.append("   AND tm.topicid = ?topicId");
//		
//		IFollower follower = (IFollower) Database.sql(IFollower.class, sql.toString());
//		follower.set("topicId", this.getTopicId());
//		follower.select();	
//		
//		return follower;
//	}
//
//	public void removeMe() throws Exception {
//		
//		StringBuffer sql = new StringBuffer();
//		sql.append("DELETE");
//		sql.append("  FROM bpm_topicmapping");
//		sql.append(" WHERE topicId=?topicId");
//		sql.append("   AND userId=?userId");
//		
//		ITopicMapping tm = sql(sql.toString());
//		tm.setTopicId(this.getTopicId());
//		tm.setUserId(this.getUserId());
//		tm.update();
//		
//	}
//	
//	protected void createDatabaseToTadpole(String vmDB, String vmIP) {
//		
//		String ip = GlobalContext.getPropertyString("pole.call.ip","localhost");
//		String port = GlobalContext.getPropertyString("pole.call.port","80");
//		String db  = GlobalContext.getPropertyString("pole.call.db");
//		
//		String parameter = "?db=" + vmDB
//							+ "&email=" + this.getUserId()
//							+ "&url=" + vmIP
//							+ "&name=" + "aaaa";
//		
//		String sUrl = "http://" + ip + ":" + port + db + "/createDatabase" + parameter;
//		
//		URL url;
//		URLConnection connection;
//		InputStream is;
//		InputStreamReader isr;
//		BufferedReader br;
//		
//		try{
//			
//			url = new URL(sUrl);
//			connection = url.openConnection();
//			
//			is = connection.getInputStream();
//			isr = new InputStreamReader(is);
//			br = new BufferedReader(isr);
//			
//			String buf = null;
//			
//			while(true){
//				buf = br.readLine();
//				if(buf == null) break;
//				System.out.println(buf);
//			}
//			
//		}catch(IOException ioe){
//			System.err.println("IOException " + ioe);
//			ioe.printStackTrace();
//		}
//	}
}
