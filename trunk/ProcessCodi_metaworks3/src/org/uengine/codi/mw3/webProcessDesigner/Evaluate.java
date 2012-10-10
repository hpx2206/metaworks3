package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Session;

public class Evaluate extends Condition{
	
	MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}
	
	public Evaluate(){
		
		
		this.setName("Evaluate");
	}
	
	public boolean isMet(String scope) throws Exception{
		
		
		return false;
	}
	
	@AutowiredFromClient
	public Session session;
	
	@ServiceMethod(callByContent=true, mouseBinding="drag")
	public Session drag() throws Exception {
		session.setClipboard( this );
		return session;
	}
}
