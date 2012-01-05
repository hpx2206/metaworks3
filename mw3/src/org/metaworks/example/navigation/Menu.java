package org.metaworks.example.navigation;

import org.metaworks.annotation.Face;

//@Face(ejsPath="genericfaces/Window.ejs", options={"hideLabels"}, values={"true"})
public class Menu {
	
	protected Menu(){
		Selection selection1 = new Selection();
		selection1.setContentClassName("AContent");

		Selection selection2 = new Selection();
		selection2.setContentClassName("BContent");
		
		setSelections(new Selection[]{selection1, selection2});
	}
	
	Selection[] selections;
		public Selection[] getSelections() {
			return selections;
		}
		public void setSelections(Selection[] selections) {
			this.selections = selections;
		}

}
