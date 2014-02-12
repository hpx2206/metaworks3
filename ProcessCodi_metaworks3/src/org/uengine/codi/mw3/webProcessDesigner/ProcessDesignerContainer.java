package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Id;
import org.uengine.contexts.ComplexType;
import org.uengine.kernel.Activity;
import org.uengine.kernel.ParameterContext;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.ReceiveActivity;
import org.uengine.kernel.Role;
import org.uengine.kernel.ScopeActivity;
import org.uengine.kernel.ValueChain;
import org.uengine.kernel.ValueChainDefinition;
import org.uengine.kernel.designer.web.ActivityView;
import org.uengine.kernel.designer.web.ValueChainView;
import org.uengine.kernel.graph.Transition;

public class ProcessDesignerContainer {
	String editorId;
	@Id
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
	RolePanel	rolePanel;
		public RolePanel getRolePanel() {
			return rolePanel;
		}
		public void setRolePanel(RolePanel rolePanel) {
			this.rolePanel = rolePanel;
		}
	ProcessVariablePanel processVariablePanel;
		public ProcessVariablePanel getProcessVariablePanel() {
			return processVariablePanel;
		}
		public void setProcessVariablePanel(ProcessVariablePanel processVariablePanel) {
			this.processVariablePanel = processVariablePanel;
		}
	
	String viewType;
		public String getViewType() {
			return viewType;
		}
		public void setViewType(String viewType) {
			this.viewType = viewType;
		}
	String lastTracingTag;
		public String getLastTracingTag() {
			return lastTracingTag;
		}
		public void setLastTracingTag(String lastTracingTag) {
			this.lastTracingTag = lastTracingTag;
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
		transitionList = new ArrayList<Transition>();
		valueChainList = new ArrayList<ValueChain>();
		roleList = new ArrayList<Role>();
		
		rolePanel = new RolePanel();
		rolePanel.getMetaworksContext().setHow("menu");
		rolePanel.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		processVariablePanel = new ProcessVariablePanel();
		processVariablePanel.getMetaworksContext().setHow("menu");
		processVariablePanel.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
	}
	
	public void load(ProcessDefinition def) throws Exception{
		
		rolePanel.setEditorId(editorId);
		processVariablePanel.setEditorId(editorId);
		
		int maxX = 0;
		int maxY = 0;
		int tagCnt = 0;
		for (int l = 0; l < def.getChildActivities().size(); l++) {
			Activity activity = (Activity)def.getChildActivities().get(l);
			tagCnt = this.loadActivity(tagCnt, activity);
		}
		lastTracingTag = String.valueOf(tagCnt + 1);
		
		this.setMaxX(maxX);
		this.setMaxY(maxY);
		transitionList.addAll(def.getTransitions());
		for(Transition ts : transitionList){
			ts.getTransitionView().setViewType(viewType);
			ts.getTransitionView().setEditorId(getEditorId());
			ts.getTransitionView().setTransition(ts);
		}
		Role[] roles = def.getRoles();
		if( roles != null && roles.length > 0){
			for(Role role : roles){
				if( "Initiator".equals(role.getName()) ){
					continue;
				}
				
				if( role.getRoleView() != null){
					role.getRoleView().setViewType(viewType);
					role.getRoleView().setEditorId(getEditorId());
					role.getRoleView().setRole(role);
					
					roleList.add(role);
				}
				role.setMetaworksContext(new MetaworksContext());
				role.getMetaworksContext().setHow("menu");
				role.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
				rolePanel.getRoleList().add(role);	// rolePanel 은 화면상에 롤 변수를 담아 놓기 위한 변수
			}
		}
		
		ArrayList<ProcessVariable> pvList = processVariablePanel.getVariableList();
		ProcessVariable[] processVariables = def.getProcessVariables();
		for(int i=0; i < processVariables.length; i++){
			ProcessVariable processVariable = processVariables[i];
			processVariable.setMetaworksContext(new MetaworksContext());
			processVariable.getMetaworksContext().setHow("menu");
			processVariable.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			pvList.add(this.ignoreVariableType(processVariable));
		}
		processVariablePanel.setVariableList(pvList);
	}
	
