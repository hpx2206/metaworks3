package org.uengine.codi.mw3.marketplace;

import java.util.Calendar;

import javax.persistence.SequenceGenerator;

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
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.marketplace.category.Category;
import org.uengine.codi.mw3.marketplace.category.ICategory;
import org.uengine.codi.mw3.model.Session;
import org.uengine.persistence.dao.UniqueKeyGenerator;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(
		displayName = "$AppInfo",
		ejsPathMappingByContext = {
			"{when: 'new', face: 'genericfaces/FormFace.ejs'}",
			"{when: 'edit', face: 'genericfaces/FormFace.ejs'}"
		},
		options={"fieldOrder"},
		values={"categories,listingName,simpleOverview,fullOverview,pricing,file,logoFile"}
	)
public class AppInformation implements ContextAware {

	public final static String STATUS_REQUEST = "Request";
	public final static String STATUS_APPROVAL = "Approval";
	public final static String STATUS_REJECT = "Reject";
	public final static String STATUS_PUBLISHED = "Published";
	public final static String STATUS_UNPUBLISHED = "Unpublished";

	public AppInformation() throws Exception {

		setFile(new MetaworksFile());
		setLogoFile(new MetaworksFile());
		
		this.setMetaworksContext(new MetaworksContext());

	}
	
	@Hidden
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	@Face(displayName="카테고리")
	SelectBox categories;
		public SelectBox getCategories() {
			return categories;
		}
		public void setCategories(SelectBox categories) {
			this.categories = categories;
		}
	
	@Hidden
	int listingId;
		public int getListingId() {
			return listingId;
		}
		public void setListingId(int listingId) {
			this.listingId = listingId;
		}

	@Face(displayName="앱이름")
	String listingName;
		public String getListingName() {
			return listingName;
		}
		public void setListingName(String listingName) {
			this.listingName = listingName;
		}

	@Face(displayName="심플 설명")
	String simpleOverview;
		public String getSimpleOverview() {
			return simpleOverview;
		}
		public void setSimpleOverview(String simpleOverview) {
			this.simpleOverview = simpleOverview;
		}
		
	@Face(displayName="데테일 설명")
	String fullOverview;
		public String getFullOverview() {
			return fullOverview;
		}
		public void setFullOverview(String fullOverview) {
			this.fullOverview = fullOverview;
		}

	@Face(displayName="앱 첨부 파일")
	MetaworksFile file;
		public MetaworksFile getFile() {
			return file;
		}
		public void setFile(MetaworksFile file) {
			this.file = file;
		}

	@Face(displayName="가격")
	String pricing;
		public String getPricing() {
			return pricing;
		}
		public void setPricing(String pricing) {
			this.pricing = pricing;
		}

	@Face(displayName="로고파일")
	MetaworksFile logoFile;
		public MetaworksFile getLogoFile() {
			return logoFile;
		}
		public void setLogoFile(MetaworksFile logoFile) {
			this.logoFile = logoFile;
		}

	@Autowired
	public ProcessManagerRemote processManager;

	@AutowiredFromClient
	public Session session;

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
		
		ICategory category = new Category();
		category.setCategoryId(Integer.parseInt(categories.getSelected()));
		listing.setCategory(category);

		listing.createDatabaseMe();
		listing.flushDatabaseMe();
		
		PageNavigator gomarketHome = new PageNavigator();
		gomarketHome.session = session;
		
		
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

}
