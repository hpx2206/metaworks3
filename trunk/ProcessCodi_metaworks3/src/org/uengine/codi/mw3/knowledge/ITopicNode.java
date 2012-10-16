package org.uengine.codi.mw3.knowledge;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="bpm_knol")
public interface ITopicNode extends IDAO {
	
	@Id
	public String getId();
	public void setId(String id);
	
	public String getName();
	public void setName(String name);
	
	public String getSecuopt();
	public void setSecuopt(String secuopt);
	
	public String getAuthorId();
	public void setAuthorId(String authorId);
	
	@ServiceMethod(callByContent=true)
	public Object[] loadTopic() throws Exception;
	
	@ServiceMethod(inContextMenu=true, callByContent=true)
	@Face(displayName="$Remove")
	public Object[] remove() throws Exception;
	
	@ServiceMethod(inContextMenu=true, callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	@Face(displayName="$addTopicUser")
	public Object[] addTopicUser() throws Exception;
	
}
