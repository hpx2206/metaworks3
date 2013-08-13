package org.uengine.kernel;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.mw3.webProcessDesigner.CanvasDTO;
import org.uengine.kernel.designer.web.ValueChainView;

public class ValueChain extends CanvasDTO{

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	public ValueChain() {
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
	}
		
	String definitionId;
		public String getDefinitionId() {
			return definitionId;
		}
		public void setDefinitionId(String l) {
			definitionId = l;
		}
		
	String tracingTag;
	@Hidden
		public String getTracingTag() {
			return tracingTag;
		}
		public void setTracingTag(String tracingTag) {
			this.tracingTag = tracingTag;
		}

	ValueChainView valueChainView;
	@Hidden
		public ValueChainView getValueChainView() {
			return valueChainView;
		}
		public void setValueChainView(ValueChainView valueChainView) {
			this.valueChainView = valueChainView;
		}
	
}