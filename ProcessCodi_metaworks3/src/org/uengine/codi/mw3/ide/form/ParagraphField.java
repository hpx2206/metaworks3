package org.uengine.codi.mw3.ide.form;

import org.metaworks.WebFieldDescriptor;

public class ParagraphField extends CommonFormField {
	
	//options = { "rows", "cols" }, values = { "10", "50" }
	
	public ParagraphField() {
		this.setFieldType("java.lang.String");
		this.setDefine(false);
		this.setEjsPath("dwr/metaworks/genericfaces/richText.ejs");
	}
	
	@Override
	public boolean equalsType(WebFieldDescriptor fd){
		System.out.println("fd.getClassType().getName() : " + fd.getClassName());
		if((fd.getInputFace() != null || fd.getInputFace().equals("")) && "java.lang.String".equals(fd.getClassName()) ){
			return true;
		}
		
		return false;
	}

}
