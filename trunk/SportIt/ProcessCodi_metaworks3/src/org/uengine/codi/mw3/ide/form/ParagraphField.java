package org.uengine.codi.mw3.ide.form;

import org.metaworks.MetaworksContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

@Face(options={"fieldOrder"}, values={"displayName,id,rows,cols,hide"},
ejsPath="dwr/metaworks/org/uengine/codi/mw3/ide/form/CommonFormField.ejs",
ejsPathMappingByContext= {
		"{where: 'properties', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/FormFieldModify.ejs'}",
		"{where: 'menu', face: 'dwr/metaworks/org/uengine/codi/mw3/ide/form/Menu.ejs'}"
})
public class ParagraphField extends CommonFormField {
	
	//options = { "rows", "cols" }, values = { "10", "50" }
	int rows;
		public int getRows() {
			return rows;
		}
		public void setRows(int rows) {
			this.rows = rows;
		}

	int cols;
		public int getCols() {
			return cols;
		}
		public void setCols(int cols) {
			this.cols = cols;
		}
	
	public ParagraphField() {
		this.setName("ParagraphField");
		this.setFieldType("java.lang.String");
		this.setEjsPath("dwr/metaworks/genericfaces/richText.ejs");
	}
	
	@Override
	public CommonFormField make(WebFieldDescriptor fd)  {
		
		CommonFormField formField = super.make(fd);
			
		if (fd.getOptions().length > 0) {
			Object[] options = fd.getOptions();
			Object[] values = fd.getValues();
			int col = 0;
			int row = 0;
			for(int i = 0; i < options.length; i++) {
				if( "cols".equals(options[i]) ){
					col = Integer.parseInt((String)values[i]);
				}
				if( "rows".equals(options[i]) ){
					row = Integer.parseInt((String)values[i]);
				}
			}
			
			this.setOptionsAndValues((ParagraphField)formField, col, row);	
		}		
		
		return formField;		
	}

	public void setOptionsAndValues(ParagraphField paragraphField, int cols, int rows) {
		
		
		StringBuffer optionsBuffer = new StringBuffer();
		StringBuffer valuesBuffer = new StringBuffer();
		
		if( cols > 0 ){
			optionsBuffer.append(makeValueString("cols"));
			valuesBuffer.append(makeValueString(cols+""));
		}
		if( rows > 0 ){
			if( cols > 0 ){
				optionsBuffer.append(", ");					
				valuesBuffer.append(", ");
			}
			optionsBuffer.append(makeValueString("rows"));
			valuesBuffer.append(makeValueString(rows+""));
		}
		
		paragraphField.setCols(cols);
		paragraphField.setRows(rows);
		paragraphField.setOptions(optionsBuffer.toString());
		paragraphField.setValues(valuesBuffer.toString());
	}
		
	@ServiceMethod(callByContent=true)
	@Available(when={MetaworksContext.WHEN_EDIT}, where={"properties"})
	public Object[] apply() throws Exception{
		
		this.setOptionsAndValues(this, this.getCols(), this.getRows());
		super.apply();
		
		return new Object[]{form, formFieldProperty};
	}
}
