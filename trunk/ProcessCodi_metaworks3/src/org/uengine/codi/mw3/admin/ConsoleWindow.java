package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.Face;
import org.uengine.codi.platform.Console;

@Face(ejsPath="genericfaces/WindowTab.ejs", 
	  options={"hideLabels", "hideCloseBtn", "layout"}, 
	  values={"true", "true", "south"})

public class ConsoleWindow {

	public ConsoleWindow() {
		setConsole(new Console());
	}
	
	Console console;
		public Console getConsole() {
			return console;
		}
		public void setConsole(Console console) {
			this.console = console;
		}
	
}
