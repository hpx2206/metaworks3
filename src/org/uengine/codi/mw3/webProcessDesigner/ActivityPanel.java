package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.uengine.kernel.Activity;
import org.uengine.kernel.ParameterContextPanel;


@Face(ejsPath="genericfaces/Tab.ejs"  )
public class ActivityPanel implements ContextAware{

	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
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
		
	Object parameterValue;
	@Face(displayName="$formView")
	@Available(how="humanActivity")
		public Object getParameterValue() {
			return parameterValue;
		}
		public void setParameterValue(Object parameterValue) {
			this.parameterValue = parameterValue;
		}
	public ActivityPanel(){
		setMetaworksContext(new MetaworksContext());
	}
}
