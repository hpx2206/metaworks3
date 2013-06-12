package org.uengine.codi.mw3.ide.form;

public class CheckBoxField extends MultipleChoiceField {
	
	public CheckBoxField() {
		this.setFieldType("java.lang.String");
		this.setDefine(false);
		this.setEjsPath("dwr/metaworks/genericfaces/CheckBox.ejs");
	}
}
