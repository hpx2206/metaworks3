package org.metaworks.website;

import org.metaworks.annotation.Hidden;

public class FileContents extends Contents{
	
	public FileContents(){
		setType("file");
		setFile(new MetaworksFile()); 
	}

	@Override
	@Hidden(on=false) //overrides the annotation
	public MetaworksFile getFile() {
		return super.getFile();
	}
	

}
