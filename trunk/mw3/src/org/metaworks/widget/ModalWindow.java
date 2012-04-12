package org.metaworks.widget;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;

@Face(displayName="ModalWindow")
public class ModalWindow {
	
	public ModalWindow() {
		
	}
	
	public ModalWindow(Object panel) {
		setPanel(panel);
	}
	
	public ModalWindow(Object panel, int width, int height) {
		setPanel(panel);
		setWidth(width);
		setHeight(height);
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
