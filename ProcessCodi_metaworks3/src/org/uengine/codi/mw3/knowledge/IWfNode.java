package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.annotation.Test;
import org.metaworks.dao.IDAO;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.IInstance;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.Session;

@Table(name="bpm_knol")
@Face(
		ejsPathMappingByContext=
	{
		"{how: 'bullet', face: 'dwr/metaworks/org/uengine/codi/mw3/knowledge/IWfNode.ejs'}",
		"{how: 'uml', face: 'dwr/metaworks/org/uengine/codi/mw3/knowledge/IWfNode_uml.ejs'}",
		"{how: 'table', face: 'dwr/metaworks/org/uengine/codi/mw3/knowledge/IWfNode_table.ejs'}",
		"{how: 'mindmap', face: 'dwr/metaworks/org/uengine/codi/mw3/knowledge/IWfNode_mindmap.ejs'}",
		//"{how: 'pt', face: 'dwr/metaworks/org/uengine/codi/mw3/model/IWfNode_uml.ejs'}",
	})
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
		
	@NonLoadable
	@NonSavable
	@Hidden
	public String getTypeNext();
	public void setTypeNext(String type);
		
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
	
	@NonLoadable
	@NonSavable
	public int getLoadDepth();
	public void setLoadDepth(int loadDepth);
	
	public String getVisType();
	public void setVisType(String visType);
	
	
	/*******************************************
	 * 
	 * Service Method
	 *  
	 *******************************************/
	@ServiceMethod(callByContent=true, except={"childNode", "focus"}, target="popup")
	@Test(scenario="first", starter=true, instruction="트리 구조의 지식을 작성합니다. 엔터키를 누르면 다음라인, 탭키를 누르면 들여쓰기, 두번 엔터는 내어쓰기 입니다. <p> * 우측 클릭을 하셔서 '프로세스 발행'을 하면 메모한 내용을 기반으로 작업지시도 가능합니다.<br> 좋아하시는 키워드를 입력해보세요...", next="autowiredObject.org.uengine.codi.mw3.knowledge.MashupGoogleImage.putImage()")
	public Object[] add() throws Exception;
	
	@ServiceMethod(callByContent=true, except={"childNode", "focus"}, target="popup", inContextMenu=true)
	@Face(displayName="$MakeAsTemplate")
	public Object[] makeAsTemplate() throws Exception;
	
	@ServiceMethod(callByContent=true, except={"childNode", "focus"}, target="popup")
	public Object[] outdent() throws Exception;
	
	@ServiceMethod(callByContent=true, except={"childNode", "focus"}, target="popup")
	public Object[] indent() throws Exception;
	
	@ServiceMethod(inContextMenu=true, keyBinding="Ctrl+Down")
	@Face(displayName="$DrillInto")
	public WfPanel drillInto() throws Exception;
	
	@ServiceMethod(callByContent=true, except={"childNode", "focus"}, target="popup")
	public Object[] move() throws Exception;

	
	@ServiceMethod(callByContent=true, except={"childNode", "focus"}, target=ServiceMethodContext.TARGET_NONE)
	public void save() throws Exception;

	@ServiceMethod(callByContent=true)
	public ContentWindow linkInstance() throws Exception;
	
	@ServiceMethod(callByContent=true, mouseBinding="drop", target="popup"/*, loader="org.uengine.codi.mw3.model.Popup"*/)
	public Object[] drop() throws Exception;
	
	@ServiceMethod(callByContent=true, except={"childNode", "focus"}, inContextMenu=true, keyBinding="Ctrl+Right")
	@Face(displayName="$NewProcessInstance")
	public ContentWindow newProcessInstance() throws Exception;
		
	@ServiceMethod(inContextMenu=true, keyBinding="Ctrl+D")
	@Test(scenario="first", instruction="$first.newDocument", next="autowiredObject.org.uengine.codi.mw3.model.FileWorkItem.add()")
	@Face(displayName="$NewDocument")
	public ContentWindow newDocument() throws Exception;	
	
	@ServiceMethod(inContextMenu=true, keyBinding="Ctrl+M")
	@Face(displayName="$Mashup")
	public ContentWindow mashup() throws Exception;

	@ServiceMethod(callByContent=true, except={"childNode", "focus"}, target="popup")
	@Face(displayName="$Remove")
	public Object[] remove() throws Exception;
	
	@Face(displayName="remove")
	@ServiceMethod(callByContent=true, except={"childNode", "focus"}, target="popup", inContextMenu=true, keyBinding="Shift+Del")
	public Object[] removeNode() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public WfNode expand() throws Exception;	
	
	@ServiceMethod(callByContent=true)
	public WfNode collapse() throws Exception;	
	
	@ServiceMethod(callByContent=true, inContextMenu=true, target="popup")
	@Face(displayName="$presentation")
	public ModalWindow presentation() throws Exception;
	
	@ServiceMethod(callByContent=true, mouseBinding="drag-enableDefault")
	public Session drag() throws Exception;
	
	@ServiceMethod(inContextMenu=true, target="popup")
	@Face(displayName="가시화 방법")
	public Popup visualizationType();
	
	/*******************************************
	 * 
	 * function
	 *  
	 *******************************************/
	public void saveMe() throws Exception;
	
	public void createMe() throws Exception;
	
	@AutowiredFromClient(onDrop=true)
	public IInstance dropInstance = null;
	
	@AutowiredFromClient(onDrop=true)
	public IUser dropUser = null;
	
}
