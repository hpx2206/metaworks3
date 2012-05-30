package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.NewInstancePanel;

@Table(name="bpm_knol")
public interface IWfNode extends IDAO {

	@Id
	public String getId();
	public void setId(String id);
		
	public Long getLinkedInstId();
	public void setLinkedInstId(Long linkedInstanceId);

	@Name
	public String getName();
	public void setName(String name);
		
	public String getParentId();
	public void setParentId(String parentId);
	
	public int getNo();
	public void setNo(int no);

	@NonLoadable
	@NonSavable
	public String getNameNext();
	public void setNameNext(String nameNext);
	
	@NonLoadable
	@NonSavable
	public boolean isFocus();
	public void setFocus(boolean focus);

	
	@NonLoadable
	@NonSavable
	public WfNode getDragNode();
	public void setDragNode(WfNode dragNode);
	
	@NonLoadable
	@NonSavable
	public ArrayList<WfNode> getChildNode();
	public void setChildNode(ArrayList<WfNode> childNode);
	
	
	/*******************************************
	 * 
	 * Service Method
	 *  
	 *******************************************/
	@ServiceMethod(callByContent=true)
	public WfNode newNode() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public WfNode add() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public WfNode outdent() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public WfNode indent() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public WfNode remove() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public WfNode move() throws Exception;

	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_NONE)
	public void save() throws Exception;

	@ServiceMethod(callByContent=true)
	public ContentWindow linkInstance() throws Exception;
	
	@ServiceMethod(inContextMenu=true, keyBinding="Ctrl+N")
	public ContentWindow newProcessInstance() throws Exception;
		
	@ServiceMethod(inContextMenu=true, keyBinding="Ctrl+D")
	public ContentWindow newDocument() throws Exception;	
	
	/*******************************************
	 * 
	 * function
	 *  
	 *******************************************/
	public void saveMe() throws Exception;
	
	public void createMe() throws Exception;
}
