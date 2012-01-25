package org.uengine.codi.mw3.model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.WebObjectType;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.kernel.Activity;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.KeyedParameter;
import org.uengine.kernel.ParameterContext;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.ResultPayload;
import org.uengine.processmanager.ProcessManagerRemote;

public class WorkItemHandler implements ContextAware{
	
	public WorkItemHandler(){} //to be spring bean without argument, this is required.
	
	public void load() throws Exception{
		
		Long instanceId = new Long(getInstanceId());
		Long taskId = getTaskId();
		String tracingTag = getTracingTag();
		
		
		instance = codiPmSVC.getProcessInstance(instanceId.toString());

		if(humanActivity==null && instanceId!=null && tracingTag!=null){
			humanActivity = (HumanActivity) instance.getProcessDefinition().getActivity(tracingTag);
		}
		
		if(humanActivity.getParameters()!=null){
			//creates work item handler
			parameters = new ParameterValue[humanActivity.getParameters().length];
			for(int i=0; i<humanActivity.getParameters().length; i++){
				ParameterContext pc = humanActivity.getParameters()[i];
				
				ParameterValue pv = new ParameterValue();
				pv.setArgument(pc.getArgument().getText());
				
				
				MetaworksContext mc = new MetaworksContext();
				
				if(pc.getDirection() == ParameterContext.DIRECTION_OUT || pc.getDirection() == ParameterContext.DIRECTION_INOUT)
					mc.setWhen(MetaworksContext.WHEN_EDIT);
				else
					mc.setWhen(MetaworksContext.WHEN_VIEW);
				
				pv.setMetaworksContext(mc);
				
				parameters[i] = new ParameterValue();
				parameters[i].setArgument(pc.getArgument().getText());
				
				Serializable processVariableValue = pc.getVariable().get(instance, "");
				Class variableType = pc.getVariable().getType();
			
				if(variableType == String.class){
					parameters[i].setValueString((String) processVariableValue);
	//			}else if(Long.class.isAssignableFrom(variableType)){
	//				parameters[i].setValueNumber((Number) processVariableValue);
	//			}else if(Calendar.class.isAssignableFrom(variableType)){
	//				parameters[i].setValueCalendar((Calendar) processVariableValue);
				}
				
				parameters[i].setVariableType(variableType.getName());
				
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
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}


	String tracingTag;
		public String getTracingTag() {
			return tracingTag;
		}
		public void setTracingTag(String tracingTag) {
			this.tracingTag = tracingTag;
		}


	Long taskId;
		@Id
		public Long getTaskId() {
			return taskId;
		}
		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}
		
		
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_VIEW)
	public void complete() throws RemoteException, ClassNotFoundException, Exception{
		
		ResultPayload rp = new ResultPayload();
		
		if(parameters!=null)
		for(int i=0; i<parameters.length; i++){
			ParameterValue pv = parameters[i];

			String variableTypeName = parameters[i].getVariableType();
			Class variableType = Thread.currentThread().getContextClassLoader().loadClass(variableTypeName);
			Serializable processVariableValue = null;
		
			if(variableType == String.class){
				processVariableValue = parameters[i].getValueString();
//			}else if(Long.class.isAssignableFrom(variableType)){
//				processVariableValue = parameters[i].getValueNumber();
//			}else if(Calendar.class.isAssignableFrom(variableType)){
//				processVariableValue = parameters[i].getValueCalendar();
			}

			
			rp.setProcessVariableChange(new KeyedParameter(pv.getArgument(), processVariableValue));
		}
		
		codiPmSVC.completeWorkitem(getInstanceId(), getTracingTag(), getTaskId().toString(), rp );
		codiPmSVC.applyChanges(); //you may call this. since you can ensure this service method is the service itself
	}
		
	@Autowired
	transient protected ProcessManagerRemote codiPmSVC;
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
}
