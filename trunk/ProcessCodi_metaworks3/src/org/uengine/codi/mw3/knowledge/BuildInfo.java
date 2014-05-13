package org.uengine.codi.mw3.knowledge;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequestWrapper;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToOpener;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.dao.DAOFactory;
import org.metaworks.dao.Database;
import org.metaworks.dao.KeyGeneratorDAO;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.AmazonService;
import org.uengine.codi.mw3.ide.DockerService;
import org.uengine.codi.mw3.ide.IStorageService;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.widget.IFrame;
import org.uengine.kernel.GlobalContext;

@Face(ejsPath="genericfaces/FormFace.ejs", options={"fieldOrder"}, values={"selectVer,projectName,version,userName,modDate,comment,message,log,frame"})
public class BuildInfo extends Database<IBuildInfo> implements IBuildInfo, ContextAware{
	
	public final static String BUILD       	   = "build"; 
	public final static String BUILD_AFTER     = "afterBuild";
	public final static String BUILD_BEFORE    = "beforeBuild";
	public final static String DEPLOYEE_DEV    = "deployee_dev";
	public final static String DEPLOYEE_PORD   = "deployee_prod";
	public final static String DEPLOYEE_AFTER  = "afterDeployee";
	public final static String DEPLOYEE_BEFORE = "beforeDeployee";
	
	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
	
	String projectName;
	@Face(displayName="프로젝트")
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

	int majorVer;
		public int getMajorVer() {
			return majorVer;
		}
		public void setMajorVer(int majorVer) {
			this.majorVer = majorVer;
		}
	
	int minorVer;
		public int getMinorVer() {
			return minorVer;
		}
		public void setMinorVer(int minorVer) {
			this.minorVer = minorVer;
		}
	
	int buildVer;
		public int getBuildVer() {
			return buildVer;
		}
		public void setBuildVer(int buildVer) {
			this.buildVer = buildVer;
		}
		
