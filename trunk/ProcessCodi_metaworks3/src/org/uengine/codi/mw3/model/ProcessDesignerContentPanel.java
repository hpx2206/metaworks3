package org.uengine.codi.mw3.model;

import java.rmi.RemoteException;
import java.util.HashMap;

import net.sf.hibernate.collection.Map;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.kernel.viewer.ViewerOptions;
import org.uengine.processmanager.ProcessManagerRemote;


public class ProcessDesignerContentPanel extends ContentWindow implements ContextAware {
	
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
		
	//	processManager.remove();
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

	String definitionString;
	@Face(ejsPath="genericfaces/richText.ejs")
		public String getDefinitionString() {
			return definitionString;
		}
		public void setDefinitionString(String definitionString) {
			this.definitionString = definitionString;
		}
		
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
	String description;
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}

	@ServiceMethod(callByContent=true)
	public void save() throws RemoteException{
		defId = processManager.addProcessDefinition(name, 0, description, false, definitionString, parentFolder, defId, "process", name, null);

	}

	@Autowired
	public ProcessManagerRemote processManager;

	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

}
