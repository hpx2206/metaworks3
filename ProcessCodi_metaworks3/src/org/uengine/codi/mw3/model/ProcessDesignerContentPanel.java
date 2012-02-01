package org.uengine.codi.mw3.model;

import java.util.HashMap;

import net.sf.hibernate.collection.Map;

import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;


public class ProcessDesignerContentPanel extends ContentWindow {
	
	public void newProcessDefinition(String parentFolder) throws Exception{
		setParentFolder(parentFolder);
	}

	public void load(String defId) throws Exception{
		String defVerId = processManager.getProcessDefinitionProductionVersion(defId);
		
		HashMap options = new HashMap();
		options.put("imagePathRoot", "uengine-web/processmanager/images");
		
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
	ProcessManagerRemote processManager;


}
