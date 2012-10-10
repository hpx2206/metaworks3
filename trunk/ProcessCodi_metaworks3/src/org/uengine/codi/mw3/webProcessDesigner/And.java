package org.uengine.codi.mw3.webProcessDesigner;

import java.util.Vector;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Session;

public class And extends Condition{
	
	MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}
	
	Vector conditionsVt;
		public Vector getConditionsVt() {
			return conditionsVt;
		}
		public void setConditionsVt(Vector vt) {
			conditionsVt = vt;
		}
		
	public And(){
		this( null);
	}

	public And( Condition first, Condition second){
		this( new Condition[] { first, second});
	}
	public And( Condition cond1, Condition cond2, Condition cond3){
		this( new Condition[] { cond1, cond2, cond3});
	}
	public And( Condition cond1, Condition cond2, Condition cond3, Condition cond4){
		this( new Condition[] { cond1, cond2, cond3, cond4});
	}
	
	public And( Condition[] conditions){
		conditionsVt = new Vector();
		
		if( conditions != null){
			for( int i=0; i< conditions.length; i++)
				conditionsVt.add( conditions[i]);
		}
		this.setName("And");
	}
	
	public boolean isMet(String scope) throws Exception{
		for( int i=0; i< conditionsVt.size(); i++){
			Condition con = (Condition)conditionsVt.get(i);
			if( !con.isMet(scope))
				return false;
		}
		return true;
	}
	public void addCondition( Condition con){
		conditionsVt.add( con);
	}
	public Condition[] getConditions(){
		return (Condition[])conditionsVt.toArray( new Condition[ conditionsVt.size()]);
	}
	
	@AutowiredFromClient
	public Session session;
	
	@ServiceMethod(callByContent=true, mouseBinding="drag")
	public Session drag() throws Exception {
		session.setClipboard( this );
		return session;
	}
	
}
