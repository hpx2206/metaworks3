package org.metaworks.metadata;

import java.util.ArrayList;

import org.metaworks.annotation.Face;

@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
public class ImagePropertyPanel {

	ArrayList<ImageProperty> imageProperties;
		public ArrayList<ImageProperty> getImageProperties() {
			return imageProperties;
		}
		public void setImageProperties(ArrayList<ImageProperty> imageProperties) {
			this.imageProperties = imageProperties;
		}

	public ImagePropertyPanel() {		
	}
	
	public ImagePropertyPanel(ArrayList<ImageProperty> imageProperties) {
		this.setImageProperties(imageProperties);
	}
}
