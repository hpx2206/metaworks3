package org.uengine.codi.mw3.uidesigner;

import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Session;

public class Trash {
	
	@AutowiredFromClient
	public Session session;
	

	@ServiceMethod(callByContent=true, mouseBinding="drop", target="popup")
	public Remover onComponentDropped(){
		if(session.getClipboard() instanceof ComponentWrapper){
			ComponentWrapper droppedComponentWrapper = (ComponentWrapper)session.getClipboard();
			
			if(droppedComponentWrapper.getId()!=null){//means from palette
				ComponentWrapper compWrapperToBeRemoved = new ComponentWrapper();
				compWrapperToBeRemoved.setId(droppedComponentWrapper.getId());
				
				return new Remover(compWrapperToBeRemoved); //set the unique id (current time in millis is suffice for each client)
			}
			
		}
		
		return null;
	}
}
