package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;

public class ProcessDefinitionNode extends TreeNode{
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}

	public ArrayList<TreeNode> loadExpand(){
		
		return null;
	}
	
	public Object expand(){
		
		return null;
	}
	
	@Override
	@ServiceMethod(payload={"id", "name", "path", "type", "folder", "projectId"}, target=ServiceMethodContext.TARGET_APPEND)
	public Object action(){
		
		if( alias != null ){
			
		}
		
		return null;
		
	}
}
