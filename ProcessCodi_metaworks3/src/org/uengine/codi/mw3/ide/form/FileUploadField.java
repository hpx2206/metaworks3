package org.uengine.codi.mw3.ide.form;

import org.metaworks.WebFieldDescriptor;

public class FileUploadField extends CommonFormField {

	public FileUploadField() {
		this.setFieldType("org.metaworks.website.MetaworksFile");
		this.setDefine(true);
//		this.setEjsPath("dwr/metaworks/genericfaces/MetaworksFile.ejs");
	}
}
