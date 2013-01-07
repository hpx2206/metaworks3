package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;
import java.util.Date;

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
import org.metaworks.annotation.TypeSelector;
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
		"{how: 'quiz', face: 'dwr/metaworks/org/uengine/codi/mw3/knowledge/IWfNode_quiz.ejs'}",

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
		
	@Hidden
	public String getRefId();
	public void setRefId(String refId);
	
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
	
	public String getUrl() ;
	public void setUrl(String url) ;
	
	public String getThumbnail() ;
	public void setThumbnail(String thumbnail);
	
	public String getSecuopt();
	public void setSecuopt(String secuopt);
	
	public String getCompanyId();
	public void setCompanyId(String companyId);
	
	@NonLoadable
	@NonSavable
	@Hidden
	public String getUrlNext() ;
	public void setUrlNext(String urlNext) ;
	
	@NonLoadable
	@NonSavable
	@Hidden
	public String getThumbnailNext() ;
	public void setThumbnailNext(String thumbnailNext);
	
	@NonLoadable
	@NonSavable
	@Hidden
	public boolean isFirst();
	public void setFirst(boolean first);
	
	
	public int getBudget();
	public void setBudget(int budget);
	
	public int getEffort();
	public void setEffort(int effort);
	
	public int getBenfit();
	public void setBenfit(int benfit);
	
	public int getPenalty();
	public void setPenalty(int penalty);
	
	public Date getStartdate();
	public void setStartdate(Date startdate);
	
	public Date getEnddate();
	public void setEnddate(Date enddate);
	
	@NonLoadable
	@NonSavable
	@Hidden
	public int getProgress();
	public void setProgress(int progress);
	
	
/*	@NonLoadable
	@NonSavable
	public int getrBV();
	public void setrBV(int rBV);

	@NonLoadable
	@NonSavable
	public int getRoi();
	public void setRoi(int roi);*/
	
	/*******************************************
	 * 
	 * Service Method
	 *  
	 *******************************************/
	@ServiceMethod(callByContent=true, except={"childNode", "focus"}, target=TARGET_SELF)
	public void load() throws Exception;
	
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
	
	@ServiceMethod(callByContent=true, except="childNode", mouseBinding="drop", target=ServiceMethodContext.TARGET_APPEND /*, loader="org.uengine.codi.mw3.model.Popup"*/)
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
	
	@Face(displayName="$Remove")
	@ServiceMethod(callByContent=true, except={"childNode", "focus"}, target="popup", inContextMenu=true, keyBinding="Shift+Del")
	public Object[] removeNode() throws Exception;
	
	@ServiceMethod(callByContent=true, except={"childNode"})
	public void expand() throws Exception;	
	
	@ServiceMethod(callByContent=true, except={"childNode"})
	public void collapse() throws Exception;	
	
	@ServiceMethod(callByContent=true, inContextMenu=true, target="popup")
	@Face(displayName="$presentation")
	public ModalWindow presentation() throws Exception;
	
	@ServiceMethod(callByContent=true, target="popup")
	@Face(displayName="컨텐츠보기")
	public ModalWindow showLms() throws Exception;
	
	@ServiceMethod(callByContent=true, except="childNode", mouseBinding="drag-enableDefault")
	public Session drag() throws Exception;
	
	@ServiceMethod(inContextMenu=true, target="popup")
	@Face(displayName="가시화 방법")
	public Popup visualizationType();
	
	@ServiceMethod(inContextMenu=true, target="popup")
	@Face(displayName="하위 연결 방식")
	public Popup connectionType();

	@ServiceMethod(inContextMenu=true, target="popup")
	@Face(displayName="XML로 전환")
	public Popup xml() throws Exception;
	
	@Face(displayName="공개토픽으로 지정")
	@ServiceMethod(except={"childNode", "focus"}, inContextMenu=true)
	public Object[] topic() throws Exception;
	
	@ServiceMethod(callByContent=true, except={"childNode"}, target=ServiceMethodContext.TARGET_POPUP)
	public Popup modify();

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

	public String getConnType();
	public void setConnType(String connType);
		
}
