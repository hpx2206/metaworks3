package org.metaworks.widget;

public class Window {
	
	public Window(){
	}
	public Window(String title) {
		this(null, title);
	}	
	public Window(Object panel) {
		this(panel, "");
	}
	
	public Window(Object panel, String title) {
		this.panel = panel;
		this.title = title;
	}
	
	Object panel;
		public Object getPanel() {
			return panel;
		}
		public void setPanel(Object panel) {
			this.panel = panel;
		}

	String title;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}			
}
