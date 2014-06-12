package org.uengine.processmarket;

import java.sql.Timestamp;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Children;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

@Face(
	options={"hideAddBtn"},
	values={"true"}
)
public interface ICategory extends IDAO {
    
    @Id
    public int getCategoryId();
    public void setCategoryId(int categoryId);
    
    @Name
    public String getCategoryName();

    public void setCategoryName(String categoryName);
    
    public int getParentCategoryId();
    
    public void setParentCategoryId(int parentCategoryId);
    
    public Timestamp getModDate();
    
    public void setModdDate(Timestamp currentTimeStamp);
    
    @NonLoadable
    @NonSavable
    @Children
    @Hidden
    public ICategory getChildrenCategories();
    
    public void setChildrenCategories(ICategory childrenCategories);
    
    @NonLoadable
    @NonSavable
    @Hidden
    public boolean isSelected();
    public void setSelected(boolean selected);
    
    public boolean isDeleted();

    public void setDeleted(boolean deleted);

    @ServiceMethod(callByContent = true, when = "never")
    public Object[] selectCategory() throws Exception; // should avoid 'select()' since it is for IDAO sql data selection.

    @ServiceMethod(callByContent = true)
    public void save() throws Exception;

    @ServiceMethod(callByContent = true)
    public void checkDeleteFlag() throws Exception;

    @ServiceMethod(callByContent = true)
    public void uncheckDeleteFlag() throws Exception;

    @ServiceMethod(needToConfirm = true)
    public void delete() throws Exception;

    @ServiceMethod(callByContent = true)
    public void addChild() throws Exception;

    @Children
    @ServiceMethod(target = ServiceMethodContext.TARGET_NONE, when = "never")
    // it just return object value only
    public ICategory loadChildrenCategories() throws Exception;
    
}
