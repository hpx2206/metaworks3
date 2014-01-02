package org.uengine.kernel.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.uengine.kernel.Activity;
import org.uengine.kernel.DefaultActivity;
import org.uengine.kernel.ProcessInstance;

public class GatewayActivity extends DefaultActivity {

	public GatewayActivity() {
	}
	
	
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
	protected boolean isCompletedAllOfPreviousActivities(ProcessInstance instance) throws Exception{
		return !hasTokenInPreviousActivities(instance, this);	
	}
	
	protected boolean hasTokenInPreviousActivities(ProcessInstance instance, Activity activity) throws Exception {
		Activity child = null;
		for (Iterator<Transition> it = activity.getIncomingTransitions().iterator(); it.hasNext();) {
			child = it.next().getSourceActivity();
			int tokenCount = child.getTokenCount(instance);
			if (tokenCount > 0) {
				return true;
			}
//			else {
//				return hasTokenInPreviousActivities(instance, child);
//			}
			
			if (hasTokenInPreviousActivities(instance, child)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void executeActivity(ProcessInstance instance) throws Exception {
		//if all of previous activities completed
		if (isCompletedAllOfPreviousActivities(instance)) { 
			//since executeActivity in graph model would be called every time the incoming activities are done.
			fireComplete(instance);
		}
//		else {
//			//ignore token
//			int tokenCount = getTokenCount(instance);
//			setTokenCount(instance, --tokenCount);
//		}
	}
}
