package org.metaworks.metadata;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.NonEditable;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("MetadataProperty")
public class FileProperty extends MetadataProperty{
	
	public FileProperty() {
		setType(MetadataProperty.FILE_PROP);
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
