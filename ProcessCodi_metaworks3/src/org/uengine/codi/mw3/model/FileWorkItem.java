package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Test;
import org.metaworks.website.MetaworksFile;
import org.uengine.util.UEngineUtil;

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
	@Test(scenario="first", instruction="$first.FileWorkItem.add")
	public Object[] add() throws Exception {
	
		if(!UEngineUtil.isNotEmpty(getTitle())){
			setTitle(getFile().getFileTransfer().getFilename());
		}
		
		getFile().upload();
		
		return super.add();
	}
}
