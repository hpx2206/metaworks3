package org.uengine.codi.mw3.ide;

import java.util.ArrayList;

import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.editor.java.JavaCodeEditor;

public class OpenResource {

	String keyword;
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}

	String[] selectedResources;
		public String[] getSelectedResources() {
			return selectedResources;
		}
		public void setSelectedResources(String[] selectedResources) {
			this.selectedResources = selectedResources;
		}
		
	@ServiceMethod(payload="selectedResources", keyBinding="enter", target=ServiceMethodContext.TARGET_APPEND)
	public Object open(){
		ArrayList<Object> result = new ArrayList<Object>();
		result.add(new Remover(new ModalWindow()));
		
		for(String resourceName : this.getSelectedResources()){
			//result.add(new ToAppend(new CloudWindow("editor"), new JavaCodeEditor(resourceName)));
		}
		
		return result;
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_APPEND)
	public Remover cancel(){
		return new Remover(new ModalWindow());		
	}
}
