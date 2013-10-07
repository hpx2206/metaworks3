package org.uengine.kernel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import org.uengine.contexts.TextContext;
import org.uengine.kernel.graph.Transition;

public class ValueChainDefinition implements Serializable{
	private static final long serialVersionUID = org.uengine.kernel.GlobalContext.SERIALIZATION_UID;

	/* when : edit, view, tree */
	
	transient Hashtable<String, ValueChain> wholeChildValueChains;

	TextContext name;
		public TextContext getName() {
			return name;
		}
		public void setName(TextContext name) {
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
		this.childValueChains.add(valueChain);
	}
	
	public ValueChainDefinition() {
		//When you Need Class Like ActivityRepository Then Make it
		childValueChains = new Vector<ValueChain>();
		transitions = new ArrayList<Transition>();
	}
	
	//FIXME : Copied Code from ProcessDefinition
	String processDesignerInstanceId;
		public String getProcessDesignerInstanceId() {
			return processDesignerInstanceId;
		}
		public void setProcessDesignerInstanceId(String processDesignerInstanceId) {
			this.processDesignerInstanceId = processDesignerInstanceId;
		}
}
