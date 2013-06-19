package org.metaworks.metadata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.metadata.MetadataEditor;

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
		
	@Override
	public Object save() throws FileNotFoundException, IOException, Exception {
		int index = metadataXML.properties.indexOf(this);

		MetadataProperty editProperty = metadataXML.properties.get(index);
		editProperty.setName(this.getName());
		editProperty.setChange(true);
		editProperty.setType(this.getType());
		
		
		//파일 첨부일떄..
		MetadataFile file = new MetadataFile();
		file.setUploadedPath(this.getFile().getFilename());
		file.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		file.setMimeType(ResourceNode.findNodeType(this.getFile().getFilename()));
		file.setFileTransfer(this.getFile().getFileTransfer());
		file.metadataXml = metadataXML;
		
		editProperty.setFile(file);
		editProperty.setValue(file.getUploadedPath());
		editProperty.getFile().getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		
		//파일 첨부 끝
		
		
//		if (checkFile) {
//		
//		}
//
//		if (checkResource) {
//			editProperty.setValue(this.getResourceNode().getId());
//		}

		metadataXML.properties.remove(this);
		metadataXML.properties.add(index, editProperty);
		
		String metadataFileName = "uengine.metadata";
		String metadataFilePath = metadataXML.getFilePath() + File.separatorChar + metadataFileName;
		
		
		MetadataEditor metadataEditor = new MetadataEditor();
		metadataEditor.setResourceNode(new ResourceNode());
		metadataEditor.getResourceNode().setPath(metadataFilePath);
		metadataEditor.setContent(metadataXML.toXmlXStream());
		metadataEditor.save();
		
		this.getMetaworksContext().setWhere("ssp");
		this.getMetaworksContext().setWhen("show_detail");
		this.setFile(editProperty.getFile());

		return this;
	}


	@ServiceMethod(callByContent=true, bindingHidden=true, bindingFor="file", eventBinding={"uploaded"})
	public void changeFile(){
		
		this.getFile().getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		setFilePreview(this.getFile());
		
	}
	
	@ServiceMethod(callByContent=true, bindingHidden=true, bindingFor="resourceNode", eventBinding={"toOpener"})
	public void changeResourceFile(){
		
		MetadataFile resourceFile = new MetadataFile();
		
		resourceFile.setFilename(this.getResourceNode().getName());
		resourceFile.setUploadedPath(this.getResourceNode().getId());
		resourceFile.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
		resourceFile.setMimeType(ResourceNode.findNodeType(this.getName()));
		
		this.getFile().getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		setFilePreview(resourceFile);
		
		
	}
}
