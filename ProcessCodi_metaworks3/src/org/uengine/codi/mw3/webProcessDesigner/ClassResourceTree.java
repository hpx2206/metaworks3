package org.uengine.codi.mw3.webProcessDesigner;

import java.io.Serializable;

import org.metaworks.FieldDescriptor;
import org.metaworks.ServiceMethodContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.metaworks.dwr.MetaworksRemoteService;

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
		
		TreeNode rootnode = new TreeNode();
		rootnode.setRoot(true);
		rootnode.setId(this.getId());
		rootnode.setType(TreeNode.TYPE_FOLDER);
		rootnode.setFolder(true);
		rootnode.setLoaded(true);
		rootnode.setExpanded(true);
		
		if( resourceClass == null ){
			rootnode.setName("resource를 등록해주세요.");
		}else{
			rootnode.setName(resourceClass);
			
			try{
				String fullClassName =  resourceClass.substring(0, resourceClass.lastIndexOf(".")).replaceAll("/", ".") ;
				String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
				WebObjectType wot = MetaworksRemoteService.getInstance().getMetaworksType(fullClassName); 
				WebFieldDescriptor wfields[] = wot.getFieldDescriptors();
				FieldDescriptor fields[] = wot.metaworks2Type().getFieldDescriptors();
				if( fields != null && fields.length > 0){
					for(int j=0; j<fields.length; j++){
						WebFieldDescriptor wfd = wfields[j];
						ClassResourceNode resourceNode = new ClassResourceNode();
						resourceNode.setId(className +"-"+wfd.getName());	// TODO
						resourceNode.setName(wfd.getName());
						resourceNode.setParentId(this.getId());
						resourceNode.setType(TreeNode.TYPE_FILE_TEXT);
						rootnode.add(resourceNode);
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		this.setNode(rootnode);
		setPreLoaded(true);
	}
}
