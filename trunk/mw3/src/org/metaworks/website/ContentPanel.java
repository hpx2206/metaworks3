package org.metaworks.website;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;

@Face(ejsPath="genericfaces/Window.ejs")
public class ContentPanel {

	IContents contents;
		public IContents getContents() {
			return contents;
		}
		public void setContents(IContents contents) {
			this.contents = contents;
		}
		
	IContents newContent;
		public IContents getNewContent() {
			return newContent;
		}
		public void setNewContent(IContents newContent) {
			this.newContent = newContent;
		}

	IMenu menu;
		@Hidden
		@AutowiredToClient
		public IMenu getMenu() {
			return menu;
		}
		public void setMenu(IMenu menu) {
			this.menu = menu;
		}

	public void load() throws Exception{
		setContents(Contents.loadContents(menu));
		
		newContent = new Contents();{
			newContent.setMenuId(menu.getMenuId());
			newContent.setFile(new MetaworksFile()); //TODO: later we will create button if there's no data
			newContent.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		}
	}
	
}
