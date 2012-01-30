package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.EntityDefinition;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;


public class EntityDesignerContentPanel extends ContentWindow{
	
	EntityDefinition entityDefinition;
		public EntityDefinition getEntityDefinition() {
			return entityDefinition;
		}
		public void setEntityDefinition(EntityDefinition entityDefinition) {
			this.entityDefinition = entityDefinition;
		}

	public void newEntity(String parentFoler) throws Exception{
		entityDefinition = new EntityDefinition();
		entityDefinition.setParentFolder(parentFoler);
	}

	public void load(String defId) throws Exception{
		String defVerId = processManager.getProcessDefinitionProductionVersion(defId);
		String resource = processManager.getResource(defVerId);
		entityDefinition = (EntityDefinition) GlobalContext.deserialize(resource, EntityDefinition.class);
	}

	
	@Autowired
	ProcessManagerRemote processManager;

}
