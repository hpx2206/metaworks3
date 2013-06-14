package org.uengine.codi.mw3.ide.form;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;

@Face(ejsPath="dwr/metaworks/org/uengine/codi/mw3/ide/form/MultipleChoiceOption.ejs")
public class MultipleChoiceOption implements ContextAware {

	@AutowiredFromClient(select="parentId == autowiredObject.fieldId && autowiredObject.metaworksContext.where == 'properties'")
	public MultipleChoiceField formField;
			
	String parentId;
//		@Hidden
		public String getParentId() {
			return parentId;
		}
		public void setParentId(String parentId) {
			this.parentId = parentId;
		}
		
	String fieldId;
//		@Hidden
		public String getFieldId() {
			return fieldId;
		}
		public void setFieldId(String fieldId) {
			this.fieldId = fieldId;
		}		

	String option;
		public String getOption() {
			return option;
		}
		public void setOption(String option) {
			this.option = option;
		}
		
	String value;
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}		

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
		
	@ServiceMethod(callByContent=true)
	public Object remove() {
		
		formField.getChoiceOptions().remove(this);		
		
		formField.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		formField.getMetaworksContext().setWhere("properties");
		
		return new FormFieldProperties(formField);
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		
		if(obj instanceof MultipleChoiceOption){
			MultipleChoiceOption mc = (MultipleChoiceOption)obj;
			
			result = this.getFieldId().equals(mc.getFieldId());
		}
		
		return result;
	}
}
