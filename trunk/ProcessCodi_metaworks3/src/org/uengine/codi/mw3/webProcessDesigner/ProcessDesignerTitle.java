package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.processmanager.ProcessManagerRemote;

public class ProcessDesignerTitle implements ContextAware{
	
	public ProcessDesignerTitle(){
		
	}
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
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
		processDesignerWebContentPanel.processManager = processManager;
		return new Object[]{ processDesignerWebContentPanel.save( title ) , new Remover(new Popup())};
	}
	
	@AutowiredFromClient
	public ProcessDesignerWebContentPanel processDesignerWebContentPanel;
	
	@Autowired
	public ProcessManagerRemote processManager;
}
