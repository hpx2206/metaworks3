package org.metaworks.widget;


public class IFrame {
	
	public IFrame(){}
	
	public IFrame(String url){
		setSrc(url);
	}
	
	String src;

		public String getSrc() {
			return src;
		}
	
		public void setSrc(String src) {
			this.src = src;
		}
		
	String width;
		
		public String getWidth() {
			return width;
		}
	
		public void setWidth(String width) {
			this.width = width;
		}

	String height;

		public String getHeight() {
			return height;
		}
	
		public void setHeight(String height) {
			this.height = height;
		}
}
