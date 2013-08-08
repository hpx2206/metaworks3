package org.uengine.codi.mw3.ide.compare;

import org.metaworks.annotation.Id;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.metaworks.metadata.MetadataBundle;

public class CompareFileNavigator {
	
	String id;
	@Id
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	Tree fileTree;
		public Tree getFileTree() {
			return fileTree;
		}
		public void setFileTree(Tree fileTree) {
			this.fileTree = fileTree;
		}
		
	public CompareFileNavigator(){
		
	}
	
	public void load() throws Exception{
		
		String projectId = MetadataBundle.getProjectId();
		String mainPath = MetadataBundle.getProjectBasePath(projectId);
		
		CompareFileNode rootNode = new CompareFileNode();
		rootNode.setId(projectId);
		rootNode.setRoot(true);
		rootNode.setType(TreeNode.TYPE_FOLDER);
		rootNode.setName(projectId);
		rootNode.setFolder(true);
		rootNode.setPath(mainPath);
		rootNode.setExpanded(true);
		rootNode.setTreeId(id);
		
		rootNode.expand();
		
		Tree tree = new Tree();
		tree.setId(id);
		tree.setNode(rootNode);
		setFileTree(tree);
	}
	
	public void loadUpload() throws Exception{
		
		String projectId = MetadataBundle.getProjectId();
		String mainPath = MetadataBundle.getProjectBasePath(projectId);
		
		CompareFileNode rootNode = new CompareFileNode();
		rootNode.setId(projectId);
		rootNode.setRoot(true);
		rootNode.setType(TreeNode.TYPE_FOLDER);
		rootNode.setFolder(true);
		rootNode.setTreeId(id);
		if( isUploaded() ){
			// TODO zip 파일 푼 경로를 트리로 만들어서 뿌리기
			rootNode.setName(projectId);
			rootNode.setPath(mainPath);
			rootNode.setExpanded(true);
			
			rootNode.expand();
		}else{
			rootNode.setName("파일을 등록해주세요");
			rootNode.setPath(null);
			rootNode.setExpanded(false);
			
		}
		
		Tree tree = new Tree();
		tree.setId(id);
		tree.setNode(rootNode);
		setFileTree(tree);
	}
	
	public boolean isUploaded(){
		
		return false;
	}
}
