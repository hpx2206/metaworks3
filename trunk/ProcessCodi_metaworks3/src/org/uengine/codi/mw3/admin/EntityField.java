
package org.uengine.codi.mw3.admin;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;

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
	
	String name;
		@Id  // TODO: lesson 1 (object addressing and correlation)
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
						"DOUBLE",
						"CHAR",
						"VARCHAR",
						"DATETIME",
						"TIMESTAMP",
						},
						
						
				values ={
						"java.lang.Number", 
						"java.lang.Number",
						"java.lang.String",
						"java.lang.String",
						"java.lang.Date",
						"java.lang.Number"
					}
		)
		public String getDataType() {
			return dataType;
		}
		public void setDataType(String dataType) {
			this.dataType = dataType;
		}
						
	@ServiceMethod(when=MetaworksContext.WHEN_EDIT, where="newEntry", callByContent=true)
	public EntityDefinition add() throws Exception{

		if(entityDefinition.entityFields==null)
			entityDefinition.entityFields = new ArrayList<EntityField>();//new FormField[]{};
		
		//TODO: lesson 3 (validation with throwing exception)
		if(entityDefinition.entityFields.contains(this))
			throw new Exception("There's already existing field named '" + getName() + "'.");
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
			throw new Exception("There's no existing field named '" + getName() + "'.");
					
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
	
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW, where="newEntry")
	public EntityDefinition up(){
		int index = entityDefinition.entityFields.indexOf(this);
		
		if(index>0){
			entityDefinition.entityFields.remove(this);
			//TODO: quiz 1 (below is not proper since it will clear the type information. Prove why and fix this)
			entityDefinition.entityFields.add(index-1, this);
		}

		return entityDefinition;
	}
	
	@ServiceMethod(when=MetaworksContext.WHEN_VIEW, where="newEntry") 
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
		
		String thisName = this.getName();
		String comparatorName = ((EntityField)obj).getName();
		
		return thisName.equals(comparatorName);
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
