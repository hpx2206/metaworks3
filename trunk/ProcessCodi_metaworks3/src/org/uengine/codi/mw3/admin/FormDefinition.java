
package org.uengine.codi.mw3.admin;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.httpclient.methods.GetMethod;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.PropertyListable;
import org.uengine.processmanager.ProcessManagerRemote;

public class FormDefinition implements ContextAware, PropertyListable{
	
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		} 
		
	@Autowired
	protected ProcessManagerRemote processManager;


	public FormDefinition(){
		clearNewFormField();
	}
	
//	FormField[] formFields;
//		
//		public FormField[] getFormFields() {
//			return formFields;
//		}
//	
//		public void setFormFields(FormField[] formFields) {
//			this.formFields = formFields;
//		}
	
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}

	int version;
		public int getVersion() {
			return version;
		}
		public void setVersion(int version) {
			this.version = version;
		}

	ArrayList<FormField> formFields;
		
		public ArrayList<FormField> getFormFields() {
			return formFields;
		}
	
		public void setFormFields(ArrayList<FormField> formFields) {
			this.formFields = formFields;
		}

	FormField newFormField;

		public FormField getNewFormField() {
			return newFormField;
		}
	
		public void setNewFormField(FormField newFormField) {
			this.newFormField = newFormField;
		}
		
	@ServiceMethod(callByContent=true)
	public FormInstance preview(){
		
		FormInstance formInstance = new FormInstance();
		formInstance.formFields = new ArrayList<FormField>();
		for(FormField field: formFields){
			FormField clonedOne;
			try {
				clonedOne = (FormField) field.clone();
				clonedOne.setMetaworksContext(new MetaworksContext());
				clonedOne.getMetaworksContext().setWhen("instance-mode");
				formInstance.formFields.add(field);
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		formInstance.setMetaworksContext(new MetaworksContext());
//		formInstance.getMetaworksContext().setWhen("instance-mode");
		
		return formInstance;
	}
	
	@ServiceMethod(callByContent=true)
	public void save() throws Exception{
		String strDef = GlobalContext.serialize(this, FormDefinition.class);
		
		String defVerId = processManager.addProcessDefinition(getAlias(), getVersion(), "description", false, strDef, "-1", null, "form", getAlias(), null);
		processManager.setProcessDefinitionProductionVersion(defVerId);
		processManager.applyChanges();
	}


	public void clearNewFormField() {
		newFormField = new FormField();
		newFormField.metaworksContext = new MetaworksContext();
		newFormField.metaworksContext.setWhen(MetaworksContext.WHEN_EDIT);
		newFormField.metaworksContext.setWhere("newEntry");//TODO: lesson 6 (if there's no overriding value, metaworks will tries to use old contxt value)
		newFormField.formDefinition = this;

	}
	
	@Override
	public ArrayList<String> listProperties() {
		ArrayList<FormField> formFields = getFormFields();
		
		//System.out.println("The document contains "+formFields.size()+" form fields:\n");
		
		ArrayList array = new ArrayList();
		for (Iterator i=formFields.iterator(); i.hasNext();) {
			FormField formField=(FormField)i.next();
			if(formField.getFieldName()!=null)
				array.add(formField.getFieldName());
		}
		
		return array;
	}
	
	
}
