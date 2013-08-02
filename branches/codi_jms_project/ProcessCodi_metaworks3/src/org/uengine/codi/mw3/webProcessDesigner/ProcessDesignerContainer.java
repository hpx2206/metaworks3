package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.uengine.kernel.Activity;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.Role;
import org.uengine.kernel.graph.Transition;

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
}
