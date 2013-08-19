package org.uengine.kernel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import org.uengine.kernel.graph.Transition;

public class ValueChainDefinition implements Serializable{
	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;

	/* when : edit, view, tree */
	
	transient Hashtable<String, ValueChain> wholeChildValueChains;

	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	long valueChainSequence;
		public long getNextValueChainSequence(){
			return (++valueChainSequence);
		}
		public long getValueChainSequence() {
			return valueChainSequence;
		}
		public void setValueChainSequence(long valueChainSequence) {
			this.valueChainSequence = valueChainSequence;
		}

	private Vector<ValueChain> childValueChains;
		public Vector<ValueChain> getChildValueChains() {
			return childValueChains;
		}
		public void setChildValueChains(Vector<ValueChain> childValueChains) {
			this.childValueChains = childValueChains;
		}
		
	private ArrayList<Transition> transitions;
		public ArrayList<Transition> getTransitions() {
			if (this.transitions == null) {
				this.setTransitions(new ArrayList<Transition>());
			}
			return transitions;
		}
		private void setTransitions(ArrayList<Transition> transitions) {
			this.transitions = transitions;
		}
		public void addTransition(Transition trasition) {
			this.getTransitions().add(trasition);
		}
		
	public synchronized void addChildValueChain(ValueChain valueChain){
		addChildValueChain(valueChain, true);
	}
	
	public ValueChainDefinition() {
		//When you Need Class Like ActivityRepository Then Make it
		childValueChains = new Vector<ValueChain>();
		transitions = new ArrayList<Transition>();
	}
	
	public synchronized void addChildValueChain(ValueChain valueChain, boolean autoTagging){
		if(autoTagging){
			autoTag(valueChain);
		}
		
		this.childValueChains.add(valueChain);
		
		registerValueChain(valueChain, autoTagging);
	}
	
	protected void autoTag(ValueChain valueChain){
		if(valueChain.getTracingTag() == null){
			valueChain.setTracingTag(""+getNextValueChainSequence());
		}
	}
		
	protected boolean registerValueChain(ValueChain valueChain, boolean autoTagging){
		return registerValueChain(valueChain, autoTagging, false);
	}

	protected boolean registerValueChain(ValueChain valueChain, boolean autoTagging, boolean checkCollision) {
		
		if(wholeChildValueChains==null) wholeChildValueChains = new Hashtable<String, ValueChain>();
		
		String tracingTagOfValueChain = valueChain.getTracingTag();
		if(tracingTagOfValueChain == null){
			if(autoTagging){
				valueChain.setTracingTag(""+getNextValueChainSequence());
			}else
				throw new RuntimeException(new UEngineException("This definition is corrupt. One of child activity's tracingtag is null."));
		}
		
		if(checkCollision && wholeChildValueChains.containsKey(valueChain.getTracingTag())){
			return false;
		}
		
		wholeChildValueChains.put(valueChain.getTracingTag(), valueChain);
		
		return false;
	}
	
	//FIXME : Copied Code from ProcessDefinition
	String processDesignerInstanceId;
		public String getProcessDesignerInstanceId() {
			return processDesignerInstanceId;
		}
		public void setProcessDesignerInstanceId(String processDesignerInstanceId) {
			this.processDesignerInstanceId = processDesignerInstanceId;
		}
	
	//FIXME : Copied Code from ProcessDefinition
	String processDesignerSize;
		public String getProcessDesignerSize() {
			return processDesignerSize;
		}
		public void setProcessDesignerSize(String processDesignerSize) {
			this.processDesignerSize = processDesignerSize;
		}
}
