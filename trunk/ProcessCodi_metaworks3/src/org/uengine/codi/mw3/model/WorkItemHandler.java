package org.uengine.codi.mw3.model;

import java.io.Serializable;
import java.rmi.RemoteException;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.contexts.ComplexType;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.KeyedParameter;
import org.uengine.kernel.NeedArrangementToSerialize;
import org.uengine.kernel.ParameterContext;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.ResultPayload;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(ejsPath="genericfaces/Window.ejs", options={"hideLabels"}, values={"true"}, displayName="업무 처리 화면")
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
					pv.setArgument(pc.getArgument().getText());
					
					
					MetaworksContext mc = new MetaworksContext();
					
					if(ParameterContext.DIRECTION_OUT.equals(pc.getDirection()) || ParameterContext.DIRECTION_INOUT.equals(pc.getDirection()))
						mc.setWhen(MetaworksContext.WHEN_EDIT);
					else
						mc.setWhen(MetaworksContext.WHEN_VIEW);
					
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
							if(processVariableValue instanceof NeedArrangementToSerialize){
								((NeedArrangementToSerialize)processVariableValue).afterDeserialization();
							}
						}
						
					}else{
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
		
		
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_VIEW)
	public void complete() throws RemoteException, ClassNotFoundException, Exception{
		
		ResultPayload rp = new ResultPayload();
		
		if(parameters!=null)
		for(int i=0; i<parameters.length; i++){
			ParameterValue pv = parameters[i];

			String variableTypeName = parameters[i].getVariableType();
			//Class variableType = Thread.currentThread().getContextClassLoader().loadClass(variableTypeName);
			Serializable processVariableValue = null;

			processVariableValue = (Serializable) parameters[i].getValueObject();
		
//			if(variableType == String.class){
//			}else if(Long.class.isAssignableFrom(variableType)){
//				processVariableValue = parameters[i].getValueNumber();
//			}else if(Calendar.class.isAssignableFrom(variableType)){
//				processVariableValue = parameters[i].getValueCalendar();
//			}

			
			rp.setProcessVariableChange(new KeyedParameter(pv.getVariableName(), processVariableValue));
		}
		
		processManager.completeWorkitem(getInstanceId(), getTracingTag(), getTaskId().toString(), rp );
		processManager.applyChanges(); //you may call this. since you can ensure this service method is the service itself
	}
		
	@Autowired
	transient protected ProcessManagerRemote processManager;
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
}
