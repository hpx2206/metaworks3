package org.uengine.processmarket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.sql.Timestamp;

import org.directwebremoting.io.FileTransfer;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.dao.IDAO;
import org.metaworks.website.Download;
import org.metaworks.website.MetaworksFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.processmanager.ProcessDefinitionRemote;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.util.UEngineUtil;

public class MarketItem extends Database<IMarketItem> implements IMarketItem {
	int itemId;
	int categoryId;
	String itemName;
	String description;
	int version;
	String comCode;
	int price;
	String filePath;
	String logoImageFilePath;
	String imageFile1Path;
	String imageFile2Path;
	String imageFile3Path;
	String imageFile4Path;
	String imageFile5Path;
	Timestamp regDate;
	int starPoint;
	boolean deleted;
	Timestamp modDate;

	MetaworksFile itemFile;
	MetaworksImageFile logoImageFile;
	MetaworksImageFile imageFile1;
	MetaworksImageFile imageFile2;
	MetaworksImageFile imageFile3;
	MetaworksImageFile imageFile4;
	MetaworksImageFile imageFile5;

	String appKey;
	String defId;

	@AutowiredFromClient
	public Session session;

	@Autowired
	public ProcessManagerRemote processManager;

	// @Autowired
	// ProcessMarketSVC processMarketService;

	@Override
	public int getItemId() {
		return itemId;
	}

	@Override
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	@Override
	public int getCategoryId() {
		return categoryId;
	}

	@Override
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String getItemName() {
		return itemName;
	}

	@Override
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String getComCode() {
		return comCode;
	}

	@Override
	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String getFilePath() {
		return filePath;
	}

	@Override
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String getLogoImageFilePath() {
		return logoImageFilePath;
	}

	@Override
	public void setLogoImageFilePath(String logoImageFilePath) {
		this.logoImageFilePath = logoImageFilePath;
	}

	@Override
	public String getImageFile1Path() {
		return imageFile1Path;
	}

	@Override
	public void setImageFile1Path(String imageFile1Path) {
		this.imageFile1Path = imageFile1Path;
	}

	@Override
	public String getImageFile2Path() {
		return imageFile2Path;
	}

	@Override
	public void setImageFile2Path(String imageFile2Path) {
		this.imageFile2Path = imageFile2Path;
	}

	@Override
	public String getImageFile3Path() {
		return imageFile3Path;
	}

	@Override
	public void setImageFile3Path(String imageFile3Path) {
		this.imageFile3Path = imageFile3Path;
	}

	@Override
	public String getImageFile4Path() {
		return imageFile4Path;
	}

	@Override
	public void setImageFile4Path(String imageFile4Path) {
		this.imageFile4Path = imageFile4Path;
	}

	@Override
	public String getImageFile5Path() {
		return imageFile5Path;
	}

	@Override
	public void setImageFile5Path(String imageFile5Path) {
		this.imageFile5Path = imageFile5Path;
	}

	@Override
	public Timestamp getRegDate() {
		return regDate;
	}

	@Override
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	@Override
	public int getStarPoint() {
		return starPoint;
	}

	@Override
	public void setStarPoint(int starPoint) {
		this.starPoint = starPoint;
	}

	@Override
	public boolean isDeleted() {
		return deleted;
	}

	@Override
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public Timestamp getModDate() {
		return modDate;
	}

