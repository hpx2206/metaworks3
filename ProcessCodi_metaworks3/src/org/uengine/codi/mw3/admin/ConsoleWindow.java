package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.Face;
import org.metaworks.widget.Window;
import org.uengine.codi.platform.Console;

@Face(ejsPath="genericfaces/Window.ejs",
	  displayName="Console",
	  options={"hideLabels", "minimize"},
	  values={"true", "true"})
public class ConsoleWindow extends Window {

	public ConsoleWindow() {
		super();
		
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
