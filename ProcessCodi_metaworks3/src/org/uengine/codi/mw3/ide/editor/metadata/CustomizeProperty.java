package org.uengine.codi.mw3.ide.editor.metadata;

import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ide.ResourceNode;

public class CustomizeProperty {
	
	public CustomizeProperty() {
		// TODO Auto-generated constructor stub
	}
	
	MetadataXMLImpl metadataXML;
		public MetadataXMLImpl getMetadataXML() {
			return metadataXML;
		}
		public void setMetadataXML(MetadataXMLImpl metadataXML) {
			this.metadataXML = metadataXML;
		}

	ResourceNode resourceNode;
		public ResourceNode getResourceNode() {
			return resourceNode;
		}
		public void setResourceNode(ResourceNode resourceNode) {
			this.resourceNode = resourceNode;
		}
		
		
	@ServiceMethod(callByContent=true)
	public void load(){
		
		MetadataXMLImpl metadata = new MetadataXMLImpl();
		setMetadataXML( (MetadataXMLImpl)metadata.loadWithResourceNode(this.getResourceNode()));

	}

}
