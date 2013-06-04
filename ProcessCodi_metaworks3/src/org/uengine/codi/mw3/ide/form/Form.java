package org.uengine.codi.mw3.ide.form;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Session;

public class Form {
	
	@AutowiredFromClient
	public Session session;
	
	@AutowiredFromClient
	public Form form;
	
	ArrayList<CommonFormField> formFields;	
		public ArrayList<CommonFormField> getFormFields() {
			return formFields;
		}	
		public void setFormFields(ArrayList<CommonFormField> formFields) {
			this.formFields = formFields;
		}

	public void load() {		
		formFields = new ArrayList<CommonFormField>();
	}
	
	@ServiceMethod(mouseBinding="drop", callByContent=true) 
	public Object drop() {
		
		Object clipboard = session.getClipboard();
		SingleTextField singleTextField = null;
		
		if(clipboard instanceof SingleTextField){
			singleTextField = (SingleTextField) clipboard;			
		}
			
		form.formFields.add(singleTextField);
		
		return form;
		//return new ToAppend(form, singleTextField);
	}
	
}
