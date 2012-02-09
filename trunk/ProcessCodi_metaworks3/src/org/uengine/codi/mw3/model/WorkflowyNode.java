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
		if(getParentNode().getParentNode() != null && getContent().length()==0)
			return outdent();
		
		getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		int newNodeId = Workflowy.makeId();
		
		WorkflowyNode newNode = new WorkflowyNode();		
		newNode.setId(newNodeId);
		newNode.getMetaworksContext().setHow("add");
		
		if(getContentNext().length() == 0){
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
		}else{
			int nodeIndex = getParentNode().getChildNode().indexOf(this);			
			
			for(int i=0; i<getChildNode().size(); i++)
				getChildNode().get(i).setParentNode(newNode);
			
			newNode.setParentNode(getParentNode());
			newNode.setChildNode(getChildNode());
			newNode.setContent(getContentNext());
			
			setChildNode(new ArrayList<WorkflowyNode>());
			getParentNode().addChildNode(nodeIndex+1, newNode);
			
			return getRootNode();			
		}
	}
	
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_NEW)
	public WorkflowyNode remove(){
		int nodeIndex = getParentNode().getChildNode().indexOf(this);
		
		if(nodeIndex == 0){
			// 처리X - 첫번째 노드, 자식 존재
			if(getChildNode().size() > 0){
				getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
				getMetaworksContext().setHow("remove");
				
				setContent(getContent() + getContentNext());
				
				return getRootNode();
			}else{
				// 처리X - 첫번째 노드, 자식 미존재, 붙일 content 존재
				if(getContentNext().length() > 0){
					getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
					getMetaworksContext().setHow("remove");
					
					setContent(getContent() + getContentNext());
					
					return getRootNode();
					
					// :TODO this를 retuen 시에 setContent() 에 값을 넣어 return 하는데 retuen 이 되지를 않음 이유 알 수 없음		
				
				// 처리O - 첫번째 노드, 자식 미존재, 붙일 content 미존재
				// 현재 노드 제거, 부모 노드 포커싱
				}else{
					getParentNode().getChildNode().remove(nodeIndex);
					getParentNode().getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
					getParentNode().getMetaworksContext().setHow("remove");
					return getRootNode();
				}
			}
		} else {
			if(getChildNode().size() > 0){
				// 처리X - 두번째 노드 이상, 자식 존재, 이전 노드의 자식 존재
				if(getParentNode().getChildNode().get(nodeIndex-1).getChildNode().size() > 0)
					return this;
				// 처리O - 두번째 노드 이상, 자식 존재, 이전 노드의 자식 미존재
				// 현재 노드 제거, 현재 노드에 자식들을 이전 노드로 변경, 이전 노드 포커싱
				else {
					getParentNode().getChildNode().remove(nodeIndex);
					
					WorkflowyNode targetNode = getParentNode().getChildNode().get(nodeIndex-1);
					
					for(int i=0; i<getChildNode().size(); i++){
						getChildNode().get(i).setParentNode(targetNode);						
					}
					
					targetNode.setChildNode(getChildNode());					
					targetNode.setContent(targetNode.getContent()+getContentNext());
					targetNode.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
					targetNode.getMetaworksContext().setHow("remove");
					
					return getRootNode();
				}					
			}else{
				// 처리X - 두번째 노드 이상, 자식 미존재, 이전노드 자식노드 존재 and 붙일 content 존재
				if(getParentNode().getChildNode().get(nodeIndex-1).getChildNode().size() > 0 && getContentNext().length() > 0){
					getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
					getMetaworksContext().setHow("remove");
					setContent(getContent() + getContentNext());
					
					return getRootNode();
					
					// :TODO this를 retuen 시에 setContent() 에 값을 넣어 return 하는데 retuen 이 되지를 않음 이유 알 수 없음						
				// 처리O - 두번째 노드 이상, 자식 미존재, 이전노드 자식노드 미존재 or 붙일 content 미존재
				}else{					
					getParentNode().getChildNode().remove(nodeIndex);
					
					WorkflowyNode targetNode = getParentNode().getChildNode().get(nodeIndex-1);
					
					while (targetNode.getChildNode().size() > 0)					
						targetNode = targetNode.getChildNode().get(targetNode.getChildNode().size()-1);
						
					targetNode.setContent(targetNode.getContent()+getContentNext());
					targetNode.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
					targetNode.getMetaworksContext().setHow("remove");
					
					return getRootNode();
				}				
			}
		}
	}
	
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_NEW)
	public WorkflowyNode indent(){
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		getMetaworksContext().setHow("indent");
		
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
		getMetaworksContext().setHow("outdent");
		
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
	
	@ServiceMethod(callByContent=true)
	public WorkflowyNode newNode(){
		int newNodeId = Workflowy.makeId();
		
		WorkflowyNode newNode = new WorkflowyNode();		
		newNode.setId(newNodeId);
		
		newNode.setParentNode(this);
		addChildNode(newNode);
		
		return this;
	}	
}
