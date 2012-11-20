package org.uengine.codi.mw3.model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.ITool;
import org.uengine.contexts.ComplexType;
import org.uengine.kernel.Activity;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.KeyedParameter;
import org.uengine.kernel.NeedArrangementToSerialize;
import org.uengine.kernel.ParameterContext;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.ResultPayload;
import org.uengine.kernel.RoleMapping;
import org.uengine.processmanager.ProcessManagerRemote;

//@Face(ejsPath="faces/org/metaworks/widget/Window.ejs", options={"hideLabels"}, values={"true"}, displayName="업무 처리 화면")
public class WorkItemHandler implements ContextAware{
	
	public WorkItemHandler(){} //to be spring bean without argument, this is required.
	
	public void load() throws Exception{
		
		Long instanceId = new Long(getInstanceId());
		Long taskId = getTaskId();
		String tracingTag = getTracingTag();
		
		
		instance = processManager.getProcessInstance(instanceId.toString());

		if(humanActivity==null && instanceId!=null && tracingTag!=null){
			humanActivity = (HumanActivity) instance.getProcessDefinition().getActivity(tracingTag);
		}
		
		if(humanActivity.getParameters()!=null){
			// load map for ITool
			loadMapForITool((Map<String, Object>)makeMapForITool());
			
			//creates work item handler
				parameters = new ParameterValue[humanActivity.getParameters().length];
				for(int i=0; i<humanActivity.getParameters().length; i++){
					ParameterContext pc = humanActivity.getParameters()[i];
					
					parameters[i] = new ParameterValue();
					
					ParameterValue pv = parameters[i];
					pv.setVariableName(pc.getVariable().getName());
					pv.setArgument(pc.getArgument().getText(session!=null && session.getEmployee()!=null ? session.getEmployee().getLocale() : null));
										
					String when = this.getMetaworksContext().getWhen();
					
					MetaworksContext mc = new MetaworksContext();
					
					if(MetaworksContext.WHEN_EDIT.equals(when)){
						if(ParameterContext.DIRECTION_IN.equals(pc.getDirection()))						
							when = MetaworksContext.WHEN_VIEW;
					}
					
					mc.setWhen(when);					
					pv.setMetaworksContext(mc);
					
					
					Serializable processVariableValue = pc.getVariable().get(instance, "");
					Class variableType = pc.getVariable().getType();
//				
//					if(variableType == String.class){
//						parameters[i].setValueString((String) processVariableValue);
////					}else if(Long.class.isAssignableFrom(variableType)){
////						parameters[i].setValueNumber((Number) processVariableValue);
////					}else if(Calendar.class.isAssignableFrom(variableType)){
////						parameters[i].setValueCalendar((Calendar) processVariableValue);
//					}else 
					
					if(variableType == ComplexType.class){
						if(processVariableValue instanceof ComplexType){
							ComplexType complexType = (ComplexType) processVariableValue;
							processVariableValue = (Serializable) complexType.getTypeClass().newInstance();
							
							if(processVariableValue instanceof ContextAware){
								((ContextAware)processVariableValue).setMetaworksContext(mc);
							}
							
							if(processVariableValue instanceof NeedArrangementToSerialize){
								((NeedArrangementToSerialize)processVariableValue).afterDeserialization();
							}
							
							if(processVariableValue instanceof ITool){
								((ITool)processVariableValue).onLoad();
							}
						} else if (processVariableValue instanceof ITool) {
							if(processVariableValue instanceof ContextAware){
								((ContextAware)processVariableValue).setMetaworksContext(mc);
							}
							
							// for completed ComplexType Object implements ITool
							((ITool) processVariableValue).onLoad();
						}
						
					}else{
						
						if(variableType==Boolean.class){
							processVariableValue = new Boolean(false);
						}else if(variableType==Number.class){
							processVariableValue = new Integer(0);
						}else
						
							processVariableValue = (Serializable) variableType.newInstance();
					}
					
					pv.setValueObject(processVariableValue);
				}
				
				releaseMapForITool();
		}
		
		setInstanceId(instanceId.toString());
		setTracingTag(humanActivity.getTracingTag());
		setTaskId(taskId);
	}
	
	
	transient protected HumanActivity humanActivity;
//	@NonLoadable
//	@NonSavable
//	@Hidden
//		public HumanActivity getHumanActivity() {
//			return humanActivity;
//		}
//		public void setHumanActivity(HumanActivity humanActivity) {
//			this.humanActivity = humanActivity;
//		}
		
	transient protected ProcessInstance instance;
	
	//// in old manners, we should carry all the following parameters by passing query string or json something:
	


	ParameterValue[] parameters;
		@Valid
		public ParameterValue[] getParameters() {
			return parameters;
		}
		public void setParameters(ParameterValue[] parameters) {
			this.parameters = parameters;
		}
		
