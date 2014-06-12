package org.uengine.kernel.graph;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.uengine.kernel.Activity;
import org.uengine.kernel.ActivityReference;
import org.uengine.kernel.ComplexActivity;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.UEngineException;
import org.uengine.processmanager.ProcessTransactionContext;

public class FlowActivity extends ComplexActivity {
	
	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;

	private ArrayList<Transition> transitions = new ArrayList<Transition>();
		public ArrayList<Transition> getTransitions() {
			if (this.transitions == null) {
				this.setTransitions(new ArrayList<Transition>());
			}
			return transitions;
		}
		private void setTransitions(ArrayList<Transition> transitions) {
			this.transitions = transitions;
		}
		public void addTransition(Transition trasition) {
			this.getTransitions().add(trasition);
		}

	@Override
	public void afterDeserialization() {
		super.afterDeserialization();

		Transition transition = null;
		if(transitions!=null){
			for (Iterator<Transition> it = transitions.iterator(); it.hasNext();) {
				transition = it.next();
	
				// source
				String source = transition.getSource();
				Activity sourceActivity = getProcessDefinition().getActivity(source);
				sourceActivity.addOutgoingTransition(transition);
				transition.setSourceActivity(sourceActivity);
	
				// target
				String target = transition.getTarget();
				Activity targetActivity = getProcessDefinition().getActivity(target);
				targetActivity.addIncomingTransition(transition);
				transition.setTargetActivity(targetActivity);
			}
		}
	}

	private Activity getStartActivity() throws UEngineException {
		Activity child = null;
		for (Iterator it = getChildActivities().iterator(); it.hasNext();) {
			child = (Activity) it.next();
			if( child.getOutgoingTransitions().size() == 0 && child.getIncomingTransitions().size() == 0){
				// null 로 리턴될 경우 super 로직을 태움
				return null;
			}else	if (child.getIncomingTransitions().size() == 0) {
				return child;
			}
		}

		// inconsistent xml or miss starting point
		// it should be never triggered
		UEngineException exception = new UEngineException("inconsistent xml or miss starting point");
		exception.setErrorLevel(UEngineException.FATAL);
		throw exception;
	}

	@Override
	protected void executeActivity(ProcessInstance instance) throws Exception {
		if (getTransitions().size() == 0) {
//			System.out.println("This is conventional uengine process - 2");
			super.executeActivity(instance);
		}else{
			Activity startActivity = getStartActivity();
			if (startActivity != null) {
				queueActivity(startActivity, instance);
			} else {
				throw new UEngineException("Can't find start activity");
			}
		}
		

	}

	@Override
	protected void onEvent(String command, ProcessInstance instance, Object payload) throws Exception {
		
		if (getTransitions().size() == 0) {
//			System.out.println("This is conventional uengine process - 1");
			super.onEvent(command, instance, payload);
		}else{
//			System.out.println("This is graph-based process");
			if (command.equals(CHILD_DONE)) {

				// when we finish??
				// boolean stillRunning = false;
				Activity currentActivity = (Activity) payload;
				List<Activity> possibleNextActivities = currentActivity.getPossibleNextActivities(instance, "");
				
				if (possibleNextActivities.size() == 0) {
					// fireComplete(instance);
					 setStatus(instance, STATUS_COMPLETED);
					 // change the status to be completed 
					 //after the completion of all the activities
					 if (instance != null && instance.isSubProcess()) {
						instance.getProcessDefinition().returnToMainProcess(instance);
					 }
				}

				// register token before queueActivity()
				for (int i = 0; i < possibleNextActivities.size(); i++) {
					Activity child = possibleNextActivities.get(i);
					
					child.reset(instance);
					child.setStartedTime(instance, (Calendar)null);
					
					int tokenCount = child.getTokenCount(instance);
					child.setTokenCount(instance, ++tokenCount);
				}

				for (int i = 0; i < possibleNextActivities.size(); i++) {
					queueActivity(possibleNextActivities.get(i), instance);
				}
			}
		}
	}
	@Override
	public ActivityReference getInitiatorHumanActivityReference(
			ProcessTransactionContext ptc) {
		// TODO 처음이 휴먼 액티비티가 아닐수 있기때문에, 제일 처음 나오는 휴먼엑티비티를 찾아야함
		
		// transition 이 없으면 super 로직을 태움
		ActivityReference startActivityRef = new ActivityReference();
		try {
			Activity act = getStartActivity();
			if( act == null){
				startActivityRef = super.getInitiatorHumanActivityReference(ptc);
			}else{
				startActivityRef.setActivity(act);
				startActivityRef.setAbsoluteTracingTag(act.getTracingTag());
			}
		} catch (UEngineException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		
		
		return startActivityRef;
	}

}
