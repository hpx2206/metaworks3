package org.uengine.codi.mw3.model;

import java.net.UnknownHostException;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

import com.mongodb.MongoException;

public class Workflowy implements ContextAware {
	@Available
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	WorkflowyNode node;
		public WorkflowyNode getNode() {
			return node;
		}
		public void setNode(WorkflowyNode node) {
			this.node = node;
		}

	@ServiceMethod
    @Face(displayName="시작하기")
	public void load() throws UnknownHostException, MongoException {
		node = new WorkflowyNode(1);
		node.setName("ROOT");				
		node.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		node.getMetaworksContext().setWhere("ROOT");
		node.load();
		
		if(node.getChildNode().size() == 0)
			node.newNode();
		
		setNode(node);		
	}
}
