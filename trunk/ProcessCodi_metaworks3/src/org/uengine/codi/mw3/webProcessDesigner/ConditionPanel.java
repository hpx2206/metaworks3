package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.Choice;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;

public class ConditionPanel  implements ContextAware{
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	String conditionLabel;
		public String getConditionLabel() {
			return conditionLabel;
		}
		public void setConditionLabel(String conditionLabel) {
			this.conditionLabel = conditionLabel;
		}
		
	String conditionId;
		public String getConditionId() {
			return conditionId;
		}
		public void setConditionId(String conditionId) {
			this.conditionId = conditionId;
		}

	public ArrayList<ConditionNode>	 conditionNodes;
		public ArrayList<ConditionNode> getConditionNodes() {
			return conditionNodes;
		}
		public void setConditionNodes(ArrayList<ConditionNode> conditionNodes) {
			this.conditionNodes = conditionNodes;
		}

	String dragClassName;
		public String getDragClassName() {
			return dragClassName;
		}
		public void setDragClassName(String dragClassName) {
			this.dragClassName = dragClassName;
		}
		
	String valiableString;
		public String getValiableString() {
			return valiableString;
		}
		public void setValiableString(String valiableString) {
			this.valiableString = valiableString;
		}	
		
	public ConditionPanel() throws Exception{
			this("");
	}
	public ConditionPanel(String conditionLabel) throws Exception{
		setConditionLabel(conditionLabel);
	}
	public void load()  throws Exception{
		conditionNodes = new ArrayList<ConditionNode>();
		ConditionNode conditionNode = new ConditionNode();
		conditionNode.init(valiableString);
		conditionNodes.add(conditionNode);
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] saveCondition() throws Exception{
		if( conditionNodes != null && conditionNodes.size() > 0){
			for (Iterator<ConditionNode> iterator = conditionNodes.iterator() ; iterator.hasNext(); ) {
				ConditionNode conditionNode = (ConditionNode)iterator.next();
				System.out.println("getValiableChoice = " + conditionNode.getValiableChoice().getSelected());
				System.out.println("getSignChoice = " + conditionNode.getSignChoice().getSelected());
				System.out.println("getExpressionChoice = " + conditionNode.getExpressionChoice().getSelected());
				System.out.println("getOperandChoice = " + conditionNode.getOperandChoice().getSelected());
			}
		}
		System.out.println("conditionLabel = " + conditionLabel);
		LineShape lineShape = new LineShape();
		lineShape.setId(this.getConditionId());
		lineShape.setLabel(this.getConditionLabel());
		
		return new Object[]{ new Remover(new ModalWindow()), lineShape};
	}
	
	@ServiceMethod(callByContent=true)
	public Object[] addConditionNode() throws Exception{
		ConditionNode newNode = new ConditionNode();
		newNode.init(valiableString);
		conditionNodes.add(newNode);
		return new Object[]{conditionNodes};
	}

	@AutowiredFromClient
	public Session session;
	
	@AutowiredFromClient
	public ProcessDesignerWebContentPanel processDesignerWebContentPanel;
}
