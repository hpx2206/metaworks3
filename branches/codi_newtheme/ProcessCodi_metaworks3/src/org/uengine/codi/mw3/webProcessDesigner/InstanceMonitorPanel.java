package org.uengine.codi.mw3.webProcessDesigner;

import org.uengine.codi.mw3.model.IInstance;
import org.uengine.codi.mw3.model.Session;
import org.uengine.processmanager.ProcessManagerRemote;


public class InstanceMonitorPanel {
	
	InstanceMonitorNavigator instanceMonitorNavigator; 
		public InstanceMonitorNavigator getInstanceMonitorNavigator() {
			return instanceMonitorNavigator;
		}
		public void setInstanceMonitorNavigator(
				InstanceMonitorNavigator instanceMonitorNavigator) {
			this.instanceMonitorNavigator = instanceMonitorNavigator;
		}
		
	InstanceMonitor instanceMonitor;
		public InstanceMonitor getInstanceMonitor() {
			return instanceMonitor;
		}
		public void setInstanceMonitor(InstanceMonitor instanceMonitor) {
			this.instanceMonitor = instanceMonitor;
		}
		
		
	public boolean load(String instanceId, Session session, ProcessManagerRemote processManager) throws Exception {

		instanceMonitorNavigator = new InstanceMonitorNavigator();
		instanceMonitorNavigator.session = session;
		instanceMonitorNavigator.load(instanceId);
		
		IInstance instance = instanceMonitorNavigator.getInstance();
		if(instance.next()){
			instanceMonitor = new InstanceMonitor();
			instanceMonitor.session = session;
			instanceMonitor.processManager = processManager; 
			instanceMonitor.load(instance.getInstId().toString());
			instance.beforeFirst();
			
			return true;
		}else{
			// 프로세스가 없는 경우임..
			return false;
		}
	}
	
}
