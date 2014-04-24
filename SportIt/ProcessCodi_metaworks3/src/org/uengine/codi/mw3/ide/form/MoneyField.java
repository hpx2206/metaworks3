package org.uengine.codi.mw3.ide.form;

import org.metaworks.WebFieldDescriptor;
import org.metaworks.annotation.Face;

public class MoneyField extends CommonFormField {
	
	String format;
	@Face(ejsPath="dwr/metaworks/genericfaces/SelectBox.ejs",
			options={"$$ Dollars", "€ Euros", "£ Pounds", "¥ Yen", "￦ 원"}, values={"$$", "€ ", "£", "¥", "￦"})
		public String getFormat() {
			return format;
		}	
		public void setFormat(String format) {
			this.format = format;
		}

	public MoneyField() {		
		this.setFieldType("String");
		this.setEjsPath("dwr/metaworks/genericfaces/MoneyField.ejs");
		
	}
	
	@Override
	public Object[] apply() throws Exception{
		this.setValues(this.getFormat());
		this.setOptions("format");
				
		return super.apply();
	}
}
