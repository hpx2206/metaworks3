package org.essencia.mini.test;

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
	
	
	@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_APPEND)
	public Object expand() throws Exception { 
		List<TreeNode> rtn = null;
		if("".equals(getId())){
			rtn = logic(practice);
		}else{
			rtn = getChild();
		}
		return rtn;
	}

	
	@ServiceMethod(payload={"id", "name", "folder"}, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] action() throws Exception {
		Component c = new Alpha();
		c.setId("11111111111111111");
		c.setName("mynameiscomponent");
		c.setDescription("thisisdescription");
		
		EditorWindow editorWindow = new EditorWindow();
		editorWindow.setPanel(c);
		return new Object[]{new ToAppend(editorWindow,true), new Refresh(editorWindow)};
	}	
	public TreeNode transfer(Component component){
		TreeNode t = new ComponentTreeNode();
		t.setId(component.getId());
		t.setName(component.getName());
		t.setParentId(component.getParentComponentId());
		if(component.getChildComponents().size()>0){
			t.setFolder(true);
		}
		ArrayList<TreeNode> childNode = null;
		if(component.getChildComponents().size()!=0){
			childNode = new ArrayList<TreeNode>();
			for(Component c : component.getChildComponents()){
				childNode.add(transfer(c));
			}
			t.setChild(childNode);
		}
		return t;
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
