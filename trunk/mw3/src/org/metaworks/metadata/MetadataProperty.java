package org.metaworks.metadata;

import org.metaworks.MetaworksContext;
import org.metaworks.component.SelectBox;
import org.metaworks.website.MetaworksFile;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;


@XStreamAlias("MetadataProperty")
public class MetadataProperty {
	
	public MetadataProperty() {
		setFile(new MetaworksFile()); 
	}
	
	@XStreamAsAttribute
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	
	@XStreamOmitField
	SelectBox selectedType;
		public SelectBox getSelectedType() {
			return selectedType;
		}
		public void setSelectedType(SelectBox selectedType) {
			this.selectedType = selectedType;
		}

	@XStreamAsAttribute
	boolean isKeyEditable;
		public boolean isKeyEditable() {
			return isKeyEditable;
		}
		public void setKeyEditable(boolean isKeyEditable) {
			this.isKeyEditable = isKeyEditable;
		}
	@XStreamAsAttribute
	boolean isRemote;
		public boolean isRemote() {
			return isRemote;
		}
		public void setRemote(boolean isRemote) {
			this.isRemote = isRemote;
		}

	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	String value;
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	
	@XStreamOmitField
	MetaworksFile file;
		public MetaworksFile getFile() {
			return file;
		}
		public void setFile(MetaworksFile file) {
			this.file = file;
		}
	
	@XStreamOmitField
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	@XStreamAsAttribute
	int index;
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
	
}
