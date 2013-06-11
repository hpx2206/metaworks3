package org.metaworks.metadata;

import org.metaworks.annotation.Hidden;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("MetadataProperty")
public class ProcessProperty extends MetadataProperty{
	
	public ProcessProperty() {
		setType(MetadataProperty.PROCESS_PROP);
	}
	
	String value;
		@Hidden
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}

}
