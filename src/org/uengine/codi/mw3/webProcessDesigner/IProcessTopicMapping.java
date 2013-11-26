package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.dao.IDAO;

public interface IProcessTopicMapping extends IDAO {

	
	public String getProcessName();
	public void setProcessName(String processName);
	
	public String getProcessPath();
	public void setProcessPath(String processPath);
	
	public String getTopicId();
	public void setTopicId(String topicId);
	
	public String getType();
	public void setType(String type);
}
