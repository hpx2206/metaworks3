package org.metaworks.metadata;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
ejsPathMappingByContext={
	"{where: 'ssp', face: 'dwr/metaworks/org/metaworks/metadata/ProcessProperty.ejs'}"
})
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
