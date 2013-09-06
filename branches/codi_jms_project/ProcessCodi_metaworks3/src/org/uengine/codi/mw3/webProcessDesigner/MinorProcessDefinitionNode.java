package org.uengine.codi.mw3.webProcessDesigner;

import java.io.File;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.Workspace;

public class MinorProcessDefinitionNode extends TreeNode implements ContextAware {
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}		
		
	String path;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}	
	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}	
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
		@Override
		@ServiceMethod(callByContent=true, except="child", target=ServiceMethodContext.TARGET_SELF)
		public Object expand() throws Exception {
			// TODO Auto-generated method stub
			
			File file = new File(this.getPath());
			String[] childFilePaths = file.list();
				for(int i=0; i<childFilePaths.length; i++){
					File childFile = new File(file.getAbsolutePath() + File.separatorChar + childFilePaths[i]);
					if( !childFile.isDirectory()){
						String type = ResourceNode.findNodeType(childFile.getName());
						
						if(!type.equals(TreeNode.TYPE_FILE_VALUECHAIN)){
							continue;
						}
						MajorProcessDefinitionNode valueChainNode = new MajorProcessDefinitionNode();
						valueChainNode.setType(TreeNode.TYPE_FILE_VALUECHAIN);
						valueChainNode.setFolder(true);
						valueChainNode.setName(childFile.getName());
	//					valueChainNode.setId(this.getId() + File.separatorChar + childFile.getName());
						valueChainNode.setId(file.getPath());
						valueChainNode.setExpanded(true);
						valueChainNode.setPath(childFile.getPath());
	//					valueChainNode.setPath(this.getPath() + File.separatorChar + childFile.getName());
						valueChainNode.setDefId(childFile.getName());
						valueChainNode.setAlias(childFile.getName());
						valueChainNode.setParentId(this.getId());
						valueChainNode.setMetaworksContext(getMetaworksContext());
						
						this.add(valueChainNode);
					}
				}
			return this;
		}		
		
		
		
}
