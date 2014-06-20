package org.essencia.model;

import org.metaworks.annotation.Face;

@Face(ejsPath="genericfaces/Tab.ejs"  )
public class PropertiesPanel {

	Element element;
		@Face(displayName="  element  ")
		public Element getElement() {
			return element;
		}
		public void setElement(Element element) {
			this.element = element;
		}
	
	Extension extension;
		@Face(displayName="  extension  ")
		public Extension getExtension() {
			return extension;
		}
		public void setExtension(Extension extension) {
			this.extension = extension;
		}
	
		
	public PropertiesPanel(){
		element = new Element();
		extension = new Extension();
	}
}
