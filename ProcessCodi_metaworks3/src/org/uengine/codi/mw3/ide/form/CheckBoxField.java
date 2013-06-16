package org.uengine.codi.mw3.ide.form;

public class CheckBoxField extends MultipleChoiceField {
	
	public CheckBoxField() {
		this.setName("CheckBox");
		this.setDisplayName("CheckBox");
		this.setFieldType("java.lang.String");
		this.setEjsPath("dwr/metaworks/genericfaces/CheckBox.ejs");
	}
}
