package competition.form;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Order;
import org.uengine.codi.CodiProcessManagerBean;
import org.uengine.codi.ITool;
import org.uengine.codi.MetaworksUEngineSpringConnectionAdapter;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.codi.mw3.marketplace.App;
import org.uengine.codi.mw3.marketplace.AppMapping;
import org.uengine.codi.mw3.model.RecentItem;
import org.uengine.persistence.dao.UniqueKeyGenerator;
import org.uengine.processmanager.ProcessManagerBean;

public class competitionRegister implements Serializable, ContextAware,ITool {
	
	String title;
		@Order(value=1)
		@Face(displayName="설명")
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}

	String gameName;
		@Order(value=2)
		@Face(displayName="대회명")
		public String getGameName() {
			return gameName;
		}
		public void setGameName(String gameName) {
			this.gameName = gameName;
		}
	
	String gameId;
		@Hidden
		public String getGameId() {
			return gameId;
		}
		public void setGameId(String gameId) {
			this.gameId = gameId;
		}
	
	String userId;
		@Hidden
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
	String userNum;
		@Hidden
		public String getUserNum() {
			return userNum;
		}
		public void setUserNum(String userNum) {
			this.userNum = userNum;
		}

	String userName;
		@Order(value=3)
		@Face(displayName="신청자")
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
	String comCode;
		@Hidden
		public String getComCode() {
			return comCode;
		}
		public void setComCode(String comCode) {
			this.comCode = comCode;
		}

	String comName;
		@Hidden
		public String getComName() {
			return comName;
		}
		public void setComName(String comName) {
			this.comName = comName;
		}
		
		
	String userPw;
		@Hidden
		public String getUserPw() {
			return userPw;
		}
		public void setUserPw(String userPw) {
			this.userPw = userPw;
		}

	String 	UserYN;
		@Hidden
		public String getUserYN() {
			return UserYN;
		}
		public void setUserYN(String userYN) {
			UserYN = userYN;
		}
		
	String serviceId;
		@Hidden
		public String getServiceId() {
			return serviceId;
		}
		public void setServiceId(String serviceId) {
			this.serviceId = serviceId;
		}
		
	String serviceUrl;
	 	@Hidden
		public String getServiceUrl() {
			return serviceUrl;
		}
		public void setServiceUrl(String serviceUrl) {
			this.serviceUrl = serviceUrl;
		}
		
	String topServiceUrl;
 		@Hidden
		public String getTopServiceUrl() {
			return topServiceUrl;
		}
		public void setTopServiceUrl(String topServiceUrl) {
			this.topServiceUrl = topServiceUrl;
		}

	String rmk;
 		@Hidden
		public String getRmk() {
			return rmk;
		}
		public void setRmk(String rmk) {
			this.rmk = rmk;
		}
	

	String serviceName;
		@Order(value=6)
		@Face(displayName="신청 서비스")
		public String getServiceName() {
			return serviceName;
		}
		public void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}
		
	String competitionId;
		@Hidden
		public String getCompetitionId() {
			return competitionId;
		}
		public void setCompetitionId(String competitionId) {
			this.competitionId = competitionId;
		}
		
	String startDate;
		@Face(displayName="시작일")
		@Order(value=4)
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
	
	String endDate;
		@Face(displayName="종료일")
		@Order(value=5)
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		
		
	public competitionRegister(){
		this.metaworksContext = new MetaworksContext();
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	public void loadService(){
		System.out.println("loadService...");
	}
	
	public void registerCompetition() throws Exception{
		System.out.println("registerCompetition...");
//		String url="http://202.68.237.16:8080/ict/cloud/setGamesInfo.json";
		String url="http://175.207.44.155:9090/ict/cloud/setGamesInfo.json";
		String param="?gamesNm="+this.getGameName()
				    +"&gamesStaDt="+ this.getStartDate()
				    +"&gamesEndDt="+ this.getEndDate()
				    +"&rmk="+ this.getRmk();
		url+=param;
		
		JSONObject json = connectService(url);
		if(json ==null){
			System.out.println("서비스를 가져오지 못했습니다.");
		}
		if(!"00".equals(json.get("resultCode"))){
			System.out.println(json.get("resultMsg").toString());
		}else{
			this.setGameId(String.valueOf(json.get("GAMES_ID")));
			System.out.println("대회가 생성되었습니다. id : " + getGameId() + ", name : " + getGameName());
		}	
	}
	
	
	
	
	public void checkDuplicateId() throws Exception{
		System.out.println("checkDuplicateId...");
		//String url="http://202.68.237.16:8080/ict/cloud/getChkId.json";
		String url="http://175.207.44.155:9090/ict/cloud/getChkId.json";
		String param="?gamesId="+this.getGameId()
				    +"&UserId=" +this.getUserId(); 
				   
		url+=param;
		
		JSONObject json = connectService(url);
		if(json ==null){
			System.out.println("서비스를 가져오지 못했습니다.");
		}
		if(!"00".equals(json.get("resultCode"))){
			System.out.println(json.get("resultMsg").toString());
		}else{
		
			if("Y".equals(json.get("USE_YN"))){
				this.setUserYN("Y");
				System.out.println(this.getGameId()+"대회에 \""+this.getUserId()+"\"아이디가 존재하지 않습니다. 아이디등록 단계로 갑니다.");
			}else{
				this.setUserYN("N");
				System.out.println("\""+this.getUserId()+"\" 아이디가 존재합니다... 서비스등록 단계로 갑니다.");
			}
		}
	}
	
	
	public void registerAdmin()  throws Exception{
		System.out.println("registerAdmin...");
		//String url="http://202.68.237.16:8080/ict/cloud/setAdminInfo.json";
		String url="http://175.207.44.155:9090/ict/cloud/setAdminInfo.json";
		String param="?gamesId="+this.getGameId()
				    +"&UserId=" +this.getUserId()
				    +"&UserNm=" +this.getUserName()
				    +"&UserPw=" +this.getUserPw(); 
				   
		url+=param;
		
		JSONObject json = connectService(url);
		if(json ==null){
			throw new Exception("서비스를 가져오지 못했습니다.");
		}
		if(!"00".equals(json.get("resultCode"))){
			System.out.println(json.get("resultMsg").toString());
		}else{
			this.setUserNum(json.get("USER_NUM").toString());
			
			if("Y".equals(json.get("USE_YN"))){
				System.out.println("\""+this.getGameId()+"\" 대회에 \""+this.getUserId()+"\" 아이디 등록 처리가 되지 않았습니다.");
			}else{
				System.out.println("\""+this.getGameId()+"\" 대회에 \""+this.getUserId()+"\" 아이디가 정상 등록 되었습니다.");
			}
		}
		
	}
	
	public void registerService() throws Exception{
		System.out.println("registerService...");
		//String url="http://202.68.237.16:8080/ict/cloud/setSstmList.json";
		String url="http://175.207.44.155:9090/ict/cloud/setSstmList.json";
		String param="?gamesId="+this.getGameId()
				    +"&sstmId=" +this.getServiceId();
				   
		url+=param;
		
		JSONObject json = connectService(url);
		if(json ==null){
			System.out.println("서비스를 가져오지 못했습니다.");
		}
		if(!"00".equals(json.get("resultCode"))){
			System.out.println(json.get("resultMsg").toString());
			this.setTitle("해당 대회등록에 실패하였습니다.");
			this.setUserYN("N");
		}else{
		
			JSONArray array = (JSONArray) json.get("ResultData");
			
			for(int i=0; i<array.size(); i++){
				JSONObject tmpJson =  (JSONObject)new JSONParser().parse(array.get(i).toString());
				System.out.println("\""+this.getGameId()+"\" 대회에 서비스 \""+ String.valueOf(tmpJson.get("sstmNm"))+" \" 가 등록되었습니다.");
			}
			
			this.setTitle("사용자 고유번호 "+this.getUserNum()+" 로 해당 대회가 등록되었습니다.");
			this.setUserYN("Y");
		}
	}
	
	public void registerApp() throws Exception{
		App app = new App();

		MetaworksUEngineSpringConnectionAdapter connectionAdapter = new MetaworksUEngineSpringConnectionAdapter();
		
		CodiProcessManagerBean pm = new CodiProcessManagerBean();
		pm.setConnectionFactory(connectionAdapter);
		pm.setAutoCloseConnection(false);
		pm.setManagedTransaction(true);
		
		app.setAppId( UniqueKeyGenerator.issueWorkItemKey(((ProcessManagerBean) pm).getTransactionContext()).intValue());
		app.setCreateDate(Calendar.getInstance().getTime());
		app.setProjectId(this.getCompetitionId());
		app.setIsDeleted(false);
		app.setComcode(this.getComCode());
		app.setStatus(App.STATUS_PUBLISHED);
		
		app.createDatabaseMe();
		app.flushDatabaseMe();
		
		RecentItem item = new RecentItem();
		item.setEmpCode(this.getUserId());
		item.setItemType("compete");
		item.setItemId(String.valueOf(app.getAppId()));
		item.setUpdateDate(new Date());
		item.createDatabaseMe();
		
		AppMapping appMapping = new AppMapping();
		appMapping.setAppId(app.getAppId());
		appMapping.setComCode(this.getComCode());
		appMapping.setAppName(this.getGameName());
//		appMapping.setUrl("202.68.237.16:8080/ict/?gameId="+this.getGameId());
		appMapping.setUrl("175.207.44.155:9090/ict/?gamesId="+this.getGameId());
//		appMapping.setUrl("www.naver.com");
		appMapping.setIsDeleted(false);
		
		appMapping.createDatabaseMe();
		appMapping.flushDatabaseMe();
	}
	
	public void approveCompetition() throws Exception{
		WfNode wfNode = new WfNode();
		wfNode.setId(this.getCompetitionId());
		wfNode.copyFrom(wfNode.databaseMe());
		wfNode.setExt("approval");
		wfNode.syncToDatabaseMe();
	}
	
	
	
	public JSONObject connectService(String strUrl) throws ParseException{
		StringBuffer result = new StringBuffer();
		JSONObject json = null;
	try {
		 
		URL url = new URL(strUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Accept-Charset", "UTF-8");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setUseCaches(false);
	    conn.setDoInput(true);
	    conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
 
		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
		
		String output;
		while ((output = br.readLine()) != null) {
			System.out.println(output);
			result.append(output);
		}
		json =  (JSONObject)new JSONParser().parse(result.toString());
		
		conn.disconnect();
		
	  } catch (MalformedURLException e) {
 
		e.printStackTrace();
 
	  } catch (IOException e) {
 
		e.printStackTrace();
 
	  }
	return json;
	}
	
	MetaworksContext metaworksContext = new MetaworksContext();
	@Override
	@Hidden
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	@Override
	public void setMetaworksContext(MetaworksContext context) {
		this.metaworksContext = context;
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
}
