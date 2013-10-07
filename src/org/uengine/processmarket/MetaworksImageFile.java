package org.uengine.processmarket;

import org.metaworks.website.MetaworksFile;

public class MetaworksImageFile extends MetaworksFile {

    @Override
    public String overrideUploadPathPrefix() {
//	String webRoot = "../webapps/mw3"; // shuld be get GlobalContext maybe webroot.relativepath
//	String wasBin = ".";
//	String imageFolderPath = "/fileSystem/images/";
//	String marketItemPath = "/fileSystem/marketItems/";
//
//	if (getFileTransfer().getMimeType() != null) {
//	    if (getFileTransfer().getMimeType().startsWith("image")) {
//		return wasBin + imageFolderPath;
////		return webRoot + imageFolderPath;
//	    } else {
//		return wasBin + marketItemPath;
//	    }
//	} else {
//	    return super.overrideUploadPathPrefix();
//	}
	return super.overrideUploadPathPrefix();
    }
}
