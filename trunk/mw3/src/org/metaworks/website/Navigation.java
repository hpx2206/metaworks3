package org.metaworks.website;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

@Face(
		ejsPath="genericfaces/Window.ejs",
		options={"hideAddBtn", "hideLabels"},
		values={"true", "true"}
)
public class Navigation implements ContextAware{

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
		getMenu().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	
	@ServiceMethod(when=MetaworksContext.WHEN_EDIT)
	public void addNewMenu() throws Exception{
		newMenu = new Menu();
		newMenu.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
	@ServiceMethod(callByContent = true, when=MetaworksContext.WHEN_EDIT)
	public void addSubMenu() throws Exception{
		newMenu = new Menu();
		newMenu.setParentMenuId(contentPanel.getMenu().getMenuId());
		newMenu.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
	@AutowiredFromClient
	public ContentPanel contentPanel;

	
	MetaworksContext metaworksContext;
	
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

		
}
