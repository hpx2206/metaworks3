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

    public String getLogoImageFilePath();

    public void setLogoImageFilePath(String logoImageFilePath);

    public String getImage1FilePath();

    public void setImage1FilePath(String image1FilePath);

    public String getImage2FilePath();

    public void setImage2FilePath(String image2FilePath);

    public String getImage3FilePath();

    public void setImage3FilePath(String image3FilePath);

    public String getImage4FilePath();

    public void setImage4FilePath(String image4FilePath);

    public String getImage5FilePath();

    public void setImage5FilePath(String image5FilePath);

    public Timestamp getRegDate();

    public void setRegDate(Timestamp regDate);

    public int getStarPoint();

    public void setStarPoint(int starPoint);

    public boolean isDeleted();

    public void setDeleted(boolean deleted);

    public Timestamp getModDate();

    public void setModDate(Timestamp modDate);

    @ORMapping(objectFields = { "uploadedPath" }, databaseFields = { "filePath" })
    public MetaworksFile getItemFile();

    public void setItemFile(MetaworksFile itemFile);

    @ServiceMethod(callByContent = true)
    public void save() throws Exception;

    @ServiceMethod(needToConfirm = true)
    public void delete() throws Exception;
}
