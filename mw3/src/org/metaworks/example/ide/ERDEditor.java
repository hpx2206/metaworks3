package org.metaworks.example.ide;

import org.metaworks.annotation.Face;

@Face(
		ejsPath="genericfaces/Window.ejs",
		options={"hideLabels"},
		values ={"true"}
		)

public class ERDEditor {
	
	public ERDiagram erd;
	
		public ERDiagram getErd() {
			return erd;
		}
	
		public void setErd(ERDiagram erd) {
			this.erd = erd;
		}
		
	public ERDEditor(){
		setErd(new ERDiagram());
	}
}
