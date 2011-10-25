package org.metaworks.website;

import org.metaworks.MetaworksObject;
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
	

	
	public Object[] selectMenu() throws Exception{
		subMenu = (IMenu) Database.sql(IMenu.class, "select * from menu where parentMenuId = ?parentMenuId");;
		subMenu.setParentMenuId(this.getMenuId());
		subMenu.select();
		this.setSelected(true);
		
//		Navigation navigation = new Navigation();
//		navigation.setSelectedMenuId(getMenuId());
		
		ContentPanel contentPanel = new ContentPanel();
		contentPanel.setMenu(this);
		contentPanel.load();
		
		FeedbackPanel feedbackPanel = new FeedbackPanel();
		feedbackPanel.load(this);
		
		return new Object[]{this, contentPanel, feedbackPanel};
	}

	static public IMenu loadMainMenu() throws Exception{
		IMenu mainMenu = (IMenu) Database.sql(IMenu.class, "select * from menu where parentMenuId = -1");;
		mainMenu.select();
		
		return mainMenu;
	}
	
	public Navigation save() throws Exception {
		
		try{
			IDAO menuId = Database.sql(IDAO.class, "select max(menuId) 'menuId' from menu");
			menuId.select();
			menuId.next();

			setMenuId(menuId.getInt("menuId") + 1);
		}catch(Exception e){
			
		}
		
		createDatabaseMe();
		syncToDatabaseMe();
		flushDatabaseMe(); //required in this case due to the following command will loads the data immediately.
		
		return new Navigation(); //let refreshes the whole navigation part.
	}

}
