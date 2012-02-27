
package org.uengine.codi.mw3.admin;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;

@Face(options={"hideNewBtn"},
	  values={"true"})   
public class EntityField implements Cloneable, ContextAware{

	public EntityField(){		
	}
	
	boolean key;
		@Face(displayName="KEY")
		public boolean isKey() {
			return key;
		}
		public void setKey(boolean key) {
			this.key = key;
		}
	
	String name;	
		@Id
		@Face(displayName="Name")
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	
	String dataType;
		@Range(
				options={
						"INT", 
						"CHAR",
						"VARCHAR",
						"DATETIME",
						"TIMESTAMP",
						},
						
						
				values ={
						"INT", 
						"CHAR",
						"VARCHAR",
						"DATETIME",
						"TIMESTAMP"
					}
		)
		@Face(displayName="DataType")
		public String getDataType() {
			return dataType;
		}
		public void setDataType(String dataType) {
			this.dataType = dataType;
		}

	int length;
		@Face(displayName="Length/Set")
		@Range(
				size=5
		)
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			this.length = length;
		}

	/*
	boolean unsinged;	
		@Face(displayName="Unsinged")
		public boolean isUnsinged() {
			return unsinged;
		}
		public void setUnsinged(boolean unsinged) {
			this.unsinged = unsinged;
		}
	*/
		
	boolean allowNull;
		@Face(displayName="Allow NULL")
		public boolean isAllowNull() {
			return allowNull;
		}
		public void setAllowNull(boolean allowNull) {
			this.allowNull = allowNull;
		}

	String defaultValue;	
		@Face(displayName="Default")
		public String getDefaultValue() {
			return defaultValue;
		}
		public void setDefaultValue(String defaultValue) {
			this.defaultValue = defaultValue;
		}
		
	
	String comment;
		@Face(displayName="Comment")
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}		

	String clientObjectId;
		public String getClientObjectId() {
			return clientObjectId;
		}
		public void setClientObjectId(String clientObjectId) {
			this.clientObjectId = clientObjectId;
		}		
	
	@ServiceMethod(when=MetaworksContext.WHEN_EDIT, where="newEntry", callByContent=true)
	public EntityModeler add() throws Exception{
		
		// validate
		if(getName() == null)
			throw new Exception("Name is required.");
		
		if(!(getDataType().equals("TIMESTAMP") || getDataType().equals("DATETIME"))){
			if(getLength() == 0)
				throw new Exception("Length/Set is required.");
		}

		// add
		EntityModeler entityModeler = entityDefinition.getEntityModeler();
		
		if(entityModeler.getEntityFields() == null)
			entityModeler.setEntityFields(new ArrayList<EntityField>());
		
		if(entityModeler.getEntityFields().contains(this))
			throw new Exception("There's already existing field named '" + getName() + "'.");
		
		EntityField clonedOne = (EntityField) this.clone();
		clonedOne.setMetaworksContext(new MetaworksContext());
		clonedOne.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		clonedOne.getMetaworksContext().setWhere("in-container");
		
		entityModeler.getEntityFields().add(clonedOne); 
		entityModeler.init();
		
		return entityModeler;
	}
		
	@ServiceMethod(when=MetaworksContext.WHEN_EDIT, where="in-container", callByContent=true)
	public EntityModeler save() throws Exception{
		EntityModeler entityModeler = entityDefinition.getEntityModeler();
		
		int index = entityModeler.getEntityFields().indexOf(this);
		if(index == -1)
			throw new Exception("There's no existing field named '" + getName() + "'.");
		
		EntityField clonedOne = (EntityField) this.clone();
		clonedOne.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		entityModeler.getEntityFields().remove(this);
		entityModeler.getEntityFields().add(index, clonedOne); 
		
		return entityModeler;
	}
		
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW, where="in-container")
	public EntityModeler remove(){
		EntityModeler entityModeler = entityDefinition.getEntityModeler();
		
		entityModeler.getEntityFields().remove(this);
		
		return entityModeler;
	}
	
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW, where="in-container", callByContent=true)
	public EntityModeler up(){
		EntityModeler entityModeler = entityDefinition.getEntityModeler();
		
		int index = entityModeler.getEntityFields().indexOf(this);
		
		if(index > 0){
			entityModeler.getEntityFields().remove(this);
			entityModeler.getEntityFields().add(index-1, this);
		}

		return entityModeler;
	}
	
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW, where="in-container", callByContent=true) 
	public EntityModeler down(){
		EntityModeler entityModeler = entityDefinition.getEntityModeler();
			
		int index = entityModeler.getEntityFields().indexOf(this);
		
		if(index < entityModeler.getEntityFields().size()-1){
			entityModeler.getEntityFields().remove(this);
			entityModeler.getEntityFields().add(index+1, this);
		}

		return entityModeler;
	}
	
	@Override //TODO: lesson 1 (object addressing and correlation)
	public boolean equals(Object obj) {
		if(obj==null) return false;
		
		String thisName = this.getName();
		String comparatorName = ((EntityField)obj).getName();
		
		return thisName.equals(comparatorName);
	}
	
	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	
	@AutowiredFromClient	
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
