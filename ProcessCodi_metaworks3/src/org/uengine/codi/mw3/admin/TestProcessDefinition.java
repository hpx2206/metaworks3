package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.ServiceMethod;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.ProcessDefinition;

public class TestProcessDefinition extends ProcessDefinition{

	@ServiceMethod
	public void load(){
		
		HumanActivity ha = new HumanActivity();
		ha.setName("step1");
		addChildActivity(ha);

		HumanActivity ha2 = new HumanActivity();
		ha2.setName("step2");
		addChildActivity(ha2);

	
	}
	
	
}
