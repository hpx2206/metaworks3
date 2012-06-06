package org.uengine.codi.mw3.model;

import java.io.Serializable;
import java.rmi.RemoteException;

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
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.KeyedParameter;
import org.uengine.kernel.NeedArrangementToSerialize;
import org.uengine.kernel.ParameterContext;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.ResultPayload;
import org.uengine.processmanager.ProcessManagerRemote;
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
							
							if(processVariableValue instanceof ITool){
								((ITool)processVariableValue).onLoad();
							}
						} else if (processVariableValue instanceof ITool) {
							// for completed ComplexType Object implements ITool
							((ITool) processVariableValue).onLoad();
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
		
	@ServiceMethod(callByContent=true)
	public void cancel() throws Exception{
		instance = processManager.getProcessInstance(instanceId);

		humanActivity = (HumanActivity) instance.getProcessDefinition()
					.getActivity(tracingTag);

		WorkList worklist = instance.getWorkList();
		worklist.cancelWorkItem(getTaskId().toString(), new KeyedParameter[]{}, instance.getProcessTransactionContext());

		CommentWorkItem cancelledHistory = new CommentWorkItem();
		cancelledHistory.processManager = processManager;
		cancelledHistory.session = session;
		cancelledHistory.setInstId(new Long(getInstanceId()));
		
		cancelledHistory.setTitle(humanActivity.getName().getText() + " 업무를 취소했습니다.");
		cancelledHistory.setWriter(session.getUser());
		cancelledHistory.add();
	}

	@AutowiredFromClient
	public Session session;
		
	@ServiceMethod(callByContent=true, when="COMMITABLE")
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
			throw new Exception("본 건을 처리완료할 권한이 없네요...");

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
	
			
	@ServiceMethod(callByContent=true, when=MetaworksContext.WHEN_VIEW)
	@Hidden(when="COMPLETED")
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
