package org.uengine.codi;

import java.io.Serializable;

import org.metaworks.ContextAware;

public interface ITool extends ContextAware, Serializable{
	
	public void onLoad();
	
	public void beforeComplete();
	
	public void afterComplete();

}
