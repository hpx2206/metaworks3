package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.EntityDefinition;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(ejsPath="genericfaces/Tab.ejs",
      options={"hideLabels"},
	  values={"true"})
public class EntityDesignerContentPanel {
	
	@Autowired
	EntityDefinition entityDefinition;		
		public EntityDefinition getEntityDefinition() {
			return entityDefinition;
		}
		public void setEntityDefinition(EntityDefinition entityDefinition) {
			this.entityDefinition = entityDefinition;
		}
	
	String a;	
		public String getA() {
			return a;
		}
		public void setA(String a) {
			this.a = a;
		}
	
	String b;
		public String getB() {
			return b;
		}
		public void setB(String b) {
			this.b = b;
		}

	
		
	@Autowired
	ProcessManagerRemote processManager;
		
	public void newEntity(String parentFoler) throws Exception{
		entityDefinition.init();		
		entityDefinition.setParentFolder(parentFoler);
		entityDefinition.getMetaworksContext().setWhere("newEntity");
	}

	public void load(String defId) throws Exception{
		String defVerId = processManager.getProcessDefinitionProductionVersion(defId);
		String resource = processManager.getResource(defVerId);
		entityDefinition = (EntityDefinition) GlobalContext.deserialize(resource, EntityDefinition.class);
		
		entityDefinition.init();
		

	}

	

}
