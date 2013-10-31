package org.uengine.codi.mw3.knowledge;

import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.knowledge.ITopicNode;

public interface IProjectNode extends ITopicNode {
	
	@ServiceMethod(callByContent=true, payload="name")
	public IProjectNode findById() throws Exception;
}
