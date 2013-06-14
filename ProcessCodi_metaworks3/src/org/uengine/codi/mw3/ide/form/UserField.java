package org.uengine.codi.mw3.ide.form;

import org.metaworks.annotation.Face;

@Face(displayName="$form.field.UserField",
		options={"hideEditBtn"}, values={"true"},
		ejsPathMappingByContext={
			"{where: 'form', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/UserField.ejs'}",
			"{where: 'properties', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Properties.ejs'}",
			"{where: 'menu', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Menu.ejs'}"
		})
public class UserField extends CommonFormField {
	
	public UserField() {
		this.setFieldType("org.uengine.codi.mw3.model.User");
		this.setDefine(true);
	}

}
