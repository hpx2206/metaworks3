package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.persistence.Id;

import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.kernel.viewer.ViewerOptions;
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
	    ViewerOptions options = new ViewerOptions();

	    options.put("imagePathRoot", "/uengine-web/processmanager/");
	    
	    options.setViewType(options.HORIZONTAL, options.HORIZONTAL);
	    options.put("flowControl", new Boolean(true));
	//  options.put("dontCollapseScopes", new Boolean(true));
	    options.put("decorated", new Boolean(true));
	    options.put("show hidden activity", new Boolean(true));
	    options.put("ShowAllComplexActivities", new Boolean(true));
	//  options.put("enableEvent_onActivityClicked", new Boolean(true));
	    options.put("align", "center");
	    //options.put("locale", loggedUserLocale);
	    
//	    if (pi != null) {
//	        options.put("enableUserEvent_compensateTo", "Back to here");
//	        options.put("enableUserEvent_refreshMultipleInstances_org.uengine.kernel.SubProcessActivity", "Refresh Multiple Instances");
//	        options.put("enableUserEvent_showLog", "See Execution Log");
//	        //options.put("enableUserEvent_locateWorkItem", "Work Item Handler");
//	        options.put("enableUserEvent_locateWorkItem_org.uengine.kernel.ReceiveActivity", "Work Item Handler");
//	    }
	    
	    options.put("enableUserEvent_viewFormDefinition_org.uengine.kernel.FormActivity", "View Form Definition");
	    options.put("enableUserEvent_drillInto_org.uengine.kernel.SubProcessActivity", "Drill Into");

		setChartHTML( 
				processManager.viewProcessInstanceFlowChart(getInstanceId(), options)
		);		
	}
		
	@Autowired public ProcessManagerRemote processManager;
}
