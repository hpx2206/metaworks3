package org.metaworks.website;

import org.metaworks.annotation.Hidden;

public class ImageContents extends Contents{
	
	public ImageContents(){
		setType("img");
	}

	@Hidden(on=false) //overrides the annotation
	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return super.getUrl();
	}

	@Hidden(on=false) //overrides the annotation
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return super.getWidth();
	}

	@Hidden(on=false) //overrides the annotation
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return super.getHeight();
	}

}
