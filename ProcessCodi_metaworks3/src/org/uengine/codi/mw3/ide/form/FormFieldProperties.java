package org.uengine.codi.mw3.ide.form;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

@Face(options={"hideEditBtn"}, values={"true"})
public class FormFieldProperties extends Properties {
	
	CommonFormField formField;	
		public CommonFormField getFormField() {
			return formField;
		}
		public void setFormField(CommonFormField formField) {
			this.formField = formField;
		}
		
	public FormFieldProperties(){
		this(new CommonFormField());
	}
	
	public FormFieldProperties(CommonFormField formField){
		this.setFormField(formField);
	}
	
	@ServiceMethod(callByContent=true)
	public void test(){
		System.out.println("11");
	}
}
