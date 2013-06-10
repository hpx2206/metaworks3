package org.uengine.codi.mw3.ide.editor.metadata;

import java.util.ArrayList;

import org.metaworks.annotation.Face;
import org.metaworks.metadata.MetadataProperty;
import org.metaworks.metadata.MetadataXML;
import org.uengine.codi.mw3.ide.ResourceNode;

public class MetadataXMLImpl extends MetadataXML{
	
	ArrayList<MetadataProperty> properties;
	@Face(ejsPath="dwr/metaworks/org/uengine/codi/mw3/ide/editor/metadata/MetadataPropertyImpl.ejs")
		public ArrayList<MetadataProperty> getProperties() {
			int index;
			
			for(index=0; index < properties.size(); index++){
				properties.get(index).setIndex(index);
			}
			return properties;
		}
		public void setProperties(ArrayList<MetadataProperty> properties) {
			this.properties = properties;
		}
	MetadataProperty newMetadataProperty;
	@Face(ejsPath="dwr/metaworks/org/uengine/codi/mw3/ide/editor/metadata/MetadataPropertyImpl.ejs")
		public MetadataProperty getNewMetadataProperty() {
			return newMetadataProperty;
		}
		public void setNewMetadataProperty(MetadataProperty newMetadataProperty) {
			this.newMetadataProperty = newMetadataProperty;
		}

	
	public void loadMetadataXml(MetadataXML xml){
		this.setFilePath(xml.getFilePath());
		this.setProperties(xml.getProperties());
	}

	public MetadataXML loadWithResourceNode(ResourceNode resourceNode){
		MetadataXML metadata = loadWithPath(resourceNode.getPath());
		metadata.setFilePath(resourceNode.getId());
		return metadata;
	}
}
