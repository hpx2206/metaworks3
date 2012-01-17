package org.metaworks.example.roomnine;

import java.util.ArrayList;
import java.util.Date;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;

@Face(
	    ejsPathMappingByContext=
		{
			"{when: 'edit', face: 'genericfaces/ObjectFace.ejs'}",
			"{when: 'view', face: 'genericfaces/ObjectFace.ejs'}",
			"{when: 'instance-mode', face: 'faces/org/metaworks/example/roomnine/FormField.ejs'}",
		}		
	)
public class FormField implements Cloneable, ContextAware {

	String fieldName;
	String type;
	String valueString;
	Date valueDate;
	
	@AutowiredFromClient
	public FormDefinition formDefinition;
	
	MetaworksContext metaworksContext;
	
	public FormField() {
		
	}
	
	@Id
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Range(
			options={
					"Text", 
					"Number", 
					"Money", 
					"Date", 
					"File", 
					"Source Code"},
			values ={
					"java.lang.String", 
					"java.lang.Long", 
					"java.lang.Double", 
					"java.util.Date", 
					"org.metaworks.FileContext", 
					"org.metaworks.website.SourceCode"
			}
	)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Hidden
	public String getValueString() {
		return valueString;
	}

	public void setValueString(String valueString) {
		this.valueString = valueString;
	}

	@Hidden
	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}
	
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}

	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}

	
	@ServiceMethod(when=MetaworksContext.WHEN_EDIT, where="newEntry", callByContent=true)
	public FormDefinition add() throws Exception {
		if(formDefinition.formFields == null)
			formDefinition.formFields = new ArrayList<FormField>();
		
		if(formDefinition.formFields.contains(this)) 
			throw new Exception("There's already existing field named '" + getFieldName() + ".");
		
		FormField clonedOne = (FormField)this.clone();
		clonedOne.setMetaworksContext(new MetaworksContext());
		clonedOne.getMetaworksContext().setWhere("in-container");
		
		formDefinition.formFields.add(clonedOne);
		
		formDefinition.clearNewFormField();
		
		return formDefinition;
	}
	
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW,where="in-container")
	public FormDefinition remove() {
		formDefinition.formFields.remove(this);
		return formDefinition;
	}
	
	@ServiceMethod(when=MetaworksContext.WHEN_EDIT, where = "in-container",callByContent=true)
	public FormDefinition save() throws Exception {
		int index = formDefinition.formFields.indexOf(this);
		if(index==1) 
			throw new Exception("There's no existing field named '" + getFieldName() + "'.");
		
		formDefinition.formFields.remove(this);
		
		FormField clonedOne = (FormField)this.clone();
		
		formDefinition.formFields.add(index,clonedOne);
		
		clonedOne.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		return formDefinition;
		
	}
	
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW,where="in_container") 
	public FormDefinition up() {
		int index = formDefinition.formFields.indexOf(this);
		if(index > 0) {
			formDefinition.formFields.remove(this);
			formDefinition.formFields.add(index-1,this);
		}
		
		return formDefinition;
	}
	
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW, where = "in-container") 
	public FormDefinition down() {
		int index = formDefinition.formFields.indexOf(this);
		if(index < formDefinition.formFields.size()-1) {
			formDefinition.formFields.remove(this);
			formDefinition.formFields.add(index+1,this);
		}
		
		return formDefinition;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null) return false;
		
		String thisFieldName = this.getFieldName();
		String comparatorFieldName = ((FormField)obj).getFieldName();
		
		return thisFieldName.equals(comparatorFieldName);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
		
}
