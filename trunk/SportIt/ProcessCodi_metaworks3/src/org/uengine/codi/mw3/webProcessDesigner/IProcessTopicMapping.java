package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.annotation.Id;
import org.metaworks.dao.IDAO;

public interface IProcessTopicMapping extends IDAO {

	@Id
	public String getTopicId();
	public void setTopicId(String topicId);
	
	public String getProcessName();
	public void setProcessName(String processName);
	
	public String getProcessPath();
	public void setProcessPath(String processPath);
	
	
	public String getType();
	public void setType(String type);
	
	public void updateDatabaseMe() throws Exception;
}
