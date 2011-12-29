package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.Face;
import org.uengine.codi.mw3.model.ILogin;

@Face(ejsPath="genericfaces/Window.ejs", displayName="Form Editor", options={"hideLabels"}, values={"true"})

public class Admin {
	
	public Admin(ILogin login){
		formDefinition = new FormDefinition();
		formInstance = new FormInstance();
	}

	FormDefinition formDefinition;

		public FormDefinition getFormDefinition() {
			return formDefinition;
		}
	
		public void setFormDefinition(FormDefinition formDefinition) {
			this.formDefinition = formDefinition;
		}
	
	FormInstance formInstance;
	
		public FormInstance getFormInstance() {
			return formInstance;
		}
	
		public void setFormInstance(FormInstance formInstance) {
			this.formInstance = formInstance;
		}
}
