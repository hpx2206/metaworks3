package org.uengine.codi.mw3.ide.form;

public class DropDownField extends MultipleChoiceField {
	
	public DropDownField(){
		this.setName("DropDown");
		this.setDisplayName("DropDown");
		this.setFieldType("java.lang.String");
		this.setEjsPath("dwr/metaworks/genericfaces/SelectBox.ejs");
	}
}
