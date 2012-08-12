package org.metaworks.website;

public class MetaworksFile extends AbstractMetaworksFile {

	@Override
	public String overrideUploadPathPrefix(){
	    if(getDirectory() == null || "".equals(getDirectory()))
	    	return "fileSystem/";
	    else
	    	return "fileSystem/" + getDirectory();
	}
	

}
