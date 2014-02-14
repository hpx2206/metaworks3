package org.uengine.codi.mw3.ide.form;

public class UserField extends CommonFormField {
	
	public UserField() {
		this.setName("UserField");
		this.setFieldType("org.uengine.codi.mw3.model.User");
		this.setDefine(true);
	}

	@Override
	public String generateConstructorCode() {
		StringBuffer beforeCompelete = new StringBuffer();
	
		beforeCompelete
		.append(super.generateConstructorCode())
		.append("		" + this.getter())
		.append("().modePicker();\n");
		
		return beforeCompelete.toString();
	}
	
}
