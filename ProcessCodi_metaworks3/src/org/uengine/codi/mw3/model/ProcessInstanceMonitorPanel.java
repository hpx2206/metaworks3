package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.persistence.Id;

import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.layout.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.viewer.ViewerOptions;
import org.uengine.processmanager.ProcessManagerRemote;

public class ProcessInstanceMonitorPanel extends Layout{
	
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
		
		
	public void load(String instanceId)
			throws Exception {
		
		
		processInstanceMonitor = new ProcessInstanceMonitor();
		processInstanceMonitor.setInstanceId(instanceId);
		processInstanceMonitor.load(processManager);
		
		processInstanceNavigator = new ProcessInstanceNavigator();
		processInstanceNavigator.load(instanceId);
		processInstanceNavigator.processManager = processManager;
		
		setWest(processInstanceNavigator);
		setCenter(processInstanceMonitor);
	}
	
	public ProcessManagerRemote processManager;

}
