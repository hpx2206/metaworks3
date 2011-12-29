package org.uengine.codi.mw3.admin;

import java.io.FileOutputStream;
import java.util.ArrayList;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.kernel.GlobalContext;

public class FormInstance{

	ArrayList<FormField> formFields;
	
		public ArrayList<FormField> getFormFields() {
			return formFields;
		}
	
		public void setFormFields(ArrayList<FormField> formFields) {
			this.formFields = formFields;
		}

	
	@ServiceMethod(callByContent=true)
	@Face(displayName = "submit")
	public void save() throws Exception {
		GlobalContext.serialize(this, new FileOutputStream("/Users/jyjang/formInst.xml"), FormDefinition.class);
	}
	
	

}
