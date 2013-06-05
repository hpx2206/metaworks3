package org.uengine.codi.mw3.ide.editor.metadata;

import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ide.ResourceNode;

public class CustomizeProperty {
	
	public CustomizeProperty() {
		// TODO Auto-generated constructor stub
	}
	
	MetadataXML metadataXML;
		public MetadataXML getMetadataXML() {
			return metadataXML;
		}
		public void setMetadataXML(MetadataXML metadataXML) {
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
		
		MetadataXML metadata = new MetadataXML();
		setMetadataXML( metadata.loadWithResourceNode(this.getResourceNode()));

	}

}
