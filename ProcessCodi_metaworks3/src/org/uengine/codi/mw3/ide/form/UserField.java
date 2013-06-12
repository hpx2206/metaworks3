package org.uengine.codi.mw3.ide.form;

import org.metaworks.WebFieldDescriptor;

public class UserField extends CommonFormField {
	
	public UserField() {
		this.setFieldType("org.uengine.codi.mw3.model.User");
		this.setDefine(true);
	}
	
	@Override
	public boolean equalsType(WebFieldDescriptor fd){
			
		System.out.println("fd.getClassType().getName() : " + fd.getClassName());
		if(fd.getInputFace() != null && fd.getInputFace().equals(this.getEjsPath()) && "org.uengine.codi.mw3.model.IUser".equals(fd.getClassName()) ){
			return true;
		}
		
		return false;
	}
}
