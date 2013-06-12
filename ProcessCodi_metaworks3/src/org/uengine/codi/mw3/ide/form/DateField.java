package org.uengine.codi.mw3.ide.form;

import org.metaworks.WebFieldDescriptor;
import org.metaworks.annotation.Face;

public class DateField extends CommonFormField {
	
	String format;
		@Face(ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", options={"YYYY-MM-DD", "DD-MM-YYYY"}, values={"yy-mm-dd", "dd-mm-yy"})
		public String getFormat() {
			return format;
		}
		public void setFormat(String format) {
			this.format = format;
		}

	public DateField() {		
		this.setFieldType("java.util.Date");
		this.setDefine(true);
//		this.setEjsPath("dwr/metaworks/genericfaces/Date.ejs");
	}
	
	@Override
	public boolean equalsType(WebFieldDescriptor fd){
			
		if(fd.getInputFace() != null && fd.getInputFace().equals(this.getEjsPath()) && "java.util.Date".equals(fd.getClassName()) ){
			return true;
		}
		
		return false;
	}
	
	@Override
	public Object apply() {
		this.setValues(makeValueString(this.getFormat()));
		this.setOptions(makeValueString("format"));
				
		super.apply();	
		
		return form;
	}
	
}
