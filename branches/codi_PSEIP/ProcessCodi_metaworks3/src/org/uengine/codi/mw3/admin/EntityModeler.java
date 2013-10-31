package org.uengine.codi.mw3.admin;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;

public class EntityModeler {
	public EntityModeler() {
	}
	
	ArrayList<EntityField> entityFields;
		@Face(options="{hideAddBtn}", values={"true"})		
		public ArrayList<EntityField> getEntityFields() {
			return entityFields;
		}
		public void setEntityFields(ArrayList<EntityField> formFields) {
			this.entityFields = formFields;
		}


	transient EntityField newEntityField;
		public EntityField getNewEntityField() {
			return newEntityField;
		}
		public void setNewEntityField(EntityField newEntityField) {
			this.newEntityField = newEntityField;
		}

	protected void init() {
		newEntityField = new EntityField();
		newEntityField.metaworksContext = new MetaworksContext();
		newEntityField.metaworksContext.setWhen(MetaworksContext.WHEN_EDIT);
		newEntityField.metaworksContext.setWhere("newEntry");//TODO: lesson 6 (if there's no overriding value, metaworks will tries to use old contxt value)
	}
	
	
}