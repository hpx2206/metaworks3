package org.uengine.codi.mw3.ide.form;

import org.metaworks.annotation.Face;

@Face(displayName="$form.field.CheckBoxField",
		options={"hideEditBtn"}, values={"true"},
		ejsPathMappingByContext={
			"{where: 'form', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/CheckBoxField.ejs'}",
			"{where: 'properties', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Properties.ejs'}",
			"{where: 'menu', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Menu.ejs'}"
})
public class CheckBoxField extends MultipleChoiceField {
	
	public CheckBoxField() {
		this.setFieldType("java.lang.String");
		this.setEjsPath("dwr/metaworks/genericfaces/CheckBox.ejs");
	}
}
