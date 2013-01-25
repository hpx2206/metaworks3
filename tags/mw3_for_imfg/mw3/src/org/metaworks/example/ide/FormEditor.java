package org.metaworks.example.ide;

import org.metaworks.annotation.Face;

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
	
		public FormDefinition getFormDefinition() {
			return formDefinition;
		}
	
		public void setFormDefinition(FormDefinition formDefinition) {
			this.formDefinition = formDefinition;
		}
}
