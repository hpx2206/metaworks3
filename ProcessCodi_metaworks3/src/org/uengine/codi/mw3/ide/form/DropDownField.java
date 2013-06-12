package org.uengine.codi.mw3.ide.form;

public class DropDownField extends MultipleChoiceField {
	
	public DropDownField(){
		this.setFieldType("java.lang.String");
		this.setDefine(false);
		this.setEjsPath("dwr/metaworks/genericfaces/SelectBox.ejs");
	}
}
