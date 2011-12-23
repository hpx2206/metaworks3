package org.metaworks.website;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.dao.IDAO;

public class Menu  extends Database<IMenu> implements IMenu{
	
	int menuId;
	int parentMenuId;

	String name;
	IMenu subMenu;
	boolean selected;

	int orderId;
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
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
		subMenu = (IMenu) sql(IMenu.class, "select * from menu where parentMenuId = ?parentMenuId order by orderId");;
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
		
		session.setMenu(this);
		
		return new Object[]{this, contentPanel, feedbackPanel, session};
	}
	
	public IMenu loadSubMenu() throws Exception{
		subMenu = (IMenu) sql(IMenu.class, "select * from menu where parentMenuId = ?parentMenuId order by orderId");;
		subMenu.setParentMenuId(this.getMenuId());
		subMenu.select();

		return subMenu;
	}

	static public IMenu loadMainMenu() throws Exception{
		Menu homeMenu = new Menu();
		homeMenu.setName("src/");
		homeMenu.setMenuId(-1);
		homeMenu.setSubMenu(homeMenu.loadSubMenu());
		homeMenu.setEnabled(true);
		homeMenu.setSelected(true);

//		IMenu mainMenu = (IMenu) sql(IMenu.class, "select * from menu where parentMenuId = -1");;
//		mainMenu.select();
		
		return homeMenu;
	}
	
	public void save() throws Exception {
		
		try{
			IMenu menuId = sql("select max(menuId) 'menuId' from menu");
			menuId.select();
			menuId.next();

			setMenuId(menuId.getInt("menuId") + 1);
		}catch(Exception e){
			
		}
		
		int orderId = 0;
		IMenu orderIdDAO = sql("select max(orderId) 'orderId' from menu where parentMenuId=?parentMenuId");
		orderIdDAO.setParentMenuId(getParentMenuId());
		orderIdDAO.select();
		
		if(orderIdDAO.next())
			orderId = orderIdDAO.getInt("orderId") + 1;
		
		setOrderId(orderId);

		
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
		IMenu followingMenus = sql("update menu set orderId=orderId-1 where orderId > ?orderId and parentMenuId = ?parentMenuId");
		followingMenus.setParentMenuId(getParentMenuId());
		followingMenus.setOrderId(getOrderId());
		followingMenus.update();

		deleteDatabaseMe();
	}

	@Override
	public Navigation moveUp() throws Exception {
		if(databaseMe().getOrderId() == 0)
			throw new Exception("It's top");
		
		IMenu prev = sql("update menu set orderId=orderId+1 where orderId=?orderId and parentMenuId=?parentMenuId");
		prev.setOrderId(databaseMe().getOrderId() - 1);
		prev.setParentMenuId(databaseMe().getParentMenuId());
		prev.update();
		
		databaseMe().setOrderId(databaseMe().getOrderId() - 1);
		flushDatabaseMe();
		
		return new Navigation();
	}
	
	@Override
	public Navigation moveDown() throws Exception {

		IMenu nextContents = sql("update menu set orderId=orderId-1 where orderId=?orderId and parentMenuId=?parentMenuId");
		nextContents.setOrderId(databaseMe().getOrderId() + 1); //points next one
		nextContents.setParentMenuId(databaseMe().getParentMenuId());
		int nextWasExisted = nextContents.update();
		
		if(nextWasExisted == 0)
			throw new Exception("It's bottom");
		
		databaseMe().setOrderId(databaseMe().getOrderId() + 1);
		flushDatabaseMe();
		
		return new Navigation();
	}


}
