package org.uengine.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;

public class WSDPickerPanel  implements ContextAware{
	
	MetaworksContext metaworksContext;
	@Hidden
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	WebServiceDefinition webServiceDefinition;
	@Hidden
		public WebServiceDefinition getWebServiceDefinition() {
			return webServiceDefinition;
		}
		public void setWebServiceDefinition(WebServiceDefinition webServiceDefinition) {
			this.webServiceDefinition = webServiceDefinition;
		}
	Tree tree;
		public Tree getTree() {
			return tree;
		}
		public void setTree(Tree tree) {
			this.tree = tree;
		}
	public WSDPickerPanel(){
		this.setMetaworksContext(new MetaworksContext());
	}
	public void init(){
		
		ArrayList<ResourceProperty> rList = webServiceDefinition.getResourceList();
		tree = new Tree();
		tree.setId(webServiceDefinition.getName());
		
		TreeNode rootnode = new TreeNode();
		rootnode.setRoot(true);
		rootnode.setId(webServiceDefinition.getName());
		rootnode.setName(webServiceDefinition.getName());
		rootnode.setType(TreeNode.TYPE_FOLDER);
		rootnode.setFolder(true);
		rootnode.setLoaded(true);
		rootnode.setExpanded(true);
		rootnode.setAlign(TreeNode.ALIGN_LEFT);
		
		// 같은 패스별로 노드를 만들기 위하여 HashMap을 사용
		HashMap<String, ArrayList<ResourceProperty>> map = new HashMap<String, ArrayList<ResourceProperty>>(); 
		for( ResourceProperty resourceProperty : rList){
			String key = resourceProperty.getPath();
			ArrayList<ResourceProperty> rp;
			if( map.containsKey(key) ){
				 rp = map.get(key);
			}else{
				rp = new ArrayList<ResourceProperty>();
			}
			rp.add(resourceProperty);
			map.put(key, rp);
		}
		Iterator<String> itr = map.keySet().iterator();
		while (itr.hasNext()) {
			String key = (String)itr.next();
			ArrayList<ResourceProperty> rpList = map.get(key);
			ArrayList<MethodProperty> methodList 	= new ArrayList<MethodProperty>();
			ArrayList<ResourceProperty> childResourceList 	= new ArrayList<ResourceProperty>();
			for( ResourceProperty resourceProperty : rpList){
				methodList.addAll(resourceProperty.getMethods());
				childResourceList.addAll(resourceProperty.getChildResources());
			}
			
			String nodeName = key.replaceAll("\\\\", "/");
			WSDTreeNode rNode = new WSDTreeNode();
			rNode.setId(nodeName);
			rNode.setName(nodeName);
			rNode.setType(TreeNode.TYPE_FOLDER);
			rNode.setFolder(true);
			rNode.setPropertyType(WSDTreeNode.RESOURCE_TYPE);
			rNode.setMethods(methodList);
			rNode.setChildResources(childResourceList);
			
			rootnode.add(rNode);
		}
				
		tree.setNode(rootnode);
	}
}
