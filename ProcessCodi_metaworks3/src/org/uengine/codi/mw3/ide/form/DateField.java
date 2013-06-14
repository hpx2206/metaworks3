package org.uengine.codi.mw3.ide.form;

import org.metaworks.WebFieldDescriptor;
import org.metaworks.annotation.Face;

@Face(displayName="$form.field.DateField",
		options={"hideEditBtn"}, values={"true"},
		ejsPathMappingByContext={
			"{where: 'form', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/DateField.ejs'}",
			"{where: 'properties', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Properties.ejs'}",
			"{where: 'menu', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Menu.ejs'}"
})
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
		this.setDisplayName("$form.field.DateField");
//		this.setEjsPath("dwr/metaworks/genericfaces/Date.ejs");
	}
	
	@Override
	public CommonFormField make(WebFieldDescriptor fd)  {
		
		CommonFormField formField = super.make(fd);
		
		formField.setOptions(makeValueString((String)fd.getOptions()[0]));
		formField.setValues(makeValueString((String)fd.getValues()[0]));
		
		return formField;		
	}
	
	@Override
	public Object apply() {
		this.setValues(makeValueString(this.getFormat()));
		this.setOptions(makeValueString("format"));
				
		super.apply();	
		
		return form;
	}
	
}
