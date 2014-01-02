package org.uengine.kernel.graph;

import java.util.Iterator;

import org.uengine.kernel.Activity;
import org.uengine.kernel.ProcessInstance;

public class OrGatewayActivity extends GatewayActivity {

//	@Override
//	protected boolean isCompletedAllOfPreviousActivities(ProcessInstance instance) throws Exception {
//		return !hasTokenInPreviousActivities(instance, this);
//	}
//	


	public OrGatewayActivity() {
		super("Or");
	}

}
