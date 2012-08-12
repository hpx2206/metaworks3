package org.metaworks.website;

import org.metaworks.website.AbstractMetaworksFile;
import org.uengine.kernel.FormActivity;
import org.uengine.util.UEngineUtil;

public class MetaworksFile extends AbstractMetaworksFile{

	@Override
	public String overrideUploadPathPrefix() {
		
		String base = FormActivity.FILE_SYSTEM_DIR + UEngineUtil.getCalendarDir() + "/";
		
	    if(getDirectory() == null || "".equals(getDirectory()))
	    	return base;
	    else
	    	return base + getDirectory();
	}

}
