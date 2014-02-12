package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Order;
import org.uengine.kernel.Activity;
import org.uengine.kernel.ParameterContextPanel;


@Face(ejsPath="dwr/metaworks/genericfaces/Tab.ejs")
public class ActivityPanel  implements ContextAware{

	MetaworksContext metaworksContext;
	@Hidden
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	Activity activity;
		@Face(displayName="$Properties")
		@Order(1)
		@Available(condition="activity")
		public Activity getActivity() {
			return activity;
		}
		public void setActivity(Activity activity) {
			this.activity = activity;
		}
	
	Documentation document;
		@Face(displayName="$Documentation")
		@Order(2)
		@Available(condition="document")
		public Documentation getDocument() {
			return document;
		}
		public void setDocument(Documentation document) {
			this.document = document;
		}
		
	ParameterContextPanel parameterContextPanel;
	@Face(displayName="$ParameterSetting")
	@Order(3)
	@Available(condition="parameterContextPanel")
		public ParameterContextPanel getParameterContextPanel() {
			return parameterContextPanel;
		}
		public void setParameterContextPanel(ParameterContextPanel parameterContextPanel) {
			this.parameterContextPanel = parameterContextPanel;
		}

	public ActivityPanel(){
		this.setMetaworksContext(new MetaworksContext());
	}
}
