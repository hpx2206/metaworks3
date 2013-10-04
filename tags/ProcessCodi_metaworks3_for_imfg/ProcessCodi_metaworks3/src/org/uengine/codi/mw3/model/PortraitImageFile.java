package org.uengine.codi.mw3.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.dao.TransactionContext;
import org.metaworks.website.MetaworksFile;

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

		String prefix = TransactionContext.getThreadLocalInstance()
				.getRequest().getSession().getServletContext()
				.getRealPath("/images/portrait/");
		System.out.println(prefix);
		// prefix = "/Volumes/DATA/Projects/kipi/uengine/";

		String portraitFileName = getEmpCode() + ".jpg";
		String uploadPath = prefix + File.separator + portraitFileName;
		new File(uploadPath).getParentFile().mkdirs();
		copyStream(getFileTransfer().getInputStream(), new FileOutputStream(uploadPath));

		setFileTransfer(null); // ensure to clear the data
	}

}