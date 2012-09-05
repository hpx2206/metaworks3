package org.uengine.processmarket;

import java.sql.Timestamp;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;
import org.metaworks.website.Download;
import org.metaworks.website.MetaworksFile;


@Face(
	displayName = "$AppInfo",
	ejsPathMappingByContext = {
		"{where: 'in-list', face: 'faces/org/uengine/processmarket/IMarketItem.ejs'}",
		"{where: 'normal', face: 'faces/org/uengine/processmarket/IMarketItem.ejs'}",
		"{when: 'edit', face: 'faces/org/uengine/processmarket/IMarketItem.ejs'}",
		"{when: 'new', face: 'genericfaces/ObjectFace.ejs'}",
	}
)
public interface IMarketItem extends IDAO {

    @Id
    public int getItemId();

    public void setItemId(int itemId);

    public int getCategoryId();

    public void setCategoryId(int categoryId);

    @Name
    public String getItemName();

    public void setItemName(String itemName);

    public String getDescription();

    public void setDescription(String description);

    public int getVersion();

    public void setVersion(int version);

    public String getComCode();

    public void setComCode(String comCode);

    public int getPrice();

    public void setPrice(int price);

    @Hidden
    public String getFilePath();

    public void setFilePath(String filePath);

    @Hidden
    public String getLogoImageFilePath();

    public void setLogoImageFilePath(String logoImageFilePath);

    @Hidden
    public String getImageFile1Path();

    public void setImageFile1Path(String imageFile1Path);

    @Hidden
    public String getImageFile2Path();

    public void setImageFile2Path(String imageFile2Path);

    @Hidden
    public String getImageFile3Path();

    public void setImageFile3Path(String imageFile3Path);

    @Hidden
    public String getImageFile4Path();

    public void setImageFile4Path(String imageFile4Path);

    @Hidden
    public String getImageFile5Path();

    public void setImageFile5Path(String imageFile5Path);

    @Hidden
    public Timestamp getRegDate();

    public void setRegDate(Timestamp regDate);

    @Hidden
    public int getStarPoint();

    public void setStarPoint(int starPoint);

    @Hidden
    public boolean isDeleted();

    public void setDeleted(boolean deleted);

    @Hidden
    public Timestamp getModDate();

    public void setModDate(Timestamp modDate);

    @ORMapping(objectFields = { "uploadedPath" }, databaseFields = { "filePath" })
    public MetaworksFile getItemFile();

    public void setItemFile(MetaworksFile itemFile);

    @ORMapping(objectFields = { "uploadedPath" }, databaseFields = { "logoImageFilePath" })
    public MetaworksImageFile getLogoImageFile();

    public void setLogoImageFile(MetaworksImageFile logoImageFile);

    @ORMapping(objectFields = { "uploadedPath" }, databaseFields = { "imageFile1Path" })
    public MetaworksImageFile getImageFile1();

    public void setImageFile1(MetaworksImageFile imageFile1);

    @ORMapping(objectFields = { "uploadedPath" }, databaseFields = { "imageFile2Path" })
    public MetaworksImageFile getImageFile2();

    public void setImageFile2(MetaworksImageFile imageFile2);

    @ORMapping(objectFields = { "uploadedPath" }, databaseFields = { "imageFile3Path" })
    public MetaworksImageFile getImageFile3();

    public void setImageFile3(MetaworksImageFile imageFile3);

    @ORMapping(objectFields = { "uploadedPath" }, databaseFields = { "imageFile4Path" })
    public MetaworksImageFile getImageFile4();

    public void setImageFile4(MetaworksImageFile imageFile4);

    @ORMapping(objectFields = { "uploadedPath" }, databaseFields = { "imageFile5Path" })
    public MetaworksImageFile getImageFile5();

    public void setImageFile5(MetaworksImageFile imageFile5);

    @ServiceMethod(callByContent = true)
    public void save() throws Exception;

    @Hidden
    @ServiceMethod(needToConfirm = true)
    public void delete() throws Exception;

    @Hidden
    @ServiceMethod
    public IMarketItem detail() throws Exception;

    @Hidden
    @ServiceMethod
    IMarketItem inList() throws Exception;

    @Hidden
    @ServiceMethod(needToConfirm = true)
    public Download purchase() throws Exception;

    @NonLoadable
    @NonSavable
    @Hidden
    public String getAppKey();

    public void setAppKey(String appKey);

    @NonLoadable
    @NonSavable
    @Hidden
    public String getDefId();

    public void setDefId(String defId);

    @ServiceMethod(callByContent = true)
    public IMarketItem addToMarket() throws Exception;
}
