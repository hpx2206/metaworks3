package org.uengine.codi.mw3.ide.form;

import org.metaworks.WebFieldDescriptor;

public class SingleTextField extends CommonFormField {
	
	public SingleTextField() {
		this.setFieldType("String");
	}
	
	@Override
	public boolean equalsType(WebFieldDescriptor fd){
		System.out.println("fd.getClassType().getName() : " + fd.getClassName());
		if((fd.getInputFace() == null || fd.getInputFace().equals("")) && "java.lang.String".equals(fd.getClassName()) ){
			return true;
		}
		
		return false;
	}
}
