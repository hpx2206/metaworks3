package org.uengine.codi.mw3.model;

import java.util.HashMap;

import net.sf.hibernate.collection.Map;

import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.viewer.ViewerOptions;
import org.uengine.processmanager.ProcessManagerRemote;


public class ProcessDesignerContentPanel extends ContentWindow {
	
	public void newProcessDefinition(String parentFolder) throws Exception{
		setParentFolder(parentFolder);
	}

	public void load(String defId) throws Exception{
		String defVerId = processManager.getProcessDefinitionProductionVersion(defId);
		
	    
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

		String chartHTML = processManager.viewProcessDefinitionFlowChart(defVerId, options);

		setChartHTML(chartHTML);
		setDefVerId(defVerId);
		setDefId(defId);
		
		processManager.remove();
	}
	
	String chartHTML;
		public String getChartHTML() {
			return chartHTML;
		}
		public void setChartHTML(String chartHTML) {
			this.chartHTML = chartHTML;
		}
		
	String parentFolder;
		public String getParentFolder() {
			return parentFolder;
		}
		public void setParentFolder(String parentFolder) {
			this.parentFolder = parentFolder;
		}
		
	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
	
	String defVerId;
		public String getDefVerId() {
			return defVerId;
		}
		public void setDefVerId(String defVerId) {
			this.defVerId = defVerId;
		}


	@Autowired
	public ProcessManagerRemote processManager;


}
