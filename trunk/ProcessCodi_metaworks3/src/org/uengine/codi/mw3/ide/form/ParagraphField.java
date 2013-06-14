package org.uengine.codi.mw3.ide.form;

import org.metaworks.annotation.Face;

@Face(displayName="$form.field.ParagraphField",
		options={"hideEditBtn"}, values={"true"},
		ejsPathMappingByContext={
			"{where: 'form', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/ParagraphField.ejs'}",
			"{where: 'properties', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Properties.ejs'}",
			"{where: 'menu', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Menu.ejs'}"
})
public class ParagraphField extends CommonFormField {
	
	//options = { "rows", "cols" }, values = { "10", "50" }
	
	public ParagraphField() {
		this.setFieldType("java.lang.String");
		this.setEjsPath("dwr/metaworks/genericfaces/richText.ejs");
	}

}
