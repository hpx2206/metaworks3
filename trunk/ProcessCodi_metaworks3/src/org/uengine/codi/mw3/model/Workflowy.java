package org.uengine.codi.mw3.model;

import java.net.UnknownHostException;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class Workflowy implements ContextAware {
	
	static int id = 0;
	
	public Workflowy() throws UnknownHostException, MongoException{
		//Mongo m = new Mongo();		
		//DB db = m.getDB( "workflowy" );
		node = new WorkflowyNode();
		node.setId(makeId());
		node.setContent("ROOT");				
		node.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		node.getMetaworksContext().setWhere("ROOT");
		
		int newNodeId = makeId();
		WorkflowyNode newNode = new WorkflowyNode();
		newNode.setParentNode(node);
		newNode.setId(newNodeId);
		
		node.childNode.add(newNode);
		
		setNode(node);
	}
	
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	public static int makeId(){
		id = id +1;
		
		return id;		
	}	

	WorkflowyNode node;
		public WorkflowyNode getNode() {
			return node;
		}
		public void setNode(WorkflowyNode node) {
			this.node = node;
		}
}
