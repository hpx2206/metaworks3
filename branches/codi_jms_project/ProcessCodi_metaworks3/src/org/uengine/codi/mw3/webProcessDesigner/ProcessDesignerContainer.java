package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.uengine.kernel.Activity;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.Role;
import org.uengine.kernel.ValueChain;
import org.uengine.kernel.ValueChainDefinition;
import org.uengine.kernel.graph.Transition;

import com.google.api.gbase.client.GmPublishingPriority.Value;

public class ProcessDesignerContainer {
	String editorId;
		public String getEditorId() {
			return editorId;
		}
		public void setEditorId(String editorId) {
			this.editorId = editorId;
		}
	ArrayList<Activity> activityList;
		public ArrayList<Activity> getActivityList() {
			return activityList;
		}
		public void setActivityList(ArrayList<Activity> activityList) {
			this.activityList = activityList;
		}
	ArrayList<ValueChain> valueChainList;
		public ArrayList<ValueChain> getValueChainList() {
			return valueChainList;
		}
		public void setValueChainList(ArrayList<ValueChain> valueChainList) {
			this.valueChainList = valueChainList;
		}
	ArrayList<Transition> transitionList;
		public ArrayList<Transition> getTransitionList() {
			return transitionList;
		}
		public void setTransitionList(ArrayList<Transition> transitionList) {
			this.transitionList = transitionList;
		}
	ArrayList<Role> roleList;
		public ArrayList<Role> getRoleList() {
			return roleList;
		}
		public void setRoleList(ArrayList<Role> roleList) {
			this.roleList = roleList;
		}
	ArrayList<ProcessVariable> variableList;
		public ArrayList<ProcessVariable> getVariableList() {
			return variableList;
		}
		public void setVariableList(ArrayList<ProcessVariable> variableList) {
			this.variableList = variableList;
		}
	String viewType;
		public String getViewType() {
			return viewType;
		}
		public void setViewType(String viewType) {
			this.viewType = viewType;
		}
	public ProcessDesignerContainer(){
		activityList = new ArrayList<Activity>();
		roleList = new ArrayList<Role>();
		transitionList = new ArrayList<Transition>();
	}
	
	public void loadValueChain(ValueChainDefinition def) throws Exception{
		if(valueChainList == null){
			valueChainList = new ArrayList<ValueChain>();
		}
		for (int l = 0; l < def.getChildValueChains().size(); l++) {
			ValueChain valueChain = (ValueChain)def.getChildValueChains().get(l);
			if( valueChain.getValueChainView() != null ){
				valueChain.getValueChainView().setViewType(viewType);
				valueChain.getValueChainView().setEditorId(getEditorId());
				valueChain.getValueChainView().setValueChain(valueChain);
			}
			valueChainList.add(valueChain);
		}
		transitionList = def.getTransitions();
		for(Transition ts : transitionList){
			ts.getTransitionView().setViewType(viewType);
			ts.getTransitionView().setEditorId(getEditorId());
			ts.getTransitionView().setTransition(ts);
		}
	}
	
	public void load(ProcessDefinition def) throws Exception{

		for (int l = 0; l < def.getChildActivities().size(); l++) {
			Activity activity = (Activity)def.getChildActivities().get(l);
			if( activity.getActivityView() != null ){
				activity.getActivityView().setViewType(viewType);
				activity.getActivityView().setEditorId(getEditorId());
				activity.getActivityView().setActivity(activity);
			}
			activityList.add(activity);
		}
		transitionList = def.getTransitions();
		for(Transition ts : transitionList){
			ts.getTransitionView().setViewType(viewType);
			ts.getTransitionView().setEditorId(getEditorId());
			ts.getTransitionView().setTransition(ts);
		}
	}
	
	public ProcessDefinition containerToDefinition(ProcessDesignerContainer container){
		ProcessDefinition def = new ProcessDefinition();
		if( activityList != null ){
			for(Activity act : activityList){
				def.addChildActivity(act);
			}
		}
		if( transitionList != null ){
			for(Transition ts : transitionList){
				def.addTransition(ts);
			}
		}
		
		return def;
	}
	
	public ValueChainDefinition containerToValueChainDefinition(ProcessDesignerContainer container){
		ValueChainDefinition def = new ValueChainDefinition();
		if( valueChainList != null ){
			for(ValueChain valueChain : valueChainList){
				def.addChildValueChain(valueChain);
			}
		}
		if( transitionList != null ){
			for(Transition ts : transitionList){
				def.addTransition(ts);
			}
		}
		return def;
	}
}
