package org.uengine.codi.mw3.marketplace;

import java.util.Calendar;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.Refresh;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.website.MetaworksFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.marketplace.category.Category;
import org.uengine.codi.mw3.marketplace.category.ICategory;
import org.uengine.codi.mw3.model.InstanceViewContent;
import org.uengine.codi.mw3.model.ProcessMap;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.KeyedParameter;
import org.uengine.kernel.ResultPayload;
import org.uengine.persistence.dao.UniqueKeyGenerator;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(
		displayName = "$AppInfo",
		ejsPath="genericfaces/FormFace.ejs"
		,
		options={"fieldOrder"},
		values={"categories,listingName,simpleOverview,fullOverview,pricing,file,logoFile"}
	)
public class AppInformation implements ContextAware, ITool {

	public final static String STATUS_REQUEST = "Request";
	public final static String STATUS_APPROVED = "Approved";
	public final static String STATUS_REJECT = "Reject";
	public final static String STATUS_PUBLISHED = "Published";
	public final static String STATUS_UNPUBLISHED = "Unpublished";

	public AppInformation() throws Exception {

		setFile(new MetaworksFile());
		setLogoFile(new MetaworksFile());
		
		this.setMetaworksContext(new MetaworksContext());

	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	
	SelectBox categories;
		@Face(displayName="카테고리")
		public SelectBox getCategories() {
			return categories;
		}
		public void setCategories(SelectBox categories) {
			this.categories = categories;
		}
	
	int listingId;
		@Hidden
		public int getListingId() {
			return listingId;
		}
		public void setListingId(int listingId) {
			this.listingId = listingId;
		}

	String listingName;
		@Face(displayName="앱이름")
		public String getListingName() {
			return listingName;
		}
		public void setListingName(String listingName) {
			this.listingName = listingName;
		}
	
	String simpleOverview;
		@Face(displayName="심플 설명", ejsPath="dwr/metaworks/genericfaces/richText.ejs", options={"rows", "cols"}, values={"10", "130"})
		public String getSimpleOverview() {
			return simpleOverview;
		}
		public void setSimpleOverview(String simpleOverview) {
			this.simpleOverview = simpleOverview;
		}
	
	String fullOverview;
		@Face(displayName="데테일 설명", ejsPath="dwr/metaworks/genericfaces/richText.ejs", options={"rows", "cols"}, values={"15", "130"})
		public String getFullOverview() {
			return fullOverview;
		}
		public void setFullOverview(String fullOverview) {
			this.fullOverview = fullOverview;
		}

	MetaworksFile file;
		@Face(displayName="앱 첨부 파일")
		public MetaworksFile getFile() {
			return file;
		}
		public void setFile(MetaworksFile file) {
			this.file = file;
		}

	String pricing;
		@Face(displayName="가격")
		public String getPricing() {
			return pricing;
		}
		public void setPricing(String pricing) {
			this.pricing = pricing;
		}

	MetaworksFile logoFile;
		@Face(displayName="로고파일", options={"width", "height"}, values={"150", "150"})
		public MetaworksFile getLogoFile() {
			return logoFile;
		}
		public void setLogoFile(MetaworksFile logoFile) {
			this.logoFile = logoFile;
		}
	
	IApp app;
		public IApp getApp() {
			return app;
		}
		public void setApp(IApp app) {
			this.app = app;
		}

	ICategory category;
		public ICategory getCategory() {
			return category;
		}
		public void setCategory(ICategory category) {
			this.category = category;
		}
		
	@Autowired
	transient public ProcessManagerRemote processManager;

	@AutowiredFromClient
	transient public Session session;
	
	@Autowired
	transient public InstanceViewContent instanceView;
	

	@ServiceMethod(callByContent = true, when = "new")
	public Object add() throws Exception {
		
		if (getFile() == null || getFile().getFileTransfer() == null || getFile().getFileTransfer().getFilename() == null || getFile().getFilename() == null)
			throw new MetaworksException("$YouMustAttachItemFile");

		if (getLogoFile() == null || getLogoFile().getFileTransfer() == null || getFile().getFileTransfer().getFilename() == null || getLogoFile().getFilename() == null)
			throw new MetaworksException("$YouMustAttachLogoFile");

		getFile().upload();
		getLogoFile().upload();

		App listing = new App();
		
		listing.setAppId(UniqueKeyGenerator.issueWorkItemKey(((ProcessManagerBean)processManager).getTransactionContext()).intValue());
		listing.setAppName(listingName);
		listing.setSimpleOverview(simpleOverview);
		listing.setFullOverview(fullOverview);
		listing.setPricing(pricing);
		listing.setExtfile(getFile());
		listing.setLogoFile(getLogoFile());
		listing.setCreateDate(Calendar.getInstance().getTime());
		listing.setVendorId(session.getCompany().getComCode());
		listing.setStatus(STATUS_REQUEST);
		listing.setIsDeleted(false);
		
		this.setApp(listing);
		
		ICategory category = new Category();
		category.setCategoryId(Integer.parseInt(categories.getSelected()));
		listing.setCategory(category);

		listing.createDatabaseMe();
		listing.flushDatabaseMe();
		
		PageNavigator gomarketHome = new PageNavigator();
		gomarketHome.session = session;
		
		
		
		String defId = "appRegister_001.process";
		
		ProcessMap goProcess = new ProcessMap();
		goProcess.session = session;
		goProcess.processManager = processManager;
		goProcess.instanceView = instanceView;
		goProcess.setDefId(defId);
		
		// 프로세스 발행
	    Long instId = Long.valueOf(goProcess.initializeProcess());
	    
	    // 프로세스 실행
	    ResultPayload rp = new ResultPayload();
	    rp.setProcessVariableChange(new KeyedParameter("requestAppendApp", this));
	    
	    //무조건 compleate
	    processManager.executeProcessByWorkitem(instId.toString(), rp);
	    
		return new Refresh(gomarketHome.goMarketplace(), true);
		

	}
	
	@ServiceMethod(callByContent = true, when="edit")
	public void edit() throws Exception {

		if (getFile() == null || getFile().getFileTransfer() == null || getFile().getFileTransfer().getFilename() == null || getFile().getFilename() == null)
			throw new MetaworksException("$YouMustAttachItemFile");

		if (getLogoFile() == null || getLogoFile().getFileTransfer() == null || getFile().getFileTransfer().getFilename() == null || getLogoFile().getFilename() == null)
			throw new MetaworksException("$YouMustAttachLogoFile");
		
		
		App listing = new App();
		
		listing.setAppId(getListingId());
		
		//file이 수정되지 않았을경우 파일 업로드 하지 않음, 추후에 파일 업로드 유무에 관련없이 기존 파일 긁어 와서 재 저장하는식으로 변경할예정 (wrote by jisun)
		if(!(listing.databaseMe().getExtfile().getFilename()).equals(getFile().getFilename())){
			getFile().upload();
		}
		
		if(!(listing.databaseMe().getLogoFile().getFilename()).equals(getLogoFile().getFilename())){
			getLogoFile().upload();
		}

		
		listing.setAppName(listingName);
		listing.setSimpleOverview(simpleOverview);
		listing.setFullOverview(fullOverview);
		listing.setPricing(pricing);
		listing.setExtfile(getFile());
		listing.setLogoFile(getLogoFile());
		listing.setCreateDate(Calendar.getInstance().getTime());
		listing.setVendorId(session.getCompany().getComCode());
		listing.setStatus(STATUS_REQUEST);
		listing.setIsDeleted(false);
		
		ICategory category = new Category();
		category.setCategoryId(Integer.parseInt(categories.getSelected()));
		listing.setCategory(category);
		
		listing.syncToDatabaseMe();

	}
	
	@Override
	public void onLoad() throws Exception {
		if(MetaworksContext.WHEN_VIEW.equals(this.getMetaworksContext().getWhen())){
			this.getLogoFile().getMetaworksContext().setWhen("image");
			this.getFile().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		}
		
	}
	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterComplete() throws Exception {
		
		
	}

}
