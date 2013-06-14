package org.uengine.codi.mw3.ide.form;

import org.metaworks.annotation.Face;

@Face(displayName="$form.field.SingleLineTextField",
      options={"hideEditBtn"}, values={"true"},
	  ejsPathMappingByContext={
		"{where: 'form', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/SingleLineTextField.ejs'}",
		"{where: 'properties', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Properties.ejs'}",
		"{where: 'menu', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Menu.ejs'}"
})
public class SingleLineTextField extends CommonFormField {
	
	public SingleLineTextField() {
		this.setFieldType("java.lang.String");
		this.setDisplayName("$form.field.SingleLineTextField");
		this.setId("zzzzzzzzzzzzzzzzzzzzzz");	
	}
}
