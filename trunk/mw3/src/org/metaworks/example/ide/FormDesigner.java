package org.metaworks.example.ide;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FormDesigner {
	
	public static String[] layoutComponent = new String[]{"Border Layout", "Grid Layout", "Flex Grid", "H/V Layout", "Flow Layout"};
	
	public FormDesigner(){
		setFormDefinition(new FormDefinition());
	}

	
	public Map<Type, String> fieldNames = new HashMap<Type, String>();
		public Map<Type, String> getFieldNames() {
			return fieldNames;
		}
		public void setFieldNames(Map<Type, String> fieldNames) {
			this.fieldNames = fieldNames;
		}

	public ArrayList<String> commonComponent = new ArrayList<String>();
		public ArrayList<String> getCommonComponent() {
			return commonComponent;
		}
		public void setCommonComponent(ArrayList<String> commonComponent) {
			this.commonComponent = commonComponent;
		}	

	public FormDefinition formDefinition;	
		public FormDefinition getFormDefinition() {
			return formDefinition;
		}
	
		public void setFormDefinition(FormDefinition formDefinition) {
			this.formDefinition = formDefinition;
		}	
}
