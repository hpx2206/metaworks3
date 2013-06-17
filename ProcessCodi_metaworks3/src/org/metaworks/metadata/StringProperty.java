package org.metaworks.metadata;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.mw3.ide.ResourceNode;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
ejsPathMappingByContext={
	"{where: 'ssp', face: 'dwr/metaworks/org/metaworks/metadata/StringProperty.ejs'}"
})
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
		
	@XStreamOmitField
	ResourceNode resourceNode;
		@Hidden
		public ResourceNode getResourceNode() {
			return resourceNode;
		}
		public void setResourceNode(ResourceNode resourceNode) {
			this.resourceNode = resourceNode;
		}
	
	@Override
	public Object edit() throws Exception {
		// TODO Auto-generated method stub
		return super.edit();
	}
	
	@Override
	public Object save() throws FileNotFoundException, IOException, Exception {
		// TODO Auto-generated method stub
		return super.save();
	}
}