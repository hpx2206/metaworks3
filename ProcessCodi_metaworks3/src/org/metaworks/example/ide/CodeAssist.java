package org.metaworks.example.ide;

import java.util.ArrayList;

import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.admin.EntityDefinition;
import org.uengine.codi.mw3.model.WebServiceAdapterContentPanel;
import org.uengine.codi.mw3.model.WebServiceDefinition;

public class CodeAssist {
	
	public CodeAssist(){
		setDocument(new CodeAssistDocument());
		
		setEntityDefinition(new EntityDefinition());
		setWebServiceDefinition(new WebServiceDefinition());
	}
	
	ArrayList<String> assistances;
		public ArrayList<String> getAssistances() {
			return assistances;
		}
		public void setAssistances(ArrayList<String> assistances) {
			this.assistances = assistances;
		}
		
	String srcCodeObjectId;
		public String getSrcCodeObjectId() {
			return srcCodeObjectId;
		}
		public void setSrcCodeObjectId(String srcCodeObjectId) {
			this.srcCodeObjectId = srcCodeObjectId;
		}
		
	String selectedItem;
		public String getSelectedItem() {
			return selectedItem;
		}
		public void setSelectedItem(String selectedItem) {
			this.selectedItem = selectedItem;
		}
		
	String lineAssistRequested;
			
		public String getLineAssistRequested() {
			return lineAssistRequested;
		}
		public void setLineAssistRequested(String lineAssistRequested) {
			this.lineAssistRequested = lineAssistRequested;
		}

	CodeAssistDocument document; 
			
		public CodeAssistDocument getDocument() {
			return document;
		}
		public void setDocument(CodeAssistDocument document) {
			this.document = document;
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
	
	@ServiceMethod(callByContent=true)
	public CodeAssistDocument showDoc(){		
		return new CodeAssistDocument(this);
	}
	
}
