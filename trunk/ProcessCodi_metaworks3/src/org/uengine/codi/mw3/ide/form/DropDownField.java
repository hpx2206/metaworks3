package org.uengine.codi.mw3.ide.form;

import org.metaworks.annotation.Face;

@Face(options={"fieldOrder"}, values={"displayName,id,fieldSize,hide"},
ejsPath="dwr/metaworks/org/uengine/codi/mw3/ide/form/CommonFormField.ejs",
ejsPathMappingByContext= {
		"{where: 'properties', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/FormFieldModify.ejs'}",
		"{where: 'menu', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Menu.ejs'}"
})
public class DropDownField extends MultipleChoiceField {
	
	public DropDownField(){
		this.setName("DropDownField");
		this.setFieldType("java.lang.String");
		this.setEjsPath("dwr/metaworks/genericfaces/SelectBox.ejs");
	}
}
