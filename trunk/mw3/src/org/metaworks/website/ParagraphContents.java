package org.metaworks.website;

import org.metaworks.annotation.Hidden;

public class ParagraphContents extends Contents{
	
	public ParagraphContents(){
		setType("p");
	}

	@Hidden(on=false) //overrides the annotation
	@Override
	public String getParagraph() {
		// TODO Auto-generated method stub
		return super.getParagraph();
	}
	

}
