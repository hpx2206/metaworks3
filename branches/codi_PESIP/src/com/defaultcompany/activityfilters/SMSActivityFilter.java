package com.defaultcompany.activityfilters;

import org.uengine.kernel.Activity;
import org.uengine.kernel.ActivityFilter;
import org.uengine.kernel.ActivityInstanceContext;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.LocalSMSActivity;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ProcessInstance;

public class SMSActivityFilter implements ActivityFilter {

	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;
	
	@Override
	public void beforeExecute(Activity activity, ProcessInstance instance)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecute(Activity activity, ProcessInstance instance)
			throws Exception {
		// TODO Auto-generated method stub
		if(activity instanceof HumanActivity){
			Activity firstExecuted = null;
			try {
				firstExecuted = ((ActivityInstanceContext) instance.getProcessTransactionContext().getExecutedActivityInstanceContextsInTransaction().get(0)).getActivity();
			} catch (Exception e) { }
			
			if(firstExecuted == null || firstExecuted == activity) {
				return;
			}
			
			LocalSMSActivity smsAct = new LocalSMSActivity() {
				@Override
				public void fireComplete(ProcessInstance instance) throws Exception {
					//disabled
				}
				
			};
			smsAct.setContents("\"" + instance.getName() + "\"건의 \"" + activity.getName() + "\" 업무가 전달되었습니다.");
			smsAct.setToRole(((HumanActivity) activity).getRole());
			smsAct.executeActivity(instance);
		}
	}

	@Override
	public void afterComplete(Activity activity, ProcessInstance instance)
			throws Exception {
		

	}

	@Override
	public void onPropertyChange(Activity activity, ProcessInstance instance,
			String propertyName, Object changedValue) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDeploy(ProcessDefinition definition) throws Exception {
		// TODO Auto-generated method stub

	}

}
