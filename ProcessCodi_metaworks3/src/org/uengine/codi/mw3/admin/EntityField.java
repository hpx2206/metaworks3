
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
public class EntityField implements Cloneable, ContextAware{

	public EntityField(){
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
	public EntityDefinition add() throws Exception{

		if(entityDefinition.entityFields==null)
			entityDefinition.entityFields = new ArrayList<EntityField>();//new FormField[]{};
		
		//TODO: lesson 3 (validation with throwing exception)
		if(entityDefinition.entityFields.contains(this))
			throw new Exception("There's already existing field named '" + getFieldName() + "'.");
		EntityField clonedOne = (EntityField) this.clone(); //TODO: lesson 2 (cloning to avoid reflective problem)

		clonedOne.setMetaworksContext(new MetaworksContext());  //TODO: lesson 4 (context injection)
		clonedOne.getMetaworksContext().setWhere("in-container");
		
		entityDefinition.entityFields.add(clonedOne); 

		//clear the entries for newFormField	//TODO: lesson 6 (context clearing)
		entityDefinition.init();
		//
		
		return entityDefinition;
	}
		
	@ServiceMethod(when=MetaworksContext.WHEN_EDIT, where="in-container", callByContent=true)
	public EntityDefinition save() throws Exception{
		//TODO: lesson 3 (validation with throwing exception)
		
		int index = entityDefinition.entityFields.indexOf(this);
		if(index==-1)
			throw new Exception("There's no existing field named '" + getFieldName() + "'.");
					
		entityDefinition.entityFields.remove(this);
		
		EntityField clonedOne = (EntityField) this.clone(); //TODO: lesson 2 (cloning to avoid reflective problem)
		
		entityDefinition.entityFields.add(index, clonedOne); 
		
		clonedOne.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		return entityDefinition;
	}
		
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW, where="in-container")
	public EntityDefinition remove(){
		entityDefinition.entityFields.remove(this);
		
		return entityDefinition;
	}
	
	//TODO: quiz 2 (when the form field is first order, this button should be shown.
	//              Improve 'up' method and 'down' method not to be shown when it is in the first order and in the last order.
	
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW, where="in-container")
	public EntityDefinition up(){
		int index = entityDefinition.entityFields.indexOf(this);
		
		if(index>0){
			entityDefinition.entityFields.remove(this);
			//TODO: quiz 1 (below is not proper since it will clear the type information. Prove why and fix this)
			entityDefinition.entityFields.add(index-1, this);
		}

		return entityDefinition;
	}
	
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW, where="in-container") 
	public EntityDefinition down(){
		int index = entityDefinition.entityFields.indexOf(this);
		
		if(index<entityDefinition.entityFields.size()-1){
			entityDefinition.entityFields.remove(this);      //TODO: lesson 1 (object addressing and correlation)
			//TODO: quiz 1 (below is not proper since it will clear the type information. Prove why and fix this)
			entityDefinition.entityFields.add(index+1, this);
		}

		return entityDefinition;
	}
	
	
	
	@Override //TODO: lesson 1 (object addressing and correlation)
	public boolean equals(Object obj) {
		if(obj==null) return false;
		
		String thisFieldName = this.getFieldName();
		String comparatorFieldName = ((EntityField)obj).getFieldName();
		
		return thisFieldName.equals(comparatorFieldName);
	}
	
	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	
	@AutowiredFromClient  //TODO: lesson 0 (auto-wiring client-side objects)
	transient public EntityDefinition entityDefinition;
	

	
	///// context //////  TODO: lesson 4 (context injection)
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
}
