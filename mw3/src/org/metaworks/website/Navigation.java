package org.metaworks.website;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

@Face(ejsPath="genericfaces/Window.ejs")
public class Navigation {

	IMenu menu;

		public IMenu getMenu() {
			return menu;
		}
	
		public void setMenu(IMenu menu) {
			this.menu = menu;
		}
	
	IMenu newMenu;
			
		public IMenu getNewMenu() {
			return newMenu;
		}
	
		public void setNewMenu(IMenu newMenu) {
			this.newMenu = newMenu;
		}

	public Navigation() throws Exception{
		setMenu(Menu.loadMainMenu());
	}
	
	@ServiceMethod
	public void addNewMenu() throws Exception{
		newMenu = new Menu();
		newMenu.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
	@ServiceMethod(callByContent = true)
	public void addSubMenu() throws Exception{
		newMenu = new Menu();
		newMenu.setParentMenuId(contentPanel.getMenu().getMenuId());
		newMenu.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
	@AutowiredFromClient
	public ContentPanel contentPanel;
	
}
