package org.metaworks.metadata;


import java.io.File;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.website.AbstractMetaworksFile;
import org.uengine.codi.mw3.CodiClassLoader;


@Face(ejsPath="org/metaworks/website/MetaworksFile.ejs")
public class MetadataFile extends AbstractMetaworksFile {
	
	@AutowiredFromClient
	public MetadataXML metadataXml;
	
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}

	@Override
	public String overrideUploadPathPrefix() {
		if(metadataXml != null && this.getType() != null){
			return metadataXml.getFilePath() + File.separatorChar + this.getType() + File.separatorChar	;
		}else if(metadataXml != null && this.getType() == null){
			return metadataXml.getFilePath() + File.separatorChar;
		}else {
			return CodiClassLoader.mySourceCodeBase();
		}
	}
	
	@Override
	public String renameUploadFile(String filename) {
		return super.renameUploadFile(filename);
	}

	@Override
	public String renameUploadFileWithMimeType(String filename, String mimeType) {
		return super.renameUploadFileWithMimeType(filename, mimeType);
	}
	
}
