package org.uengine.codi.mw3.ide.form;

public class FileUploadField extends CommonFormField {

	public FileUploadField() {
		this.setName("FileUpload");
		this.setDisplayName("FileUpload");
		this.setFieldType("org.metaworks.website.MetaworksFile");
		this.setDefine(true);
//		this.setEjsPath("dwr/metaworks/genericfaces/MetaworksFile.ejs");
	}
}
