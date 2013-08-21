package org.uengine.kernel;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.mw3.webProcessDesigner.MajorProcessDefinitionNode;
import org.uengine.codi.mw3.webProcessDesigner.ProcessDefinitionHolder;
import org.uengine.contexts.TextContext;
import org.uengine.kernel.designer.web.ValueChainView;

public class ValueChain implements ContextAware, java.io.Serializable{

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	String valueChainId;
		public String getValueChainId() {
			return valueChainId;
		}
		public void setValueChainId(String valueChainId) {
			this.valueChainId = valueChainId;
		}
	TextContext name;
		public TextContext getName() {
			return name;
		}
		public void setName(TextContext name) {
			this.name = name;
		}
		
	ValueChainView valueChainView;
	@Hidden
		public ValueChainView getValueChainView() {
			return valueChainView;
		}
		public void setValueChainView(ValueChainView valueChainView) {
			this.valueChainView = valueChainView;
		}
	MajorProcessDefinitionNode majorProcessDefinitionNode;
		public MajorProcessDefinitionNode getMajorProcessDefinitionNode() {
			return majorProcessDefinitionNode;
		}
		public void setMajorProcessDefinitionNode(
				MajorProcessDefinitionNode majorProcessDefinitionNode) {
			this.majorProcessDefinitionNode = majorProcessDefinitionNode;
		}

	public ValueChain() {
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
	}
}