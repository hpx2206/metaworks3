package org.metaworks.widget;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;

@Face(displayName="ModalWindow")
public class ModalWindow implements ContextAware {
	
	public ModalWindow() {
		this(null, 400, 0, null);
	}
	
	public ModalWindow(Object panel) {
		this(panel, 400, 0, null);
	}
	
	public ModalWindow(Object panel, String title) {
		this(panel, 400, 0, title);
	}
	
	public ModalWindow(Object panel, int width, int height){
		this(panel, width, height, null);
	}
			
	public ModalWindow(Object panel, int width, int height, String title) {
		setMetaworksContext(new MetaworksContext());

		setPanel(panel);
		setWidth(width);
		setHeight(height);		
		setTitle(title);
		setOpen(true);
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	boolean open;
		@Hidden
		public boolean isOpen() {
			return open;
		}
		public void setOpen(boolean open) {
			this.open = open;
		}
		
	String title;
		@Hidden
		public String getTitle() {
			return title;
		}
	
		public void setTitle(String title) {
			this.title = title;
		}

	int width;	
		@Hidden
		public int getWidth() {
			return width;
		}
		public void setWidth(int width) {
			this.width = width;
		}

	int height;
		@Hidden
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
	
	Object panel;
		public Object getPanel() {
			return panel;
		}
		public void setPanel(Object panel) {
			this.panel = panel;
		}
}
