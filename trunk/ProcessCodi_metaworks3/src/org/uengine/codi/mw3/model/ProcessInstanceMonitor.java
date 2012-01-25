package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.persistence.Id;

import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.processmanager.ProcessManagerRemote;

public class ProcessInstanceMonitor {
	
	String instanceId;
	@Id
		public String getInstanceId() {
			return instanceId;
		}
	
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}

		
	String chartHTML;
			
		public String getChartHTML() {
			return chartHTML;
		}
	
		public void setChartHTML(String chartHTML) {
			this.chartHTML = chartHTML;
		}

	@ServiceMethod
	public void load() throws RemoteException{
		setChartHTML( 
				processManager.viewProcessInstanceFlowChart(getInstanceId(), new Hashtable())
		);		
	}
		
	@Autowired ProcessManagerRemote processManager;
}
