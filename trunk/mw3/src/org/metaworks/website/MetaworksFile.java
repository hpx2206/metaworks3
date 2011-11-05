package org.metaworks.website;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.directwebremoting.io.FileTransfer;
import org.metaworks.ServiceMethodContext;
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
		
	BufferedImage image;
			
		public BufferedImage getImage() {
			return image;
		}
	
		public void setImage(BufferedImage image) {
			this.image = image;
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

	@ServiceMethod(target=ServiceMethodContext.TARGET_NONE) //it doesn't cause refresh so that the recursive call of constructor of MetaworksFile javascript object never happened
	public BufferedImage downloadImage() throws FileNotFoundException, IOException, Exception{
		image = javax.imageio.ImageIO.read(new File(uploadedPath));
		
		return image;
	}

//	@ServiceMethod(callByContent=true)
	public void upload() throws FileNotFoundException, IOException, Exception{
		
		if(fileTransfer==null) 
			throw new Exception("No file attached");
		
		
		String uploadPath = "fileSystem/" + fileTransfer.getFilename();
		
		new File(uploadPath).getParentFile().mkdirs();
		
		copyStream(fileTransfer.getInputStream(), new FileOutputStream(uploadPath));
		
		setUploadedPath(uploadPath); //only when the file has been successfully uploaded, this value is set, that means your can download later
		setMimeType(fileTransfer.getMimeType());
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
