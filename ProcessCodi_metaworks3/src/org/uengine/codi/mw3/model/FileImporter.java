package org.uengine.codi.mw3.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.platform.Console;

public class FileImporter{

	public FileImporter(){
		file = new MetaworksFile();
	}
	
	String parentDirectory;
	@Hidden
		public String getParentDirectory() {
			return parentDirectory;
		}
	
		public void setParentDirectory(String parentDirectory) {
			this.parentDirectory = parentDirectory;
		}


	MetaworksFile file;
	
		public MetaworksFile getFile() {
			return file;
		}
	
		public void setFile(MetaworksFile file) {
			this.file = file;
		}
		
		
	@ServiceMethod(callByContent=true)
	public void upload() throws FileNotFoundException, IOException, Exception{
		
		String fileName = file.getFileTransfer().getFilename();
		
		if(fileName.endsWith(".prom") || fileName.endsWith(".zip")){
			int BUFFER = 2048;
			
			BufferedOutputStream dest = null;
	         InputStream fis =  file.getFileTransfer().getInputStream();
	         ZipInputStream zis = new 
	        		 ZipInputStream(new BufferedInputStream(fis));
	         
	         ZipEntry entry;
	         while((entry = zis.getNextEntry()) != null) {
	            Console.addLog("Extracting: " +entry);
	            int count;
	            byte data[] = new byte[BUFFER];
	            // write the files to the disk
	            
	            File file = new File(CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/" + entry.getName());
	            file.getParentFile().mkdirs();
	            
	            FileOutputStream fos = new 
	            		FileOutputStream(file);
	            
	            dest = new 
	              BufferedOutputStream(fos, BUFFER);
	            
	            while ((count = zis.read(data, 0, BUFFER)) 
	              != -1) {
	               dest.write(data, 0, count);
	            }
	            
	            dest.flush();
	            dest.close();
	         }
	         
	         return;
		}
		
		file.upload();
		
		String resourceBase = CodiClassLoader.getMyClassLoader().sourceCodeBase();

		if(fileName.endsWith(".jar")){ //when user uploads user-defined library file.
			CodiClassLoader.refreshClassLoader(null); 
		}
		
		new File(file.getUploadedPath()).renameTo(new File(resourceBase + getParentDirectory() + "/" + fileName));
		//file.getUploadedPath()
	}
	
}
