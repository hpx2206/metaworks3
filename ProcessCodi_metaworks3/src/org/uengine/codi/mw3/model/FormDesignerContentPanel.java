package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(ejsPath="genericfaces/Window.ejs", displayName="Form Designer", options={"hideLabels"}, values={"true"})
public class FormDesignerContentPanel extends ContentWindow {
	

	ClassDefinition formDefinition;
		public ClassDefinition getFormDefinition() {
			return formDefinition;
		}
	
		public void setFormDefinition(ClassDefinition formDefinition) {
			this.formDefinition = formDefinition;
		}
	
	public void newForm(String parentFolder){
		formDefinition = new ClassDefinition();
		formDefinition.getMetaworksContext().setWhere("form");
		formDefinition.setParentFolder(parentFolder);
		formDefinition.setPackageName(parentFolder);
		formDefinition.load();
	}
	
	public void load(String defId) throws Exception{
		String defVerId = codiPmSVC.getProcessDefinitionProductionVersion(defId);
		String resource = codiPmSVC.getResource(defVerId);
		formDefinition = (ClassDefinition) GlobalContext.deserialize(resource, ClassDefinition.class);
	}

	
	@Autowired
	public ProcessManagerRemote codiPmSVC;

}
