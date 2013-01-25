package org.metaworks.website;

public class MetaworksFile extends AbstractMetaworksFile {

	@Override
	public String overrideUploadPathPrefix(){
    	return "fileSystem/";
	}
	

}
