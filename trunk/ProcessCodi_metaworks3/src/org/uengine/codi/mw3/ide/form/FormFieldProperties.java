package org.uengine.codi.mw3.ide.form;

import org.metaworks.annotation.Face;

@Face(options={"hideEditBtn"}, values={"true"}, ejsPath="genericfaces/FormFace.ejs")
public class FormFieldProperties {
	
	CommonFormField formField;	
		public CommonFormField getFormField() {
			return formField;
		}
		public void setFormField(CommonFormField formField) {
			this.formField = formField;
		}

	public FormFieldProperties(){
		this(null);
	}
	
	public FormFieldProperties(CommonFormField formField){
		this.setFormField(formField);
	}
}
