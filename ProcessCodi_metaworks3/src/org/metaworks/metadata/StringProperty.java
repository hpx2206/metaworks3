package org.metaworks.metadata;

import org.metaworks.annotation.Hidden;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("MetadataProperty")
public class StringProperty extends MetadataProperty{

	public StringProperty() {
		setType(MetadataProperty.STRING_PROP);
	}
	
	
	@XStreamOmitField
	MetadataFile file;
		@Hidden
		public MetadataFile getFile() {
			return file;
		}
		public void setFile(MetadataFile file) {
			this.file = file;
		}
		
}
