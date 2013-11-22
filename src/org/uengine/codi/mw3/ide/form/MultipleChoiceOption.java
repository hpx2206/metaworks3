package org.uengine.codi.mw3.ide.form;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;

@Face(ejsPath="dwr/metaworks/org/uengine/codi/mw3/ide/form/MultipleChoiceOption.ejs")
public class MultipleChoiceOption implements ContextAware {

	public final static String OPTION_FIELD_ID_PREFIX = "OPTIONFIELD_";
	
//	@AutowiredFromClient
//	public Form form;				// for action
	
	@AutowiredFromClient
	public FormFieldProperties formFieldProperty;
	
	String fieldId;
		@Hidden
		public String getFieldId() {
			return fieldId;
		}
		public void setFieldId(String fieldId) {
			this.fieldId = fieldId;
		}
	
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
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
		
	public MultipleChoiceOption() {
		this.setMetaworksContext(new MetaworksContext());
	}

	@ServiceMethod(callByContent=true)//, target=ServiceMethodContext.TARGET_SELF)
	@Available(when={MetaworksContext.WHEN_EDIT}, where={"properties"})
	public Object add() {	

		if(formFieldProperty.getFormField() instanceof MultipleChoiceField){
			MultipleChoiceField multipleChoiceField = (MultipleChoiceField)formFieldProperty.getFormField();
			
			MultipleChoiceOption option = new MultipleChoiceOption();
			option.setFieldId(this.makeOptionFieldId());
			option.setOption("");
			option.setValue("");			
			
			multipleChoiceField.getMultipleChoiceOptionPanel().getChoiceOptions().add(option);
			multipleChoiceField.setOptionsAndValues(multipleChoiceField, multipleChoiceField.getMultipleChoiceOptionPanel().getChoiceOptions());
		}
		
		return new Object[]{formFieldProperty};
	}	
		
	@ServiceMethod(callByContent=true)
	public Object[] remove() {
		
		if(formFieldProperty.getFormField() instanceof MultipleChoiceField){
			MultipleChoiceField multipleChoiceField = (MultipleChoiceField)formFieldProperty.getFormField();
			
			int pos = multipleChoiceField.getMultipleChoiceOptionPanel().getChoiceOptions().indexOf(this);
			if(pos > -1){
				multipleChoiceField.getMultipleChoiceOptionPanel().getChoiceOptions().remove(pos);
				multipleChoiceField.setOptionsAndValues(multipleChoiceField, multipleChoiceField.getMultipleChoiceOptionPanel().getChoiceOptions());
			}
		}
		return new Object[]{formFieldProperty};
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
	
	public String makeOptionFieldId() {		
		int id = 0;
		
		MultipleChoiceField multipleChoiceField = (MultipleChoiceField)formFieldProperty.getFormField();
		
		if(multipleChoiceField.getMultipleChoiceOptionPanel().getChoiceOptions() != null && multipleChoiceField.getMultipleChoiceOptionPanel().getChoiceOptions().size() > 0) {
			
			int max_id = 0;
			int cur_id = 0;
			
			for(int i = 0; i <multipleChoiceField.getMultipleChoiceOptionPanel().getChoiceOptions().size(); i++) {								
				MultipleChoiceOption option =  multipleChoiceField.getMultipleChoiceOptionPanel().getChoiceOptions().get(i);
				cur_id = Integer.parseInt(option.getFieldId().replace(OPTION_FIELD_ID_PREFIX, ""));

				if (max_id < cur_id)
					max_id = cur_id;
								
			}
			id = max_id + 1;
		}
			
		return OPTION_FIELD_ID_PREFIX + String.valueOf(id);	
	}
}
