package org.uengine.processmarket;

import java.sql.Timestamp;

import org.metaworks.MetaworksContext;
import org.metaworks.dao.Database;
import org.metaworks.dao.IDAO;

public class Category extends Database<ICategory> implements ICategory {

    int categoryId;
    String categoryName;
    int parentCategoryId;
    Timestamp modDate;
    ICategory childrenCategories;
    boolean deleted;
    boolean selected;

    @Override
    public int getCategoryId() {
	return categoryId;
    }

    @Override
    public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
    }

    @Override
    public String getCategoryName() {
	return categoryName;
    }

    @Override
    public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
    }

    @Override
    public int getParentCategoryId() {
	return parentCategoryId;
    }

    @Override
    public void setParentCategoryId(int parentCategoryId) {
	this.parentCategoryId = parentCategoryId;
    }

    @Override
    public ICategory getChildrenCategories() {
	return childrenCategories;
    }

    @Override
    public void setChildrenCategories(ICategory childrenCategories) {
	this.childrenCategories = childrenCategories;
    }

    @Override
    public Timestamp getModDate() {
	return modDate;
    }

    @Override
    public void setModdDate(Timestamp currentTimeStamp) {
	this.modDate = currentTimeStamp;
    }

    @Override
    public boolean isSelected() {
	return selected;
    }

    @Override
    public void setSelected(boolean selected) {
	this.selected = selected;
    }

    @Override
    public boolean isDeleted() {
	return this.deleted;
    }

    @Override
    public void setDeleted(boolean deleted) {
	this.deleted = deleted;
    }

    @Override
    public Object[] selectCategory() throws Exception {
	MarketItemPanel mp = fillMarketItemPanel();
	if (isSelected()) {
	    setChildrenCategories(null);
	    setSelected(false);
	    return new Object[] { this, mp };
	}

	String sql = "select * from category where parentCategoryId = ?parentCategoryId";
	childrenCategories = (ICategory) sql(ICategory.class, sql);
	childrenCategories.setParentCategoryId(this.categoryId);
	childrenCategories.select();
	this.setSelected(true);

	return new Object[] { this, mp };
    }

    private MarketItemPanel fillMarketItemPanel() throws Exception {
	MarketItemPanel mp = new MarketItemPanel();
	IMarketItem items = MarketItem.loadCategoryItems(this);
	items.getMetaworksContext().setHow(MetaworksContext.HOW_IN_LIST);
	items.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	mp.setMarketItem(items);
	return mp;
    }

    @Override
    public void save() throws Exception {
	try {
	    IDAO categoryId = sql(IDAO.class, "select max(categoryId) 'categoryId' from category");
	    categoryId.select();
	    if (categoryId.next()) {
		setCategoryId(categoryId.getInt("categoryId") + 1);
	    } else {
		setCategoryId(0);
	    }
	} catch (Exception e) {

	}

	createDatabaseMe();

    }

    @Override
    public void checkDeleteFlag() throws Exception {
	databaseMe().setDeleted(true);
	getMetaworksContext().setWhen(WHEN_EDIT); // leave edit mode
    }

    @Override
    public void uncheckDeleteFlag() throws Exception {
	databaseMe().setDeleted(false);
	getMetaworksContext().setWhen(WHEN_EDIT); // leave edit mode
    }

    @Override
    public void delete() throws Exception {
	deleteDatabaseMe();
    }

    @Override
    public void addChild() throws Exception {
	ICategory child = new Category();
	child.setParentCategoryId(this.categoryId);
	child.getMetaworksContext().setWhen(WHEN_NEW);
	getMetaworksContext().setWhen(WHEN_EDIT);

	setChildrenCategories(child);
	setSelected(true);
    }

    @Override
    public ICategory loadChildrenCategories() throws Exception {
	String sql = "select * from category where parentCategoryId = ?parentCategoryId";
	childrenCategories = (ICategory) sql(ICategory.class, sql);
	childrenCategories.setParentCategoryId(this.parentCategoryId);
	childrenCategories.select();

	return childrenCategories;
    }

    static public ICategory loadRootCategory() throws Exception {
	ICategory mainCategory = (ICategory) sql(ICategory.class, "select * from category where parentCategoryId = -1");
	mainCategory.select();
	return mainCategory;
    }

}
