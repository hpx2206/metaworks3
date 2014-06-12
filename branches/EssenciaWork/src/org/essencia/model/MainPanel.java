package org.essencia.model;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.example.ide.Workspace;
import org.metaworks.widget.layout.Layout;


public class MainPanel {

	public MainPanel(){
		
	}
	
	public MainPanel(Object panel){
		setPanel(panel);
	}
	
	Object panel;
		public Object getPanel() {
			return panel;
		}
		public void setPanel(Object panel) {
			this.panel = panel;
		}
	
	
}
