package org.uengine.kernel;

import java.util.Vector;

public class SignalEventActivity  extends EventActivity implements MessageListener{

	@Override
	protected void executeActivity(ProcessInstance instance) throws Exception {
		//start listens...
	}
	@Override
	public boolean onMessage(ProcessInstance instance, Object payload)	throws Exception {
//		Vector activityInstances =  instance.getCurrentRunningActivities();
//		for(int i=0; i<activityInstances.size(); i++){
//			Activity nextAct = (Activity) activityInstances.get(i);
//			nextAct.stop(instance);
//		}
		
		fireComplete(instance);
		
		return true;
	}
	
	public SignalEventActivity(){
		super();
		if( this.getName() == null ){
			setName(this.getClass().getSimpleName());
		}
	}

	@Override
	public String getMessage() {
		return "event";//getClass().getName();  //just simply return the event name as it's classname.
	}

}
