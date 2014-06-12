package org.metaworks.metadata;

import java.util.ArrayList;

import org.metaworks.annotation.Face;

@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs" , options={"disableHeight"}, values={"true"} ) 
public class StringPropertyPanel {	
	
	ArrayList<StringProperty> stringProperties;
		public ArrayList<StringProperty> getStringProperties() {
			return stringProperties;
		}
		public void setStringProperties(ArrayList<StringProperty> stringProperties) {
			this.stringProperties = stringProperties;
		}
	
	public StringPropertyPanel() {		
	}
	
	public StringPropertyPanel(ArrayList<StringProperty> stringProperties) {
		this.setStringProperties(stringProperties);
	}
}
