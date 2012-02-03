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
import org.uengine.kernel.AssignActivity;
import org.uengine.kernel.Condition;
import org.uengine.kernel.DefaultActivity;
import org.uengine.kernel.Evaluate;
import org.uengine.kernel.Otherwise;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.SwitchActivity;

public class Node implements ContextAware{

	private String name;
	private Node parent;
	private List<Node> child;
	private String condition;
	private boolean isMatch;
	private boolean isRoot;
	private Object value;
	
	private int nodeId;
	
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	@Id
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public List<Node> getChild() {
		return child;
	}
	public void setChild(List<Node> child) {
		this.child = child;
	}
	
	@Range(options={">","<","="}, values={">","<","=="})
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
//	
//	public boolean isMatch() {
//		return isMatch;
//	}
//	public void setMatch(boolean isMatch) {
//		this.isMatch = isMatch;
//	}
//	
	public boolean isRoot() {
		return isRoot;
	}
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	public Node() {
		this.child = new ArrayList<Node>();
		this.isRoot = false;
	}
	
	public Node(String name) {
		this.name = name;
		this.isRoot = false;
		this.child = new ArrayList<Node>();
		
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	}
	
	public Node(String name,boolean isRoot) {
		this.name = name;
		this.isRoot = isRoot;
		this.child = new ArrayList<Node>();
		
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
	}
	
	public Node(String name,int nodeId) {
		this.name = name;
		this.nodeId = nodeId;
		
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
	} 

	public void addChildNode(Node newNode) {
		this.child.add(newNode);
	}	
	
	@ServiceMethod(when="new", callByContent=true)
	public void save() {
		getMetaworksContext().setWhen("view");
	}
	
	@ServiceMethod(callByContent=true)//where="parent")
	public void addChildNode(){
		
		newChildNode = new Node("True Node" + this.nodeId);
		
		newChildNode.setMetaworksContext(new MetaworksContext());
		newChildNode.getMetaworksContext().setWhen("new");
		newChildNode.setValue("");
		
		addChildNode(newChildNode);
		
		newChildNode = new Node("False Node" + this.nodeId);
		
		newChildNode.setMetaworksContext(new MetaworksContext());
		newChildNode.getMetaworksContext().setWhen("new");
		newChildNode.setValue("");
		
		addChildNode(newChildNode);
		
	}
	
	@ServiceMethod
	public void conditionsRefresh() {
		System.out.println("conditionsRefresh()");
	}
	
	transient Node newChildNode;
		
		public Node getNewChildNode() {
			return newChildNode;
		}
		public void setNewChildNode(Node newChildNode) {
			this.newChildNode = newChildNode;
		}
		
	public void execute() {
		
		System.out.println(getName() + " Node execute()");
		
		if(this.child.size() > 0) {
			if(!isRoot) {
				
				ScriptEngineManager sem = new ScriptEngineManager();
				ScriptEngine jsEngine = sem.getEngineByName("JavaScript");
				
				try {
					isMatch = (Boolean) jsEngine.eval(this.getCondition());
					System.out.println("RESULT=" + isMatch);
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(isMatch) {
					this.child.get(0).execute();
				} else {
					this.child.get(1).execute();
				}
			}
			
		}
		
	}
	
	SwitchActivity createSwitchActivity(){
		SwitchActivity act = new SwitchActivity();
		act.setConditions(new Condition[]{new Evaluate(ProcessVariable.forName(getName()), getCondition(), getValue()),new Otherwise()});
		
		List<Node> childs = getChild();
		
		// Node 유형별 분기 
		if(childs != null)
			for(Node child : childs){
				act.addChildActivity(child.createSwitchActivity());
			}
		else{
			AssignActivity aa = new AssignActivity();
			aa.setVariable(ProcessVariable.forName("보험료"));
			aa.setVal(100);
			
			act.addChildActivity(aa);

			aa = new AssignActivity();
			aa.setVariable(ProcessVariable.forName("보험료"));
			aa.setVal(50);

			act.addChildActivity(aa);
		}
		
		return act;
	}
	
	
	MetaworksContext metaworksContext;


		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
}
