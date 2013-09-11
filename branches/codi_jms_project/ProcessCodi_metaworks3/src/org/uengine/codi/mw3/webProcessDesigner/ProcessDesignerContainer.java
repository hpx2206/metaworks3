package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.apache.velocity.runtime.directive.Parse;
import org.uengine.kernel.Activity;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.Role;
import org.uengine.kernel.ValueChain;
import org.uengine.kernel.ValueChainDefinition;
import org.uengine.kernel.designer.web.ActivityView;
import org.uengine.kernel.designer.web.ValueChainView;
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
	int maxX;
		public int getMaxX() {
			return maxX;
		}
		public void setMaxX(int maxX) {
			this.maxX = maxX;
		}
	int maxY;
		public int getMaxY() {
			return maxY;
		}
		public void setMaxY(int maxY) {
			this.maxY = maxY;
		}
	public ProcessDesignerContainer(){
		this.init();
	}
	
	public void init(){
		activityList = new ArrayList<Activity>();
		roleList = new ArrayList<Role>();
		transitionList = new ArrayList<Transition>();
		valueChainList = new ArrayList<ValueChain>();
	}
	
	public void load(ProcessDefinition def) throws Exception{
		int maxX = 0;
		int maxY = 0;
		for (int l = 0; l < def.getChildActivities().size(); l++) {
			Activity activity = (Activity)def.getChildActivities().get(l);
			ActivityView view = activity.getActivityView();
			if( view != null ){
				view.setViewType(viewType);
				view.setEditorId(getEditorId());
				view.setActivity(activity);
				// 엑티비티의 max 좌표 구하기
				int viewX = view.getX() != null ? Integer.parseInt(view.getX()) : 0 ;
				int viewY = view.getY() != null ? Integer.parseInt(view.getY()) : 0 ;
				int viewWidth = view.getWidth() != null ? Integer.parseInt(view.getWidth()) : 0 ;
				int viewHeight = view.getHeight() != null ? Integer.parseInt(view.getHeight()) : 0 ;
				if( viewX > maxX ){
					maxX = viewX + viewWidth;
				}
				if( viewY > maxY ){
					maxY = viewY + viewHeight;
				}
			}
			activityList.add(activity);
		}
		this.setMaxX(maxX);
		this.setMaxY(maxY);
		transitionList = def.getTransitions();
		for(Transition ts : transitionList){
			ts.getTransitionView().setViewType(viewType);
			ts.getTransitionView().setEditorId(getEditorId());
			ts.getTransitionView().setTransition(ts);
		}
		Role[] roles = def.getRoles();
		if( roles != null && roles.length > 0){
			for(Role role : roles){
				if( role.getRoleView() != null){
					role.getRoleView().setViewType(viewType);
					role.getRoleView().setEditorId(getEditorId());
					role.getRoleView().setRole(role);
					roleList.add(role);
				}
			}
		}
	}
	
	public ProcessDefinition containerToDefinition(ProcessDesignerContainer container){
		ProcessDefinition def = new ProcessDefinition();
		if( activityList != null ){
			for(Activity act : activityList){
				def.addChildActivity(act);
			}
		}
		if( roleList != null ){
			Role[] roles = new Role[1];
			// default role
			Role initiator = new Role();
			initiator.setName("Initiator");
			roles[0] = initiator;
			def.setRoles(roles);
			for(Role role : roleList){
				def.addRole(role);
			}
		}
		if( transitionList != null ){
			for(Transition ts : transitionList){
				def.addTransition(ts);
			}
		}
		
		return def;
	}
	
	public void loadValueChain(ValueChainDefinition def) throws Exception{
		int maxX = 0;
		int maxY = 0;
		if(valueChainList == null){
			valueChainList = new ArrayList<ValueChain>();
		}
		for (int l = 0; l < def.getChildValueChains().size(); l++) {
			ValueChain valueChain = (ValueChain)def.getChildValueChains().get(l);
			ValueChainView view = valueChain.getValueChainView();
			if( view != null ){
				view.setViewType(viewType);
				view.setEditorId(getEditorId());
				view.setValueChain(valueChain);
				// 엑티비티의 max 좌표 구하기
				int viewX = view.getX() != null ? Integer.parseInt(view.getX()) : 0 ;
				int viewY = view.getY() != null ? Integer.parseInt(view.getY()) : 0 ;
				int viewWidth = view.getWidth() != null ? Integer.parseInt(view.getWidth()) : 0 ;
				int viewHeight = view.getHeight() != null ? Integer.parseInt(view.getHeight()) : 0 ;
				if( viewX > maxX ){
					maxX = viewX + viewWidth;
				}
				if( viewY > maxY ){
					maxY = viewY + viewHeight;
				}
			}
			valueChainList.add(valueChain);
		}
		this.setMaxX(maxX);
		this.setMaxY(maxY);
		
		transitionList = def.getTransitions();
		for(Transition ts : transitionList){
			ts.getTransitionView().setViewType(viewType);
			ts.getTransitionView().setEditorId(getEditorId());
			ts.getTransitionView().setTransition(ts);
		}
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