	String version;
	@Face(displayName="버전")
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}

	String userName;
	@Face(displayName="이름")
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
		
	String userId;
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		
	Date modDate;
	@Face(displayName="날짜")
		public Date getModDate() {
			return modDate;
		}
		public void setModDate(Date modDate) {
			this.modDate = modDate;
		}

	String comment;
	    @Face(displayName="설명", ejsPath="dwr/metaworks/genericfaces/richText.ejs", options={"cols", "rows","whenMode"}, values={"10", "5","edit"})
	    public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}

	Boolean devDistributed;
		public Boolean getDevDistributed() {
			return devDistributed;
		}
		public void setDevDistributed(Boolean devDistributed) {
			this.devDistributed = devDistributed;
		}
	
	Boolean prodDistributed;
		public Boolean getProdDistributed() {
			return prodDistributed;
		}
		public void setProdDistributed(Boolean prodDistributed) {
			this.prodDistributed = prodDistributed;
		}

	String message;
	@Face(displayName="결과")
	@Available(how={BUILD_AFTER, DEPLOYEE_AFTER})
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	
	String log;
	@Face(displayName="로그")
	@Available(how=BUILD_AFTER)
		public String getLog() {
			return log;
		}
		public void setLog(String log) {
			this.log = log;
		}

	SelectBox selectVer;
	@Face(displayName="빌드버전")
	@Available(how={DEPLOYEE_BEFORE})
		public SelectBox getSelectVer() {
			return selectVer;
		}
		public void setSelectVer(SelectBox selectVer) {
			this.selectVer = selectVer;
		}
	
	public BuildInfo(){
		this.setMetaworksContext(new MetaworksContext());
	}
	
	public void loadSelectVersion() throws Exception{
		selectVer = new SelectBox();
		IBuildInfo findBuildInfo = this.findBuildVersion(false);
		while(findBuildInfo.next()){
			String version = findBuildInfo.getMajorVer()+"."+findBuildInfo.getMinorVer()+"."+findBuildInfo.getBuildVer();
			selectVer.add(version, String.valueOf(findBuildInfo.getBuildVer()));
		}
		selectVer.setMetaworksContext(new MetaworksContext());
		selectVer.getMetaworksContext().setWhen(WHEN_EDIT);
	}

	public boolean executeCommand(String cmd[]) throws IOException, InterruptedException{
		
		Runtime runTime = Runtime.getRuntime();
		Process process = runTime.exec(cmd);
		
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
//		InputStream errorIs = process.getErrorStream();
//		InputStreamReader errorIsr = new InputStreamReader(errorIs);
//		BufferedReader errorBr = new BufferedReader(errorIsr);
//		while((line = errorBr.readLine()) != null){
//			System.out.println(line);
//		}

		boolean successed = false;
		String line;
		this.setLog("");
		while((line = br.readLine()) != null){
			this.setLog(this.getLog()+line + "\n");
			System.out.println(line);
			if(line.contains("BUILD SUCCESS"))
				successed = true;
		}
		
		
		
		int execTime = process.waitFor();
		
		System.out.println("exe time: " + execTime);
		return successed;
	}	

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	@Face(displayName="$resource.menu.build")
	@Available(how=BUILD_BEFORE)
	public Object build() throws Exception {
		
		String codebase = GlobalContext.getPropertyString("codebase");	
		
		String cmd[] = new String[3];
		String osName = System.getProperty("os.name");
		String realPath = new HttpServletRequestWrapper(TransactionContext.getThreadLocalInstance().getRequest())
		 				.getRealPath("")+File.separatorChar+"resources"+File.separatorChar+"maven"+File.separatorChar;
		String command = null;	
		
		if(osName.toLowerCase().startsWith("window")){
			command = realPath + GlobalContext.getPropertyString("maven.depoly.bat", "depoly.bat");
			cmd[0] = "cmd.exe";
			cmd[1] = "/C";
			cmd[2] = command + " " + realPath +  " " + codebase+File.separatorChar+projectId + " " + projectName + " " + this.getVersion();

		}else{
			command = realPath + GlobalContext.getPropertyString("maven.depoly.sh", "depoly.sh");
			cmd[0] = "/bin/sh";
			cmd[1] = "-c";
			cmd[2] = command + " " + realPath +  " " + codebase+File.separatorChar+projectId + " " + projectName + " " + this.getVersion();
		}
		//command arg[1]=pom.xml,depoly.sh 파일 위치 arg[2]=해당 프로젝트 경로 arg[3]=프로젝트 이름
		System.out.println("command ==>" + cmd[2]);
		boolean successed = executeCommand(cmd);
		if(!successed){
			this.setMessage("해당 프로젝트 빌드에 실패하였습니다.");
		}else{
			this.setMessage("해당 프로젝트 빌드에 성공하였습니다.");
			this.setId(createNewId());
			this.setDevDistributed(false);
			this.setProdDistributed(false);
			this.createDatabaseMe();
		}
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setHow(BUILD_AFTER);

		return new Refresh(this);
	}
	
	
	@ServiceMethod
	@Face(displayName="확인")
	@Available(how={BUILD_AFTER, DEPLOYEE_AFTER})
	public Object confirm(){
		return new Remover(new Popup());
	}
	
	public IBuildInfo findBuildVersion(boolean limited) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from buildInfo where projectId =?projectId ");
		sql.append("order by majorVer desc, minorVer desc, buildVer desc ");
		if(limited)
			sql.append("limit 1;");
		
		IBuildInfo findListing = (IBuildInfo) Database.sql(IBuildInfo.class, sql.toString());
		findListing.set("projectId", this.getProjectId());
		findListing.select();
		
		return findListing;
	}
	
	public IBuildInfo findDevDistributed() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from buildInfo where projectId =?projectId and devDistributed is true ");
		
		IBuildInfo findListing = (IBuildInfo) Database.sql(IBuildInfo.class, sql.toString());
		findListing.set("projectId", this.getProjectId());
		findListing.select();
		
		return findListing;
	}
	
	public IBuildInfo findProdDistributed() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from buildInfo where projectId =?projectId and prodDistributed is true ");
		
		IBuildInfo findListing = (IBuildInfo) Database.sql(IBuildInfo.class, sql.toString());
		findListing.set("projectId", this.getProjectId());
		findListing.select();
		
		return findListing;
	}
	
	public IBuildInfo findBybuildVersion() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from buildInfo where projectId =?projectId and buildVer =?buildVer ");
		
		IBuildInfo findListing = (IBuildInfo) Database.sql(IBuildInfo.class, sql.toString());
		findListing.set("projectId", this.getProjectId());
		findListing.set("buildVer", this.getBuildVer());
		findListing.select();
		
		return findListing;
	}
	
	public String createNewId() throws Exception{
		Map options = new HashMap();
		options.put("useTableNameHeader", "false");
		options.put("onlySequenceTable", "true");
		
		KeyGeneratorDAO kg = DAOFactory.getInstance(TransactionContext.getThreadLocalInstance()).createKeyGenerator("buildInfo", options);
		kg.select();
		kg.next();
		
		Number number = kg.getKeyNumber();
		
		return String.valueOf(number.intValue());
	}
	
	@ServiceMethod(callByContent = true, eventBinding = "change", bindingFor = "selectVer", bindingHidden = true, target=ServiceMethodContext.TARGET_SELF)
	@Hidden
	public void selectVersion() throws Exception{
		System.out.println(this.selectVer.getSelected());
		this.setBuildVer(Integer.parseInt(this.selectVer.getSelected()));
		IBuildInfo findBuildInfo = findBybuildVersion();
		if(findBuildInfo.next()){
			String version = findBuildInfo.getMajorVer()+"."+findBuildInfo.getMinorVer()+"."+findBuildInfo.getBuildVer();
			this.setVersion(version);
			this.setProjectName(findBuildInfo.getProjectName());
			this.setModDate(findBuildInfo.getModDate());
			this.setUserName(findBuildInfo.getUserName());
			this.setComment(findBuildInfo.getComment());
			this.setMajorVer(findBuildInfo.getMajorVer());
			this.setMinorVer(findBuildInfo.getMinorVer());
			this.setBuildVer(findBuildInfo.getBuildVer());
		}

	}
	
	@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_APPEND)
	@Available(how=DEPLOYEE_BEFORE)
	@Face(displayName="배포")
	public Object deployee() throws Exception{
		System.out.println(this.selectVer.getSelected());
		
		boolean successed = false;
		
		String reopsitoryService = GlobalContext.getPropertyString("file.repository.service");
		
		IStorageService storageService=null;
		String url=null;
		
		if("amazon".equals(reopsitoryService)){
			storageService = new AmazonService();
			url = GlobalContext.getPropertyString("app.url.dev")+projectName;
		}else if("docker".equals(reopsitoryService)){
			storageService = new DockerService();
			url = GlobalContext.getPropertyString("app.url.dev")+projectName+"-"+this.getVersion();
		}
		
		successed = storageService.putObject(this.getProjectId(), this.getProjectName(), this.getVersion(), false);
		if(!successed){
			this.setMessage("해당 프로젝트 배포에 실패하였습니다.");
		}else{
			BuildInfo buildInfo = new BuildInfo();
			IBuildInfo findBuildInfo = this.findDevDistributed();
			if(findBuildInfo.next()){
				buildInfo.copyFrom(findBuildInfo);
				buildInfo.databaseMe().setDevDistributed(false);
			}
			findBuildInfo = this.findBybuildVersion();
			if(findBuildInfo.next()){
				buildInfo.copyFrom(findBuildInfo);
				buildInfo.databaseMe().setDevDistributed(true);
			}
			
			this.setMessage("해당 프로젝트 배포에 성공하였습니다. : 테스트 서버 -" + url);
		}

		this.getMetaworksContext().setHow(DEPLOYEE_AFTER);
		
		return new Refresh(this);
	}
	
}
