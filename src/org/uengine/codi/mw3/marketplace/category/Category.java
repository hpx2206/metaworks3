package org.uengine.codi.mw3.marketplace.category;

import java.sql.Timestamp;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.dao.IDAO;
import org.uengine.codi.mw3.marketplace.AppDetail;
import org.uengine.codi.mw3.marketplace.AppList;
import org.uengine.codi.mw3.marketplace.MarketplaceCenterPanel;
import org.uengine.codi.mw3.marketplace.searchbox.MarketplaceSearchBox;
import org.uengine.codi.mw3.model.Session;


public class Category extends Database<ICategory> implements ICategory {

	@AutowiredFromClient
	public Session session;

	@AutowiredFromClient
	public AppDetail appDetail;
	
	int categoryId;
		@Override
		public int getCategoryId() {
			return categoryId;
		}
		@Override
		public void setCategoryId(int categoryId) {
			this.categoryId = categoryId;
		}

	String categoryName;
		@Override
		public String getCategoryName() {
			return categoryName;
		}
		@Override
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}


	int parentCategoryId;
		@Override
		public int getParentCategoryId() {
			return parentCategoryId;
		}
		@Override
		public void setParentCategoryId(int parentCategoryId) {
			this.parentCategoryId = parentCategoryId;
		}

	ICategory childrenCategories;
		@Override
		public ICategory getChildrenCategories() {
			return childrenCategories;
		}
		@Override
		public void setChildrenCategories(ICategory childrenCategories) {
			this.childrenCategories = childrenCategories;
		}

	Timestamp modDate;
		@Override
		public Timestamp getModDate() {
			return modDate;
		}
		@Override
		public void setModdDate(Timestamp currentTimeStamp) {
			this.modDate = currentTimeStamp;
		}

	boolean selected; 
		@Override
		public boolean isSelected() {
			return selected;
		}
		@Override
		public void setSelected(boolean selected) {
			this.selected = selected;
		}

	boolean deleted;
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
		
/*		MarketplaceCenterPanel centerPanel = new MarketplaceCenterPanel();
		
		try {
			centerPanel.setAppList(App.findForCategory(String.valueOf(this.getCategoryId())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		session.setLastSelectedItem(String.valueOf(this.getCategoryId()));
		session.setSearchKeyword(null);
		
		
/*		
		App findApps = new App();
		findApps.session = session;
		findApps.setCategory(this.databaseMe());
		findApps.setComcode(session.getCompany().getComCode());
		
		IApp getApp = findApps.findForCategory();
		
		
		MarketplaceSearchBox searchBox = new MarketplaceSearchBox();
		searchBox.setKeyEntetSearch(true);
		searchBox.setKeyUpSearch(true);
		
		
		//center
		MarketplaceCenterPanel centerPanel = new MarketplaceCenterPanel();
		centerPanel.setSearchBox(searchBox);
		centerPanel.setListing(getApp);
		centerPanel.setCategory(this);
		centerPanel.getMetaworksContext().setWhen("searchForCategory");
		
		MarketplaceCenterWindow centerWin = new MarketplaceCenterWindow(session);
		centerWin.setCenterPanel(centerPanel);
		//centerWin.getCenterPanel().getListing().getMetaworksContext().setWhen((getApp.size() > 0) ? "searchForCategory" : "HavntResult");
		
		//east
		MarketplaceEastPanel east = new MarketplaceEastPanel();
		east.session = session;
		east.load();
		
		//west
		
		MarketCategoryPanel marketCategory = new MarketCategoryPanel(session);
		marketCategory.setCategory(this.loadRootCategory());
		
		
		Layout layout = new Layout();
		layout.setName("center");
		//layout.setWest(marketCategory);
		layout.setCenter(centerWin);
		layout.setEast(east);*/
		
		if(appDetail == null)
			return new Object[]{session, new MarketplaceSearchBox()};
		else{
			MarketplaceCenterPanel centerPanel = new MarketplaceCenterPanel();
			
			try {
				AppList appList = new AppList();
				appList.load(String.valueOf(this.getCategoryId()), null);
				
				centerPanel.setAppList(appList);
				centerPanel.getMetaworksContext().setHow(MetaworksContext.HOW_IN_LIST);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return new Object[]{session, centerPanel, new MarketplaceSearchBox()};
		}
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
		ICategory mainCategory = (ICategory) sql(ICategory.class,
				"select * from category where parentCategoryId = -1 order by categoryId");

		mainCategory.select();

		mainCategory.getMetaworksContext().setWhen("view");
		return mainCategory;
	}
	
}