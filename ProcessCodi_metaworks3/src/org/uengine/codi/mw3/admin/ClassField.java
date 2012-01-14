
package org.uengine.codi.mw3.admin;

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
import org.metaworks.example.ide.SourceCode;
import org.metaworks.website.MetaworksFile;

@Face(
	ejsPathMappingByContext=
	{
		"{when: 'edit', face: 'genericfaces/ObjectFace.ejs'}",
		"{when: 'view', face: 'genericfaces/ObjectFace.ejs'}",
		"{when: 'instance-mode', face: 'faces/org/uengine/codi/mw3/admin/FormField.ejs'}",
	}		
)
public class ClassField implements Cloneable, ContextAware{

	public ClassField(){
	}
	
	String fieldName;
		@Id  // TODO: lesson 1 (object addressing and correlation)
		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
	
	String type;
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
						"org.metaworks.website.MetaworksFile", 
						"org.metaworks.example.ide.SourceCode"
				}
		)
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
	String valueString;
	@Hidden
		public String getValueString() {
			return valueString;
		}
		public void setValueString(String valueString) {
			this.valueString = valueString;
		}

	Long valueLong;
	@Hidden
		public Long getValueLong() {
			return valueLong;
		}
		public void setValueLong(Long valueLong) {
			this.valueLong = valueLong;
		}

	Date valueDate;
	@Hidden
		public Date getValueDate() {
			return valueDate;
		}
		public void setValueDate(Date valueDate) {
			this.valueDate = valueDate;
		}
		
	SourceCode valueSourceCode;
	@Hidden	
		public SourceCode getValueSourceCode() {
			return valueSourceCode;
		}
		public void setValueSourceCode(SourceCode valueSourceCode) {
			this.valueSourceCode = valueSourceCode;
		}
		
	MetaworksFile valueFile;
	@Hidden	
		public MetaworksFile getValueFile() {
			return valueFile;
		}
		public void setValueFile(MetaworksFile valueFile) {
			this.valueFile = valueFile;
		}
				
	@ServiceMethod(when=MetaworksContext.WHEN_EDIT, where="newEntry", callByContent=true)
	public ClassDefinition add() throws Exception{

		if(classDefinition.classFields==null)
			classDefinition.classFields = new ArrayList<ClassField>();//new FormField[]{};
		
		//TODO: lesson 3 (validation with throwing exception)
		if(classDefinition.classFields.contains(this))
			throw new Exception("There's already existing field named '" + getFieldName() + "'.");
		ClassField clonedOne = (ClassField) this.clone(); //TODO: lesson 2 (cloning to avoid reflective problem)

		clonedOne.setMetaworksContext(new MetaworksContext());  //TODO: lesson 4 (context injection)
		clonedOne.getMetaworksContext().setWhere("in-container");
		
		classDefinition.classFields.add(clonedOne); 

		//clear the entries for newFormField	//TODO: lesson 6 (context clearing)
		classDefinition.init();
		//
		
		return classDefinition;
	}
		
	@ServiceMethod(when=MetaworksContext.WHEN_EDIT, where="in-container", callByContent=true)
	public ClassDefinition save() throws Exception{
		//TODO: lesson 3 (validation with throwing exception)
		
		int index = classDefinition.classFields.indexOf(this);
		if(index==-1)
			throw new Exception("There's no existing field named '" + getFieldName() + "'.");
					
		classDefinition.classFields.remove(this);
		
		ClassField clonedOne = (ClassField) this.clone(); //TODO: lesson 2 (cloning to avoid reflective problem)
		
		classDefinition.classFields.add(index, clonedOne); 
		
		clonedOne.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		return classDefinition;
	}
		
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW, where="in-container")
	public ClassDefinition remove(){
		classDefinition.classFields.remove(this);
		
		return classDefinition;
	}
	
	//TODO: quiz 2 (when the form field is first order, this button should be shown.
	//              Improve 'up' method and 'down' method not to be shown when it is in the first order and in the last order.
	
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW, where="in-container")
	public ClassDefinition up(){
		int index = classDefinition.classFields.indexOf(this);
		
		if(index>0){
			classDefinition.classFields.remove(this);
			//TODO: quiz 1 (below is not proper since it will clear the type information. Prove why and fix this)
			classDefinition.classFields.add(index-1, this);
		}

		return classDefinition;
	}
	
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW, where="in-container") 
	public ClassDefinition down(){
		int index = classDefinition.classFields.indexOf(this);
		
		if(index<classDefinition.classFields.size()-1){
			classDefinition.classFields.remove(this);      //TODO: lesson 1 (object addressing and correlation)
			//TODO: quiz 1 (below is not proper since it will clear the type information. Prove why and fix this)
			classDefinition.classFields.add(index+1, this);
		}

		return classDefinition;
	}
	
	
	
	@Override //TODO: lesson 1 (object addressing and correlation)
	public boolean equals(Object obj) {
		if(obj==null) return false;
		
		String thisFieldName = this.getFieldName();
		String comparatorFieldName = ((ClassField)obj).getFieldName();
		
		return thisFieldName.equals(comparatorFieldName);
	}
	
	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	
	@AutowiredFromClient  //TODO: lesson 0 (auto-wiring client-side objects)
	transient public ClassDefinition classDefinition;
	

	
	///// context //////  TODO: lesson 4 (context injection)
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
}
