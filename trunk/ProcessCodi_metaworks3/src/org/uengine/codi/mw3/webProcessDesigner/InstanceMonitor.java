package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;
import java.util.Properties;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.Activity;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ProcessInstance;
import org.uengine.processmanager.ProcessManagerRemote;

public class InstanceMonitor {
	static Properties statusColors = new Properties();
	static{
		statusColors.setProperty(Activity.STATUS_READY, 	"#5F6061");
		statusColors.setProperty(Activity.STATUS_COMPLETED, "#888888");
		statusColors.setProperty(Activity.STATUS_RUNNING, 	"#027dbc");
		statusColors.setProperty(Activity.STATUS_FAULT, 	"#ee2020");
		statusColors.setProperty(Activity.STATUS_SKIPPED, 	"#2020aa");
		statusColors.setProperty(Activity.STATUS_SUSPENDED, "#20cccc");
		statusColors.setProperty(Activity.STATUS_TIMEOUT, 	"#aaaa20");
		statusColors.setProperty(Activity.STATUS_RETRYING, 	"#ee2020");
	}
	public static String getStatusColor(String status){		
		return statusColors.getProperty(status);
	}
	
	String instanceId;
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	ProcessInstance instance;
	public ProcessInstance getInstance() {
		return instance;
	}
	public void setInstance(ProcessInstance instance) {
		this.instance = instance;
	}
	ProcessDefinition procDef;
	public ProcessDefinition getProcDef() {
		return procDef;
	}
	public void setProcDef(ProcessDefinition procDef) {
		this.procDef = procDef;
	}
	String tempTracingTag;
	public String getTempTracingTag() {
		return tempTracingTag;
	}
	public void setTempTracingTag(String tempTracingTag) {
		this.tempTracingTag = tempTracingTag;
	}
	
	ProcessViewer processViewer;
		public ProcessViewer getProcessViewer() {
			return processViewer;
		}
		public void setProcessViewer(ProcessViewer processViewer) {
			this.processViewer = processViewer;
		}
	public void load(String instanceId) throws Exception {
		this.setInstanceId(instanceId);
		ProcessInstance instance = processManager.getProcessInstance(instanceId);
		ProcessDefinition procDef = instance.getProcessDefinition();

		processViewer = new ProcessViewer();
		
		ProcessDesignerContainer processDesignerContainer = new ProcessDesignerContainer();
		processDesignerContainer.setViewType("definitionView");
		processDesignerContainer.setEditorId(instanceId);
		processDesignerContainer.init();
		processDesignerContainer.load(procDef);
		
		ArrayList<Activity> activityList = processDesignerContainer.getActivityList();
		for (int l = 0; l < activityList.size(); l++) {
			Activity activity = activityList.get(l);
			String status = instance.getStatus(activity.getTracingTag());
			activity.getActivityView().setInstStatus(status);
			activity.getActivityView().setBackgroundColor(InstanceMonitor.getStatusColor(status));
		}
		
		
		processViewer.setProcessDesignerContainer(processDesignerContainer);
		processViewer.setProcessDesignerInstanceId(procDef.getProcessDesignerInstanceId());
		processViewer.setTitle(procDef.getName().getText());
		
		processViewer.setDesignerMaxX(processDesignerContainer.getMaxX());
		processViewer.setDesignerMaxY(processDesignerContainer.getMaxY());
	}

	public void loadProcess(String path) throws Exception {
		System.out.println("path = " + path);

	}

	@ServiceMethod(callByContent=true, target="popup")
	public Popup showActivityInfo() throws Exception{
		if( instanceId == null ) return null;
		Popup popup = new Popup(400,275);
		TaskInfoPanel taskInfo = new TaskInfoPanel();

		ProcessInstance instance = processManager.getProcessInstance(instanceId);
		ProcessDefinition procDef = instance.getProcessDefinition();

		Activity activity = procDef.getActivity(getTempTracingTag());
		taskInfo.load(getInstanceId(), activity, instance);
		popup.setPanel(taskInfo);
		return popup;
	}
	@AutowiredFromClient
	public Session session;

	@Autowired
	public ProcessManagerRemote processManager;

}
