package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.component.TreeNode;

public class ConditionExPressionPanel {

	ConditionTreeNode	conditionTreeNode;
		public ConditionTreeNode getConditionTreeNode() {
			return conditionTreeNode;
		}
		public void setConditionTreeNode(ConditionTreeNode conditionTreeNode) {
			this.conditionTreeNode = conditionTreeNode;
		}
		
	public ArrayList<Role>	 roleList;
		public ArrayList<Role> getRoleList() {
			return roleList;
		}
		public void setRoleList(ArrayList<Role> roleList) {
			this.roleList = roleList;
		}
	public ArrayList<PrcsValiable>	 prcsValiableList;
		public ArrayList<PrcsValiable> getPrcsValiableList() {
			return prcsValiableList;
		}
		public void setPrcsValiableList(ArrayList<PrcsValiable> prcsValiableList) {
			this.prcsValiableList = prcsValiableList;
		}
		
	public void init() throws Exception{
		ConditionTreeNode conditionTreeNode = new ConditionTreeNode();
		conditionTreeNode.setMetaworksContext(new MetaworksContext());
		conditionTreeNode.getMetaworksContext().setHow("condition");
		
		conditionTreeNode.setParentId("rootNode");
		conditionTreeNode.setType(TreeNode.TYPE_FILE_CODE);
		
		conditionTreeNode.setRoleList(roleList);
		conditionTreeNode.setPrcsValiableList(prcsValiableList);
		conditionTreeNode.conditionInit();
		
		setConditionTreeNode(conditionTreeNode);
	}
}
