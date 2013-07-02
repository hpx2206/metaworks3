package org.metaworks.metadata;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.metaworks.annotation.Face;
import org.metaworks.website.AbstractMetaworksFile;


@Face(ejsPath="org/metaworks/website/MetaworksFile.ejs", options={"hideText"}, values={"true"})
public class MetadataFile extends AbstractMetaworksFile {
	
	public MetadataFile() {
		setAuto(true);
	}
	
	String baseDir;
		public String getBaseDir() {
			return baseDir;
		}
		public void setBaseDir(String baseDir) {
			this.baseDir = baseDir;
		}
		
	String typeDir;
		public String getTypeDir() {
			return typeDir;
		}
		public void setTypeDir(String typeDir) {
			this.typeDir = typeDir;
		}
		
	@Override
	public String overrideUploadPathPrefix() {
		return baseDir;
	}
	
	
	@Override
	public void upload() throws FileNotFoundException, IOException, Exception{
		if(this.getFileTransfer() == null || this.getFileTransfer().getFilename()==null || this.getFileTransfer().getFilename().length() <= 0) 
			throw new Exception("No file attached");
		
		String prefix = overrideUploadPathPrefix();
		String filePath = this.getTypeDir() + File.separatorChar +  this.getFileTransfer().getFilename();
		String uploadPath = prefix + File.separatorChar + filePath;
		
		new File(uploadPath).getParentFile().mkdirs();
		
		copyStream(this.getFileTransfer().getInputStream(), new FileOutputStream(uploadPath));
		
		setUploadedPath(filePath); //only when the file has been successfully uploaded, this value is set, that means your can download later
		setMimeType(this.getFileTransfer().getMimeType());
		
		this.setFileTransfer(null); //ensure to clear the data
	}
}
