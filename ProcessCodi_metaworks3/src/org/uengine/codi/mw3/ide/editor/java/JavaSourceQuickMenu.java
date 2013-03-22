package org.uengine.codi.mw3.ide.editor.java;

import org.metaworks.component.Menu;
import org.metaworks.component.MenuItem;

public class JavaSourceQuickMenu extends Menu {
	public JavaSourceQuickMenu(){
		this.setId("JavaSourceQuickMenu");
		this.setName("JavaSourceQuickMenu");
		this.setContext(true);
		
		this.add(new MenuItem("genGetAndSet", "Generate Getters and Setters"));
	}
}
