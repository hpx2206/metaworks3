package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.FormDefinition;
import org.uengine.codi.mw3.admin.FormInstance;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(ejsPath="genericfaces/Window.ejs", displayName="Form Designer", options={"hideLabels"}, values={"true"})
public class FormDesignerContentPanel extends ContentWindow {
	

	FormDefinition formDefinition;
		public FormDefinition getFormDefinition() {
			return formDefinition;
		}
	
		public void setFormDefinition(FormDefinition formDefinition) {
			this.formDefinition = formDefinition;
		}
	
	FormInstance formInstance;	
		public FormInstance getFormInstance() {
			return formInstance;
		}
	
		public void setFormInstance(FormInstance formInstance) {
			this.formInstance = formInstance;
		}
	
	public void newForm(String parentFolder){
		formDefinition = new FormDefinition();
		formDefinition.setParentFolder(parentFolder);
		
		formInstance = new FormInstance();
	}
	
	public void load(String defId) throws Exception{
		String defVerId = codiPmSVC.getProcessDefinitionProductionVersion(defId);
		String resource = codiPmSVC.getResource(defVerId);
		formDefinition = (FormDefinition) GlobalContext.deserialize(resource, FormDefinition.class);
	}

	
	@Autowired
	public ProcessManagerRemote codiPmSVC;

}
