package org.uengine.codi.mw3.ide.form;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;

public class MultipleChoiceField extends CommonFormField {
	
	public final static String OPTION_FIELD_ID_PREFIX = "OPTIONFIELD_";
	
	ArrayList<MultipleChoiceOption> choiceOptions;
		@Face(displayName="$multipleChoiceOption.displayname")
		@Available(when={MetaworksContext.WHEN_EDIT}, where={"properties"})
		public ArrayList<MultipleChoiceOption> getChoiceOptions() {
			return choiceOptions;
		}
		public void setChoiceOptions(ArrayList<MultipleChoiceOption> choiceOptions) {
			this.choiceOptions = choiceOptions;
		}
		
	int selectedIndex;
		@Hidden
		public int getSelectedIndex() {
			return selectedIndex;
		}
		public void setSelectedIndex(int selectedIndex) {
			this.selectedIndex = selectedIndex;
		}

	public MultipleChoiceField(){
		this.setFieldType("java.lang.String");
		this.setEjsPath("dwr/metaworks/genericfaces/RadioButton.ejs");
		this.setChoiceOptions(new ArrayList<MultipleChoiceOption>());
	}
	
	@Override
	public void init(){
		super.init();
		this.setChoiceOptions(new ArrayList<MultipleChoiceOption>());
	}
	
	@ServiceMethod(callByContent=true)//, target=ServiceMethodContext.TARGET_SELF)
	@Available(when={MetaworksContext.WHEN_EDIT}, where={"properties"})
	public Object addOption() {	
		
		MultipleChoiceOption option = new MultipleChoiceOption();
		option.setParentId(this.getFieldId());
		option.setFieldId(this.makeOptionFieldId());
		option.setOption("");
		option.setValue("");
		
		this.getChoiceOptions().add(option);
		
		return new FormFieldProperties(this);
		//return this;
	}
		
	@Override
	public Object apply() {
		
		if(this.getChoiceOptions() != null && this.getChoiceOptions().size() > 0) {
			StringBuffer optionsBuffer = new StringBuffer();
			StringBuffer valuesBuffer = new StringBuffer();
			
			for(int i = 0; i < this.getChoiceOptions().size(); i++) {
				
				MultipleChoiceOption choiceOption = this.getChoiceOptions().get(i);
				
				optionsBuffer
				.append(makeValueString(choiceOption.getOption()));
				
				valuesBuffer
				.append(makeValueString(choiceOption.getValue()));
				
				if(i != this.getChoiceOptions().size()-1) {
					optionsBuffer.append(", ");					
					valuesBuffer.append(", ");
				}				
			}
			
			this.setOptions(optionsBuffer.toString());
			this.setValues(valuesBuffer.toString());
		}
		
		super.apply();
		
		return form;
	}
	
	@Override
	public CommonFormField make(WebFieldDescriptor fd)  {
		
		CommonFormField formField = super.make(fd);
		int field_id = Integer.parseInt(formField.getFieldId().replace(Form.FORM_FIELD_ID_PREFIX, ""));
		
		if (fd.getOptions().length > 0) {
			Object[] options = fd.getOptions();
			Object[] values = fd.getValues();
			
			ArrayList<MultipleChoiceOption> choiceOptions = new ArrayList<MultipleChoiceOption>();
			
			for(int i = 0; i < options.length; i++) {
				MultipleChoiceOption mc = new MultipleChoiceOption();
				mc.setParentId(formField.getFieldId());
				mc.setFieldId(makeOptionFieldId());
				mc.setOption((String)options[i]);
				mc.setValue((String)values[i]);
				choiceOptions.add(mc);
			}
			
			((MultipleChoiceField)formField).setChoiceOptions(choiceOptions);
		}
		
		return formField;		
	}
	
	public String makeOptionFieldId() {	
		int id = 0;
		
		if(this.getChoiceOptions() != null && this.getChoiceOptions().size() > 0) {
			
			int max_id = 0;
			int cur_id = 0;
			
			for(int i = 0; i <this.getChoiceOptions().size(); i++) {								
				MultipleChoiceOption option =  this.getChoiceOptions().get(i);
				cur_id = Integer.parseInt(option.getFieldId().replace(OPTION_FIELD_ID_PREFIX, ""));

				if (max_id < cur_id)
					max_id = cur_id;
								
			}
			id = max_id + 1;
		}
		
		return OPTION_FIELD_ID_PREFIX + String.valueOf(id);
	}
}
