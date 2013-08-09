package org.uengine.codi.mw3.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.metadata.MetadataBundle;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.mw3.ide.compare.CompareFileNavigator;

public class FileImporter{

	public FileImporter(){
		metaworksFile = new MetaworksFile();
	}
	
	String parentDirectory;
	@Hidden
		public String getParentDirectory() {
			return parentDirectory;
		}
		public void setParentDirectory(String parentDirectory) {
			this.parentDirectory = parentDirectory;
		}


	MetaworksFile metaworksFile;
		public MetaworksFile getFile() {
			return metaworksFile;
		}
		public void setFile(MetaworksFile file) {
			this.metaworksFile = file;
		}
		
	@AutowiredFromClient(select="autowiredObject.id=='target'")
	public CompareFileNavigator compareFileNavigator;

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] upload() throws IOException {
		
		// 확장자를 파싱하기 위한 스트링
		String extension = null;
		String[] parseExtension = null;
		
		String projectId = MetadataBundle.getProjectId();
		String mainPath = MetadataBundle.getProjectBasePath(projectId);
		
		boolean flag = false;
		if(metaworksFile != null && metaworksFile.getFileTransfer() != null && metaworksFile.getFileTransfer().getFilename() != null && !"".equals(metaworksFile.getFileTransfer().getFilename()) ){
			try {
				metaworksFile.upload();
				
				extension = metaworksFile.getFilename(); 
				parseExtension = extension.replace('.', '@').split("@");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			metaworksFile.setFileTransfer(null);
			return new Object[] { new Remover(new Popup(), true)};
		}
		
		
		if(parseExtension[1].equals("process")) {
			if(compareFileNavigator != null){
				compareFileNavigator.setUploaded(true);
				try {
					compareFileNavigator.setFileName(metaworksFile.getFilename());
					compareFileNavigator.setUploadPath(mainPath);
					compareFileNavigator.loadUpload();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return new Object[] { new Remover(new Popup(), true) , new Refresh(compareFileNavigator)};
			
		} else if (parseExtension[1].equals("zip")){
		
			String zipPath = metaworksFile.getUploadedPath();
			String destPath = mainPath + File.separatorChar + "temp";
			try {
				
				ZipInputStream zis = new ZipInputStream(new FileInputStream(metaworksFile.overrideUploadPathPrefix() + File.separatorChar + zipPath));
				ZipEntry ze = null;
	//		    ze = zis.getNextEntry();
			    
			    byte[] buf = new byte[1024];
			    while ((ze = zis.getNextEntry()) != null) {
	//		    while(ze != null){
			    	
			    	int n;
			    	String entryName = destPath  + File.separatorChar + ze.getName();
			    	File file = new File(entryName);
			    	
			    	if (ze.isDirectory()){ 
			    		file.mkdirs();
			    	}
//		    		ze = zis.getNextEntry();
			    	else {	
				    	FileOutputStream fos = new FileOutputStream(file);
				    	while ((n = zis.read(buf, 0, 1024)) > -1) {
				    		fos.write(buf, 0, n);
		            }
		
			    	fos.close();
		            zis.closeEntry();
			    	}
	
		        } //while
		
		        zis.close();
		        flag = true;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
			if( flag ){
				if(compareFileNavigator != null){
					compareFileNavigator.setUploaded(true);
					try {
						compareFileNavigator.setFileName(metaworksFile.getFilename());
						compareFileNavigator.setUploadPath(destPath);
						compareFileNavigator.loadUpload();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return new Object[] { new Remover(new Popup(), true) , new Refresh(compareFileNavigator)};
		}
		
		else {
			return null;
		}
		
	}

}
