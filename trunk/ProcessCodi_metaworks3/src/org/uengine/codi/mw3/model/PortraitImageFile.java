package org.uengine.codi.mw3.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.website.MetaworksFile;
import org.uengine.kernel.GlobalContext;

public class PortraitImageFile extends MetaworksFile {

	public PortraitImageFile() {
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
	}

	String empCode;
		@Hidden
		public String getEmpCode() {
			return empCode;
		}
		public void setEmpCode(String empCode) {
			this.empCode = empCode;
		}


	@Override
	public void upload() throws FileNotFoundException, IOException, Exception {
		if (getFileTransfer() == null && getFileTransfer().getFilename() != null && getFileTransfer().getFilename().length() > 0)
			throw new Exception("No file attached");

		String fileSystemPath = GlobalContext.getPropertyString("filesystem.path",".");
		String portraitPath = fileSystemPath + "/portrait";
		String srcName = portraitPath + "/" + getEmpCode() + ".jpg";
		String thumnailName = portraitPath + "/" + getEmpCode() + ".thumnail.jpg";
		
		// 업로드 폴더 확인 및 생성
		File portraitFile = new File(portraitPath);
		if(!portraitFile.exists() || !portraitFile.isDirectory())
			portraitFile.mkdirs();

		File thumnailNameFile =new File(thumnailName);
		if(thumnailNameFile.exists()){
			thumnailNameFile.delete();
		}
		
		copyStream(getFileTransfer().getInputStream(), new FileOutputStream(srcName));
		
		setFileTransfer(null); // ensure to clear the data
	}

}
