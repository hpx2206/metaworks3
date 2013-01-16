package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.website.MetaworksFile;
import org.uengine.util.UEngineUtil;

public class FileWorkItem extends WorkItem{
	
	
	public FileWorkItem(){
		setType("file");
		setFile(new MetaworksFile()); 
	}
	
	
	String versionUpOption;
		@Range(
				options={"Major Upgrade", "Minor Upgrade"},
				values={"Major", "Minor"}
		)
		public String getVersionUpOption() {
			return versionUpOption;
		}
		public void setVersionUpOption(String versionUpOption) {
			this.versionUpOption = versionUpOption;
		}

	@Override
	@Hidden(on=false) //overrides the annotation
	public MetaworksFile getFile() {
		return super.getFile();
	}

	@Override
	@Test(scenario="first", instruction="$first.FileWorkItem.add")
	public Object[] add() throws Exception {
		
		if(getTaskId()!=null){
			
			if("Major".equals(getVersionUpOption())){
				setMajorVer(getMajorVer()+1);
				setMinorVer(0);
			}else{
				setMinorVer(getMinorVer()+1);
			}
			
		}
	
		if(!UEngineUtil.isNotEmpty(getTitle())){
			setTitle(getFile().getFileTransfer().getFilename());
		}
		
		getFile().upload();
		
		return super.add();
	}

	@ServiceMethod(inContextMenu=true, callByContent=true, except="file")
	public void edit() throws Exception {
		setFile(new MetaworksFile());
		
		super.edit();
		
		
	}
	


	
	
}
