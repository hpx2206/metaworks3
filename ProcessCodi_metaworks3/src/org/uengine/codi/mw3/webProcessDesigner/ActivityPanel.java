package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Order;
import org.uengine.kernel.Activity;
import org.uengine.kernel.ParameterContextPanel;


@Face(	ejsPath="genericfaces/Tab.ejs")
public class ActivityPanel {

	Activity activity;
		@Face(displayName="$Properties")
		@Order(1)
		public Activity getActivity() {
			return activity;
		}
		public void setActivity(Activity activity) {
			this.activity = activity;
		}
	
	Documentation document;
		@Face(displayName="$Documentation")
		@Order(2)
		public Documentation getDocument() {
			return document;
		}
		public void setDocument(Documentation document) {
			this.document = document;
		}
		
	ParameterContextPanel parameterContextPanel;
	@Face(displayName="$ParameterSetting")
	@Order(3)
		public ParameterContextPanel getParameterContextPanel() {
			return parameterContextPanel;
		}
		public void setParameterContextPanel(ParameterContextPanel parameterContextPanel) {
			this.parameterContextPanel = parameterContextPanel;
		}

	public ActivityPanel(){
		
	}
}
