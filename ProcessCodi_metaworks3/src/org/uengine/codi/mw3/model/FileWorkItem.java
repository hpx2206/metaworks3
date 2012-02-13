package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Hidden;
import org.metaworks.website.MetaworksFile;

public class FileWorkItem extends WorkItem{
	
	
	public FileWorkItem(){
		setType("file");
		setFile(new MetaworksFile()); 
	}

	@Override
	@Hidden(on=false) //overrides the annotation
	public MetaworksFile getFile() {
		return super.getFile();
	}

	@Override
	public WorkItem[] add() throws Exception {

		getFile().upload();
		
		return super.add();
	}
}
