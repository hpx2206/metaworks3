package org.uengine.codi.mw3.model;

import javax.servlet.http.HttpSession;

import org.metaworks.annotation.Face;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.ClassDefinition;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(ejsPath="genericfaces/Window.ejs", displayName="Form Designer", options={"hideLabels"}, values={"true"})
public class FormDesignerContentPanel extends ContentWindow {
	

	ClassDefinition formDefinition;
		public ClassDefinition getFormDefinition() {
			return formDefinition;
		}
	
		public void setFormDefinition(ClassDefinition formDefinition) {
			this.formDefinition = formDefinition;
		}
	
	public void newForm(String parentFolder){
		HttpSession session = TransactionContext.getThreadLocalInstance().getRequest().getSession(); 
		String userId = (String)session.getAttribute("userId");
		
		User user = new User();
		user.setUserId(userId);
		
		formDefinition = new ClassDefinition();
		formDefinition.getMetaworksContext().setWhere("form");
		formDefinition.setParentFolder(parentFolder);
		formDefinition.setPackageName(parentFolder.replaceAll("/", "."));
		formDefinition.setAuthor(user);
		formDefinition.load();
	}
	
	public void load(String defId) throws Exception{
		String defVerId = codiPmSVC.getProcessDefinitionProductionVersion(defId);
		String resource = codiPmSVC.getResource(defVerId);
		formDefinition = (ClassDefinition) GlobalContext.deserialize(resource, ClassDefinition.class);
		formDefinition.getMetaworksContext().setWhere("form");
	}

	
	@Autowired
	public ProcessManagerRemote codiPmSVC;

}
