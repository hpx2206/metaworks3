package org.uengine.codi.mw3.knowledge;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;

public class Workflowy implements ContextAware {
	public Workflowy() throws Exception {
		node = new WorkflowyNode(0);
		node.setName("ROOT");				
		node.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		node.getMetaworksContext().setWhere("ROOT");
		node.load();
		
		if(node.getChildNode().size() == 0){
			WorkflowyNode newNode = new WorkflowyNode(WorkflowyNode.makeId());
			newNode.setParentNode(node);
			
			node.getChildNode().add(newNode);
			node.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		}
		
		setNode(node);
	}
			
	WorkflowyNode node;
		public WorkflowyNode getNode() {
			return node;
		}
		public void setNode(WorkflowyNode node) {
			this.node = node;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}		
}
