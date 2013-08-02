package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.annotation.Face;
import org.uengine.kernel.Activity;


@Face(ejsPath="genericfaces/Tab.ejs"  )
public class ActivityPanel {

	Activity activity;
		@Face(displayName="$Properties")
		public Activity getActivity() {
			return activity;
		}
	
		public void setActivity(Activity activity) {
			this.activity = activity;
		}
	
	Documentation document;
		@Face(displayName="$Documentation")
		public Documentation getDocument() {
			return document;
		}
	
		public void setDocument(Documentation document) {
			this.document = document;
		}
}
