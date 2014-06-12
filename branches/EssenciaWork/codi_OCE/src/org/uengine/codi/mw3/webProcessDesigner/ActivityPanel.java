package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.kernel.Activity;
import org.uengine.kernel.ParameterContextPanel;


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
		
	ParameterContextPanel parameterContextPanel;
		@Face(displayName="$ParameterSetting")
		public ParameterContextPanel getParameterContextPanel() {
			return parameterContextPanel;
		}
		public void setParameterContextPanel(ParameterContextPanel parameterContextPanel) {
			this.parameterContextPanel = parameterContextPanel;
		}

//	boolean documentFlag;
//		@Hidden
//		public boolean isDocumentFlag() {
//			return documentFlag;
//		}
//		public void setDocumentFlag(boolean documentFlag) {
//			this.documentFlag = documentFlag;
//		}
//
//	boolean parameterFlag;
//		@Hidden
//		public boolean isParameterFlag() {
//			return parameterFlag;
//		}
//		public void setParameterFlag(boolean parameterFlag) {
//			this.parameterFlag = parameterFlag;
//		}
		
	public ActivityPanel(){
		
	}
}
