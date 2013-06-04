package org.uengine.codi.mw3.ide.form;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Session;

public class SingleTextField extends CommonFormField {
		
//	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND, mouseBinding="drag")
//	public Object drag() {
//		
//		SingleTextField singleTextField = new SingleTextField();
//		singleTextField.setMetaworksContext(new MetaworksContext());
//		singleTextField.getMetaworksContext().setHow("view");
////		form.formFields.add(singleTextField);
//		
//		return new ToAppend(form, singleTextField);
//	}
	
	@Available(when="view", where="menu")
	public Session drag() {
		
		SingleTextField singleTextField = new SingleTextField();
		singleTextField.setFormfieldId("Formfield_" + form.formFields.size());
		singleTextField.setMetaworksContext(new MetaworksContext());
		singleTextField.getMetaworksContext().setWhen("view");
		singleTextField.getMetaworksContext().setWhere("form");
		
		session.setClipboard(singleTextField);
		
		return session;
	}
		
	@Available(when="view", where="form")
	public Object modify() {			

		SingleTextField singleTextField = new SingleTextField();
//		singleTextField.setFormfieldId(this.getFormfieldId());
		singleTextField.setMetaworksContext(new MetaworksContext());
		singleTextField.getMetaworksContext().setWhen("modify");
		singleTextField.getMetaworksContext().setWhere("properties");
		
		properties.setFormField(singleTextField);

		return properties;
	}
	
}
