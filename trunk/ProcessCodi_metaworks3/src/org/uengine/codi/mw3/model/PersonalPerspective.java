package org.uengine.codi.mw3.model;

import org.metaworks.annotation.ServiceMethod;

public class PersonalPerspective extends Perspective {

	public PersonalPerspective() {
		setLabel("Personal");
	}

	@ServiceMethod
	public Object[] loadAllICanSee() throws Exception{
		return loadInstanceListPanel("allICanSee", null);
	}
	
	@ServiceMethod
	public Object[] loadAll() throws Exception{
		return loadInstanceListPanel("all", null);
	}
	
	@ServiceMethod
	public Object[] loadInbox() throws Exception{
		return loadInstanceListPanel("inbox", null);
	}
	
	@ServiceMethod
	public Object[] loadRequest() throws Exception{
		return loadInstanceListPanel("request", null);
	}
	
	@ServiceMethod
	public Object[] loadStopped() throws Exception{
		return loadInstanceListPanel("stopped", null);
	}
}
