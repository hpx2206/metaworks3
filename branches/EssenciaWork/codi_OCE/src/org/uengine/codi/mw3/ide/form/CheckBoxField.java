package org.uengine.codi.mw3.ide.form;

public class CheckBoxField extends MultipleChoiceField {
	
	public CheckBoxField() {
		this.setName("CheckBoxField");
		this.setFieldType("java.lang.String");
		this.setEjsPath("dwr/metaworks/genericfaces/CheckBox.ejs");
	}
	
	@Override
	public void init(){
		super.init();
		this.getMetaworksContext().setHow("checkbox");
	}
}
