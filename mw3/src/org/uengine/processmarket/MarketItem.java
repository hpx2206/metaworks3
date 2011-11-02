package org.uengine.processmarket;

import java.sql.Timestamp;

import org.metaworks.dao.Database;
import org.metaworks.dao.IDAO;
import org.metaworks.website.MetaworksFile;

public class MarketItem extends Database<MarketItem> implements IMarketItem {
    int itemId;
    int categoryId;
    String itemName;
    String description;
    int version;
    String comCode;
    int price;
    String filePath;
    String logoImageFilePath;
    String image1FilePath;
    String image2FilePath;
    String image3FilePath;
    String image4FilePath;
    String image5FilePath;
    Timestamp regDate;
    int starPoint;
    boolean deleted;
    Timestamp modDate;

    MetaworksFile itemFile;

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
    public String getImage1FilePath() {
	return image1FilePath;
    }

    @Override
    public void setImage1FilePath(String image1FilePath) {
	this.image1FilePath = image1FilePath;
    }

    @Override
    public String getImage2FilePath() {
	return image2FilePath;
    }

    @Override
    public void setImage2FilePath(String image2FilePath) {
	this.image2FilePath = image2FilePath;
    }

    @Override
    public String getImage3FilePath() {
	return image3FilePath;
    }

    @Override
    public void setImage3FilePath(String image3FilePath) {
	this.image3FilePath = image3FilePath;
    }

    @Override
    public String getImage4FilePath() {
	return image4FilePath;
    }

    @Override
    public void setImage4FilePath(String image4FilePath) {
	this.image4FilePath = image4FilePath;
    }

    @Override
    public String getImage5FilePath() {
	return image5FilePath;
    }

    @Override
    public void setImage5FilePath(String image5FilePath) {
	this.image5FilePath = image5FilePath;
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
    public void save() throws Exception {
	try {
	    IDAO itemId = sql(IDAO.class, "select max(itemId) 'itemId' from marketItem");
	    itemId.select();
	    if (itemId.next()) {
		setItemId(itemId.getInt("itemId") + 1);
	    } else {
		setItemId(0);
	    }
	} catch (Exception e) {

	}
	createDatabaseMe();
    }

    @Override
    public void delete() throws Exception {
	deleteDatabaseMe();
    }

    public static IMarketItem loadCategoryItems(ICategory category) throws Exception {
	IMarketItem items = (IMarketItem) sql(IMarketItem.class, "select * from marketItem where categoryId = ?categoryId");
	items.setCategoryId(category.getCategoryId());
	items.select();
	return items;
    }

    public static IMarketItem loadCategoryItemsByKeyword(String keyword) throws Exception {
	IMarketItem items = (IMarketItem) sql(IMarketItem.class, "select * from marketItem where itemName like '%" + keyword + "%'");
	items.select();
	return items;
    }

}
