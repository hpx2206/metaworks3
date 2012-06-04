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
import org.metaworks.annotation.TypeSelector;
import org.metaworks.dao.IDAO;
import org.uengine.codi.mw3.model.CommentWorkItem;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.FileWorkItem;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.ImageWorkItem;
import org.uengine.codi.mw3.model.MovieWorkItem;
import org.uengine.codi.mw3.model.NewInstancePanel;
import org.uengine.codi.mw3.model.PostingsWorkItem;
import org.uengine.codi.mw3.model.ScheduleWorkItem;
import org.uengine.codi.mw3.model.SourceCodeWorkItem;
import org.uengine.codi.mw3.model.WorkItem;

@Table(name="bpm_knol")
public interface IWfNode extends IDAO {

	@Id
	public String getId();
	public void setId(String id);
	
	
//	@TypeSelector(
//			values = 		{ 
//					"text",			
//					"img",		
//					"mov", 				
//					"src",
//					"file", 
//					"schdle",
//					//"generic"
//				}, 
//			classes = 		{ 
//					WfNode.class,  	
//					WfImageNode.class,					
//					WfMovieNode.class,
//					WfSourceNode.class,
//					WfFileNode.class,
//					WfScheduleNode.class,
//					//GenericWorkItem.class
//				} 
//	)
	public String getType();
	public void setType(String type);
		
	public Long getLinkedInstId();
	public void setLinkedInstId(Long linkedInstanceId);

	public String getAuthorId();
	public void setAuthorId(String authorId);
	
	@ORMapping(databaseFields="authorId", objectFields="userId")
	public IUser getAuthor();
	public void setAuthor(IUser author);

	@Name
	public String getName();
	public void setName(String name);
		
	public String getParentId();
	public void setParentId(String parentId);
	
	@Hidden
	public int getNo();
	public void setNo(int no);

	@NonLoadable
	@NonSavable
	@Hidden
	public String getNameNext();
	public void setNameNext(String nameNext);
	
	@NonLoadable
	@NonSavable
	@Hidden
	public boolean isFocus();
	public void setFocus(boolean focus);

	@NonLoadable
	@NonSavable
	@Hidden
	public boolean isClose();
	public void setClose(boolean close);
	
	@NonLoadable
	@NonSavable
	@Hidden
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
	@ServiceMethod(callByContent=true, except={"childNode", "focus"}, target="popup")
	public Object[] add() throws Exception;
	
	@ServiceMethod(callByContent=true, except={"childNode", "focus"}, target="popup")
	public Object[] outdent() throws Exception;
	
	@ServiceMethod(callByContent=true, except={"childNode", "focus"}, target="popup")
	public Object[] indent() throws Exception;
	
	@ServiceMethod(callByContent=true, except={"childNode", "focus"}, target="popup")
	public Object[] remove() throws Exception;
	
	@ServiceMethod(callByContent=true, except={"childNode", "focus"}, target="popup")
	public Object[] move() throws Exception;

	
	@ServiceMethod(callByContent=true, except={"childNode", "focus"}, target=ServiceMethodContext.TARGET_NONE)
	public void save() throws Exception;

	@ServiceMethod(callByContent=true)
	public ContentWindow linkInstance() throws Exception;
	
	@ServiceMethod(inContextMenu=true, keyBinding="Ctrl+N")
	public ContentWindow newProcessInstance() throws Exception;
		
	@ServiceMethod(inContextMenu=true, keyBinding="Ctrl+D")
	public ContentWindow newDocument() throws Exception;	
	
	@ServiceMethod(inContextMenu=true, keyBinding="Ctrl+M")
	public ContentWindow mashup() throws Exception;

	@ServiceMethod(inContextMenu=true, keyBinding="Shift+Right")
	public WfPanel drillInto() throws Exception;

		
	@ServiceMethod(callByContent=true)
	public WfNode expand() throws Exception;	
	
	@ServiceMethod(callByContent=true)
	public WfNode collapse() throws Exception;	
	
	/*******************************************
	 * 
	 * function
	 *  
	 *******************************************/
	public void saveMe() throws Exception;
	
	public void createMe() throws Exception;
}
