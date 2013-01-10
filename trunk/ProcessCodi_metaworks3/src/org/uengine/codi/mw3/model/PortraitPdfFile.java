package org.uengine.codi.mw3.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.metaworks.MetaworksContext;
import org.metaworks.website.MetaworksFile;
import org.metaworks.annotation.Hidden;
import org.metaworks.dao.TransactionContext;

public class PortraitPdfFile extends MetaworksFile{
	
	public PortraitPdfFile(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("pdf");
	}
	
	String inputFilePath;
		@Hidden
		public String getInputFilePath() {
			return inputFilePath;
		}
		public void setInputFilePath(String inputFilePath) {
			this.inputFilePath = inputFilePath;
		}
		
	String outputFilePath;
		@Hidden
		public String getOutputFilePath() {
			return outputFilePath;
		}
		public void setOutputFilePath(String outputFilePath) {
			this.outputFilePath = outputFilePath;
		}
	
	@Override
	public void upload() throws FileNotFoundException, IOException, Exception {
		if(getFileTransfer() == null && getFileTransfer().getFilename() != null && getFileTransfer().getFilename().length() > 0)
			throw new Exception("No file attached");
//		String outPath = outputFilePath + ".pdf";
//		
//		new File(outPath).getParentFile().mkdirs();
//		copyStream(getFileTransfer().getInputStream(), new FileOutputStream(outPath));
		
		String prefix = TransactionContext.getThreadLocalInstance()
				.getRequest().getSession().getServletContext()
				.getRealPath("/images/portrait/pdf/");
		System.out.println(prefix);
		
		String portraitFileName = "aaa" + ".pdf";
		String uploadPath = prefix + File.separator + portraitFileName;
		new File(uploadPath).getParentFile().mkdirs();
		copyStream(getFileTransfer().getInputStream(), new FileOutputStream(uploadPath));
		
		setFileTransfer(null);
	}

}
