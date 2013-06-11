package org.metaworks.metadata;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Hidden;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("MetadataProperty")
public class ImageProperty extends MetadataProperty{
	
	public ImageProperty() {
		setType(MetadataProperty.IMAGE_PROP);
	}
	
	
	String value;
		@Hidden
		@Available(when=MetaworksContext.WHEN_VIEW)
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}


}
