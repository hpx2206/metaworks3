package org.metaworks.metadata;

import java.util.ArrayList;

import org.metaworks.annotation.Face;

@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
public class FilePropertyPanel {
	
	ArrayList<FileProperty> fileProperties;
		public ArrayList<FileProperty> getFileProperties() {
			return fileProperties;
		}
		public void setFileProperties(ArrayList<FileProperty> fileProperties) {
			this.fileProperties = fileProperties;
		}

	public FilePropertyPanel() {	
	}
	
	public FilePropertyPanel(ArrayList<FileProperty> fileProperties) {
		this.setFileProperties(fileProperties);
	}
}
