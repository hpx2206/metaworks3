package org.metaworks.website;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.directwebremoting.io.FileTransfer;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonEditable;
import org.metaworks.annotation.ServiceMethod;

public abstract class AbstractMetaworksFile implements ContextAware {
	
	public AbstractMetaworksFile() {
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
	}
	

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	transient FileTransfer fileTransfer;
		@Face(ejsPath="dwr/metaworks/org/directwebremoting/io/FileTransfer.ejs")
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
	
	String filename;
		@NonEditable
		@Hidden	
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
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
		
	String deletedPath;
		@NonEditable
		@Hidden
		public String getDeletedPath() {
			return deletedPath;
		}
		public void setDeletedPath(String deletedPath) {
			this.deletedPath = deletedPath;
		}
		
	String mimeType;
		@NonEditable
		@Hidden
		public String getMimeType() {
			return mimeType;
		}
		public void setMimeType(String mimeType) {
			this.mimeType = mimeType;
		}
		
	String directory;
		@Hidden	
		public String getDirectory() {
			return directory;
		}
		public void setDirectory(String directory) {
			this.directory = directory;
		}
		
	boolean auto;	
		public boolean isAuto() {
			return auto;
		}
		public void setAuto(boolean auto) {
			this.auto = auto;
		}
		
	@ServiceMethod(target="append", callByContent=true)
	public Download download() throws FileNotFoundException, IOException, Exception{
		return new Download(new FileTransfer(new String(this.getFilename().getBytes("UTF-8"),"ISO8859_1"), getMimeType(), new FileInputStream(overrideUploadPathPrefix() + "/" + this.getUploadedPath())));
	}

	@ServiceMethod(target=ServiceMethodContext.TARGET_NONE) //it doesn't cause refresh so that the recursive call of constructor of MetaworksFile javascript object never happened
	public BufferedImage downloadImage() throws FileNotFoundException, IOException, Exception{
		image = javax.imageio.ImageIO.read(new File(overrideUploadPathPrefix() + "/" + uploadedPath));
		
		return image;
	}

	/**
	 * You should call this method in the container as well. Normally you may call this method just before the database insertion to 
	 * save the file and set the properties as well when you have set the @ORMapping annotation to map the file location and mimetype
	 * so that your DAO saves it. 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
	@ServiceMethod(callByContent=true)
	public void upload() throws FileNotFoundException, IOException, Exception{
		
		if(fileTransfer==null && fileTransfer.getFilename()!=null && fileTransfer.getFilename().length() > 0) 
			throw new Exception("No file attached");
		
		String prefix = overrideUploadPathPrefix();
		
		String uploadFileName = renameUploadFile(fileTransfer.getFilename());		
		String uploadPath = prefix + uploadFileName;
		
		new File(uploadPath).getParentFile().mkdirs();
		
		copyStream(fileTransfer.getInputStream(), new FileOutputStream(uploadPath));
		
		setUploadedPath(uploadFileName); //only when the file has been successfully uploaded, this value is set, that means your can download later
		setMimeType(fileTransfer.getMimeType());
		
		fileTransfer = null; //ensure to clear the data
	}
	
	@ServiceMethod(payload={"deletedPath", "auto"})
	public void remove() throws FileNotFoundException, IOException, Exception{
		if(getDeletedPath().length() == 0) 
			throw new Exception("No file attached");
		
		File f = new File(getDeletedPath());
		// 확장자 없으면 폴더. 다만 폴더안에는 아무것도 없어야 한다.
		if (f.exists()) {
			boolean de = f.delete();
			if (de) {	
				setUploadedPath(null);
				
				if(!isAuto())
					setDeletedPath(null);
				System.out.println("Successed Delete!!");
			} else {
				System.out.println("Failed Delete!!");
			}
		} else {
			setUploadedPath(null);

			if(!isAuto())
				setDeletedPath(null);
			
			System.out.println("File Not Found!!");
		}
	}

	// set parted Stored file path by MimeType
	abstract public String overrideUploadPathPrefix();
	
	public String renameUploadFile(String filename) {
		String fileBody;
		String fileExt;
		
		fileBody = Long.toString(System.currentTimeMillis());
		
		if(filename.lastIndexOf(".") != -1){
			fileExt = filename.substring(filename.lastIndexOf("."));
			
			fileBody += fileExt;
		}
		
		return fileBody;
	}
	
	private String getDirectoryName() {
		String path;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		path = df.format(new Date()); 
		
		return path;
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
