package org.uengine.codi.mw3.ide.libraries;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.ActivityWindow;
import org.uengine.kernel.Activity;

public class ActivityNode extends TreeNode{

	Activity activity;
		public Activity getActivity() {
			return activity;
		}
		public void setActivity(Activity activity) {
			this.activity = activity;
		}
	String path;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	@AutowiredFromClient
	public Session session;

	@ServiceMethod(callByContent=true, mouseBinding="drag")
	public Session drag() {
		session.setClipboard(this);
		return session;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object showProperties() throws Exception{
		ModalWindow modalWindow = new ModalWindow();
		
		ActivityWindow activityWindow = new ActivityWindow();
		
		activityWindow.getActivityPanel().setActivity(activity);
		activityWindow.getActivityPanel().setDocument(activity.getDocumentation());
		modalWindow.setPanel(activityWindow);
		modalWindow.setWidth(700);
		modalWindow.setHeight(500);
		
		
		return modalWindow;
	}	
}