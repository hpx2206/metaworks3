package org.uengine.codi.mw3.marketplace;

import java.util.Calendar;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.website.MetaworksFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.knowledge.IWfNode;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.codi.mw3.marketplace.category.Category;
import org.uengine.codi.mw3.marketplace.category.ICategory;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.ProcessMap;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.KeyedParameter;
import org.uengine.kernel.ResultPayload;
import org.uengine.persistence.dao.UniqueKeyGenerator;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(displayName = "$AppInfo")
public class AppInformation implements ContextAware, ITool {

	public AppInformation() throws Exception {

		setLogoFile(new MetaworksFile());
		this.setMetaworksContext(new MetaworksContext());

	}

	public AppInformation(int appId) throws Exception {
		this.setAppId(appId);
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	SelectBox categories;
		@Face(displayName = "카테고리")
		public SelectBox getCategories() {
			return categories;
		}
		public void setCategories(SelectBox categories) {
			this.categories = categories;
		}

	int appId;
		@Hidden
		public int getAppId() {
			return appId;
		}
		public void setAppId(int appId) {
			this.appId = appId;
		}

	String appName;
		@Face(displayName = "앱이름")
		public String getAppName() {
			return appName;
		}
		public void setAppName(String appName) {
			this.appName = appName;
		}

	String simpleOverview;
		@Face(displayName = "심플 설명", ejsPath = "dwr/metaworks/genericfaces/richText.ejs", options = { "rows", "cols" }, values = { "10", "130" })
		public String getSimpleOverview() {
			return simpleOverview;
		}
		public void setSimpleOverview(String simpleOverview) {
			this.simpleOverview = simpleOverview;
		}

	String fullOverview;
		@Face(displayName = "데테일 설명", ejsPath = "dwr/metaworks/genericfaces/richText.ejs", options = {
				"rows", "cols" }, values = { "15", "130" })
		public String getFullOverview() {
			return fullOverview;
		}
		public void setFullOverview(String fullOverview) {
			this.fullOverview = fullOverview;
		}

	SelectBox attachProject;
		@Face(displayName = "프로젝트")
		public SelectBox getAttachProject() {
			return attachProject;
		}
		public void setAttachProject(SelectBox attachProject) {
			this.attachProject = attachProject;
		}

	String pricing;
		@Face(displayName = "가격")
		public String getPricing() {
			return pricing;
		}
		public void setPricing(String pricing) {
			this.pricing = pricing;
		}

	MetaworksFile logoFile;
		@Face(displayName = "로고파일", options = { "width", "height" }, values = {
				"150", "150" })
		public MetaworksFile getLogoFile() {
			return logoFile;
		}
		public void setLogoFile(MetaworksFile logoFile) {
			this.logoFile = logoFile;
		}

	ICategory category;
		public ICategory getCategory() {
			return category;
		}
		public void setCategory(ICategory category) {
			this.category = category;
		}

	String projectId;
		@NonLoadable
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}

	String projectName;
		@NonLoadable
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
		
	String url;
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
	@Autowired
	transient public ProcessManagerRemote processManager;

	@AutowiredFromClient
	transient public Session session;

	@Autowired
	transient public InstanceViewContent instanceView;

	@ServiceMethod(callByContent = true, when = "new")
	public Object add() throws Exception {

		if (getLogoFile() == null || getLogoFile().getFileTransfer() == null || getLogoFile().getFileTransfer().getFilename() == null || getLogoFile().getFilename() == null)
			throw new MetaworksException("$YouMustAttachLogoFile");

		getLogoFile().upload();

		ICategory category = new Category();
		category.setCategoryId(Integer.parseInt(categories.getSelected()));

		WfNode project = new WfNode();
		project.setId(this.getAttachProject().getSelected());

		App listing = new App();

		listing.setAppId(UniqueKeyGenerator.issueWorkItemKey(((ProcessManagerBean) processManager).getTransactionContext()).intValue());
		listing.setAppName(appName);
		listing.setSimpleOverview(simpleOverview);
		listing.setFullOverview(fullOverview);
		listing.setPricing(pricing);
		listing.setLogoFile(getLogoFile());
		listing.setCreateDate(Calendar.getInstance().getTime());
		listing.setComcode(session.getCompany().getComCode());
		listing.setStatus(App.STATUS_REQUEST);
		listing.setIsDeleted(false);
		listing.setCategory(category);
		listing.setProject(project);
		listing.createDatabaseMe();
		listing.flushDatabaseMe();

		this.setAppId(listing.getAppId());
		this.setProjectId(this.getAttachProject().getSelected());
		this.setProjectName(this.getAttachProject().getSelectedText());

		PageNavigator gomarketHome = new PageNavigator();
		gomarketHome.session = session;

		String defId = "AppRegister.process";
		//
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
		
		return new Refresh(gomarketHome.goMarketplace(), true);

	}

	@ServiceMethod(callByContent = true, when = "edit")
	public Object edit() throws Exception {

		if (getLogoFile() == null || getLogoFile().getFileTransfer() == null || getLogoFile().getFileTransfer().getFilename() == null || getLogoFile().getFilename() == null)
			throw new MetaworksException("$YouMustAttachLogoFile");

		App listing = new App();
		listing.setAppId(this.getAppId());
		
		if (!(listing.databaseMe().getLogoFile().getFilename()).equals(getLogoFile().getFilename())) {
			getLogoFile().upload();
		}

		listing.setAppName(appName);
		listing.setSimpleOverview(simpleOverview);
		listing.setFullOverview(fullOverview);
		listing.setPricing(pricing);
		listing.setLogoFile(getLogoFile());
		listing.setCreateDate(Calendar.getInstance().getTime());
		listing.setComcode(session.getCompany().getComCode());
		listing.setStatus(listing.databaseMe().getStatus());	
		listing.setIsDeleted(false);

		ICategory category = new Category();
		category.setCategoryId(Integer.parseInt(categories.getSelected()));
		listing.setCategory(category);

		listing.syncToDatabaseMe();
		listing.flushDatabaseMe();

		Marketplace goVendorPage = new Marketplace();
		goVendorPage.session = session;

		return new Refresh(goVendorPage.showMyVendor(), true);

	}

	@Override
	public void onLoad() throws Exception {
		if (MetaworksContext.WHEN_VIEW.equals(this.getMetaworksContext()
				.getWhen())) {
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

	}

	public void load() throws Exception{
		App app = new App();
		app.setAppId(this.getAppId());
		
		try {
			app.copyFrom(app.databaseMe());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		this.appName = app.getAppName();
		this.simpleOverview = app.getSimpleOverview();			
		this.fullOverview = app.getFullOverview(); 
		this.logoFile = app.getLogoFile();
		this.setUrl(app.getUrl());
		
	}
	
	@ServiceMethod(target = ServiceMethodContext.TARGET_NONE)
	public void appExcute(){
	}
}
