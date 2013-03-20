package org.uengine.codi.mw3.webProcessDesigner;

public class ConditionExPressionPanel {

	ConditionTreeNodeView	conditionTreeNode;
		public ConditionTreeNodeView getConditionTreeNode() {
			return conditionTreeNode;
		}
		public void setConditionTreeNode(ConditionTreeNodeView conditionTreeNode) {
			this.conditionTreeNode = conditionTreeNode;
		}
		
	public ConditionExPressionPanel(){
		conditionTreeNode = new ConditionTreeNodeView();
	}
}
