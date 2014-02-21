package org.uengine.kernel.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.uengine.kernel.Activity;
import org.uengine.kernel.Condition;
import org.uengine.kernel.DefaultActivity;
import org.uengine.kernel.Or;
import org.uengine.kernel.Otherwise;
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
		/* TODO gate 에서 무조건 추가하는 건가요?? otherwise 도??
		Transition transition = null;
		for (Iterator<Transition> it = getOutgoingTransitions().iterator(); it.hasNext();) {
			transition = it.next();
			if (transition.isMet(instance, scope)) {
				activities.add(transition.getTargetActivity());
			}
		}
		return activities;
		*/

//		System.out.println("outgoingTransitions: " + getOutgoingTransitions().size());
		boolean otherwiseFlag = false;
		Activity otherwiseActivity = null;
		for (Iterator<Transition> it = getOutgoingTransitions().iterator(); it.hasNext(); ) {
			Transition ts = (Transition)it.next();
			if( ts.getCondition() != null){
				Condition condition = ts.getCondition();
				if( condition.isMet(instance, scope) ){
					if( condition instanceof Or){
						Condition[] condis =  ((Or) condition).getConditions();
						if( condis[0] instanceof Otherwise){
							// 순서가 없다보니 Otherwise가 먼저와서 무조건 true 가 발생하는 경우가 생김
							// Otherwise가 먼저 올 경우는 일단 스킵했다가 다시 해준다.
							otherwiseFlag = true;
							otherwiseActivity = ts.getTargetActivity();
							continue;
						}
					}
					activities.add(ts.getTargetActivity());
				}
			}else{
				activities.add(ts.getTargetActivity());
			}
		}
		if( otherwiseFlag && activities.isEmpty()){
			activities.add(otherwiseActivity);
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
		}else{
//			fireComplete(instance);
		}
//		else {
//			//ignore token
//			int tokenCount = getTokenCount(instance);
//			setTokenCount(instance, --tokenCount);
//		}
	}
}
