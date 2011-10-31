package org.metaworks.website;

import org.metaworks.annotation.Hidden;

public class SourceCodeContents extends Contents{
	
	public SourceCodeContents(){
		setType("src");
	}

	@Hidden(on=false) //overrides the annotation
	@Override
	public String getParagraph() {
		// TODO Auto-generated method stub
		return super.getParagraph();
	}

}
