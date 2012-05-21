package org.uengine.codi.mw3.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.mw3.CodiClassLoader;

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
		
		file.upload();
		
		String resourceBase = CodiClassLoader.getMyClassLoader().sourceCodeBase();

		if(fileName.endsWith(".jar")){ //when user uploads user-defined library file.
			CodiClassLoader.refreshClassLoader(null); 
		}
		
		new File(file.getUploadedPath()).renameTo(new File(resourceBase + getParentDirectory() + "/" + fileName));
		//file.getUploadedPath()
	}
	
}
