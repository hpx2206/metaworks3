package org.uengine.codi.mw3.ide.form;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;

public class FormFieldMenu {

	ArrayList<CommonFormField> formFields;	
		public ArrayList<CommonFormField> getFormFields() {
			return formFields;
		}	
		public void setFormFields(ArrayList<CommonFormField> formFields) {
			this.formFields = formFields;
		}
	
	public void load() {
		
		formFields = new ArrayList<CommonFormField>();
		
		SingleTextField singleTextField = new SingleTextField();
		singleTextField.setMetaworksContext(new MetaworksContext());
		singleTextField.getMetaworksContext().setWhen("view");
		singleTextField.getMetaworksContext().setWhere("menu");
		
		MultipleChoiceField multipleChoiceField = new MultipleChoiceField();
		multipleChoiceField.setMetaworksContext(new MetaworksContext());
		multipleChoiceField.getMetaworksContext().setWhen("view");
		multipleChoiceField.getMetaworksContext().setWhere("menu");
		
		formFields.add(singleTextField);
//		formFields.add(multipleChoiceField);		
	}
}
