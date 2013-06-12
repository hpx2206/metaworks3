package org.uengine.codi.mw3.ide.form;

import org.metaworks.WebFieldDescriptor;

public class ParagraphField extends CommonFormField {
	
	//options = { "rows", "cols" }, values = { "10", "50" }
	
	public ParagraphField() {
		this.setFieldType("java.lang.String");
		this.setEjsPath("dwr/metaworks/genericfaces/richText.ejs");
	}

}
