package org.uengine.codi.mw3.ide.editor.metadata;

import org.metaworks.metadata.MetadataXML;
import org.uengine.codi.mw3.ide.ResourceNode;

public class MetadataXMLImpl extends MetadataXML{

	public MetadataXML loadWithResourceNode(ResourceNode resourceNode){
		MetadataXML metadata = loadWithPath(resourceNode.getPath());
		metadata.setFilePath(resourceNode.getId());
		return metadata;
	}
}
