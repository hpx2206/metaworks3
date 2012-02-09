package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;

public class WorkflowyNode implements ContextAware {

	public WorkflowyNode(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		childNode = new ArrayList<WorkflowyNode>();
	}
		
	int id;
		@Id
		public int getId() {
			return id;
		}	
		public void setId(int id) {
			this.id = id;
		}

	String content;
		public String getContent() {
			return content;
		}
	
		public void setContent(String content) {
			this.content = content;
		}

	String contentNext;
		public String getContentNext() {
			return contentNext;
		}
		public void setContentNext(String contentNext) {
			this.contentNext = contentNext;
		}

	WorkflowyNode parentNode;	
		public WorkflowyNode getParentNode() {
			return parentNode;
		}
		public void setParentNode(WorkflowyNode parentNode) {
			this.parentNode = parentNode;
		}		
		
	ArrayList<WorkflowyNode> childNode;
		public ArrayList<WorkflowyNode> getChildNode() {
			return childNode;
		}
		public void setChildNode(ArrayList<WorkflowyNode> childNode) {
			this.childNode = childNode;
		}

	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	private void addChildNode(WorkflowyNode newNode){
		addChildNode(childNode.size(), newNode);	
	}
	
	private void addChildNode(int index, WorkflowyNode newNode){
		childNode.add(index, newNode);
	}
	
	private WorkflowyNode getRootNode(){
		
		if(getParentNode() == null)
			return this;
		else
			return getParentNode().getRootNode();
	}
	
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_NEW)
	public WorkflowyNode add(){
		if(getParentNode().getParentNode() != null && getContent() == null)
			return outdent();
		
		getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		int newNodeId = Workflowy.makeId();
		
		WorkflowyNode newNode = new WorkflowyNode();		
		newNode.setId(newNodeId);
		
		if(getContentNext() != null)
			newNode.setContent(getContentNext());
			
		if(getChildNode().size()>0){			
			newNode.setParentNode(this);
			addChildNode(0, newNode);
			
			return getRootNode();
			//return this;
		}else{
			newNode.setParentNode(getParentNode());
			
			int nodeIndex = getParentNode().getChildNode().indexOf(this);			
			getParentNode().addChildNode(nodeIndex+1, newNode);
			
			
			return getRootNode();
			//return getParentNode();
		}
	}

	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_NEW)
	public WorkflowyNode indent(){
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		int nodeIndex = getParentNode().getChildNode().indexOf(this);
		
		if(nodeIndex > 0){
			getParentNode().getChildNode().remove(nodeIndex);
			
			setParentNode(getParentNode().getChildNode().get(nodeIndex-1));
			getParentNode().addChildNode(this);
			
			return getRootNode();			
			//return getParentNode().getParentNode();
		}else{
			return this;
		}
	}
	
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_NEW)
	public WorkflowyNode outdent(){
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		if(getParentNode().getParentNode() != null){
			int nodeIndex = getParentNode().getParentNode().getChildNode().indexOf(getParentNode());
						
			getParentNode().getChildNode().remove(getParentNode().getChildNode().indexOf(this));
			
			setParentNode(getParentNode().getParentNode());
			getParentNode().addChildNode(nodeIndex+1, this);
			
			return getRootNode();
		}else{
			return this;
		}			
	}
}
