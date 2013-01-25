package org.metaworks.example.navigation;

public class Main {

	Menu menu;
		public Menu getMenu() {
			return menu;
		}
		public void setMenu(Menu menu) {
			this.menu = menu;
		}

	Content content;
		public Content getContent() {
			return content;
		}
		public void setContent(Content content) {
			this.content = content;
		}
	
	protected Main(){
		setMenu(new Menu());
		setContent(new Content());
	}

}
