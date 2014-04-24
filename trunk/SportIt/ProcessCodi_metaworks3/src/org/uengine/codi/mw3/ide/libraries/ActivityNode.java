package org.uengine.codi.mw3.ide.libraries;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.model.Session;
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
	
}
