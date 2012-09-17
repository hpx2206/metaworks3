package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.ORMappingListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.ITool;
import org.uengine.kernel.ProcessInstance;
import org.uengine.processmanager.ProcessManagerRemote;

public class GenericWorkItemHandler implements ORMappingListener{

	String serializedTool;

		public String getSerializedTool() {
			return serializedTool;
		}
	
		public void setSerializedTool(String serializedTool) {
			this.serializedTool = serializedTool;
		}

	@Override
	public void onRelation2Object() {

		try {
			setTool( org.uengine.kernel.GlobalContext.deserialize(getSerializedTool()));
			
			if(getTool() instanceof ITool){
				ITool tool = (ITool)getTool();
				tool.onLoad();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onObject2Relation() {
		try {
			setSerializedTool(org.uengine.kernel.GlobalContext.serialize(getTool(), Object.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

	Object tool;

		public Object getTool() {
			return tool;
		}
	
		public void setTool(Object tool) {
			this.tool = tool;
		}
	
	Number instanceId;
		
	
		
	
	
		public Number getInstanceId() {
			return instanceId;
		}
	
		public void setInstanceId(Number instanceId) {
			this.instanceId = instanceId;
		}


	String tracingTag;
		public String getTracingTag() {
			return tracingTag;
		}
	
		public void setTracingTag(String tracingTag) {
			this.tracingTag = tracingTag;
		}
	
	
	Number taskId;
	
		public Number getTaskId() {
			return taskId;
		}
	
		public void setTaskId(Number taskId) {
			this.taskId = taskId;
		}

	@ServiceMethod(callByContent=true)
	@Face(displayName="완료")
	public InstanceViewContent complete() throws RemoteException, ClassNotFoundException, Exception{
			
			ProcessInstance instance = processManager.getProcessInstance(getInstanceId().toString());

			processManager.completeWorkitem(getInstanceId().toString(), getTracingTag(), getTaskId().toString(), null);
			processManager.applyChanges();
			
			//refreshes the instanceview so that the next workitem can be show up
			Instance refInstance = new Instance();
			refInstance.setInstId(getInstanceId().longValue());
			
			instanceViewContent.load(refInstance);
			
			return instanceViewContent;

	}

	@Autowired
	public ProcessManagerRemote processManager;
	
	@Autowired
	public InstanceViewContent instanceViewContent;
}
