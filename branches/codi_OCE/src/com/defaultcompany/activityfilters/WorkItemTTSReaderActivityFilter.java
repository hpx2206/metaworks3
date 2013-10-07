package com.defaultcompany.activityfilters;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;

import org.uengine.kernel.Activity;
import org.uengine.kernel.ActivityFilter;
import org.uengine.kernel.ActivityInstanceContext;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.RoleMapping;

import com.defaultcompany.activity.TTSActivity;


public class WorkItemTTSReaderActivityFilter implements ActivityFilter {

	@Override
	public void beforeExecute(Activity activity, ProcessInstance instance)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterExecute(Activity activity, ProcessInstance instance)
			throws Exception {
//		if(!(activity instanceof HumanActivity) || instance.isNew()) return;
		 
		if(activity instanceof HumanActivity) {
			
			Activity firstExecuted = null;
			try {
				firstExecuted = ((ActivityInstanceContext) instance.getProcessTransactionContext().getExecutedActivityInstanceContextsInTransaction().get(0)).getActivity();
			} catch (Exception e) { }
			
			if(firstExecuted == null || firstExecuted == activity) {
				return;
			}
			
			String userName = "";
			String instanceName = "";
			String actName = "";
			
			HumanActivity humanActivity = (HumanActivity)activity;
			RoleMapping roleMapping = humanActivity.getRole().getMapping(instance);
			
			userName = roleMapping.getResourceName();
			instanceName = instance.getName();
			actName = activity.getName().toString();
			
			Formatter formatter = new Formatter();
			formatter.format(
					  GlobalContext.getMessageForWeb("tts_work_grant", GlobalContext.DEFAULT_LOCALE)
					, userName
					, instanceName
					, actName
					, userName
					, instanceName
					, actName
					, userName);
			
			TTSActivity ta = new TTSActivity();
			ta.setContent(formatter.toString());
			ta.executeActivity(null);
		}
		
	}

	@Override
	public void afterComplete(Activity activity, ProcessInstance instance)
			throws Exception {
		if(!(activity instanceof HumanActivity) || instance.isNew()) return;
		
//		final StringBuffer messageBuf = new StringBuffer()
//	 		.append("축하합니다. ")
//	 		.append(roleMapping.getResourceName())
//	 		.append(" 님에게 ")
//	 		.append(instance.getName())
//	 		//.append(" 에 대한 ")
//	 		//.append(activity.getName())
//	 		.append(" 업무가 부여되었습니다.");
		
//		final ProcessInstance pi = instance;
//		
//		Thread thread = new Thread() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				SoundPlayActivity spa = new SoundPlayActivity();
//				
//				try {
//					spa.setSoundUrl("http://uengine.dyndns.org:8080/uengine-tts/ttsServer.jsp?message=" + URLEncoder.encode(messageBuf.toString(), "UTF-8"));
//					spa.playSound(pi);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		};
//		
//		thread.start();

	}
	
	public static void main(String[] args) throws Exception {
        
		Formatter formatter = new Formatter();
	    formatter.format(GlobalContext.getMessageForWeb("tts_work_grant", GlobalContext.DEFAULT_LOCALE), "이동현", "인스턴스 1" , "유저피커");	
		TTSActivity ta = new TTSActivity();
		ta.setContent(formatter.toString());
		ta.executeActivity(null);
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
