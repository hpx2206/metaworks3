package org.uengine.codi.mw3.model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Vector;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.ITool;
import org.uengine.contexts.ComplexType;
import org.uengine.kernel.Activity;
import org.uengine.kernel.ActivityInstanceContext;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.KeyedParameter;
import org.uengine.kernel.NeedArrangementToSerialize;
import org.uengine.kernel.ParameterContext;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.ResultPayload;
import org.uengine.kernel.RoleMapping;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.webservices.worklist.DefaultWorkList;
import org.uengine.webservices.worklist.WorkList;

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
			//creates work item handler
				parameters = new ParameterValue[humanActivity.getParameters().length];
				for(int i=0; i<humanActivity.getParameters().length; i++){
					ParameterContext pc = humanActivity.getParameters()[i];
					
					parameters[i] = new ParameterValue();
					
					ParameterValue pv = parameters[i];
					pv.setVariableName(pc.getVariable().getName());
					pv.setArgument(pc.getArgument().getText(session!=null && session.getEmployee()!=null ? session.getEmployee().getLocale() : null));
					
					MetaworksContext mc = new MetaworksContext();
										
					if(MetaworksContext.WHEN_EDIT.equals(this.getMetaworksContext().getWhen())){
						if(ParameterContext.DIRECTION_IN.equals(pc.getDirection()))						
							this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
					}
					
					mc.setWhen(this.getMetaworksContext().getWhen());					
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
	public void cancel() throws Exception{
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
		
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_EDIT)
//	@Available(when={"NEW"})
	public InstanceViewContent complete() throws RemoteException, ClassNotFoundException, Exception{
		
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
		
		//refreshes the instanceview so that the next workitem can be show up
		Instance instance = new Instance();
		instance.setInstId(new Long(getInstanceId()));
		
		instanceViewContent.load(instance);
		
		return instanceViewContent;
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
		
}
