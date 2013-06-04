package org.uengine.codi.mw3.ide.form;

public class FormFieldProperties {
	
	CommonFormField formField;	
		public CommonFormField getFormField() {
			return formField;
		}
		public void setFormField(CommonFormField formField) {
			this.formField = formField;
		}
		
	public void load() {
		formField = new CommonFormField();
	}
}
