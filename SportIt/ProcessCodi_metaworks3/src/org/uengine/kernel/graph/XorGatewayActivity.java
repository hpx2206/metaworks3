package org.uengine.kernel.graph;

import org.uengine.kernel.ProcessInstance;

public class XorGatewayActivity extends GatewayActivity {
	
	public XorGatewayActivity() {
		super("Xor");
	}

	@Override
	protected boolean isCompletedAllOfPreviousActivities(ProcessInstance instance) throws Exception {
		//XOR Gateway case,
		//complete this activity, when one previous activity is completed
		return true;
	}

}
