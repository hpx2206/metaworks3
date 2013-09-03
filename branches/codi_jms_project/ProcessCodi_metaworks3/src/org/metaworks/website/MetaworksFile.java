package org.metaworks.website;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.AbstractMetaworksFile;
import org.uengine.kernel.FormActivity;
import org.uengine.search.solr.SolrData;
import org.uengine.util.UEngineUtil;

public class MetaworksFile extends AbstractMetaworksFile{

	@Override
	public String overrideUploadPathPrefix() {
		
		String base = FormActivity.FILE_SYSTEM_DIR;
		
		return base + "/";
		
	}

	@Override
	public String renameUploadFile(String filename) {
		return UEngineUtil.getCalendarDir() + "/" + super.renameUploadFile(filename);
	}

	@Override
	public String renameUploadFileWithMimeType(String filename, String mimeType) {
		// TODO Auto-generated method stub
		return UEngineUtil.getCalendarDir() + "/" + super.renameUploadFileWithMimeType(filename, mimeType);
	}
	
	@Override
	public void upload() throws FileNotFoundException, IOException, Exception{
		
		super.upload();
		
		// 검색엔진에 데이타 넣기
		SolrData data = new SolrData();
		data.insertFile(this);
	}

}
