package org.uengine.codi.mw3.ide.editor.java;

import org.metaworks.MetaworksException;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.MenuItem;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.menu.CloudMenu;

public class JavaSourceMenu extends CloudMenu {
	
	public JavaSourceMenu(){
		this.setId("Source");
		this.setName("Source");
		
		this.add(new MenuItem("genGetAndSet", "Generate Getters and Setters"));
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public Object genGetAndSet() throws Exception {
		Object clipboard = session.getClipboard();
		if(clipboard instanceof JavaCodeEditor){
			JavaCodeEditor editor = (JavaCodeEditor)clipboard;
			
			GenerateGettersAndSetters genGNS = new GenerateGettersAndSetters();
			genGNS.setId(editor.getId());
			genGNS.setContent(editor.getContent());
			genGNS.load();
			
			if(genGNS.getFieldTree().getNode().getChild().size() > 0)
				return new ModalWindow(genGNS, 600, 0, "Generate Getters And Setters");
			else
				throw new MetaworksException("The operation is not applicable to the current selection. Select a field which is not declared as type variable or type the declares such fields."); 
		}else{
			return null;
		}
		
	}
}
