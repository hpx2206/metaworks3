package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.uengine.kernel.Activity;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.Role;
import org.uengine.kernel.graph.Transition;

public class ProcessDesignerContainer {
	
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
		
	public ProcessDesignerContainer(){
		activityList = new ArrayList<Activity>();
		roleList = new ArrayList<Role>();
		transitionList = new ArrayList<Transition>();
	}
	
	public void load(ProcessDefinition def) throws Exception{

		for (int l = 0; l < def.getChildActivities().size(); l++) {
			activityList.add((Activity)def.getChildActivities().get(l));
		}
		transitionList = def.getTransitions();
//		def.getRoles();
	}
	
	public ProcessDefinition containerToDefinition(ProcessDesignerContainer container){
		ProcessDefinition def = new ProcessDefinition();
		
//		def.setRoles(roles);
//		def.setChildActivities(ac);
		
		return def;
	}
}
