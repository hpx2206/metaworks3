package org.uengine.codi.mw3.ide.form;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.WebFieldDescriptor;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;

public class MultipleChoiceField extends CommonFormField {
		
	ArrayList<MultipleChoiceOption> choiceOptions;
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
		this.setFieldType("String");
		this.setEjsPath("genericfaces/RadioButton.ejs");
	}
	
	@Override
	public void init(){
		super.init();
		this.setChoiceOptions(new ArrayList<MultipleChoiceOption>());
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
	@Available(when={MetaworksContext.WHEN_EDIT}, where={"properties"})
	public Object addOption() {
		if(choiceOptions == null)
			choiceOptions = new ArrayList<MultipleChoiceOption>();
		
		MultipleChoiceOption option = new MultipleChoiceOption();
		option.setParentId(this.getFieldId());
		option.setId(form.createFormFieldId());
		option.setOption("");
		option.setValue("");
		choiceOptions.add(option);
		
		return this;
	}
		
	@Override
	public Object save() {
		
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
		
		super.save();
		
		return form;
	}
	
	@Override
	public boolean equalsType(WebFieldDescriptor fd){
			
		System.out.println("fd.getClassType().getName() : " + fd.getClassName());
		if(fd.getInputFace() != null && fd.getInputFace().equals(this.getEjsPath()) && "java.lang.String".equals(fd.getClassName()) ){
			return true;
		}
		
		return false;
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
				mc.multipleChoiceField = (MultipleChoiceField)formField;
				mc.setParentId(formField.getFieldId());
				mc.setId(Form.FORM_FIELD_ID_PREFIX + String.valueOf(++field_id));
				mc.setOption((String)options[i]);
				mc.setValue((String)values[i]);
				choiceOptions.add(mc);
			}
			
			((MultipleChoiceField)formField).setChoiceOptions(choiceOptions);
		}
		
		return formField;		
	}

}
