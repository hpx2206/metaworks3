package org.uengine.codi.mw3.marketplace;

import java.util.Calendar;
import java.util.Date;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.dao.Database;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalPanel;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.cloud.saasfier.TenantContext;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.StartCodi;
import org.uengine.codi.mw3.knowledge.FilepathInfo;
import org.uengine.codi.mw3.knowledge.IProjectNode;
import org.uengine.codi.mw3.knowledge.ProjectNode;
import org.uengine.codi.mw3.knowledge.TopicMapping;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.codi.mw3.marketplace.category.Category;
import org.uengine.codi.mw3.marketplace.category.ICategory;
import org.uengine.codi.mw3.model.ICompany;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.Locale;
import org.uengine.codi.mw3.model.ProcessMap;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.project.oce.AppServerManage;
import org.uengine.codi.mw3.project.oce.KtProbProjectServers;
import org.uengine.codi.mw3.project.oce.KtProjectServers;
import org.uengine.codi.mw3.project.oce.NewServer;
import org.uengine.kernel.KeyedParameter;
import org.uengine.kernel.ResultPayload;
import org.uengine.persistence.dao.UniqueKeyGenerator;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;

public class App extends Database<IApp> implements IApp, ITool, ContextAware {
	
	public final static String STATUS_REQUEST = "Request";
	public final static String STATUS_APPROVED = "Approved";
	public final static String STATUS_REJECTED = "Rejected";
	public final static String STATUS_PUBLISHED = "Published";
	public final static String STATUS_UNPUBLISHED = "Unpublished";

	public App() throws Exception{
		this.setUseIaas("1".equals(StartCodi.USE_IAAS));
	}
	String topicId;
		@Hidden
		public String getTopicId() {
			return topicId;
		}
		public void setTopicId(String topicId) {
			this.topicId = topicId;
		}
	int appId;
		public int getAppId() {
			return appId;
		}
		public void setAppId(int appId) {
			this.appId = appId;
		}

	String appName;
		public String getAppName() {
			return appName;
		}
	
		public void setAppName(String appName) {
			this.appName = appName;
		}
		
	String simpleOverview;
		public String getSimpleOverview() {
			return simpleOverview;
		}
		public void setSimpleOverview(String simpleOverview) {
			this.simpleOverview = simpleOverview;
		}
	
	String fullOverview;
		public String getFullOverview() {
			return fullOverview;
		}
		public void setFullOverview(String fullOverview) {
			this.fullOverview = fullOverview;
		}
	
	String pricing;
		public String getPricing() {
			return pricing;
		}
		public void setPricing(String pricing) {
			this.pricing = pricing;
		}
	
	Date createDate;
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
	
	Date updateDate;
		public Date getUpdateDate() {
			return updateDate;
		}
		public void setUpdateDate(Date updateDate) {
			this.updateDate = updateDate;
		}
	
	String version;
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		
	MetaworksFile logoFile;
		public MetaworksFile getLogoFile() {
			return logoFile;
		}
		public void setLogoFile(MetaworksFile logoFile) {
			this.logoFile = logoFile;
		}

	String status;
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
	
	ICompany company;
		public ICompany getCompany() {
			return company;
		}
		public void setCompany(ICompany company) {
			this.company = company;
		}
		
	String comcode;
		public String getComcode() {
			return comcode;
		}
		public void setComcode(String comcode) {
			this.comcode = comcode;
		}
		
	String comName;
		public String getComName() {
			return comName;
		}
		public void setComName(String comName) {
			this.comName = comName;
		}

