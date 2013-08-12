package org.uengine.codi.mw3.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.AutowiredToClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.metadata.MetadataBundle;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.mw3.ide.compare.CompareFileNavigator;
import org.uengine.codi.mw3.ide.compare.CompareImportFile;
import org.uengine.codi.mw3.ide.compare.CompareImportFilePanel;
import org.uengine.codi.mw3.ide.compare.FileComparePanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewer;

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
	@AutowiredToClient
	public ProcessViewer processViewer;
	
	@AutowiredFromClient(select="autowiredObject.id=='target'")
	public CompareFileNavigator compareFileNavigator;
	
	@AutowiredFromClient
	public CompareImportFilePanel compareImportFilePanel;
	
	@AutowiredFromClient
	public FileComparePanel fileComparePanel;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] upload() throws IOException {
		boolean flag = false;
		String projectId = MetadataBundle.getProjectId();
		String mainPath = MetadataBundle.getProjectBasePath(projectId);
		String fileName = null;
		
		if(metaworksFile != null && metaworksFile.getFileTransfer() != null && metaworksFile.getFileTransfer().getFilename() != null && !"".equals(metaworksFile.getFileTransfer().getFilename()) ){
			try {
				fileName = metaworksFile.getFileTransfer().getFilename();
				metaworksFile.upload();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			metaworksFile.setFileTransfer(null);
			return new Object[] { new Remover(new Popup(), true)};
		}
		

//
//		if(fileName.endsWith(".prom") || fileName.endsWith(".zip")){
//			int BUFFER = 2048;
//			
//			BufferedOutputStream dest = null;
//		    InputStream fis =  metaworksFile.getFileTransfer().getInputStream();
//		    ZipInputStream zis = new 
//		    ZipInputStream(new BufferedInputStream(fis));
//		     
//		    ZipEntry entry;
//		    
//		    while((entry = zis.getNextEntry()) != null) {
//		    	Console.addLog("Extracting: " +entry);
//		    	int count;
//		    	byte data[] = new byte[BUFFER];
//	        
//		    // write the files to the disk	        
//	        File file = new File(CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/" + entry.getName());
//	        file.getParentFile().mkdirs();
//	        
//	        FileOutputStream fos = new FileOutputStream(file);
//	        
//	        dest = new BufferedOutputStream(fos, BUFFER);
//	        
//	        while ((count = zis.read(data, 0, BUFFER)) != -1) {
//	           dest.write(data, 0, count);
//	        }
//	        
//	        dest.flush();
//	        dest.close();
//	     }
//	     
//	     return;
//	}
//	
//	file.upload();
//	
//	String resourceBase = CodiClassLoader.getMyClassLoader().sourceCodeBase();
//	
//	if(fileName.endsWith(".jar")){ //when user uploads user-defined library file.
//		CodiClassLoader.refreshClassLoader(null); 
//	}
//	
//	new File(file.getUploadedPath()).renameTo(new File(resourceBase + getParentDirectory() + "/" + fileName));
//	//file.getUploadedPath()
//	}
//
		if(fileName.endsWith(".process")) {
			compareFileNavigator.setUploadType(".process");
			if(compareFileNavigator != null){
				compareFileNavigator.setUploaded(true);
				
				CompareImportFile compareImportFile = new CompareImportFile();
				try {
					compareFileNavigator.setFileName(metaworksFile.getFilename());
					compareFileNavigator.setUploadPath(fileComparePanel.getFileUploadPath());
					compareFileNavigator.loadUpload();
					
					
					compareImportFile.setSelectedProcessAlias(fileComparePanel.getFileUploadPath() + File.separatorChar + metaworksFile.getUploadedPath());
					compareImportFile.setFileName(metaworksFile.getFilename());
					compareImportFile.load();
					
					return new Object[] { new Remover(new Popup(), true) , new Refresh(compareFileNavigator), new Refresh(compareImportFile)};
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
			
//			int BUFFER = 2048;
//			
//			BufferedOutputStream dest = null;
//			InputStream fis = fileTransfer.getInputStream();
//			
//			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
//			ZipEntry entry;
//			
//			while((entry = zis.getNextEntry()) != null) {
//				Console.addLog("Extracting : " + entry);
//				int count;
//				byte data[] = new byte[BUFFER];
//				
//				File file = new File(CodiClassLoader.getMyClassLoader().sourceCodeBase() + File.separatorChar + entry.getName());
//				file.getParentFile().mkdirs();
//				
//				FileOutputStream fos = new FileOutputStream(file);
//				
//				dest = new BufferedOutputStream(fos, BUFFER);
//				
//				while((count = zis.read(data, 0, BUFFER)) != -1) {
//					dest.write(data, 0, count);
//				}
//				dest.flush();
//				dest.close();
//			}
			
		} else if (fileName.endsWith(".zip")){

			compareFileNavigator.setUploadType(".zip");
			String destPath = fileComparePanel.getFileUploadPath();
			
			FileInputStream fis = null;
		    ZipInputStream zipIs = null;
		    ZipEntry zEntry = null;
			
			try {
				fis = new FileInputStream(metaworksFile.overrideUploadPathPrefix() + File.separatorChar + metaworksFile.getUploadedPath());
				zipIs = new ZipInputStream(new BufferedInputStream(fis));
				
				while((zEntry = zipIs.getNextEntry()) != null) {
					try{
						byte[] tmp = new byte[4*1024];
						FileOutputStream fos = null;
						File file = new File(destPath + File.separator +  zEntry.getName());
						//create all folder needed to store in correct relative path.
						file.getParentFile().mkdirs();
						
						fos = new FileOutputStream(file);
						int size = 0;
						while((size = zipIs.read(tmp)) != -1) {
							fos.write(tmp, 0, size);
						}
						
						fos.flush();
						fos.close();
					} catch(Exception e) {
						
					}
		        } //while
				zipIs.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
			
			flag = true;
			if( flag ){
				if(compareFileNavigator != null){
					compareFileNavigator.setUploaded(true);
					try {
						compareFileNavigator.setFileName(metaworksFile.getFilename());
						compareFileNavigator.setUploadPath(fileComparePanel.getFileUploadPath());
						compareFileNavigator.loadUpload();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return new Object[] { new Remover(new Popup(), true) , new Refresh(compareFileNavigator)};
		}
		
		else if (fileName.endsWith(".jar")) {
			
			return null;
		}
		
		else {
			return null;
		}
		
	}

}
