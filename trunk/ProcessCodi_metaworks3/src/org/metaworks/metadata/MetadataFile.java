package org.metaworks.metadata;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.metaworks.annotation.Face;
import org.metaworks.website.AbstractMetaworksFile;
import org.uengine.kernel.GlobalContext;


@Face(ejsPath="org/metaworks/website/MetaworksFile.ejs")
public class MetadataFile extends AbstractMetaworksFile {


	String company;
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		
	String filePath;
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
	
	@Override
	public String overrideUploadPathPrefix() {
		
		String sep ="/";
		String codebase = GlobalContext.getPropertyString("codebase");
		String tenantId = "uEngine";
		String filePath = this.getFilePath();
		filePath = filePath.substring(0, filePath.lastIndexOf("\\"));
		
		return codebase + sep + tenantId  + sep + filePath;
		
	}
	
	@Override
	public String renameUploadFile(String filename) {
		return "/" + super.renameUploadFile(filename);
	}

	@Override
	public String renameUploadFileWithMimeType(String filename, String mimeType) {
		return "/" + super.renameUploadFileWithMimeType(filename, mimeType);
	}
	
	@Override
	public void upload() throws FileNotFoundException, IOException, Exception {
		if(getFileTransfer()==null || getFileTransfer().getFilename()==null || getFileTransfer().getFilename().length() <= 0) 
			throw new Exception("No file attached");
		
		String prefix = overrideUploadPathPrefix();

		String uploadFileName = renameUploadFileWithMimeType(getFileTransfer().getFilename(), getFileTransfer().getMimeType());	
		String uploadPath = prefix + uploadFileName;
		
		new File(uploadPath).getParentFile().mkdirs();
		
		copyStream(getFileTransfer().getInputStream(), new FileOutputStream(uploadPath));
		
		setUploadedPath(uploadFileName); //only when the file has been successfully uploaded, this value is set, that means your can download later
		setMimeType(getFileTransfer().getMimeType());
		
		setFileTransfer(null);	//ensure to clear the data
	}

}
