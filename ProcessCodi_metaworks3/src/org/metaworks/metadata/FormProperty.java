package org.metaworks.metadata;

import org.metaworks.annotation.Hidden;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("MetadataProperty")
public class FormProperty extends MetadataProperty{
	
	public FormProperty() {
		setType(MetadataProperty.FORM_PROP);
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
