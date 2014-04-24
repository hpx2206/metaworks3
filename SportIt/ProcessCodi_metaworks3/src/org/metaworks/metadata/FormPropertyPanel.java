package org.metaworks.metadata;

import java.util.ArrayList;

import org.metaworks.annotation.Face;

@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs" , options={"disableHeight"}, values={"true"})
public class FormPropertyPanel {
	
	ArrayList<FormProperty> formProperties;
		public ArrayList<FormProperty> getFormProperties() {
			return formProperties;
		}
		public void setFormProperties(ArrayList<FormProperty> formProperties) {
			this.formProperties = formProperties;
		}

	public FormPropertyPanel() {		
	}
	
	public FormPropertyPanel(ArrayList<FormProperty> formProperties) {
		this.setFormProperties(formProperties);
	}
}
