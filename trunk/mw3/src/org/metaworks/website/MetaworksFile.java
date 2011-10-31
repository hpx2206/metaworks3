package org.metaworks.website;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.directwebremoting.io.FileTransfer;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;

public class MetaworksFile {
	
	FileTransfer fileTransfer;
	
		public FileTransfer getFileTransfer() {
			return fileTransfer;
		}
	
		public void setFileTransfer(FileTransfer fileTransfer) {
			this.fileTransfer = fileTransfer;
		}
		
	String uploadedPath;
		@Id
		@NonEditable
		public String getUploadedPath() {
			return uploadedPath;
		}
		public void setUploadedPath(String uploadedPath) {
			this.uploadedPath = uploadedPath;
		}
		
	String mimeType;
		@NonEditable
		public String getMimeType() {
			return mimeType;
		}
		public void setMimeType(String mimeType) {
			this.mimeType = mimeType;
		}

	@ServiceMethod
	public void download() throws FileNotFoundException, IOException, Exception{
		fileTransfer = new FileTransfer(uploadedPath, getMimeType(), new FileInputStream(uploadedPath));
	}

	@ServiceMethod(callByContent=true)
	public void upload() throws FileNotFoundException, IOException, Exception{
		
		if(fileTransfer==null) 
			throw new Exception("No file attached");
		
		String uploadPath = "/Users/jyjang/Documents/" + fileTransfer.getFilename();
		copyStream(fileTransfer.getInputStream(), new FileOutputStream(uploadPath));
		
		setUploadedPath(uploadPath); //only when the file has been successfully uploaded, this value is set, that means your can download later
		
	}
	
	
	static public void copyStream(InputStream sourceInputStream, OutputStream targetOutputStream) throws Exception{
		int length = 1024;
		byte[] bytes = new byte[length]; 
		int c; 
		int total_bytes=0;
			
		while ((c = sourceInputStream.read(bytes)) != -1) { 
				total_bytes +=c; 
				targetOutputStream.write(bytes,0,c); 
		} 
		
		if (sourceInputStream != null) try { sourceInputStream.close(); } catch (Exception e) {}
		if (targetOutputStream != null) try { targetOutputStream.close(); } catch (Exception e) {}
	}

}
