package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.uengine.kernel.Activity;
import org.uengine.kernel.RestWebServiceActivity;
import org.uengine.webservice.MethodProperty;
import org.uengine.webservice.ParameterProperty;

public class PoolMappingTree extends MappingTree {
	
	Activity activity;
		public Activity getActivity() {
			return activity;
		}
		public void setActivity(Activity activity) {
			this.activity = activity;
		}

	@Override
	@ServiceMethod(payload={"id", "align","parentEditorId","activity"} , target=ServiceMethodContext.TARGET_SELF)
	public void init() throws Exception{
		RestWebServiceActivity activity = (RestWebServiceActivity)this.getActivity();
		TreeNode rootnode = new TreeNode();
		rootnode.setRoot(true);
		rootnode.setId(activity.getName() + "Root");
		rootnode.setName(activity.getDescription() != null && activity.getDescription().getText() != null ? activity.getDescription().getText() : activity.getName().getText() );
		rootnode.setType(TreeNode.TYPE_FOLDER);
		rootnode.setLoaded(true);
		rootnode.setFolder(true);
		rootnode.setExpanded(true);
		rootnode.setAlign(TreeNode.ALIGN_LEFT);
		
		MethodProperty mp = activity.getMethod();
		if( mp != null && mp.getId() != null ){
			TreeNode mpNode = new TreeNode();
			mpNode.setId(mp.getId());
			mpNode.setName(mp.getId() );
			mpNode.setLoaded(true);
			mpNode.setFolder(true);
			mpNode.setExpanded(true);
			mpNode.setType(TreeNode.TYPE_FOLDER);
			mpNode.setAlign(TreeNode.ALIGN_LEFT);
			
			if( mp.getRequest() != null ){
				ParameterProperty[] pp = mp.getRequest();
				for(int i=0; i < pp.length; i++){
					TreeNode childNode = new TreeNode();
					childNode.setId(pp[i].getName());
					childNode.setName(pp[i].getName() );
					childNode.setLoaded(true);
					childNode.setExpanded(true);
					childNode.setType(TreeNode.TYPE_FILE_TEXT);
					childNode.setAlign(TreeNode.ALIGN_LEFT);
					
					mpNode.add(childNode);
				}
			}
			
			rootnode.add(mpNode);
		}
		
		this.setNode(rootnode);
		
		setPreLoaded(true);
	}
}