	String instanceId;
	@Hidden
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}


	String tracingTag;
	@Hidden
		public String getTracingTag() {
			return tracingTag;
		}
		public void setTracingTag(String tracingTag) {
			this.tracingTag = tracingTag;
		}


	Long taskId;
		@Id
		@Hidden
		public Long getTaskId() {
			return taskId;
		}
		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}
		
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_EDIT)
	public Object cancel() throws Exception{
		instance = processManager.getProcessInstance(instanceId);

		humanActivity = (HumanActivity) instance.getProcessDefinition().getActivity(tracingTag);

//		WorkList worklist = instance.getWorkList();
//		worklist.cancelWorkItem(getTaskId().toString(), new KeyedParameter[]{}, instance.getProcessTransactionContext());
//
//		humanActivity.setStatus(instance, Activity.ACTIVITY_STOPPED)

		humanActivity.stop(instance);
		
		processManager.applyChanges();
		
		CommentWorkItem cancelledHistory = new CommentWorkItem();
		cancelledHistory.processManager = processManager;
		cancelledHistory.session = session;
		cancelledHistory.setInstId(new Long(getInstanceId()));
		
		cancelledHistory.setTitle(humanActivity.getName().getText() + " task has been cancelled by me.");
		cancelledHistory.setWriter(session.getUser());
		cancelledHistory.add();
		if("sns".equals(session.getEmployee().getPreferUX())){
			return new Remover(new ModalWindow());
		}else{
			return this;
		}
		
	}

	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_EDIT)
	public void skip() throws Exception{
		instance = processManager.getProcessInstance(instanceId);

		humanActivity = (HumanActivity) instance.getProcessDefinition()
					.getActivity(tracingTag);

//		WorkList worklist = instance.getWorkList();
//		worklist.cancelWorkItem(getTaskId().toString(), new KeyedParameter[]{}, instance.getProcessTransactionContext());
//
//		humanActivity.setStatus(instance, Activity.ACTIVITY_STOPPED)

		humanActivity.skip(instance);

		List activities = humanActivity.getPropagatedActivities(instance);
		
		//resume the process
//		Vector activityInstances =  instance.getCurrentRunningActivitiesDeeply();
		for(int i=0; i<activities.size(); i++){
			Activity nextAct = (Activity) activities.get(i);
			nextAct.resume(instance);
		}
		
		processManager.applyChanges();
		
		CommentWorkItem cancelledHistory = new CommentWorkItem();
		cancelledHistory.processManager = processManager;
		cancelledHistory.session = session;
		cancelledHistory.setInstId(new Long(getInstanceId()));
		
		cancelledHistory.setTitle(humanActivity.getName().getText() + " has been skipped by me.");
		cancelledHistory.setWriter(session.getUser());
		cancelledHistory.add();
		
	}

	@AutowiredFromClient
	public Session session;
		
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_EDIT, validate=true)
//	@Available(when={"NEW"})
	public Object[] complete() throws RemoteException, ClassNotFoundException, Exception{
						
		instance = processManager.getProcessInstance(instanceId);

		humanActivity = null;
		if (instanceId != null && tracingTag != null) {
			humanActivity = (HumanActivity) instance.getProcessDefinition()
					.getActivity(tracingTag);
		}

		if (!humanActivity.getActualMapping(instance).getEndpoint()
				.equals(session.getUser().getUserId())) {
			throw new Exception("$NotPermittedToComplete");

		}

		// load map for ITool
		loadMapForITool((Map<String, Object>)makeMapForITool());
		
		ResultPayload rp = new ResultPayload();
		
		if(parameters!=null)
		for(int i=0; i<parameters.length; i++){
			ParameterValue pv = parameters[i];

			
			String variableTypeName = parameters[i].getVariableType();
			//Class variableType = Thread.currentThread().getContextClassLoader().loadClass(variableTypeName);
			Serializable processVariableValue = null;

			processVariableValue = (Serializable) parameters[i].getValueObject();
		
//				if(variableType == String.class){
//				}else if(Long.class.isAssignableFrom(variableType)){
//					processVariableValue = parameters[i].getValueNumber();
//				}else if(Calendar.class.isAssignableFrom(variableType)){
//					processVariableValue = parameters[i].getValueCalendar();
//				}

			if(processVariableValue instanceof ITool){
				((ITool)processVariableValue).beforeComplete();
			}
			
			rp.setProcessVariableChange(new KeyedParameter(pv.getVariableName(), processVariableValue));
		}
		
		processManager.completeWorkitem(getInstanceId(), getTracingTag(), getTaskId().toString(), rp );
		processManager.applyChanges();
		
		if(parameters!=null){
			for(int i=0; i<parameters.length; i++){				
				Serializable processVariableValue = null;
				processVariableValue = (Serializable) parameters[i].getValueObject();
				
				if(processVariableValue instanceof ITool){
					((ITool)processVariableValue).afterComplete();
				}
			}
		}
		
		releaseMapForITool();
		
		//refreshes the instanceview so that the next workitem can be show up
		if("sns".equals(session.getEmployee().getPreferUX())){
			InstanceViewThreadPanel panel = new InstanceViewThreadPanel();
			panel.getMetaworksContext().setHow("instanceList");
			panel.getMetaworksContext().setWhere("sns");
			panel.session = session;
			panel.load(getInstanceId());
			return new Object[]{panel, new Remover(new ModalWindow())};
		}else{
			Instance instance = new Instance();
			instance.setInstId(new Long(getInstanceId()));
			
			instanceViewContent.session = session;
			instanceViewContent.load(instance);
			
			return new Object[]{instanceViewContent, new Remover(new ModalWindow())};
		}
	}
	
	@Autowired
	public InstanceViewContent instanceViewContent;
	
			
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_EDIT)
	public void save() throws RemoteException, ClassNotFoundException, Exception{
		
		ResultPayload rp = new ResultPayload();
		
		if(parameters!=null)
		for(int i=0; i<parameters.length; i++){
			ParameterValue pv = parameters[i];

			String variableTypeName = parameters[i].getVariableType();
			//Class variableType = Thread.currentThread().getContextClassLoader().loadClass(variableTypeName);
			Serializable processVariableValue = null;

			processVariableValue = (Serializable) parameters[i].getValueObject();
		
//				if(variableType == String.class){
//				}else if(Long.class.isAssignableFrom(variableType)){
//					processVariableValue = parameters[i].getValueNumber();
//				}else if(Calendar.class.isAssignableFrom(variableType)){
//					processVariableValue = parameters[i].getValueCalendar();
//				}

			
			rp.setProcessVariableChange(new KeyedParameter(pv.getVariableName(), processVariableValue));
		}
		
		processManager.saveWorkitem(getInstanceId(), getTracingTag(), getTaskId().toString(), rp );
//			processManager.applyChanges(); //you may call this. since you can ensure this service method is the service itself
	}
	
	@ServiceMethod(callByContent=true, when="compete")
	public InstanceViewContent accept() throws Exception{
		instance = processManager.getProcessInstance(instanceId.toString());
		
		humanActivity = (HumanActivity) instance.getProcessDefinition().getActivity(tracingTag);
		
		
		RoleMapping roleMapping = RoleMapping.create();
		roleMapping.setName(humanActivity.getRole().getName());
		roleMapping.setEndpoint(session.getEmployee().getEmpCode());		
		
		processManager.delegateWorkitem(this.getInstanceId(), this.getTracingTag(), roleMapping);
		processManager.applyChanges();
		
		//refreshes the instanceview so that the next workitem can be show up
		Instance instance = new Instance();
		instance.setInstId(new Long(getInstanceId()));
		
		instanceViewContent.load(instance);
		
		return instanceViewContent;
	}
	
			
	@Autowired
	transient public ProcessManagerRemote processManager;
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	protected Map<String, Object> makeMapForITool()
			throws Exception {
		Map<String, Object> mapForITool = new HashMap<String, Object>();

		mapForITool.put(ITool.ITOOL_INSTANCEID_KEY, getInstanceId());
		mapForITool.put(ITool.ITOOL_TRACINGTAG_KEY, getTracingTag());
		mapForITool.put(ITool.ITOOL_SESSION_KEY, session);
		mapForITool.put(ITool.ITOOL_PROCESS_MANAGER_KEY, processManager);
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT1_KEY, humanActivity.getExtValue1());
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT2_KEY, humanActivity.getExtValue2());
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT3_KEY, humanActivity.getExtValue3());
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT4_KEY, humanActivity.getExtValue4());
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT5_KEY, humanActivity.getExtValue5());
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT6_KEY, humanActivity.getExtValue6());
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT7_KEY, humanActivity.getExtValue7());
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT8_KEY, humanActivity.getExtValue8());
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT9_KEY, humanActivity.getExtValue9());
		mapForITool.put(ITool.ITOOL_ACTIVITY_EXT10_KEY, humanActivity.getExtValue10());
		
		
		
		return mapForITool;
	}
	
	private void loadMapForITool(Map<String, Object> map) {
		TransactionContext.getThreadLocalInstance().setSharedContext(
				ITool.ITOOL_MAP_KEY, map);
	}

	private void releaseMapForITool() {
		TransactionContext.getThreadLocalInstance().setSharedContext(
				ITool.ITOOL_MAP_KEY, null);
	}		
}
