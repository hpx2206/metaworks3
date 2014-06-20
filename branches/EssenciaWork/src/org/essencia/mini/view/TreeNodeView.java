package org.essencia.mini.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.essencia.mini.kernel.Practice;
import org.essencia.model.EditorWindow;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;

import com.thoughtworks.xstream.XStream;

@Face(ejsPath = "dwr/metaworks/org/metaworks/component/TreeNode.ejs")
public class TreeNodeView extends TreeNode{
	
	List<Practice> practiceList;
		public List<Practice> getPracticeList() {
			return practiceList;
		}
		public void setPracticeList(List<Practice> practiceList) {
			this.practiceList = practiceList;
		}
		
	private String path;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		
	public TreeNodeView(){
		
	}
	
	@Override
	@ServiceMethod(callByContent=true, except="child", target=ServiceMethodContext.TARGET_APPEND)
	public Object expand() throws Exception {
		return new ToAppend(this, this.loadChild());
	}
	
	public List<TreeNode> loadChild() throws Exception { 
		List<TreeNode> child = null;
		if("PRACTICES".equals(getName())){
			importPractices();
			child = transferPracticeToTreeNode(getPracticeList());
		} else {
			child = getChild();	
		}
		return child;
	}
	
	@ServiceMethod(payload={"id", "name", "folder"}, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] action() throws Exception {
		EditorWindow editorWindow = new EditorWindow();
		editorWindow.setPanel(this);
		return new Object[]{new ToAppend(editorWindow,true), new Refresh(editorWindow)};
	}	
	
	
//	@ServiceMethod(payload={"id", "name", "path", "folder", "projectId", "type"}, mouseBinding="right", target=ServiceMethodContext.TARGET_POPUP)
//	public Object[] showContextMenu() {
//		
//		return new Object[]{new ResourceContextMenu(this)};
//	}
	
	/**
	 * TreeDataSource를 받아서 TreeNode로 변환해주는 method by IK
	 * @param tds
	 * @return TreeNode
	 */
	public TreeNode makeTreeNode(TreeDataSource tds){
		
		TreeNode treeNode = new TreeNodeView();
		treeNode.setId(tds.getId());
		treeNode.setName(tds.getName());
		treeNode.setParentId(tds.getParentId());
		
		if( tds.getChildList() != null && tds.getChildList().size() > 0 ){
			treeNode.setFolder(true);
			ArrayList<TreeNode> childNode = new ArrayList<TreeNode>();
			for(TreeDataSource ds : tds.getChildList()){
				childNode.add(makeTreeNode(ds));
			}
			treeNode.setChild(childNode);
		}
		
		return treeNode;
	}
	
	/**
	 * List<Practice>를 받아서 List<TreeNode>로 변환해주는 method by IK
	 * @param List<Practice>
	 * @return List<TreeNode>
	 */
	public List<TreeNode> transferPracticeToTreeNode(List<Practice> practices){
		List<TreeNode> rtnList = new ArrayList<TreeNode>();
		for(Practice practice : practices){
			TreeNode treeNodes = new TreeNodeView();
			treeNodes.setId(practice.getId());
			treeNodes.setName(practice.getName());
			treeNodes.setFolder(true);
			TreeNode alphas =  makeTreeNode(DataSourceFactory.getTreeDataSource(practice.getAlphas().get(0)));
			TreeNode activities =  makeTreeNode(DataSourceFactory.getTreeDataSource(practice.getActivities().get(0)));
			TreeNode competencies =  makeTreeNode(DataSourceFactory.getTreeDataSource(practice.getCompetencies().get(0)));
			treeNodes.getChild().add(alphas);
			treeNodes.getChild().add(activities);
			treeNodes.getChild().add(competencies);
			rtnList.add(treeNodes);
		}
		return rtnList;
	}

	/**
	 * testPractices package에 있는 테스트용 xml 파일을 읽어오는 method by IK
	 */
	public void importPractices(){
		setPracticeList(new ArrayList<Practice>());
		try {
			FileInputStream fis;
			InputStreamReader isr;
			XStream x;
			URL url = TreeNodeView.class.getClassLoader().getResource("org/essencia/mini/view/testPractices");
			File file = new File(url.getPath());
			File[] files = file.listFiles();
			for(File f : files){
				fis = new FileInputStream(f);
				isr = new InputStreamReader(fis, "UTF-8");
				x = new XStream();
				getPracticeList().add((Practice)x.fromXML(isr));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
