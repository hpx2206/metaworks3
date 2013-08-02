package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class ActivityWindow  {

	ActivityPanel activityPanel;
		public ActivityPanel getActivityPanel() {
			return activityPanel;
		}
		public void setActivityPanel(ActivityPanel activityPanel) {
			this.activityPanel = activityPanel;
		}

	public ActivityWindow(){
		activityPanel = new ActivityPanel();
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] apply(){
		activityPanel.getActivity().setDocumentation(activityPanel.getDocument());
		return new Object[]{new ApplyProperties(activityPanel.getActivity().getTracingTag() , activityPanel.getActivity()), new Remover(new ModalWindow())};
	}

	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] cancel(){
		return new Object[]{new Remover(new ModalWindow())};
		
	}
}
