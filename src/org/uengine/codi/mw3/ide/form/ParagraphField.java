package org.uengine.codi.mw3.ide.form;

public class ParagraphField extends CommonFormField {
	
	//options = { "rows", "cols" }, values = { "10", "50" }
	
	public ParagraphField() {
		this.setName("ParagraphField");
		this.setFieldType("java.lang.String");
		this.setEjsPath("dwr/metaworks/genericfaces/richText.ejs");
	}

}
