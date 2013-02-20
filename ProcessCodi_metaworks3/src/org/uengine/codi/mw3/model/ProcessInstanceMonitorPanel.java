package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.processmanager.ProcessManagerRemote;

public class ProcessInstanceMonitorPanel {
	
	ProcessInstanceNavigator processInstanceNavigator; 
		public ProcessInstanceNavigator getProcessInstanceNavigator() {
			return processInstanceNavigator;
		}
		public void setProcessInstanceNavigator(
				ProcessInstanceNavigator processInstanceNavigator) {
			this.processInstanceNavigator = processInstanceNavigator;
		}
		
	ProcessInstanceMonitor processInstanceMonitor;	
		public ProcessInstanceMonitor getProcessInstanceMonitor() {
			return processInstanceMonitor;
		}
		public void setProcessInstanceMonitor(
				ProcessInstanceMonitor processInstanceMonitor) {
			this.processInstanceMonitor = processInstanceMonitor;
		}
		

	@AutowiredFromClient
	public Session session;
		
		
	public void load(String instanceId)
			throws Exception {
		
		
		processInstanceMonitor = new ProcessInstanceMonitor();
		processInstanceMonitor.setInstanceId(instanceId);
		processInstanceMonitor.session = session;
		processInstanceMonitor.load(processManager);
		
/*		processInstanceNavigator = new ProcessInstanceNavigator();
		processInstanceNavigator.session = session;
		processInstanceNavigator.load(instanceId);
		processInstanceNavigator.processManager = processManager;*/
		
		//setWest(processInstanceNavigator);
		//setCenter(processInstanceMonitor);
	}
	
	public ProcessManagerRemote processManager;

}
