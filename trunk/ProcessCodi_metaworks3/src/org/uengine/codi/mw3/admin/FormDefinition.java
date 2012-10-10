
package org.uengine.codi.mw3.admin;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

public class FormDefinition {
	
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		} 
		
	@Autowired
	public ProcessManagerRemote processManager;


	public FormDefinition(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
	}
	
	@ServiceMethod(callByContent=true)
	public void save() throws Exception{
//		String strDef = GlobalContext.serialize(this, FormDefinition.class);
//		
//		String defVerId = processManager.addProcessDefinition(getAlias(), getVersion(), "description", false, strDef, "-1", null, "form", getAlias(), null);
//		processManager.setProcessDefinitionProductionVersion(defVerId);
//		processManager.applyChanges();
	}
	
//	@ServiceMethod(callByContent=true)
//	public FormInstance preview(){
//		
//		FormInstance formInstance = new FormInstance();
//		formInstance.formFields = new ArrayList<FormField>();
//		for(FormField field: formFields){
//			FormField clonedOne;
//			try {
//				clonedOne = (FormField) field.clone();
//				clonedOne.setMetaworksContext(new MetaworksContext());
//				clonedOne.getMetaworksContext().setWhen("instance-mode");
//				formInstance.formFields.add(field);
//			} catch (CloneNotSupportedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
////		formInstance.setMetaworksContext(new MetaworksContext());
////		formInstance.getMetaworksContext().setWhen("instance-mode");
//		
//		return formInstance;
//	}
}
