package org.uengine.processmarket;

import java.sql.Timestamp;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;
import org.metaworks.website.MetaworksFile;


@Face(
	ejsPathMappingByContext = {
		"{where: 'in-list', face: 'faces/org/uengine/procesmarket/IMarketItem.ejs'}",
		"{where: 'normal', face: 'faces/org/uengine/procesmarket/IMarketItem.ejs'}",
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

    public int getStarPoint();

    public void setStarPoint(int starPoint);

    public boolean isDeleted();

    public void setDeleted(boolean deleted);

    @Hidden
    public Timestamp getModDate();

    public void setModDate(Timestamp modDate);

    @ORMapping(objectFields = { "uploadedPath" }, databaseFields = { "filePath" })
    public MetaworksFile getItemFile();

    public void setItemFile(MetaworksFile itemFile);

    @ORMapping(objectFields = { "uploadedPath" }, databaseFields = { "logoImageFilePath" })
    public MetaworksFile getLogoImageFile();

    public void setLogoImageFile(MetaworksFile logoImageFile);

    @ORMapping(objectFields = { "uploadedPath" }, databaseFields = { "imageFile1Path" })
    public MetaworksFile getImageFile1();

    public void setImageFile1(MetaworksFile imageFile1);

    @ORMapping(objectFields = { "uploadedPath" }, databaseFields = { "imageFile2Path" })
    public MetaworksFile getImageFile2();

    public void setImageFile2(MetaworksFile imageFile2);

    @ORMapping(objectFields = { "uploadedPath" }, databaseFields = { "imageFile3Path" })
    public MetaworksFile getImageFile3();

    public void setImageFile3(MetaworksFile imageFile3);

    @ORMapping(objectFields = { "uploadedPath" }, databaseFields = { "imageFile4Path" })
    public MetaworksFile getImageFile4();

    public void setImageFile4(MetaworksFile imageFile4);

    @ORMapping(objectFields = { "uploadedPath" }, databaseFields = { "imageFile5Path" })
    public MetaworksFile getImageFile5();

    public void setImageFile5(MetaworksFile imageFile5);

    @ServiceMethod(callByContent = true)
    public void save() throws Exception;

    @ServiceMethod(needToConfirm = true)
    public void delete() throws Exception;

    @ServiceMethod
    public IMarketItem detail() throws Exception;
}
