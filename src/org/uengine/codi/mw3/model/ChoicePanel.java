package org.uengine.codi.mw3.model;

public class ChoicePanel {
	String[] optionValues;
	String[] optionNames;
	
	public String[] getOptionValues() {
		return optionValues;
	}
	public void setOptionValues(String[] optionValues) {
		this.optionValues = optionValues;
	}
	public String[] getOptionNames() {
		return optionNames;
	}
	public void setOptionNames(String[] optionNames) {
		this.optionNames = optionNames;
	}
	
	String selected;
		public String getSelected() {
			return selected;
		}
		public void setSelected(String selected) {
			this.selected = selected;
		}
		
	String selectedText;
		public String getSelectedText() {
			return selectedText;
		}
		public void setSelectedText(String selectedText) {
			this.selectedText = selectedText;
		}
	
	public void setSelectedValue(String value) {
		for(int i=0;  i < getOptionValues().length; i++) {
			if(value.equals(getOptionValues()[i])) {
				setSelected(value);
				setSelectedText(getOptionNames()[i]);
			}
		}
	}
	
	public void setSelectedTextValue(String value) {
		for(int i=0;  i < getOptionNames().length; i++) {
			if(value.equals(getOptionNames()[i])) {
				setSelectedText(value);
				setSelected(getOptionValues()[i]);
			}
		}
	}
}
