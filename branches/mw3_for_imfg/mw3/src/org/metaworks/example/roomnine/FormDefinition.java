package org.metaworks.example.roomnine;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;

public class FormDefinition implements ContextAware {

	public FormDefinition() {
		clearNewFormField();
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
	public FormInstance preview() {
		FormInstance formInstance = new FormInstance();
		formInstance.formFields = new ArrayList<FormField>();
		for(FormField field : formFields) {
			FormField clonedOne;
			try {
				clonedOne = (FormField)field.clone();
				clonedOne.getMetaworksContext().setWhen("instance-mode");
				formInstance.formFields.add(field);
			} catch(CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		
		return formInstance;
	}
	
	@ServiceMethod(callByContent=true)
	public void save() throws Exception {
		System.out.println("save()");
	}
	
	public void clearNewFormField() {
		newFormField = new FormField();
		newFormField.metaworksContext = new MetaworksContext();
		newFormField.metaworksContext.setWhen(MetaworksContext.WHEN_EDIT);
		newFormField.metaworksContext.setWhere("newEntry");
		newFormField.formDefinition = this;
		
	}
	
	@Override
	public MetaworksContext getMetaworksContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMetaworksContext(MetaworksContext context) {
		// TODO Auto-generated method stub

	}

}
