package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;

public class ClassResourceTree extends Tree {
	
	String resourceClass;
		public String getResourceClass() {
			return resourceClass;
		}
		public void setResourceClass(String resourceClass) {
			this.resourceClass = resourceClass;
		}
	boolean preLoaded;
		@Hidden
		public boolean isPreLoaded() {
			return preLoaded;
		}
		public void setPreLoaded(boolean preLoaded) {
			this.preLoaded = preLoaded;
		}	
		
	@ServiceMethod(callByContent= true, target=ServiceMethodContext.TARGET_SELF)	
	public void init() throws Exception{
		
		VariableTreeNode rootnode = new VariableTreeNode();
		rootnode.setRoot(true);
		rootnode.setId(this.getId());
		rootnode.setTreeId(this.getId());
		rootnode.setType(TreeNode.TYPE_FOLDER);
		rootnode.setFolder(true);
		rootnode.setLoaded(true);
		rootnode.setExpanded(true);
		rootnode.setAlign(this.getAlign());
		
		if( resourceClass == null ){
			rootnode.setName("resource를 등록해주세요.");
		}else{
//			String fullClassName =  resourceClass.substring(0, resourceClass.lastIndexOf(".")).replaceAll("/", ".") ;
			String fullClassName =  resourceClass;
			String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
			
			rootnode.setId(className);
			rootnode.setName(resourceClass);
			rootnode.setClassName(fullClassName);
			try{
				rootnode.setChild(rootnode.loadExpand(rootnode));				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		this.setNode(rootnode);
		setPreLoaded(true);
	}
}
