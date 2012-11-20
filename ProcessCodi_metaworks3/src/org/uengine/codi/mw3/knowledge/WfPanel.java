package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToNext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.uengine.codi.mw3.model.Session;

public class WfPanel implements ContextAware {
	
	@AutowiredFromClient
	public Session session;
	
	boolean first;
		@Hidden
		public boolean isFirst() {
			return first;
		}
		public void setFirst(boolean first) {
			this.first = first;
		}

	WfNode wfNode;		
		public WfNode getWfNode() {
			return wfNode;
		}
		public void setWfNode(WfNode wfNode) {
			this.wfNode = wfNode;
		}

	ArrayList<IWfNode> path;
	@Face(options="alignment", values="horizontal")
		public ArrayList<IWfNode> getPath() throws Exception{
			return path; 
		}
		public void setPath(ArrayList<IWfNode> path) {
			this.path = path;
		}
		
	String rootNodeId;
		public String getRootNodeId() {
			return rootNodeId;
		}
		public void setRootNodeId(String rootNodeId) {
			this.rootNodeId = rootNodeId;
		}

	String keyword;
		@Hidden
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	public WfPanel(){
		this.setFirst(true);
	}
	
	@ServiceMethod(payload={"first"})
	public void load() throws Exception {
		load(session.getCompany().getComCode());
	}
	
	public void load(String nodeId) throws Exception {
		load(nodeId, MetaworksContext.WHEN_VIEW);
	}
	
	public void load(String nodeId, String metaworksContext) throws Exception {
		setFirst(false);
		
		setRootNodeId(nodeId);
		
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(metaworksContext);
		
		WfNode node = new WfNode();
		node.setId(nodeId);
		node.getMetaworksContext().setWhen(metaworksContext);
		node.getMetaworksContext().setHow("ROOT");
		node.setFirst(true);		
		//node.load(nodeId);		
		
/*		if(node.getChildNode().size() == 0){
			WfNode newNode = new WfNode();
			
			node.addChildNode(newNode);
						
			newNode.createMe();
			newNode.setFocus(true);
		}*/
		
		
		//node.setName("");				
		//
		//node.getMetaworksContext().setWhere("ROOT");
		
		/*
		if(getMetaworksContext().getWhen().equals(MetaworksContext.WHEN_VIEW)){
			node.load();
			
			if(node.getChildNode().size() == 0){
				WorkflowyNode newNode = new WorkflowyNode(WorkflowyNode.makeId());
				newNode.setParentNode(node);
				
				node.getChildNode().add(newNode);
				node.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
			}
		}
		*/
		
		setWfNode(node);
		
		try{
			String rootPosition = session.getCompany().getComCode();
			
			//setting path
			path = new ArrayList<IWfNode>();
			IWfNode currNode = getWfNode();
			
			if(!rootPosition.equals(currNode.getId()))
			do{
				WfNode parentNode = new WfNode();
				parentNode.setId(currNode.getParentId());
				
				if(rootPosition.equals(currNode.getParentId()))
					break;
				
				currNode = parentNode.databaseMe();
				currNode.getMetaworksContext().setHow("PATH");
				path.add(0, currNode);
			}while(!rootPosition.equals(currNode.getId()));
	
			WfNode root = new WfNode();
			root.setId(rootPosition);
			root.setName("Home");
			root.getMetaworksContext().setHow("PATH");
			path.add(0, root);
			
			setPath(path);
		}catch(Exception e){}

	}		
	
	public void clearFocus(){
		getWfNode().clearFocus();
	}
	
	public WfNode getNode(String nodeId){
		return getWfNode().getNode(nodeId);
	}
	
	@ServiceMethod
	public void search() throws Exception {
		getWfNode().getChildNode().clear();
		
		getWfNode().search(getKeyword());
		
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	@Test(scenario="first", instruction="$first.wfNode.NewProcessInstance", next="autowiredObject.org.uengine.codi.mw3.knowledge.IWfNode.newDocument()")
	public Object newNode() throws Exception {
		WfNode newNode = new WfNode();
		newNode.setAuthorId(session.getUser().getUserId());
		
		String rootPosition = session.getCompany().getComCode();

		
		WfNode rootNode = new WfNode();		
		rootNode.setId(rootPosition);
		rootNode.loadChildren();
		rootNode.getMetaworksContext().setHow("ROOT");
		rootNode.addChildNode(newNode);
				
		newNode.createMe();
		newNode.setFocus(true);
		
		if(rootNode.getChildNode().size() > 1){
			WfNode targetNode = rootNode.getChildNode().get(newNode.getNo()-1);
			targetNode.setChildNode(null);
			
			return new ToNext(targetNode, newNode);
		}else{
			return new Refresh(rootNode);
		}
	}
	
	
}
