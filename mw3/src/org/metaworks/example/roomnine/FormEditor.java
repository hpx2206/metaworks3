package org.metaworks.example.roomnine;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

@Face(
		ejsPath="genericfaces/Window.ejs",
		options={"hideLabels"},
		values ={"true"}
		)


public class FormEditor {
	
	public FormEditor(){
		setFormDefinition(new FormDefinition());
	}

	public FormDefinition formDefinition;
		public FormInstance getFormInstance() {
			return formInstance;
		}
		
		
		public void setFormDefinition(FormDefinition formDefinition) {
			this.formDefinition = formDefinition;
		}
	
	public FormInstance formInstance;
		public void setFormInstance(FormInstance formInstance) {
			this.formInstance = formInstance;
		}
		
		public FormDefinition getFormDefinition() {
			return formDefinition;
		}
	
	
	@ServiceMethod
	public void load() throws Exception 
	{
		formDefinition = new FormDefinition();
		formInstance = new FormInstance();
	}
	 
}
