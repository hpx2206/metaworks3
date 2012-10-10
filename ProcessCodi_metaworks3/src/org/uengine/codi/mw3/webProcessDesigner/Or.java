package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Session;

public class Or extends And{
	public Or(){
		this(null);
	}

	public Or( Condition first, Condition second, String description){
		this( new Condition[] { first, second});
		this.setName("Or");
	}
	
	public Or( Condition[] conditions){
		super( conditions);
		this.setName("Or");
	}

	public boolean isMet(String scope) throws Exception{
		
		Condition[] condis = getConditions();
//		boolean matched = false;
		
		for( int i=0; i< condis.length; i++){
		
			if( condis[i].isMet( scope))
				return true;
		}
			
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