	@Override
	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}

	@Override
	public MetaworksFile getItemFile() {
		return itemFile;
	}

	@Override
	public void setItemFile(MetaworksFile itemFile) {
		this.itemFile = itemFile;
	}

	@Override
	public MetaworksImageFile getLogoImageFile() {
		return logoImageFile;
	}

	@Override
	public void setLogoImageFile(MetaworksImageFile logoImageFile) {
		this.logoImageFile = logoImageFile;
	}

	@Override
	public MetaworksImageFile getImageFile1() {
		return imageFile1;
	}

	@Override
	public void setImageFile1(MetaworksImageFile imageFile1) {
		this.imageFile1 = imageFile1;
	}

	@Override
	public MetaworksImageFile getImageFile2() {
		return imageFile2;
	}

	@Override
	public void setImageFile2(MetaworksImageFile imageFile2) {
		this.imageFile2 = imageFile2;
	}

	@Override
	public MetaworksImageFile getImageFile3() {
		return imageFile3;
	}

	@Override
	public void setImageFile3(MetaworksImageFile imageFile3) {
		this.imageFile3 = imageFile3;
	}

	@Override
	public MetaworksImageFile getImageFile4() {
		return imageFile4;
	}

	@Override
	public void setImageFile4(MetaworksImageFile imageFile4) {
		this.imageFile4 = imageFile4;
	}

	@Override
	public MetaworksImageFile getImageFile5() {
		return imageFile5;
	}

	@Override
	public void setImageFile5(MetaworksImageFile imageFile5) {
		this.imageFile5 = imageFile5;
	}

	@Override
	public void save() throws Exception {

		try {
			IDAO itemId = sql(IDAO.class,
					"select max(itemId) 'itemId' from marketItem");
			itemId.select();
			if (itemId.next()) {
				setItemId(itemId.getInt("itemId") + 1);
			} else {
				setItemId(0);
			}

			uploadMarketItemFiles(itemFile);
			uploadMarketItemFiles(logoImageFile);
			uploadMarketItemFiles(imageFile1);
			uploadMarketItemFiles(imageFile2);
			uploadMarketItemFiles(imageFile3);
			uploadMarketItemFiles(imageFile4);
			uploadMarketItemFiles(imageFile5);
		} catch (Exception e) {

		}
		setCategoryId(0); // set Category New
		// if (registringSession()) {
		// setComCode(session.getEmployee().getGlobalCom());
		// }
		createDatabaseMe();

		getMetaworksContext().setWhen(WHEN_VIEW);
	}

	private void uploadMarketItemFiles(MetaworksFile file) throws Exception {
		if (file.getFileTransfer().getFilename() != null
				&& !"".equals(file.getFileTransfer().getFilename().trim())) {
			file.upload();
		}
	}

	@Override
	public void delete() throws Exception {
		deleteDatabaseMe();
	}

	public static IMarketItem loadCategoryItems(ICategory category)
			throws Exception {
		IMarketItem items = (IMarketItem) sql(IMarketItem.class,
				"select * from marketItem where categoryId = ?categoryId");
		items.setCategoryId(category.getCategoryId());
		items.select();
		return items;
	}

	public static IMarketItem loadCategoryItemsByKeyword(String keyword)
			throws Exception {
		IMarketItem items = (IMarketItem) sql(IMarketItem.class,
				"select * from marketItem where itemName like '%" + keyword
						+ "%'");
		items.select();
		return items;
	}

	@Override
	public IMarketItem detail() throws Exception {
		IMarketItem item = databaseMe();
		item.getMetaworksContext().setHow(MetaworksContext.HOW_NORMAL);
		item.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);

		return item;
	}

	@Override
	public IMarketItem inList() throws Exception {
		IMarketItem item = databaseMe();
		item.getMetaworksContext().setHow(MetaworksContext.HOW_IN_LIST);
		item.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);

		return item;
	}

	@Override
	public Download purchase() throws Exception {
		MetaworksFile itemFile = databaseMe().getItemFile();
		itemFile.setFilename(databaseMe().getItemName() + ".prom");
		
		return itemFile.download();
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getDefId() {
		return defId;
	}

	public void setDefId(String defId) {
		this.defId = defId;
	}

	@Override
	public IMarketItem addToMarket() throws Exception {
		setItemId(100);
		setComCode("uEngine");// session.getEmployee().getGlobalCom());
		
		if(getItemFile() == null || getItemFile().getFileTransfer()==null)
			throw new Exception("$YouMustAttachItemFile");
		
		if(getLogoImageFile() == null || getLogoImageFile().getFileTransfer()==null)
			throw new Exception("$YouMustAttachLogoFile");
		
		getItemFile().upload();
		getLogoImageFile().upload();

		createDatabaseMe();
		
		//flushDatabaseMe();
		
		//getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		//databaseMe().getMetaworksContext().setWhen("view");
		return databaseMe();
	}

	private void fillImageFileObject() {
		setLogoImageFile(fillImageFileObject(getLogoImageFile()));
		setImageFile1(fillImageFileObject(getImageFile1()));
		setImageFile2(fillImageFileObject(getImageFile2()));
		setImageFile3(fillImageFileObject(getImageFile3()));
		setImageFile4(fillImageFileObject(getImageFile4()));
		setImageFile5(fillImageFileObject(getImageFile5()));
	}

	private MetaworksImageFile fillImageFileObject(MetaworksImageFile imageFile) {
		if (imageFile == null) {
			imageFile = new MetaworksImageFile();
		}
		return imageFile;
	}

	// @AutowiredFromClient
	// public MarketSession marketSession;
	//
	// private boolean registringSession() {
	// boolean hasAppKey = false;
	// if (UEngineUtil.isNotEmpty(getAppKey())){
	// hasAppKey = true;
	// }else if (marketSession != null &&
	// UEngineUtil.isNotEmpty(marketSession.getAppKey())) {
	// setAppKey(marketSession.getAppKey());
	// hasAppKey = true;
	// }
	// if(hasAppKey) {
	// setAppKey(marketSession.getAppKey());
	// if (session != null) {
	// session.setAppKey(getAppKey());
	// return true;
	// }
	// }
	// return false;
	// }
}
