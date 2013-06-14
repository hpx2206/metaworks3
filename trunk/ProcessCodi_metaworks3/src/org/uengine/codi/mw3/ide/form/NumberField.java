package org.uengine.codi.mw3.ide.form;

import org.metaworks.annotation.Face;

@Face(displayName="$form.field.NumberField",
		options={"hideEditBtn"}, values={"true"},
		ejsPathMappingByContext={
			"{where: 'form', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/NumberField.ejs'}",
			"{where: 'properties', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Properties.ejs'}",
			"{where: 'menu', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Menu.ejs'}"
})
public class NumberField extends CommonFormField {

	public NumberField() {
		this.setFieldType("java.lang.Integer");
		this.setDisplayName("$form.field.NumberField");
//		this.setEjsPath("dwr/metaworks/genericfaces/Number.ejs");
	}
}
