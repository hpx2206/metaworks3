package org.uengine.kernel.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.uengine.kernel.Activity;
import org.uengine.kernel.DefaultActivity;
import org.uengine.kernel.ProcessInstance;

public abstract class GatewayActivity extends DefaultActivity {

	public GatewayActivity(String name) {
		super(name);
	}
	
	//split logic
	@Override
	public List<Activity> getPossibleNextActivities(ProcessInstance instance, String scope) throws Exception {
		List<Activity> activities = new ArrayList<Activity>();

		Transition transition = null;
		for (Iterator<Transition> it = getOutgoingTransitions().iterator(); it.hasNext();) {
			transition = it.next();
			if (transition.isMet(instance, scope)) {
				activities.add(transition.getTargetActivity());
			}
		}
		return activities;
	}

	//join logic
	protected abstract boolean isCompletedAllOfPreviousActivities(ProcessInstance instance) throws Exception;

	@Override
	protected void executeActivity(ProcessInstance instance) throws Exception {
		//if all of previous activities completed
		if (isCompletedAllOfPreviousActivities(instance)) {
			fireComplete(instance);
		}
//		else {
//			//ignore token
//			int tokenCount = getTokenCount(instance);
//			setTokenCount(instance, --tokenCount);
//		}
	}
}
