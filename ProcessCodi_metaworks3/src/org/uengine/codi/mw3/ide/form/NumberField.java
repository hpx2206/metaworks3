package org.uengine.codi.mw3.ide.form;

import org.metaworks.WebFieldDescriptor;

public class NumberField extends CommonFormField {

	public NumberField() {
		this.setFieldType("java.lang.int");
		this.setDefine(false);
//		this.setEjsPath("dwr/metaworks/genericfaces/Number.ejs");
	}
	
	@Override
	public boolean equalsType(WebFieldDescriptor fd){
		
		if(fd.getInputFace() != null && fd.getInputFace().equals(this.getEjsPath()) && "java.lang.int".equals(fd.getClassName()) ){
			return true;
		}
		
		return false;
	}
}
