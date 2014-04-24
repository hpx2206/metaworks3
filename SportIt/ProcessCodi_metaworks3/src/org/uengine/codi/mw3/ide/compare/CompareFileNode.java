package org.uengine.codi.mw3.ide.compare;

import java.io.File;
import java.util.ArrayList;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.uengine.codi.mw3.ide.ResourceNode;

public class CompareFileNode extends TreeNode{

	String path;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
	String parentName;
		public String getParentName() {
			return parentName;
		}
		public void setParentName(String parentName) {
			this.parentName = parentName;
		}
		
	
	@Override
	@ServiceMethod(callByContent=true, except="child", target=ServiceMethodContext.TARGET_SELF)
	public Object expand() throws Exception {
		
		ArrayList<TreeNode> child = new ArrayList<TreeNode>();
		if( this.getPath() != null ){
			File file = new File(this.getPath());
			String[] childFilePaths = file.list();
	
			// folder
			for(int i=0; i<childFilePaths.length; i++){
				File childFile = new File(file.getAbsolutePath() + File.separatorChar + childFilePaths[i]);
	
				if(childFile.isDirectory()){
					CompareFileNode node = new CompareFileNode();
					node.setId(this.getId() + File.separatorChar + childFile.getName());				
					node.setName(childFile.getName());
					node.setPath(this.getPath() + File.separatorChar + childFile.getName());
					node.setParentId(this.getId());
					node.setParentName(this.getName());
					node.setType(TreeNode.TYPE_FOLDER);
					node.setFolder(true);
					node.setTreeId(this.getTreeId());
	
					child.add(node);
				}
			}
	
			// file
			for(int i=0; i<childFilePaths.length; i++){
				File childFile = new File(file.getAbsolutePath() + File.separatorChar + childFilePaths[i]);
	
				if(!childFile.isDirectory()){
					CompareFileNode node = new CompareFileNode();
					node.setId(this.getId() + File.separatorChar + childFile.getName());
					node.setName(childFile.getName());
					node.setPath(this.getPath() + File.separatorChar + childFile.getName());
					node.setParentId(this.getId());
					node.setType(ResourceNode.findNodeType(node.getName()));
					node.setTreeId(this.getTreeId());
					child.add(node);
				}
			}
	
			this.setChild(child);
	
			return this;
		}else{
			return null;
		}
	}
	
	public Object loadChild(){
		
		return null;
	}
	
	@Override
	@ServiceMethod(payload={"id", "name", "path", "type", "folder", "treeId"}, target=ServiceMethodContext.TARGET_AUTO)
	public Object[] action()  throws Exception{
		if( CompareOriginFilePanel.FILE_LOCATION.equals(this.getTreeId())){
			CompareOriginFile compareOriginFile = new CompareOriginFile();
			compareOriginFile.setSelectedProcessAlias(this.getPath());
			compareOriginFile.setFileName(this.getName());
			compareOriginFile.load();
			
			return new Object[]{compareOriginFile};
		}
		if(CompareImportFilePanel.FILE_LOCATION.equals(this.getTreeId())) {
			CompareImportFile compareImportFile = new CompareImportFile();
			compareImportFile.setSelectedProcessAlias(this.getPath());
			compareImportFile.setFileName(this.getName());
			compareImportFile.load();
			
			return new Object[]{compareImportFile};
		}
		
		return null;
	}
}
