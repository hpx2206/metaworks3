package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.EntityData;
import org.uengine.codi.mw3.admin.EntityDefinition;
import org.uengine.codi.mw3.admin.EntityQuery;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(ejsPath="genericfaces/Tab.ejs",
	  options={"hideLabels"},
	  values={"true"})
public class EntityDesignerContentPanel {
	
	public EntityDesignerContentPanel(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhere("1");
	}
	
	EntityDefinition entityDefinition;		
		public EntityDefinition getEntityDefinition() {
			return entityDefinition;
		}
		public void setEntityDefinition(EntityDefinition entityDefinition) {
			this.entityDefinition = entityDefinition;
		}
	
	EntityQuery entityQuery;
		public EntityQuery getEntityQuery() {
			return entityQuery;
		}
		public void setEntityQuery(EntityQuery entityQuery) {
			this.entityQuery = entityQuery;
		}
	
	/*
	EntityData entityData;
		public EntityData getEntityData() {
			return entityData;
		}
		public void setEntityData(EntityData entityData) {
			this.entityData = entityData;
		}
	*/
		
	@Autowired
	ProcessManagerRemote processManager;
		
	public void newEntity(String parentFoler) throws Exception{
		entityDefinition = new EntityDefinition(); 
				
		entityDefinition.init();		
		entityDefinition.setParentFolder(parentFoler);
		entityDefinition.getMetaworksContext().setWhere("newEntity");
		
		entityQuery = new EntityQuery();
	}

	public void load(String defId) throws Exception{
		String defVerId = processManager.getProcessDefinitionProductionVersion(defId);
		String resource = processManager.getResource(defVerId);
		entityDefinition = (EntityDefinition) GlobalContext.deserialize(resource, EntityDefinition.class);
		
		entityDefinition.init();
		
		entityQuery = new EntityQuery();
	}
	
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		} 	
}
