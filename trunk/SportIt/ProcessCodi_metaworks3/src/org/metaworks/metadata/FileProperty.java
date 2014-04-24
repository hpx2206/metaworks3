package org.metaworks.metadata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.metadata.MetadataEditor;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@Face(ejsPath = "dwr/metaworks/org/metaworks/metadata/MetadataProperty.ejs",
		ejsPathMappingByContext={
	"{where: 'ide', face: 'dwr/metaworks/org/metaworks/metadata/FileProperty.ejs'}",
	"{where: 'ssp', face: 'dwr/metaworks/org/metaworks/metadata/FileProperty.ejs'}"
})
@XStreamAlias("MetadataProperty")
public class FileProperty extends MetadataProperty {
	
	public FileProperty() {
		setType(MetadataProperty.FILE_PROP);
	}

	String value;
		@Available(when={MetaworksContext.WHEN_VIEW, MetaworksContext.WHEN_EDIT})
		@NonEditable(when={MetaworksContext.WHEN_EDIT})
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		
	@Override
	@ServiceMethod(callByContent=true)
	public Object edit() throws Exception {
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		return this;
	}
	
	@Override
	@ServiceMethod(callByContent=true)
	public Object[] save() throws FileNotFoundException, IOException, Exception {
		
		int index = metadataXML.properties.indexOf(this);

		MetadataProperty editProperty = metadataXML.properties.get(index);
		editProperty.setName(this.getName());
		editProperty.setChange(true);
		editProperty.setType(this.getType());

		if (FILE_PROP.equals(this.getType())
				|| IMAGE_PROP.equals(this.getType())
				|| PROCESS_PROP.equals(this.getType())
				|| FORM_PROP.equals(this.getType())) {

			if (checkFile) {
				MetadataFile file = new MetadataFile();
				file.setUploadedPath(this.getValue());
				file.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
				file.setMimeType(ResourceNode.findNodeType(this.getValue()));
				file.setFileTransfer(this.getFile().getFileTransfer());
				
				editProperty.setFile(file);
				editProperty.setValue(file.getUploadedPath());
				editProperty.getFile().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
				
				// file upload;
				file.upload();
			}

			if (checkResource) {
				editProperty.setValue(this.getResourceNode().getId());
			}

		} else if (STRING_PROP.equals(this.getType())) {
			editProperty.setValue(value);
		}

		metadataXML.properties.remove(this);
		metadataXML.properties.add(index, editProperty);
		
		
		MetadataEditor metadataEditor = new MetadataEditor();
		metadataEditor.setResourceNode(new ResourceNode());
		metadataEditor.getResourceNode().setPath(metadataXML.getFilePath());
		metadataEditor.setContent(metadataXML.toXmlXStream());
		metadataEditor.save();
		
		this.getMetaworksContext().setWhere("ssp");
		this.getMetaworksContext().setWhen("show_detail");
		this.setFile(editProperty.getFile());

		return new Object[]{this};
	}

}
