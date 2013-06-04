package org.uengine.codi.mw3.ide.form;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Session;

public class CommonFormField implements ContextAware {
	
	@AutowiredFromClient
	public Session session;
	
	@AutowiredFromClient
	public Form form;
	
	@AutowiredFromClient
	public FormFieldProperties properties;

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	String formfieldId;
		@Id
		@Hidden
		public String getFormfieldId() {
			return formfieldId;
		}
		public void setFormfieldId(String formfieldId) {
			this.formfieldId = formfieldId;
		}
		
	String fieldId;
		@Available(when="modify", where="properties")
		public String getFieldId() {
			return fieldId;
		}
	
		public void setFieldId(String fieldId) {
			this.fieldId = fieldId;
		}
		
	String fieldName;
		@Available(when="modify", where="properties")
		public String getFieldName() {
			return fieldName;
		}
	
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
	
	Boolean hidden;
		@Available(when="modify", where="properties")
		public Boolean getHidden() {
			return hidden;
		}
	
		public void setHidden(Boolean hidden) {
			this.hidden = hidden;
		}
		
	public CommonFormField() {
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setHow("eeeee");
	}
		
	@ServiceMethod(mouseBinding="drag")
	@Available(when="view", where="menu")
	public Session drag() {
		
		return session;
	}
	
	@ServiceMethod(callByContent=true)
	@Available(when="view", where="form")
	public Object modify() {

		return properties;
	}
	
	@ServiceMethod(callByContent=true)
	@Available(when="view", where="form")
	public void copy() {
		System.out.println("aaa");
	}
	
	@ServiceMethod(callByContent=true)
	@Available(when="view", where="form")
	public Object remove() {
		
		form.formFields.remove(this);
		
		return form;
	}
	
	@ServiceMethod(callByContent=true)
	@Available(when="view", where="form")
	public void up() {
		
	}
	
	@ServiceMethod(callByContent=true)
	@Available(when="view", where="form")
	public void down() {
		
	}
	
	@ServiceMethod(callByContent=true)
	@Available(when="view", where="menu")
	public Object add(){
		form.formFields.add(this);
		
		return form;
	}
}
