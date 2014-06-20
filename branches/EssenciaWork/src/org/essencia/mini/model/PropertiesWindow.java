package org.essencia.model;

import org.metaworks.annotation.Face;
import org.metaworks.widget.Window;

@Face(ejsPath="genericfaces/Window.ejs",
options={"hideLabels", "minimize"}, 
values={"true", "true"})
public class PropertiesWindow extends ContentWindow{
	PropertiesPanel propertiesPanel;
		public PropertiesPanel getPropertiesPanel() {
			return propertiesPanel;
		}
	
		public void setPropertiesPanel(PropertiesPanel propertiesPanel) {
			this.propertiesPanel = propertiesPanel;
		}
	
	public PropertiesWindow(){
		propertiesPanel = new PropertiesPanel();
	}
	
}
