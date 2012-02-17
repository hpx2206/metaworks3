package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.admin.EntityDefinition;
import org.uengine.codi.mw3.admin.EntityQuery;
import org.uengine.kernel.GlobalContext;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(ejsPath = "genericfaces/Window.ejs", displayName = "EntityDesigner Window", options = { "hideLabels" }, values = { "true" })
public class EntityDesignerWindow extends ContentWindow {
			
	EntityDesignerContentPanel entityDesignerContentPanel;
		public EntityDesignerContentPanel getEntityDesignerContentPanel() {
			return entityDesignerContentPanel;
		}
		public void setEntityDesignerContentPanel(
				EntityDesignerContentPanel entityDesignerContentPanel) {
			this.entityDesignerContentPanel = entityDesignerContentPanel;
		}
		
	public void newEntity(String parentFolder) throws Exception {
		entityDesignerContentPanel = new EntityDesignerContentPanel();
		entityDesignerContentPanel.newEntity(parentFolder);
	}
	
	public void load(String defId) throws Exception {
		entityDesignerContentPanel = new EntityDesignerContentPanel();
		//entityDesignerContentPanel.load(defId);		
		
		
		String defVerId = processManager.getProcessDefinitionProductionVersion(defId);
		String resource = processManager.getResource(defVerId);
		
		
		EntityDefinition entityDefinition = (EntityDefinition) GlobalContext.deserialize(resource, EntityDefinition.class);
		entityDefinition.setDefId(defId);
		entityDefinition.init();
		
/*		try {
			ProcessDefinition def = new ProcessDefinition();
			def.setDefId(new Long(defId));
			String authorId = def.databaseMe().getAuthor();
			
			User author = new User();
			author.setUserId(authorId);
			entityDefinition.setAuthor(author);
			
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}*/
		
		entityDesignerContentPanel.setEntityDefinition(entityDefinition);
		entityDesignerContentPanel.setEntityQuery(new EntityQuery());
	}
	
	@Autowired
	ProcessManagerRemote processManager;	
}
