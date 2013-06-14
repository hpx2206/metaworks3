package org.uengine.codi.mw3.ide.form;

import org.metaworks.annotation.Face;

@Face(displayName="$form.field.DropDownField",
		options={"hideEditBtn"}, values={"true"},
		ejsPathMappingByContext={
			"{where: 'form', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/DropDownField.ejs'}",
			"{where: 'properties', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Properties.ejs'}",
			"{where: 'menu', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Menu.ejs'}"
})
public class DropDownField extends MultipleChoiceField {
	
	public DropDownField(){
		this.setName("DropDown");
		this.setDisplayName("DropDown");
		this.setFieldType("java.lang.String");
		this.setEjsPath("dwr/metaworks/genericfaces/SelectBox.ejs");
	}
}
