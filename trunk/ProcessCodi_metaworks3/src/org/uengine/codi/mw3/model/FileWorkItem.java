package org.uengine.codi.mw3.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ConnectException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;

import org.directwebremoting.io.FileTransfer;
import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ToAppend;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.util.CodiStatusUtil;
import org.uengine.persistence.dao.UniqueKeyGenerator;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.util.UEngineUtil;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

public class FileWorkItem extends WorkItem{

	FileTransfer fileTransfer;
	@Face(ejsPath="dwr/metaworks/org/directwebremoting/io/FileTransfer.ejs")
	public FileTransfer getFileTransfer() {
		return fileTransfer;
	}	
	public void setFileTransfer(FileTransfer fileTransfer) {
		this.fileTransfer = fileTransfer;
	}	

	public FileWorkItem(){
		setType("file");
		setFile(new MetaworksFile()); 
	}


	String versionUpOption;
	@Range(
			options={"Major Upgrade", "Minor Upgrade"},
			values={"Major", "Minor"}
			)
	public String getVersionUpOption() {
		return versionUpOption;
	}
	public void setVersionUpOption(String versionUpOption) {
		this.versionUpOption = versionUpOption;
	}

	@Override
	@Hidden(on=false) //overrides the annotation
	public MetaworksFile getFile() {
		return super.getFile();
	}

	@Override
	@Test(scenario="first", instruction="$first.FileWorkItem.add")
	public Object[] add() throws Exception {
		//		long beforeTaskId = this.getTaskId();
		Object[] returnObject = null;

		if (this.getFile() == null || this.getFile().getFileTransfer() == null
				|| this.getFile().getFileTransfer().getFilename() == null)
			throw new MetaworksException("파일을 첨부해주세요.");
		//		

//		// 추가모드 일때
		if(WHEN_NEW.equals(this.getMetaworksContext().getWhen()))	{
			this.setTaskId(UniqueKeyGenerator.issueWorkItemKey(((ProcessManagerBean)processManager).getTransactionContext()));
				if(this.getGrpTaskId() == null)
					this.setGrpTaskId(this.getTaskId());
		
			if(this.getWorkItemVersionChooser() != null)
				this.getWorkItemVersionChooser().setTaskId(this.getTaskId());
	
			// 제목이 없으면 파일명을 제목으로
			if(!UEngineUtil.isNotEmpty(getTitle())){
				setTitle(getFile().getFileTransfer().getFilename());
			}
	
			this.setFileTransfer(this.getFile().getFileTransfer());
			// 파일 업로드
			getFile().upload();
	
			this.setContent(this.getFile().getUploadedPath());
			this.setTool(this.getFile().getMimeType());
			this.setExtFile(this.getFile().getFilename());
	
			String mimeType = getFile().getMimeType();
			if(mimeType != null && mimeType.indexOf("image") != 0){
				Preview preview = new Preview();
				preview.setTaskId(getTaskId());
				preview.setMimeType(mimeType);
				this.setPreview(preview);
			}else{
				this.setPreview(null);
			}
	
			// WorkItem 추가
			returnObject = super.add();
	
			this.setWorkItemVersionChooser(this.databaseMe().getWorkItemVersionChooser());

		}else if(WHEN_EDIT.equals(this.getMetaworksContext().getWhen())){
			
			int thisMajorVersion = this.getMajorVer();
			int nextVersion = this.getMajorVer()+1;
			
			this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			this.setMajorVer(nextVersion);
			this.databaseMe().setTitle(getFile().getFileTransfer().getFilename());
			String originInstId = instanceViewContent.getInstanceView().getInstanceId();
			String title = this.getTitle();
			
			FileWorkItem fileWorkItem = new FileWorkItem();
			fileWorkItem.setInstId(new Long(this.getInstId()));
			fileWorkItem.session = session;
			fileWorkItem.processManager = this.processManager;
			fileWorkItem.instanceViewContent = this.instanceViewContent;
			fileWorkItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
			fileWorkItem.setWriter(session.getUser());
			fileWorkItem.setFile(this.getFile());
			fileWorkItem.setGrpTaskId(this.getGrpTaskId());
			fileWorkItem.setMajorVer(nextVersion);
			fileWorkItem.setTitle(title);
			fileWorkItem.add();
			
			String thisVersion = String.valueOf(nextVersion+ "." + 0);
			workItemVersionChooser.getVersionSelector().add(thisVersion , thisVersion);
			workItemVersionChooser.setSelectedVersion(thisVersion);
			
			SystemWorkItem SystemWorkItem = new SystemWorkItem();
			SystemWorkItem.session = session;
			SystemWorkItem.processManager = this.processManager;
			SystemWorkItem.instanceViewContent = this.instanceViewContent;
			SystemWorkItem.setInstId(new Long(originInstId));
			SystemWorkItem.setSystemMessage("파일이 수정되었습니다. " + title + " version "+ String.valueOf(thisMajorVersion+ "." + 0) +" =>" + thisVersion);
			SystemWorkItem.add();
			
			InstanceViewThreadPanel instanceViewThreadPanel = new InstanceViewThreadPanel();
			instanceViewThreadPanel.setInstanceId(originInstId);
			
			return new Object[]{new Refresh(this) , new Refresh(workItemVersionChooser) , new ToAppend(instanceViewThreadPanel, SystemWorkItem)};
			
//			Object[] temReturnObject = fileWorkItem.add();
//			returnObject = new Object[temReturnObject.length + 1];
//			System.arraycopy(temReturnObject, 0, returnObject, 0, temReturnObject.length);
//			
//			returnObject[temReturnObject.length] = new Remover(this);
		}
		
		return returnObject;
		
	}

