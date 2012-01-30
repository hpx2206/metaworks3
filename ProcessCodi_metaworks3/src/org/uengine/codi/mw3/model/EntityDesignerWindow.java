package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.springframework.beans.factory.annotation.Autowired;

@Face(ejsPath = "genericfaces/Window.ejs", displayName = "EntityDesigner Window", options = { "hideLabels" }, values = { "true" })
public class EntityDesignerWindow extends ContentWindow {
			
	public EntityDesignerWindow(){}
	
	@Autowired
	EntityDesignerContentPanel entityDesignerContentPanel;
		public EntityDesignerContentPanel getEntityDesignerContentPanel() {
			return entityDesignerContentPanel;
		}
		public void setEntityDesignerContentPanel(
				EntityDesignerContentPanel entityDesignerContentPanel) {
			this.entityDesignerContentPanel = entityDesignerContentPanel;
		}
		
	public void newEntity(String parentFolder) throws Exception {
		entityDesignerContentPanel.newEntity(parentFolder);
	}
	
	public void load(String defId) throws Exception {
		entityDesignerContentPanel.load(defId);		
	}
}
