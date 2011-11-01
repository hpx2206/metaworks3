package org.metaworks.website;

import org.metaworks.MetaworksObject;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.dao.IDAO;

public class Menu  extends MetaworksObject<IMenu> implements IMenu{
	
	int menuId;
	int parentMenuId;

	String name;
	IMenu subMenu;
	boolean selected;

	
	public int getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(int parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public IMenu getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(IMenu subMenu) {
		this.subMenu = subMenu;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	boolean enabled;
		public boolean isEnabled() {
			return enabled;
		}
		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
	
	public Object[] selectMenu() throws Exception{
		subMenu = (IMenu) Database.sql(IMenu.class, "select * from menu where parentMenuId = ?parentMenuId");;
		subMenu.setParentMenuId(this.getMenuId());
		subMenu.select();
		this.setSelected(true);
		
//		Navigation navigation = new Navigation();
//		navigation.setSelectedMenuId(getMenuId());
		
		ContentPanel contentPanel = new ContentPanel();
		contentPanel.session = session; //TODO: removed later by propagating autowiring
		contentPanel.setMenu(this);
		contentPanel.load();
		
		FeedbackPanel feedbackPanel = new FeedbackPanel();
		feedbackPanel.session = session; //TODO: removed later by propagating autowiring
		feedbackPanel.load(this);
		
		if(session.loginUser!=null && session.loginUser.isAdmin())
			getMetaworksContext().setWhen(WHEN_EDIT);
		
		return new Object[]{this, contentPanel, feedbackPanel};
	}
	
	public IMenu loadSubMenu() throws Exception{
		subMenu = (IMenu) Database.sql(IMenu.class, "select * from menu where parentMenuId = ?parentMenuId");;
		subMenu.setParentMenuId(this.getMenuId());
		subMenu.select();

		return subMenu;
	}

	static public IMenu loadMainMenu() throws Exception{
		IMenu mainMenu = (IMenu) Database.sql(IMenu.class, "select * from menu where parentMenuId = -1");;
		mainMenu.select();
		
		return mainMenu;
	}
	
	public void save() throws Exception {
		
		try{
			IDAO menuId = Database.sql(IDAO.class, "select max(menuId) 'menuId' from menu");
			menuId.select();
			menuId.next();

			setMenuId(menuId.getInt("menuId") + 1);
		}catch(Exception e){
			
		}
		
		createDatabaseMe();
		//syncToDatabaseMe();
		//flushDatabaseMe(); //required in this case due to the following command will loads the data immediately.
		
		//return new Navigation(); //let refreshes the whole navigation part.
	}
	
	@AutowiredFromClient
	public Session session;


	@Override
	public void disable() throws Exception {
		databaseMe().setEnabled(false);
		getMetaworksContext().setWhen(WHEN_EDIT); //leave edit mode
	}
	
	@Override
	public void enable() throws Exception {
		databaseMe().setEnabled(true);
		getMetaworksContext().setWhen(WHEN_EDIT); //leave edit mode
	}
	
	@Override
	public void addChild() throws Exception {
		IMenu child = new Menu();
		child.setParentMenuId(this.getMenuId());
		child.getMetaworksContext().setWhen(WHEN_NEW);
		
		getMetaworksContext().setWhen(WHEN_EDIT);

		setSubMenu(child);
		setSelected(true);
	}
	@Override
	public void delete() throws Exception {
		deleteDatabaseMe();
	}


}
