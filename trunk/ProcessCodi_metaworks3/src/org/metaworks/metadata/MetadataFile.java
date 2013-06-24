package org.metaworks.metadata;


import java.io.File;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.website.AbstractMetaworksFile;
import org.uengine.codi.mw3.CodiClassLoader;


@Face(ejsPath="org/metaworks/website/MetaworksFile.ejs", options={"hideText"}, values={"true"})
public class MetadataFile extends AbstractMetaworksFile {
	
	public MetadataFile() {
		setAuto(true);
	}
	
	@AutowiredFromClient
	public MetadataXML metadataXml;
	
	@AutowiredFromClient
	public MetadataProperty metadataProperty;
	
	@Override
	public String overrideUploadPathPrefix() {
		
			if(metadataXml != null){
				return metadataXml.getFilePath() + File.separatorChar;
//			}else if(metadataXml != null && metadataProperty == null){
//				return metadataXml.getFilePath() + File.separatorChar;
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
