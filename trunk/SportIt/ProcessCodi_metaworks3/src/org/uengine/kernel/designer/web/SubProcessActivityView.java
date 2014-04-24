package org.uengine.kernel.designer.web;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.ActivityWindow;
import org.uengine.codi.mw3.webProcessDesigner.ProcessSelectPanel;
import org.uengine.kernel.SubProcessActivity;

public class SubProcessActivityView extends ActivityView{
	
	@AutowiredFromClient
	public Session session;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object showProperties() throws Exception{
		ModalWindow popup = (ModalWindow)super.showProperties();
		
		ActivityWindow activityWindow = (ActivityWindow)popup.getPanel();
		SubProcessActivity activity = (SubProcessActivity)activityWindow.getActivityPanel().getActivity();
		activity.setProcessSelectPanel(new ProcessSelectPanel());
		activity.getProcessSelectPanel().setDefinitionId(activity.getDefinitionId());
		activity.getProcessSelectPanel().setOpenerActivity(activity);
		
		return popup;
	}
}
