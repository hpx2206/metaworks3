package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.Remover;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Popup;

public class ProcessDesignerTitle extends ProcessDesignerWebContentPanel{
	
	public ProcessDesignerTitle(){
		
	}
	public ProcessDesignerTitle(String title){
		setTitle(title);
	}
	
	String title;
		public String getTitle() {
			return title;
		}
	
		public void setTitle(String title) {
			this.title = title;
		}
		
	@ServiceMethod(callByContent=true)
	public Object[] save() throws Exception{
		return null;
//		return new Object[]{ super.save( title ) , new Remover(new Popup())};
	}
}
