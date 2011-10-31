package org.metaworks.website;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Name;

@Face(
	ejsPath="genericfaces/Window.ejs",
	options={"hideAddBtn", "hideLabels"},
	values={"true", "true"}
)
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
		@Name
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
		
		if(session.loginUser!=null && session.loginUser.isAdmin()){
			newContent = new ParagraphContents();
			{
				newContent.setMenuId(menu.getMenuId());
				newContent.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);		
			}
		}
	}
	
	@AutowiredFromClient
	public Session session;
}
