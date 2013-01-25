package org.metaworks.website;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

@Face(
		ejsPath="genericfaces/Window.ejs",
		options={"hideAddBtn", "hideLabels", "height"},
		values={"true", "true", "400"},
		displayName="Resources"
)
public class Navigation implements ContextAware{

	IMenu menu;
		public IMenu getMenu() {
			return menu;
		}
	
		public void setMenu(IMenu menu) {
			this.menu = menu;
		}
	

	public Navigation() throws Exception{
		
		
		setMenu(Menu.loadMainMenu());
	}
	
	

	MetaworksContext metaworksContext;
	
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

		
}
