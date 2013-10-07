package org.uengine.codi.mw3.ide.compare;

import org.metaworks.annotation.Id;
import org.metaworks.component.Tree;
import org.metaworks.component.TreeNode;
import org.metaworks.metadata.MetadataBundle;
import org.uengine.codi.mw3.ide.ResourceNode;

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
	boolean uploaded;
		public void setUploaded(boolean uploaded) {
			this.uploaded = uploaded;
		}
		public CompareFileNavigator(){
			
		}
	String fileName;
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
	String uploadPath;
		public String getUploadPath() {
			return uploadPath;
		}
		public void setUploadPath(String uploadPath) {
			this.uploadPath = uploadPath;
		}
	String uploadType;
		public String getUploadType() {
			return uploadType;
		}
		public void setUploadType(String uploadType) {
			this.uploadType = uploadType;
		}
	String selectType;
		public String getSelectType() {
			return selectType;
		}
		public void setSelectType(String selectType) {
			this.selectType = selectType;
		}
	
	public void load() throws Exception{
		String projectId = MetadataBundle.getProjectId();
		String mainPath = MetadataBundle.getProjectBasePath(projectId);
		
		CompareFileNode rootNode = new CompareFileNode();
		rootNode.setId(projectId);
		rootNode.setRoot(true);
		rootNode.setType(TreeNode.TYPE_FOLDER);
		if(CompareNavigator.UPLOAD_PATH.equals(this.getSelectType())) {
			rootNode.setName("업로드 된 폴더");
			rootNode.setPath(getUploadPath());
		} else {
			rootNode.setName(projectId);
			rootNode.setPath(mainPath);
		}
		rootNode.setFolder(true);
		rootNode.setExpanded(true);
		rootNode.setTreeId(id);
		
		rootNode.expand();
		
		Tree tree = new Tree();
		tree.setId(id);
		tree.setNode(rootNode);
		setFileTree(tree);
	}
	
	public void loadUpload() throws Exception{
		
		if (uploaded && (uploadType.equals(".process"))) {
			CompareFileNode rootNode;
			if( this.getFileTree() != null && this.getFileTree().getNode() != null ){
				rootNode = (CompareFileNode) this.getFileTree().getNode();
			}else{
				rootNode = new CompareFileNode();
			}
			
			rootNode.setId(fileName);
			rootNode.setRoot(true);
			rootNode.setType(TreeNode.TYPE_FOLDER);
			rootNode.setFolder(true);
			rootNode.setTreeId(id);
			
			rootNode.setName("업로드된 파일");
			rootNode.setPath(null);
			rootNode.setExpanded(true);
			rootNode.setLoaded(true);
			// process
			CompareFileNode childNode = new CompareFileNode();
			childNode.setName(fileName);
			childNode.setPath(uploadPath);
			childNode.setType(ResourceNode.TYPE_FILE_PROCESS);
			childNode.setFolder(false);
			childNode.setTreeId(id);
			rootNode.add(childNode);
			
			Tree tree = new Tree();
			tree.setId(id);
			tree.setNode(rootNode);
			setFileTree(tree);
		}else{
			CompareFileNode rootNode = new CompareFileNode();
			rootNode.setId(fileName);
			rootNode.setRoot(true);
			rootNode.setType(TreeNode.TYPE_FOLDER);
			rootNode.setFolder(true);
			rootNode.setTreeId(id);
			
			if( uploaded && (uploadType.equals(".zip"))){
				// TODO zip 파일 푼 경로를 트리로 만들어서 뿌리기
				rootNode.setName(fileName);
				rootNode.setPath(uploadPath);
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
	}
}