	public int loadActivity(int lastTagcount , Activity activity){
		
		activity = this.ignoreVariableType(activity);
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
		if( Integer.parseInt(activity.getTracingTag()) > lastTagcount )
			lastTagcount = Integer.parseInt(activity.getTracingTag());
		
		activityList.add(activity);
		
		if( activity instanceof ScopeActivity){
			ArrayList<Activity> childActivities = ((ScopeActivity) activity).getChildActivities();
			if( childActivities != null){
				for( int i=0; i < childActivities.size(); i++){
					lastTagcount = loadActivity(lastTagcount , childActivities.get(i));
				}
			}
			ArrayList<Transition> childTransitions = ((ScopeActivity) activity).getTransitions();
			if( childTransitions != null){
				transitionList.addAll(childTransitions);
			}
		}
		return lastTagcount;
	}
	
	public ProcessDefinition containerToDefinition(ProcessDesignerContainer container) throws Exception{
		ProcessDefinition def = new ProcessDefinition();
		Role[] roles = new Role[1];
		// default role
		Role initiator = new Role();
		initiator.setName("Initiator");
		roles[0] = initiator;
		def.setRoles(roles);
		if( activityList != null ){
			for(Activity act : activityList){
				def.addChildActivity(this.fillVariableType(act));
				if( act instanceof ScopeActivity){
					if( ((ScopeActivity) act).getTransitions() != null ){
						for(Transition ts : ((ScopeActivity) act).getTransitions()){
							if(transitionList.contains(ts)){
								transitionList.remove(ts);
							}
						}
					}
				}
			}
		}
		if( roleList != null ){
			for(Role role : roleList){
				def.addRole(role);
			}
		}
		if( transitionList != null ){
			for(Transition ts : transitionList){
				def.addTransition(ts);
			}
		}
		
		if( processVariablePanel.getVariableList() != null ){
			ArrayList<ProcessVariable> pvList = processVariablePanel.getVariableList();
			ProcessVariable pvs[] = new ProcessVariable[pvList.size()];
			for(int i=0; i < pvList.size(); i++){
				pvs[i] = this.fillVariableType(pvList.get(i));
			}
			def.setProcessVariables(pvs);
		}
		
		return def;
	}
	
	public void loadValueChain(ValueChainDefinition def) throws Exception{
		
		rolePanel.setEditorId(editorId);
		processVariablePanel.setEditorId(editorId);
		
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
	
	public Activity ignoreVariableType(Activity activity){
		Class paramClass = activity.getClass();
		boolean isReceiveActivity = ReceiveActivity.class.isAssignableFrom(paramClass);
		if( isReceiveActivity ){
			ParameterContext[] contexts = ((ReceiveActivity)activity).getParameters();
			if( contexts != null && contexts.length > 0){
				for(int i=0; i < contexts.length; i++){
					ProcessVariable processVariable = contexts[i].getVariable();
					processVariable.setMetaworksContext(new MetaworksContext());
					processVariable.getMetaworksContext().setHow("activity");
					this.ignoreVariableType(processVariable);
				}
			}
		}
		return activity;
	}
	
	public ProcessVariable ignoreVariableType(ProcessVariable processVariable){
		if( processVariable.getTypeInputter() == null){
			processVariable.setTypeInputter(processVariable.getType().getName());
		}
		processVariable.setType(null);
		if( processVariable.getDefaultValue() != null ){
			processVariable.setTypeInputter(processVariable.getDefaultValue().getClass().getName());
			if( processVariable.getDefaultValue() instanceof ComplexType ){
			ComplexType complexType = (ComplexType)processVariable.getDefaultValue();
			complexType.setDesignerMode(true);
			}
		}
		
		return processVariable;
	}
	
	public Activity fillVariableType(Activity activity) throws Exception{
		Class paramClass = activity.getClass();
		boolean isReceiveActivity = ReceiveActivity.class.isAssignableFrom(paramClass);
		if( isReceiveActivity ){
			ParameterContext[] contexts = ((ReceiveActivity)activity).getParameters();
			if( contexts != null && contexts.length > 0){
				for(int i=0; i < contexts.length; i++){
					contexts[i].setVariable(this.fillVariableType(contexts[i].getVariable()));
				}
			}
			((ReceiveActivity)activity).setParameters(contexts);
		}
		return activity;
	}
	
	public ProcessVariable fillVariableType(ProcessVariable processVariable) throws Exception{
		if( processVariable.getDefaultValue() != null && processVariable.getDefaultValue() instanceof ComplexType ){
			processVariable.setType(ComplexType.class);
		}else if(processVariable.getTypeInputter() != null ){
			processVariable.setType( Class.forName(processVariable.getTypeInputter()) );
		}
		return processVariable;
	}
}