	@ServiceMethod(inContextMenu=true, when = WHEN_VIEW, callByContent=true, except="file")
	public void edit() throws Exception {
		setFile(new MetaworksFile());

		super.edit();
	}


	public int getImageForPdf(String inputFile, String outputFile) throws IOException{

		File file = new File(inputFile);

		RandomAccessFile raf = new RandomAccessFile(file, "r");
		FileChannel channel = raf.getChannel();
		ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

		PDFFile pdfFile = new PDFFile(buf);
		PDFPage	page;

		int lastPageNumber = pdfFile.getNumPages();

		for(int i=0;i<lastPageNumber; i++){
			page = pdfFile.getPage(i);

			Rectangle rect = new Rectangle(
					0,			//left
					0,			//right
					(int)page.getBBox().getWidth(),	//width
					(int)page.getBBox().getHeight()	//height
					);

			Image images = page.getImage(
					rect.width,
					rect.height,
					rect,		//crip rec
					null,		//null for ImageObserver
					true,		//fill background with white
					true		//block until drawing is done
					);

			int w = images.getWidth(null);
			int h = images.getHeight(null);

			BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

			Graphics2D g2 = bi.createGraphics();
			g2.drawImage(images, 0, 0, null);
			g2.dispose();

			ImageIO.write(bi, "jpg", new File(outputFile + "_" + i + ".jpg"));

		}


		return lastPageNumber;
	}


	public boolean convertPdf(String inputFilePath, String outputFilePath) throws FileNotFoundException, IOException, Exception, ConnectException {

		boolean isConvert = false;

		File inputFile = new File(inputFilePath);
		File outputFile = new File(outputFilePath);

		// connect to an OpenOffice.org instance running on port 8100
		OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);

		try {
			connection.connect();

			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
			converter.convert(inputFile, outputFile);

			isConvert = true;
		}finally{
			connection.disconnect();
		}

