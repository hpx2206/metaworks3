package org.uengine.codi.mw3.admin;

import org.metaworks.annotation.ServiceMethod;
import org.metaworks.example.ide.CodeAssist;
import org.metaworks.example.ide.CodeAssistDocument;
import org.uengine.codi.mw3.model.WebServiceDefinition;

public class JavaCodeAssist extends CodeAssist{
	
	public JavaCodeAssist(){
		super();
		
		setEntityDefinition(new EntityDefinition());
		setWebServiceDefinition(new WebServiceDefinition());
		
	}
	
	EntityDefinition entityDefinition;
		public EntityDefinition getEntityDefinition() {
			return entityDefinition;
		}
		public void setEntityDefinition(EntityDefinition entityDefinition) {
			this.entityDefinition = entityDefinition;
		}
		
	WebServiceDefinition webServiceDefinition;		
		public WebServiceDefinition getWebServiceDefinition() {
			return webServiceDefinition;
		}
		public void setWebServiceDefinition(WebServiceDefinition webServiceDefinition) {
			this.webServiceDefinition = webServiceDefinition;
		}
		
	String extendImportValue;	
		public String getExtendImportValue() {
			return extendImportValue;
		}
		public void setExtendImportValue(String extendImportValue) {
			this.extendImportValue = extendImportValue;
		}
		
	@ServiceMethod(callByContent=true)
	public void ExtendImport(){		
	}
	
	@ServiceMethod(callByContent=true)
	public CodeAssistDocument showDoc(){		
		return new CodeAssistDocument(this);
	}
	
}
