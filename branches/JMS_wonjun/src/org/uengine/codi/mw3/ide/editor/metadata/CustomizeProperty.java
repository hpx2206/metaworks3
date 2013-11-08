package org.uengine.codi.mw3.ide.editor.metadata;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.metadata.MetadataXML;
import org.uengine.codi.mw3.ide.ResourceNode;

@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
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
		@Hidden
		public ResourceNode getResourceNode() {
			return resourceNode;
		}
		public void setResourceNode(ResourceNode resourceNode) {
			this.resourceNode = resourceNode;
		}
		
		
	@Hidden
	@ServiceMethod(callByContent=true)
	public void load() throws Exception {
		MetadataXML metadata = new MetadataXML();
		setMetadataXML(metadata.loadWithResourceNode(this.getResourceNode()));
	}

}
