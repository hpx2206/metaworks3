package org.uengine.kernel.graph;

import org.uengine.kernel.Activity;
import org.uengine.kernel.Condition;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.designer.web.TransitionView;

public class Transition implements java.io.Serializable {
	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;
	
	private String source;
	private String target;

	private Condition condition;
	
//	private transient Activity sourceActivity;
//	private transient Activity targetActivity;
	private transient Activity sourceActivity;
	private transient Activity targetActivity;
	
	public Transition() {
	}

	public Transition(String source, String target) {
		setSource(source);
		setTarget(target);
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Activity getSourceActivity() {
		return sourceActivity;
	}

	public void setSourceActivity(Activity sourceActivity) {
		this.sourceActivity = sourceActivity;
	}

	public Activity getTargetActivity() {
		return targetActivity;
	}

	public void setTargetActivity(Activity targetActivity) {
		this.targetActivity = targetActivity;
	}
	
	TransitionView transitionView;
		public TransitionView getTransitionView() {
			return transitionView;
		}
		public void setTransitionView(TransitionView transitionView) {
			this.transitionView = transitionView;
		}
	String transitionName;
		public String getTransitionName() {
			return transitionName;
		}
		public void setTransitionName(String transitionName) {
			this.transitionName = transitionName;
		}

	public boolean isMet(ProcessInstance instance, String scope) throws Exception {
		if (condition == null) {
			return true;
		}
		return condition.isMet(instance, scope);
	}
	
	// temporarily added for LoopGatewayActivity
	public boolean isMatch() throws Exception {
		
		if (sourceActivity instanceof XorGatewayActivity) {
			// condition needed
		} else if (sourceActivity instanceof OrGatewayActivity) {
			// condition needed
		} else if (sourceActivity instanceof LoopGatewayActivity) {
			// assume to get the outgoing transition from sourceActivity
			if (((LoopGatewayActivity) sourceActivity).getMap().get("outgoing").equals(this)) {
				return true;
			}
		} else {
			return true;
		}
		
		return false;
	}
	
}
