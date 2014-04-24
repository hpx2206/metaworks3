package org.uengine.codi.mw3.ide.form;

import org.metaworks.annotation.Face;
import org.uengine.util.UEngineUtil;

@Face(options={"fieldOrder"}, values={"displayName,id,hide"},
ejsPath="dwr/metaworks/org/uengine/codi/mw3/ide/form/CommonFormField.ejs",
ejsPathMappingByContext= {
		"{where: 'properties', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/FormFieldModify.ejs'}",
		"{where: 'menu', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Menu.ejs'}"
})
public class FileUploadField extends CommonFormField {

	public FileUploadField() {
		this.setName("FileUploadField");
		this.setFieldType("org.metaworks.website.MetaworksFile");
		this.setDefine(true);
//		this.setEjsPath("dwr/metaworks/genericfaces/MetaworksFile.ejs");
	}

	@Override
	public String generateBeforeComplete() {
		StringBuffer beforeCompelete = new StringBuffer();
	
		beforeCompelete
		.append(super.generateBeforeComplete())
		.append("		if(" + this.getter() + "().ableUpload())\n")
		.append("			").append(this.getter())
		.append("().upload();\n");
		
		return beforeCompelete.toString();
	}

}
