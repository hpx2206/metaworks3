package org.uengine.codi.mw3.ide.form;

import org.metaworks.WebFieldDescriptor;

public class FileUploadField extends CommonFormField {

	public FileUploadField() {
		this.setFieldType("org.metaworks.website.MetaworksFile");
		this.setDefine(true);
//		this.setEjsPath("dwr/metaworks/genericfaces/MetaworksFile.ejs");
	}
	
	@Override
	public boolean equalsType(WebFieldDescriptor fd){
			
		if(fd.getInputFace() != null && fd.getInputFace().equals(this.getEjsPath()) && "org.metaworks.website.MetaworksFile".equals(fd.getClassName()) ){
			return true;
		}
		
		return false;
	}
}
