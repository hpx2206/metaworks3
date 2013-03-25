package org.uengine.codi.mw3.ide.editor.java;

import org.metaworks.component.Menu;
import org.metaworks.component.MenuItem;
import org.uengine.codi.mw3.ide.menu.SubMenuItem;

public class JavaEditorMenu extends Menu {

	public JavaEditorMenu(){
		this.setId("Editor");
		this.setName("Editor");
		
		this.add(new SubMenuItem(new JavaSourceMenu()));
	}
}
