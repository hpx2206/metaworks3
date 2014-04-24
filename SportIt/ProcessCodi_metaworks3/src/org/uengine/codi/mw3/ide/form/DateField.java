package org.uengine.codi.mw3.ide.form;

import org.metaworks.WebFieldDescriptor;
import org.metaworks.annotation.Face;

@Face(options={"fieldOrder"}, values={"displayName,id,format,hide"},
ejsPath="dwr/metaworks/org/uengine/codi/mw3/ide/form/CommonFormField.ejs",
ejsPathMappingByContext= {
		"{where: 'properties', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/FormFieldModify.ejs'}",
		"{where: 'menu', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Menu.ejs'}"
})
public class DateField extends CommonFormField {
	
	String format;
		@Face(displayName="$form.field.date.format", ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs", options={"YYYY-MM-DD", "DD-MM-YYYY"}, values={"yy-mm-dd", "dd-mm-yy"})
		public String getFormat() {
			return format;
		}
		public void setFormat(String format) {
			this.format = format;
		}

	public DateField() {		
		this.setName("DateField");
		this.setFormat("yy-mm-dd");
		this.setFieldType("java.util.Date");
		this.setDefine(true);
//		this.setEjsPath("dwr/metaworks/genericfaces/Date.ejs");
	}
	
	@Override
	public CommonFormField make(WebFieldDescriptor fd)  {
		
		CommonFormField formField = super.make(fd);
		
		if(fd.getOptions() != null){
			formField.setOptions(makeValueString((String)fd.getOptions()[0]));
			formField.setValues(makeValueString((String)fd.getValues()[0]));
		}
		
		return formField;		
	}
	
	@Override
	public Object[] apply() throws Exception {
		this.setValues(makeValueString(this.getFormat()));
		this.setOptions(makeValueString("format"));
				
		return super.apply();
	}
	
}
