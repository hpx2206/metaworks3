package org.uengine.codi.mw3.ide.form;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;


public class MultipleChoiceOption implements ContextAware {

	@AutowiredFromClient(select="parentId == autowiredObject.fieldId")
	public MultipleChoiceField multipleChoiceField;
			
	String parentId;
		public String getParentId() {
			return parentId;
		}
		public void setParentId(String parentId) {
			this.parentId = parentId;
		}
		
	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
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
		
		multipleChoiceField.getChoiceOptions().remove(this);
		
		
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		this.getMetaworksContext().setWhere("properties");
		
		return new FormFieldProperties(multipleChoiceField);
		
//		return multipleChoiceField;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		
		if(obj instanceof MultipleChoiceOption){
			MultipleChoiceOption mc = (MultipleChoiceOption)obj;
			
			result = this.getId().equals(mc.getId());
		}
		
		return result;
	}	
}
