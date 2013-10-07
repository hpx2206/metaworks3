package org.uengine.codi.mw3.model;


public class BrowserWindow {
	
	public BrowserWindow(){}
	
	public BrowserWindow(String className){
		setClassName(className);
	}
	
	String className;
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
}