		return isConvert;
	}

	public boolean createPreviewFile(String targetPath, String convertType){

		try {
			this.copyFrom(this.databaseMe());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if(this.getTool() == null)
			return true;

		File srcFile = new File(this.getFile().overrideUploadPathPrefix() + this.getContent());
		if(!srcFile.isFile())
			return false;

		boolean convert = true;
		String convertedFilePath = targetPath + File.separatorChar + this.makeConvertedFilename();


		if("image".equals(convertType)){
			if(this.getExt1() == null){
				convert = false;
			}
		}

		if(convert){
			CodiStatusUtil statusUtil = new CodiStatusUtil(targetPath, this.getTaskId() + "_" +  convertType);

			if(!statusUtil.ready())
				return false;

			statusUtil.queue();

			if("pdf".equals(convertType)){
				boolean converted = false;

				if(this.getTool().indexOf("ms") > 0 || this.getTool().indexOf("officedocument") > 0 ||
						this.getTool().indexOf("haansoft") > 0 ||		
						this.getTool().indexOf("plain") > 0 || this.getTool().indexOf("rtf") > 0){

					// converting office file with jod converter, open office
					System.out.println("office converting");
					try {
						convertPdf(srcFile.getAbsolutePath(), convertedFilePath);
						converted = true;
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				}else if(this.getTool().equals("application/pdf")){
					System.out.println("pdf converting");

					FileInputStream is = null;
					FileOutputStream os = null;

					try {						
						is = new FileInputStream(srcFile.getAbsolutePath());
						os = new FileOutputStream(convertedFilePath);

						MetaworksFile.copyStream(is, os);
						converted = true;
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					} finally {
						if(os != null)
							try {
								os.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						if(is != null)
							try {
								is.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
					}
				}

				if(converted){
					Preview preview = new Preview();
					preview.setTaskId(this.getTaskId());
					preview.setMimeType(convertType);
					preview.setConvertStatus("1");

					try {
						databaseMe().setExt1(preview.getConvertStatus());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}

					MetaworksRemoteService.pushClientObjects(new Object[]{new Refresh(preview)});

				}
			}else if("image".equals(convertType)){
				
				try {
					int pageCount = getImageForPdf(convertedFilePath, convertedFilePath);

					Preview preview = new Preview();
					preview.setTaskId(this.getTaskId());
					preview.setMimeType(convertType);
					preview.setPageCount(String.valueOf(pageCount));
					preview.setConvertStatus("2");

					databaseMe().setExt1(preview.getConvertStatus());
					databaseMe().setExt2(preview.getPageCount());


					MetaworksRemoteService.pushClientObjects(new Object[]{new Refresh(preview)});
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				
			}
		}


		return true;


		/*


		boolean result = false;
		String fileSystemPath = GlobalContext.getPropertyString("filesystem.path",".");
		String previewPath = fileSystemPath + "/preview";8

		String realFilePath = fileSystemPath + "/" + realContent;

		File realFile = new File(realFilePath);

		Preview preview = new Preview();
		preview.setStatusPreview("converting");
		this.setPreview(preview);
		MetaworksRemoteService.pushClientObjects(new Object[]{new Refresh(preview)});

		if(realFile.exists() && realFile.isFile() && realFile.length() > 0) {
			// Office, PDF 파일변환
			if(realFileMimeType != null && realFileMimeType.indexOf("image") != 0){
				String inputFilePath = realFilePath;
				String outputFilePath = previewPath + "/" + taskId + ".pdf";

				try{
					// office 문서 realFileMimeType -- 2007이하 버젼: indexOf("ms"), 2007이후버전: indexOf("officedocument")
					if(realFileMimeType.indexOf("ms") > 0 || realFileMimeType.indexOf("officedocument") > 0 || 
					   realFileMimeType.indexOf("plain") > 0 || realFileMimeType.indexOf("rtf") > 0){
						convertPdf(inputFilePath, outputFilePath);
						result = true;
					}else{
						MetaworksFile.copyStream(new FileInputStream(inputFilePath), new FileOutputStream(outputFilePath));
						result = true;
					}

					preview.setTaskId(taskId);
					preview.setMimeType(realFileMimeType);

					//change for converted PDF file to image file (save all pages count to ext1(bpm_worklist table))
					preview.setPageCountInt(
							getImageForPdf(inputFilePath, outputFilePath)
					);

					WorkItem workItem =new WorkItem();
					workItem.setTaskId(taskId);
					workItem.databaseMe().setExt1(preview.getPageCount());
					workItem.flushDatabaseMe();
					result = true;

				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}

		if(result) {
			preview.setConvertingResult("success");
		}else{
			preview.setConvertingResult("fail");
		}

		preview.setStatusPreview("convertingComplete");
		this.setPreview(preview);
		MetaworksRemoteService.pushClientObjects(new Object[]{new Refresh(preview)});
		return result;
		 */
	}

	public String makeConvertedFilename(){
		return this.getTaskId() + ".pdf";
	}
}
