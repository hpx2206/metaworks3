package org.uengine.kernel;

import java.util.ArrayList;

import org.uengine.webservice.MethodProperty;

public class RestWebServiceActivity extends DefaultActivity{
	
	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;
	
	public RestWebServiceActivity(){
		super("restWebService");
	}
	
	ArrayList<MethodProperty> methods;
		public ArrayList<MethodProperty> getMethods() {
			return methods;
		}
		public void setMethods(ArrayList<MethodProperty> methods) {
			this.methods = methods;
		}
	
	public void executeActivity(ProcessInstance instance) throws Exception{
		
	}

}
