package org.uengine.codi.mw3.model;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.kernel.Activity;
import org.uengine.kernel.AssignActivity;
import org.uengine.kernel.Condition;
import org.uengine.kernel.DefaultActivity;
import org.uengine.kernel.Evaluate;
import org.uengine.kernel.Otherwise;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.SwitchActivity;

public class DecisionTreeNode implements ContextAware{

	String name;
	
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	private DecisionTreeNode parent;
		public DecisionTreeNode getParent() {
			return parent;
		}
		public void setParent(DecisionTreeNode parent) {
			this.parent = parent;
		}
		
	private List<DecisionTreeNode> child;
		public List<DecisionTreeNode> getChild() {
			return child;
		}
		public void setChild(List<DecisionTreeNode> child) {
			this.child = child;
		}
		
	private String condition;
		@Range(options={">","<","="}, values={">","<","=="})
		public String getCondition() {
			return condition;
		}
		public void setCondition(String condition) {
			this.condition = condition;
		}

	private boolean isRoot;
		public boolean isRoot() {
			return isRoot;
		}
		public void setRoot(boolean isRoot) {
			this.isRoot = isRoot;
		}
	
	private Object value;
		public Object getValue() {
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
		}
	
	private int nodeId;
	@Id
		public int getNodeId() {
			return nodeId;
		}
		public void setNodeId(int nodeId) {
			this.nodeId = nodeId;
		}
		
	
	
	
	

	
	public DecisionTreeNode() {
		this.isRoot = false;
		this.child = new ArrayList<DecisionTreeNode>();
		
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	

	public void addChildNode(DecisionTreeNode newNode) {
		this.child.add(newNode);
	}	
	
	@ServiceMethod(when="new", callByContent=true)
	public void save() {
		getMetaworksContext().setWhen("view");
	}
	
	@ServiceMethod(callByContent=true)//where="parent")
	public void addChildNode(){
		
		newChildNode = new DecisionTreeNode();
		
		newChildNode.setMetaworksContext(new MetaworksContext());
		newChildNode.getMetaworksContext().setWhen("new");
		newChildNode.setValue("");
		
		addChildNode(newChildNode);
		
		newChildNode = new DecisionTreeNode();
		
		newChildNode.setMetaworksContext(new MetaworksContext());
		newChildNode.getMetaworksContext().setWhen("new");
		newChildNode.setValue("");
		
		addChildNode(newChildNode);
		
	}
	
	@ServiceMethod
	public void conditionsRefresh() {
		System.out.println("conditionsRefresh()");
	}
	
	transient DecisionTreeNode newChildNode;
		
		public DecisionTreeNode getNewChildNode() {
			return newChildNode;
		}
		public void setNewChildNode(DecisionTreeNode newChildNode) {
			this.newChildNode = newChildNode;
		}
		
	public void execute() {
		
		System.out.println(getName() + " Node execute()");
		
		if(this.child.size() > 0) {
			if(!isRoot) {
				
				ScriptEngineManager sem = new ScriptEngineManager();
				ScriptEngine jsEngine = sem.getEngineByName("JavaScript");
	
			}
			
		}
		
	}
	
	Activity createActivity(){
		List<DecisionTreeNode> childs = getChild();		
		
		// Node 유형별 분기 
		if(childs != null && childs.size() == 2){
			SwitchActivity act = new SwitchActivity();
			act.setConditions(new Condition[]{new Evaluate(ProcessVariable.forName(getName()), getCondition(), getValue()),new Otherwise()});
			
			for(DecisionTreeNode child : childs){
				act.addChildActivity(child.createActivity());
			}
			
			return act;
		}else{
			
			AssignActivity aa = new AssignActivity();
			aa.setVariable(ProcessVariable.forName(getName()));
			aa.setAssignStyle(3);
			aa.setAssignValue(getValue().toString());			
			
			return aa;
		}
		
	}
	
	
	MetaworksContext metaworksContext;


		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
}
