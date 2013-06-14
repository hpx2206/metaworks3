package org.uengine.codi.mw3.ide.form;

import org.metaworks.annotation.Face;

@Face(displayName="$form.field.FileUploadField",
		options={"hideEditBtn"}, values={"true"},
		ejsPathMappingByContext={
			"{where: 'form', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/FileUploadField.ejs'}",
			"{where: 'properties', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Properties.ejs'}",
			"{where: 'menu', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Menu.ejs'}"
})
public class FileUploadField extends CommonFormField {

	public FileUploadField() {
		this.setFieldType("org.metaworks.website.MetaworksFile");
		this.setDefine(true);
//		this.setEjsPath("dwr/metaworks/genericfaces/MetaworksFile.ejs");
	}
}
