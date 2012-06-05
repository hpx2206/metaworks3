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
import org.uengine.codi.mw3.model.Session;

public class WfPanel implements ContextAware {
	
	@AutowiredFromClient
	public Session session;
	
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
		
	public void load(String nodeId) throws Exception {
		load(nodeId, MetaworksContext.WHEN_VIEW);
	}
	
	public void load(String nodeId, String metaworksContext) throws Exception {
		
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(metaworksContext);
		
		WfNode node = new WfNode();
		node.getMetaworksContext().setWhen(metaworksContext);
		node.getMetaworksContext().setHow("ROOT");
		node.load(nodeId);
		
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
		
		
		//setting path
		path = new ArrayList<IWfNode>();
		IWfNode currNode = getWfNode();
		
		if(!"-1".equals(currNode.getId()))
		do{
			WfNode parentNode = new WfNode();
			parentNode.setId(currNode.getParentId());
			
			if("-1".equals(currNode.getParentId()))
				break;
			
			currNode = parentNode.databaseMe();
			currNode.getMetaworksContext().setHow("PATH");
			path.add(0, currNode);
		}while(!"-1".equals(currNode.getId()));

		WfNode root = new WfNode();
		root.setId("-1");
		root.setName("/");
		root.getMetaworksContext().setHow("PATH");
		path.add(0, root);
		
		setPath(path);

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
	public Object newNode() throws Exception {
		WfNode newNode = new WfNode();
		newNode.setAuthorId(session.getUser().getUserId());
		
		WfNode rootNode = new WfNode();		
		rootNode.setId("-1");
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
