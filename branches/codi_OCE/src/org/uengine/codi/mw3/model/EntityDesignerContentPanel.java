package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.admin.EntityDefinition;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(ejsPath="genericfaces/Window.ejs", displayName="Entity Designer", options={"hideLabels"}, values={"true"})
public class EntityDesignerContentPanel extends ContentWindow {

	@Autowired
	public ProcessManagerRemote processManager;

	EntityDefinition entityDefinition;
		public EntityDefinition getEntityDefinition() {
			return entityDefinition;
		}
		public void setEntityDefinition(EntityDefinition entityDefinition) {
			this.entityDefinition = entityDefinition;
		}

	public void newEntity(IUser user, String parentFoler) throws Exception {
		entityDefinition = new EntityDefinition();
		entityDefinition.setParentFolder(parentFoler);
		entityDefinition.setPackageName(parentFoler.replaceAll("/", "."));
		entityDefinition.setAuthor(user);		
	}

	public void load(String defId) throws Exception {
		try{
			
			
			String defVerId = processManager.getProcessDefinitionProductionVersion(defId);
			
			CodiClassLoader codiClassLoader = (CodiClassLoader)Thread.currentThread().getContextClassLoader();
			
			entityDefinition = (EntityDefinition) GlobalContext.deserialize(codiClassLoader.getResourceAsStream(defId), EntityDefinition.class);
			entityDefinition.setDefId(defId);
			
			entityDefinition.setMetaworksContext(new MetaworksContext());
			entityDefinition.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
			
			if(entityDefinition.queryCreatedEntity()){
				entityDefinition.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			}
			
			try {
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}finally{
			//processManager.remove();
		}	
	}

}
