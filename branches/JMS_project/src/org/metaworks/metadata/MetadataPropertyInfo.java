package org.metaworks.metadata;

import org.metaworks.annotation.Face;

@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs"  , options={"disableHeight"}, values={"true"})
public class MetadataPropertyInfo {

	MetadataProperty newMetadataProperty;
		public MetadataProperty getNewMetadataProperty() {
			return newMetadataProperty;
		}
		public void setNewMetadataProperty(MetadataProperty newMetadataProperty) {
			this.newMetadataProperty = newMetadataProperty;
		}
}
