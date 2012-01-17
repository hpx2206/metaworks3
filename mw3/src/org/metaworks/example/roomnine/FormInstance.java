package org.metaworks.example.roomnine;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

public class FormInstance {

	ArrayList<FormField> formFields;
	
	public ArrayList<FormField> getFormFields() {
		return formFields;
	}

	public void setFormFields(ArrayList<FormField> formFields) {
		this.formFields = formFields;
	}

	@ServiceMethod(callByContent=true)
	@Face(displayName="submit")
	public void save() throws Exception {
		
		for(int i=0;i<formFields.size();i++) {
			FormField fld = (FormField)formFields.get(i);
			System.out.println(fld.getFieldName());
			System.out.println(fld.getType());
			if(fld.getType().equals("java.util.Date")) {
				System.out.println(fld.getValueDate().toString());
			}
			
		}
		
		// GlobalContext.serialize(this,new FileOutputStream("/Users/naoiking/formInst.xml"),FormDefinition.class);
		System.out.println("FormInstance.save()");
		
		
	}
	
}
