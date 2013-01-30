package org.uengine.codi.mw3.knowledge;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;
import org.metaworks.widget.ModalWindow;

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
		
	@ServiceMethod(inContextMenu=true, callByContent=true, target="popup")
	@Face(displayName="$Edit")
	public ModalWindow modify() throws Exception;
	
	@ServiceMethod(inContextMenu=true, callByContent=true,target="popup")
	public ModalWindow exportHtml() throws Exception;
	
	@ServiceMethod(callByContent=true, mouseBinding="drop", target=ServiceMethodContext.TARGET_APPEND)
	public Object[] drop() throws Exception;
}
