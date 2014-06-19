package org.essencia.mini.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.essencia.ide.ResourceContextMenu;
import org.essencia.model.EditorWindow;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;

@Face(ejsPath = "dwr/metaworks/org/metaworks/component/TreeNode.ejs")
public class ComponentTreeNode extends TreeNode{
	Practice practice;
		public Practice getPractice() {
			return practice;
		}
		public void setPractice(Practice practice) {
			this.practice = practice;
		}
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
	String path;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		
	@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_APPEND)
	public Object expand() throws Exception { 
		ArrayList<TreeNode> rtn = null;
		
		File file =  new File("D:/codi/essencia/");
		
		String[] childFilePaths = file.list();
		
		for(int i = 0; i < childFilePaths.length; i++){
			rtn = new ArrayList<TreeNode>();
			TreeNode componentTree = new ComponentTreeNode();
			componentTree.setId(practice.getId());
			componentTree.setName(practice.getName());
			TreeNode a = transfer(practice.getAlphas().get(0));
			TreeNode b = transfer(practice.getAlphas().get(1));
			componentTree.getChild().add(a);
			componentTree.getChild().add(b);
			rtn.add(componentTree);
		}
		
//		if("".equals(getId())){
//			rtn = new ArrayList<TreeNode>();
//			TreeNode componentTree = new ComponentTreeNode();
//			componentTree.setId(practice.getId());
//			componentTree.setName(practice.getName());
//			TreeNode a = transfer(practice.getAlphas().get(0));
//			TreeNode b = transfer(practice.getAlphas().get(1));
//			componentTree.getChild().add(a);
//			componentTree.getChild().add(b);
//			rtn.add(componentTree);
//		}else{
//			rtn = getChild();
//		}
		return rtn;
	}

	
	@ServiceMethod(payload={"id", "name", "folder"}, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] action() throws Exception {
		EditorWindow editorWindow = new EditorWindow();
//		if("Practice".equals(this.getType())){
			Component c = new Alpha();
			c.setId("11111111111111111");
			c.setName("mynameiscomponent");
			c.setDescription("thisisdescription");
			
			editorWindow.setPanel(c);
//		}
		return new Object[]{new ToAppend(editorWindow,true), new Refresh(editorWindow)};
	}	
	public TreeNode transfer(TreeNodable treeNodableObj){
		TreeNode treeNode = new ComponentTreeNode();
		treeNode.setId(treeNodableObj.getId());
		treeNode.setName(treeNodableObj.getName());
		treeNode.setParentId(treeNodableObj.getParentId());
		
		if(treeNodableObj.getChildList() != null ){
			treeNode.setFolder(true);
			ArrayList<TreeNode> childNode = null;
			for(TreeNodable c : treeNodableObj.getChildList()){
				childNode = new ArrayList<TreeNode>();
				childNode.add(transfer(c));
				treeNode.setChild(childNode);
			}
		}
		
		return treeNode;
	}
	
	public List<TreeNode> logic(Practice practice){
		List<TreeNode> rtnList = new ArrayList<TreeNode>();
		TreeNode componentTree = new ComponentTreeNode();
		componentTree.setId(practice.getId());
		componentTree.setName(practice.getName());
		TreeNode a = transfer(practice.getAlphas().get(0));
		TreeNode b = transfer(practice.getAlphas().get(1));
		componentTree.getChild().add(a);
		componentTree.getChild().add(b);
		rtnList.add(componentTree);
		return rtnList;
	
	}
	
	@ServiceMethod(payload={"id", "name", "path", "folder", "projectId", "type"}, mouseBinding="right", target=ServiceMethodContext.TARGET_POPUP)
	public Object[] showContextMenu() {
		
		return new Object[]{new ResourceContextMenu(this)};
	}



	public ComponentTreeNode(){
		practice = new Practice();
		practice.setId("p1");
		practice.setName("Scrum");
		practice.setDescription("describe about scrum");
		
		Component alpha3 = new Alpha();
		alpha3.setId("alpha3");
		alpha3.setName("Opportunity3");
		alpha3.setType("Practice");
		alpha3.setDescription("describe about Opportunity3");
		
		Component alpha1 = new Alpha();
		alpha1.setId("alpha1");
		alpha1.setName("Opportunity1");
		alpha1.setDescription("describe about Opportunity1");
		alpha1.setParentComponentId(practice.getId());
		alpha1.getChildComponents().add(alpha3);
		
		Component alpha2 = new Alpha();
		alpha2.setId("alpha2");
		alpha2.setName("Opportunity2");
		alpha2.setDescription("describe about Opportunity2");
		alpha2.setParentComponentId(practice.getId());
		
		
		Component activity1 = new Activity();
		activity1.setId("act1");
		activity1.setName("ActName1");
		Component activity2 = new Activity();
		activity2.setId("act2");
		activity2.setName("ActName2");
		Component activity3 = new Activity();
		activity3.setId("act3");
		activity3.setName("ActName3");
		Component competency1 = new Competency();
		Component competency2 = new Competency();
		Component competency3 = new Competency();
		
		practice.getAlphas().add(alpha1);
		practice.getAlphas().add(alpha2);
		practice.getActivities().add(activity1);
		practice.getActivities().add(activity2);
		practice.getActivities().add(activity3);
	}
	
	
}