	boolean isDeleted;
		public boolean isDeleted() {
			return isDeleted;
		}
		public void setIsDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}
	
	String url;
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}

	ICategory category;
		public ICategory getCategory() {
			return category;
		}
		public void setCategory(ICategory category) {
			this.category = category;
		}
		
	SelectBox categories;
		public SelectBox getCategories() {
			return categories;
		}
		public void setCategories(SelectBox categories) {
			this.categories = categories;
		}
	
	SelectBox attachProject;
		public SelectBox getAttachProject() {
			return attachProject;
		}
		public void setAttachProject(SelectBox attachProject) {
			this.attachProject = attachProject;
		}
		
	WfNode project;
		public WfNode getProject() {
			return project;
		}
		public void setProject(WfNode project) {
			this.project = project;
		}

	IAppMapping appMapping;
		public IAppMapping getAppMapping() {
			return appMapping;
		}
		public void setAppMapping(IAppMapping appMapping) {
			this.appMapping = appMapping;
		}

	IUser user;
		public IUser getUser() {
			return user;
		}
		public void setUser(IUser user) {
			this.user = user;
		}
	
	AppInformation appInfo;
		public AppInformation getAppInfo() {
			return appInfo;
		}
		public void setAppInfo(AppInformation appInfo) {
			this.appInfo = appInfo;
		}	
	
	int installCnt;
		public int getInstallCnt() {
			return installCnt;
		}
		public void setInstallCnt(int installCnt) {
			this.installCnt = installCnt;
		}
		
	boolean companyUsed;
		public boolean isCompanyUsed() {
			return companyUsed;
		}
		public void setCompanyUsed(boolean companyUsed) {
			this.companyUsed = companyUsed;
		}
		
	SelectBox releaseVersion;
		public SelectBox getReleaseVersion() {
			return releaseVersion;
		}
		public void setReleaseVersion(SelectBox releaseVersion) {
			this.releaseVersion = releaseVersion;
		}
		
	int runningVersion;
		public int getRunningVersion() {
			return runningVersion;
		}
		public void setRunningVersion(int runningVersion) {
			this.runningVersion = runningVersion;
		}
	
	String subDomain;
		public String getSubDomain() {
			return subDomain;
		}
		public void setSubDomain(String subDomain) {
			this.subDomain = subDomain;
		}
		
	boolean useIaas;
		public boolean isUseIaas() {
			return useIaas;
		}
		public void setUseIaas(boolean useIaas) {
			this.useIaas = useIaas;
		}
		
		
	@AutowiredFromClient
	transient public Session session;
	
	@Autowired
	transient public ProcessManagerRemote processManager;
	
	@Autowired
	transient public InstanceViewContent instanceView;
	
	
	public IApp findByVendor() throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from app");
		sql.append(" where isdeleted=?isdeleted");
		sql.append(" 	and comcode=?comcode");
		sql.append(" order by appid desc");
		
		IApp findListings = (IApp) Database.sql(IApp.class, sql.toString());
		
		findListings.setComcode(this.getComcode());
		findListings.setIsDeleted(false);
		findListings.select();

		
		return findListings;
	}
	
	public IApp findMe() throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select app.*, comtable.comname, comtable.description, comtable.repmail from app, comtable");
		sql.append(" where app.comcode = comtable.comcode");
		sql.append("   and app.appId=?appId");
		
		IApp findListing = (IApp) Database.sql(IApp.class, sql.toString());
		
		findListing.setStatus(STATUS_PUBLISHED);
		findListing.setAppId(this.getAppId());
		findListing.select();
		
		if(findListing.next())
			return findListing;
		else
			return null;
	}
	
	public IApp findNewApps() throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select app.*, comtable.comname, comtable.description, comtable.repmail from app, comtable");
		sql.append(" where app.comcode = comtable.comcode");
		sql.append("   and status=?status"); 
		sql.append("   and app.isdeleted=?isdeleted");
		sql.append("   and DATE_FORMAT(createdate,'%Y-%m-%d') between DATE_SUB(CURRENT_DATE, INTERVAL 15 DAY) and CURRENT_DATE");
		
		//from 15days ago
		IApp findListing = (IApp) Database.sql(IApp.class, sql.toString());
		
		findListing.setStatus(STATUS_PUBLISHED);
		findListing.setIsDeleted(false);
		findListing.select();
		
		return findListing;
		
	}
	
	public static IApp findApp(String categoryId, String keyword) throws Exception{
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from app");
		sql.append(" where status=?status");
		sql.append("   and isdeleted=?isdeleted");		
		
		if(categoryId != null && !categoryId.equals("0"))
			sql.append("   and categoryId=?categoryId");
		if(keyword != null)
			sql.append("   and appName like ?AppName");
		
		
		IApp findListing = (IApp) Database.sql(IApp.class, sql.toString());
		
		findListing.set("categoryId", categoryId);
		findListing.setAppName("%" + keyword + "%");
		findListing.setStatus(STATUS_PUBLISHED);
		findListing.setIsDeleted(false);
		
		
		findListing.select();
		
		return findListing;
		
	}
		
	public Object readyPublished() throws Exception {
		
		this.setAppId(getAppId());
		this.setStatus(STATUS_PUBLISHED);
		
		syncToDatabaseMe();
		flushDatabaseMe();
		
		return this;
		
	}
	
	public Object readyApproved() throws Exception {
		
		this.setAppId(getAppId());
		
		this.setStatus(STATUS_APPROVED);
		syncToDatabaseMe();
		flushDatabaseMe();
		
		return this;
		
	}
	
	public void load() throws Exception {
		SelectBox categories = new SelectBox();
		SelectBox attachProject = new SelectBox();
		
		ICategory category = Category.loadRootCategory();
		if (category.size() > 0) {
			while (category.next()) {
				
				String categoryId = Integer.toString(category.getCategoryId());
				String categoryName = category.getCategoryName();

				categories.add(categoryName, categoryId);
			}
		}
		
		IProjectNode projectList = ProjectNode.load(session);	
		FilepathInfo filepathInfo = new FilepathInfo();
		int j = 0;
		if(projectList.size() > 0) {
			while(projectList.next()){
				String projectId = projectList.getId();
				String projectName = projectList.getName();
				if( j == 0 ){
					filepathInfo.setProjectId(projectList.getId());
				}
				attachProject.add(projectName, projectId);
				j++;
			}
		}
		
		this.setCategories(categories);
		this.setAttachProject(attachProject);
		this.setReleaseVersion(filepathInfo.findReleaseVersions(filepathInfo.getProjectId()));
		this.setLogoFile(new MetaworksFile());
	}
	
	public Object[] detail() throws Exception {
		MarketplaceCenterPanel centerPenal = new MarketplaceCenterPanel();
		try {
			this.copyFrom(this.databaseMe());
			
			AppMapping mapping = new AppMapping();
			mapping.setAppId(this.appId);
			mapping.setComCode(session.getEmployee().getGlobalCom());
			IAppMapping iAppMapping = mapping.findMe();
			if( iAppMapping != null ){
				// 이미 취득한 앱
				this.setCompanyUsed(true);
			}
			
			centerPenal.setAppDetail(new AppDetail(this));
			centerPenal.getMetaworksContext().setHow("detail");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.getEmployee().setPreferUX("sns");
		session.setLastPerspecteType("topic");
		session.setLastSelectedItem(this.getAppName());
		
		/*InstanceList instList = new InstanceList(session);
		instList.load();
		
		InstanceListPanel instanceListPanel = new InstanceListPanel(session);
		instanceListPanel.setInstanceList(instList);
		
		ContentWindow topicStreamWindow = new ContentWindow();
		topicStreamWindow.setPanel(instanceListPanel);*/
		
		
		//return new Object[]{instanceListPanel, centerPenal};
		return new Object[]{centerPenal};
	}
	
	public Object edit() throws Exception {
		
		this.load();
		this.getMetaworksContext().setHow(null);
		this.getMetaworksContext().setWhen("edit");
		
		this.getCategories().setSelected(String.valueOf(this.getCategory().getCategoryId()));
		
		return new ModalPanel(this);
	}

	public Object serverManage() throws Exception {
		
		AppServerManage AppServerManage = new AppServerManage();
		AppServerManage.session = session;
		
		KtProbProjectServers projectServers = new KtProbProjectServers(this.getProject().getId() , KtProjectServers.SERVER_PROB); // 운영
		projectServers.loadOceServer();
		
		AppServerManage.setProjectServers(projectServers);
		
		return new ModalPanel(AppServerManage);
	}

	public Object step1() throws Exception {
		NewServer newServer = new NewServer();
		
		if(this.getLogoFile().getFileTransfer() != null &&
				   this.getLogoFile().getFilename() != null && 
				   this.getLogoFile().getFilename().length() > 0)			
					this.getLogoFile().upload();
		
		newServer.setApp(this);
		newServer.setServerGroup(KtProjectServers.SERVER_PROB);
		newServer.setMetaworksContext(new MetaworksContext());
		newServer.getMetaworksContext().setHow("appCreate");
		newServer.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		return new ModalPanel(newServer);
	}
	
	public Object save() throws Exception {
		ICategory category = new Category();
		category.setCategoryId(Integer.parseInt(categories.getSelected()));
		
		if(this.getLogoFile().getFileTransfer() != null &&
				this.getLogoFile().getFilename() != null && 
				this.getLogoFile().getFilename().length() > 0)			
			this.getLogoFile().upload();
		
		if(MetaworksContext.WHEN_NEW.equals(this.getMetaworksContext().getWhen())){
			WfNode project = new WfNode();
			project.setId(this.getAttachProject().getSelected());
			project.copyFrom(project.databaseMe());
			
			this.setAppId( UniqueKeyGenerator.issueWorkItemKey(((ProcessManagerBean) processManager).getTransactionContext()).intValue());
			this.setCreateDate(Calendar.getInstance().getTime());
			this.setComcode(session.getCompany().getComCode());
			this.setComName(session.getCompany().getComName());
			this.setSubDomain(this.getSubDomain());
//			this.setRunningVersion(Integer.parseInt(this.getReleaseVersion().getSelected()));
			this.setProject(project);
			this.setStatus(STATUS_REQUEST);
			this.setCategory(category);
			this.setLogoFile(logoFile);
			
			createDatabaseMe();
			flushDatabaseMe();

			WfNode wfNode = new WfNode();
			
			// 앱 등록일 경우 프로세스 발행
			String defId = "AppRegister.process";
			
			ProcessMap goProcess = new ProcessMap();
			goProcess.session = session;
			goProcess.processManager = processManager;
			goProcess.instanceView = instanceView;
			goProcess.setDefId(defId);
			
			// 프로세스 발행
			Long instId = Long.valueOf(goProcess.initializeProcess());
			
			// 프로세스 실행
			ResultPayload rp = new ResultPayload();
			rp.setProcessVariableChange(new KeyedParameter("appInformation", this));
			
			// 무조건 compleate
			processManager.executeProcessByWorkitem(instId.toString(), rp);
			processManager.applyChanges();
			
//			TopicMapping tm = new TopicMapping();
//			tm.setTopicId(String.valueOf(String.valueOf(this.getAppId())));
//			tm.setUserId(session.getUser().getUserId());
//			tm.setUserName(session.getUser().getName());
//			tm.getMetaworksContext().setWhen(this.getMetaworksContext().getWhen());
//			
//			tm.saveMe();
				
		}else{
			syncToDatabaseMe();
			flushDatabaseMe();
		}

		//이 부분에 vm 관련해서 뭔가를 해야할꺼 같음
		
		
		MyVendor myVendor = new MyVendor();
		myVendor.load(session);
		
		return new ModalPanel(myVendor);
	}
	
	public Object cancel() throws Exception {
		
		MyVendor myVendor = new MyVendor();
		myVendor.load(session);
		
		return new ModalPanel(myVendor);
	}
	
	@ServiceMethod(callByContent=true)
	public Object[] addApp()throws Exception {
		
		
		
		App app = new App();
		app.setAppId(this.getAppId());
		app.copyFrom(app.databaseMe());
//		app.setAppName(this.getAppName());
//		app.setComcode(session.getCompany().getComCode());
//		app.setInstallCnt(this.getInstallCnt());
//		app.setAppId(this.getAppId());
//		app.setAppName(this.getAppName());
//		app.setSimpleOverview(this.getSimpleOverview());
//		app.setFullOverview(this.getFullOverview());
//		app.setPricing(this.getPricing());
//		app.setLogoFile(this.getLogoFile());
//		app.setCategory(this.getCategory());
		
//		
////		AppInformation setAppInfo = new AppInformation();
////		
////		setAppInfo.getMetaworksContext().setWhen("view");
////		
////		setAppInfo.setAppId(this.getAppId());
////		setAppInfo.setAppName(this.getAppName());
////		setAppInfo.setSimpleOverview(this.getSimpleOverview());
////		setAppInfo.setFullOverview(this.getFullOverview());
////		setAppInfo.setPricing(this.getPricing());
////		setAppInfo.setLogoFile(this.getLogoFile());
////		setAppInfo.setCategory(this.getCategory());
//		
		String defId = "AppAcquisition.process";
		
		ProcessMap goProcess = new ProcessMap();
		goProcess.session = session;
		goProcess.processManager = processManager;
		goProcess.instanceView = instanceView;
		goProcess.setDefId(defId);
		
		
		// 프로세스 발행
	    Long instId = Long.valueOf(goProcess.initializeProcess());
	    
	    // 프로세스 실행
	    ResultPayload rp = new ResultPayload();
	    rp.setProcessVariableChange(new KeyedParameter("requestAquisitionApp", app));
	    
	    //무조건 compleate
	    processManager.executeProcessByWorkitem(instId.toString(), rp);
	    processManager.applyChanges();
	    
	    ModalWindow removeWindow = new ModalWindow();
		removeWindow.setId("subscribe");
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		modalWindow.setWidth(300);
		modalWindow.setHeight(150);
		
		modalWindow.setTitle("앱취득 신청 완료");
		
		String panelMessage = "";
		
//		ProjectInfo info = new ProjectInfo(String.valueOf(this.getAppId()));
//		info.load();
		
//		String url = "http://" + session.getCompany().getComName() + "." + info.getDomainName() + ":9090/uengine-web"; 
				
		panelMessage = "앱 취득 신청이 완료 되었습니다. 관리자 승인 후 이용이 가능합니다.";		
//		panelMessage += "url: <a href='" + url + "'>" + url + "</a>";
		
		modalWindow.setPanel(panelMessage);
		modalWindow.getButtons().put("$Confirm", this);
		
		return new Object[] {modalWindow};
		
//		return new Object[]{new MainPanel(new OceMain(session, this.getAppId()))};
	}
	
	@Hidden
//	@ServiceMethod(callByContent = true, eventBinding = "change", bindingFor = "attachProject", bindingHidden = true, target = ServiceMethodContext.TARGET_SELF)
	public void changeProject() throws Exception{
		FilepathInfo filepathInfo = new FilepathInfo();
		filepathInfo.setProjectId(this.getAttachProject().getSelected());
		
//		this.setReleaseVersion(filepathInfo.findReleaseVersions(filepathInfo.getProjectId()));
	}

	@Override
	public void onLoad() throws Exception {
		if (MetaworksContext.WHEN_VIEW.equals(this.getMetaworksContext().getWhen())) {
			
			if(this.getLogoFile() == null){
				this.setLogoFile(new MetaworksFile());
			}
			if(this.getLogoFile().getMetaworksContext() == null){
				this.getLogoFile().setMetaworksContext(new MetaworksContext());
			}
			this.getLogoFile().getMetaworksContext().setWhen("image");
		}
	}
	
	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@AutowiredFromClient
	public Locale localeManager;
	
	
}
