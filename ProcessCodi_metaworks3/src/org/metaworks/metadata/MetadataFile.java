package org.metaworks.metadata;


import org.metaworks.annotation.Face;
import org.metaworks.website.AbstractMetaworksFile;
import org.uengine.codi.mw3.CodiClassLoader;


@Face(ejsPath="org/metaworks/website/MetaworksFile.ejs")
public class MetadataFile extends AbstractMetaworksFile {

	@Override
	public String overrideUploadPathPrefix() {
		return CodiClassLoader.mySourceCodeBase();
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
