package org.uengine.webservice;

import java.util.ArrayList;

import org.metaworks.EventContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.ToEvent;
import org.metaworks.ToOpener;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.model.Popup;

public class WSDTreeNode extends TreeNode{
	
	public static final String RESOURCE_TYPE = "resource";
	public static final String METHOD_TYPE = "method";
	
	String propertyType;
		public String getPropertyType() {
			return propertyType;
		}
		public void setPropertyType(String propertyType) {
			this.propertyType = propertyType;
		}
	
	ArrayList<MethodProperty> methods;
		public ArrayList<MethodProperty> getMethods() {
			return methods;
		}
		public void setMethods(ArrayList<MethodProperty> methods) {
			this.methods = methods;
		}

	ArrayList<ResourceProperty> childResources;
		public ArrayList<ResourceProperty> getChildResources() {
			return childResources;
		}
		public void setChildResources(ArrayList<ResourceProperty> childResources) {
			this.childResources = childResources;
		}
	public ArrayList<TreeNode> loadChild() throws Exception {
		ArrayList<TreeNode> child = new ArrayList<TreeNode>();
		// folder
		if( childResources != null ){
			for(int i=0; i<childResources.size(); i++){
				ResourceProperty resourceProperty =  childResources.get(i);
				String refName =  resourceProperty.getPath();
				String nodeName = refName.replaceAll("\\\\", "/");
				WSDTreeNode rNode = new WSDTreeNode();
				rNode.setId(nodeName);
				rNode.setName(nodeName);
				rNode.setType(TreeNode.TYPE_FOLDER);
				rNode.setFolder(true);
				rNode.setPropertyType(WSDTreeNode.RESOURCE_TYPE);
				rNode.setMethods(resourceProperty.getMethods());
				rNode.setChildResources(resourceProperty.getChildResources());
				
				child.add(rNode);
			}
		}
		if( methods != null ){
			for(int i=0; i<methods.size(); i++){
				MethodProperty methodProperty = methods.get(i);
				String nodeName = methodProperty.getId();
				
				WSDTreeNode rNode = new WSDTreeNode();
				rNode.setId(nodeName);
				rNode.setName(nodeName);
				rNode.setType(TreeNode.TYPE_FILE_HTML);
				rNode.setFolder(false);
				rNode.setMethods(this.getMethods());
				rNode.setPropertyType(WSDTreeNode.METHOD_TYPE);
				
				child.add(rNode);
			}
		}
		return child;
	}
	@ServiceMethod(payload={"id", "name", "folder","propertyType","methods","childResources"}, target=ServiceMethodContext.TARGET_APPEND)
	public Object expand() throws Exception { 
		return new ToAppend(this, this.loadChild());
	}
	
	@ServiceMethod(payload={"id", "name", "folder","propertyType", "methods"}, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] action() throws Exception {
		MethodProperty target = null;
		for(MethodProperty method: methods){
			if( this.getId().equals(method.getId()) ){
				target = method;
			}
		}
		return new Object[]{ new ToOpener(target), new Remover(new Popup(), true)};
	}
}
