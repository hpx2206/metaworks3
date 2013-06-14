package org.metaworks.metadata;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.NonEditable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
ejsPathMappingByContext={
	"{where: 'ssp', face: 'dwr/metaworks/org/metaworks/metadata/ImageProperty.ejs'}"
})
@XStreamAlias("MetadataProperty")
public class ImageProperty extends MetadataProperty{
	
	public ImageProperty() {
		setType(MetadataProperty.IMAGE_PROP);
	}
	
	
	String value;
		@Hidden
		@Available(when=MetaworksContext.WHEN_VIEW)
		@NonEditable(when={MetaworksContext.WHEN_EDIT})
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}


}
