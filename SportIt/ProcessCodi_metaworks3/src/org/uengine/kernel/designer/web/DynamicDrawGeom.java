package org.uengine.kernel.designer.web;

import java.util.ArrayList;

import org.uengine.kernel.Activity;

public interface DynamicDrawGeom {

	public String getParentGeomId();
	public void setParentGeomId(String parentGeomId);

	public String getEditorId();
	public void setEditorId(String editorId);
	
	public String getViewType();
	public void setViewType(String viewType);
	
	public ArrayList<Activity> getActivityList();
	public void setActivityList(ArrayList<Activity> activityList);
}
