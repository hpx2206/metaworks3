package org.metaworks.metadata;


import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.AbstractMetaworksFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.kernel.GlobalContext;


@Face(ejsPath="org/metaworks/website/MetaworksFile.ejs")
public class MetadataFile extends AbstractMetaworksFile {
	
	String fileSep = "/";
	
	String filePath;
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
	

	@Autowired
	ResourceNode resourceNode;

	@Override
	public String overrideUploadPathPrefix() {
		
		
		String codebase = GlobalContext.getPropertyString("codebase");
		String tenantId = "uEngine";
		
		return codebase + fileSep + tenantId  + fileSep ;
		
	}
	
	@ServiceMethod
	public String getProjectDir(){
		if(filePath == null){
			return null;
		}
		return filePath.substring(0, filePath.lastIndexOf("\\"));
	}
	
	
	@Override
	public String renameUploadFile(String filename) {
		return getProjectDir() + fileSep + super.renameUploadFile(filename);
	}

	@Override
	public String renameUploadFileWithMimeType(String filename, String mimeType) {
		return getProjectDir() + fileSep + super.renameUploadFileWithMimeType(filename, mimeType);
	}
			
}
