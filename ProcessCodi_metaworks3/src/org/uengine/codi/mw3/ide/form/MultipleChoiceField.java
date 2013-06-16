package org.uengine.codi.mw3.ide.form;

import java.util.ArrayList;

import org.metaworks.WebFieldDescriptor;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;

public class MultipleChoiceField extends CommonFormField {
	
	int selectedIndex;
		@Hidden
		public int getSelectedIndex() {
			return selectedIndex;
		}
		public void setSelectedIndex(int selectedIndex) {
			this.selectedIndex = selectedIndex;
		}

	public MultipleChoiceField(){
		
		this.setName("MultipleChoice");
		this.setDisplayName("MultipleChoice");
		this.setFieldType("java.lang.String");
		this.setEjsPath("dwr/metaworks/genericfaces/RadioButton.ejs");	
		
		MultipleChoiceOptionPanel panel = new MultipleChoiceOptionPanel();
		panel.init();
		
		this.setMultipleChoiceOptionPanel(panel);
	}
	
	MultipleChoiceOptionPanel multipleChoiceOptionPanel;
		@Face(displayName="")
		public MultipleChoiceOptionPanel getMultipleChoiceOptionPanel() {
			return multipleChoiceOptionPanel;
		}
		public void setMultipleChoiceOptionPanel(
				MultipleChoiceOptionPanel multipleChoiceOptionPanel) {
			this.multipleChoiceOptionPanel = multipleChoiceOptionPanel;
		}		

	@Override
	public CommonFormField make(WebFieldDescriptor fd)  {
		
		CommonFormField formField = super.make(fd);
			
		if (fd.getOptions().length > 0) {
			Object[] options = fd.getOptions();
			Object[] values = fd.getValues();
			
			ArrayList<MultipleChoiceOption> choiceOptions = new ArrayList<MultipleChoiceOption>();
			
			for(int i = 0; i < options.length; i++) {
				MultipleChoiceOption mc = new MultipleChoiceOption();
//				mc.setParentId(formField.getFieldId());
				mc.setFieldId(MultipleChoiceOption.OPTION_FIELD_ID_PREFIX + String.valueOf(i));
//				mc.setFieldId(makeOptionFieldId()); 
				mc.setOption((String)options[i]);
				mc.setValue((String)values[i]);
				choiceOptions.add(mc);
			}
			
			((MultipleChoiceField)formField).getMultipleChoiceOptionPanel().setChoiceOptions(choiceOptions);
			
			this.setOptionsAndValues((MultipleChoiceField)formField, choiceOptions);	
		}		
		
		return formField;		
	}
	

	public void setOptionsAndValues(MultipleChoiceField multipleChoiceField, ArrayList<MultipleChoiceOption> choiceOptions) {
		
		if(choiceOptions != null && choiceOptions.size() > 0) {
		
			StringBuffer optionsBuffer = new StringBuffer();
			StringBuffer valuesBuffer = new StringBuffer();
			
			for(int i = 0; i < choiceOptions.size(); i++) {
				
				MultipleChoiceOption choiceOption = choiceOptions.get(i);
				
				optionsBuffer
				.append(makeValueString(choiceOption.getOption()));
				
				valuesBuffer
				.append(makeValueString(choiceOption.getValue()));
				
				if(i != choiceOptions.size()-1) {
					optionsBuffer.append(", ");					
					valuesBuffer.append(", ");
				}				
			}
			
			multipleChoiceField.setOptions(optionsBuffer.toString());
			multipleChoiceField.setValues(valuesBuffer.toString());
		}
	}
}
