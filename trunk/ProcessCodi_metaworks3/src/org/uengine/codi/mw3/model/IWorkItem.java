package org.uengine.codi.mw3.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.annotation.Test;
import org.metaworks.annotation.TypeSelector;
import org.metaworks.dao.IDAO;
import org.metaworks.example.ide.SourceCode;
import org.metaworks.website.Contents;
import org.metaworks.website.FileContents;
import org.metaworks.website.ImageContents;
import org.metaworks.website.MetaworksFile;
import org.metaworks.website.ParagraphContents;
import org.metaworks.website.SourceCodeContents;
import org.metaworks.widget.ModalWindow;
import org.metaworks.widget.Window;
import org.uengine.codi.mw3.admin.WebEditor;
@Table(name = "bpm_worklist")
@Face(
		ejsPathMappingByContext=
	{
		"{when: 'new', face: 'dwr/metaworks/org/uengine/codi/mw3/model/IWorkItem_edit.ejs'}",
		"{when: 'edit', face: 'dwr/metaworks/org/uengine/codi/mw3/model/IWorkItem_edit.ejs'}",
	}		

)
public interface IWorkItem extends IDAO{
		
		@Id
		//@GeneratedValue(strategy=GenerationType.TABLE, generator="SEQ_BPM")
		public Long getTaskId();
		public void setTaskId(Long taskId);
		
		public String getTrcTag();
		public void setTrcTag(String trcTag);

	
		public String getTitle();
		public void setTitle(String title);
		
		@Hidden
		public String getContent();
		public void setContent(String content);


		@Hidden
		public String getExtFile();
		public void setExtFile(String extFile);

		
		@Hidden
		@ORMapping(databaseFields = { "content" }, objectFields = { "code" },
			objectIsNullWhenFirstDBFieldIsNull = true)
		public SourceCode getSourceCode();
		public void setSourceCode(SourceCode sourceCode);
		
		@Hidden
		@ORMapping(
			databaseFields = { "content", "instId", "taskId", "trcTag" }, 
			objectFields = { "serializedTool", "instanceId", "taskId", "tracingTag" },
			objectIsNullWhenFirstDBFieldIsNull = true,
			availableWhen= "type=='generic'"
		)
		public GenericWorkItemHandler getGenericWorkItemHandler();
		public void setGenericWorkItemHandler(
				GenericWorkItemHandler genericWorkItemHandler);


		@Hidden
		@Range(
				options={"WorkItem", "Comment",	"Image",	"Movie",	"Source Code", 	"File", "Schedule", "Postings"}, 
				values ={"wih", 	 "comment",	"img",		"mov",		"src", 			"file", "schdle" , "postings"}
		)
		@TypeSelector(
				values = 		{ 
						"wih",			
						"comment",
						"img",		
						"mov", 				
						"src",
						"file", 
						"schdle",
						"postings",
						"generic",
						"memo"
					}, 
				classes = 		{ 
						WorkItem.class,  	
						CommentWorkItem.class,					
						ImageWorkItem.class,
						MovieWorkItem.class,
						SourceCodeWorkItem.class,
						FileWorkItem.class,
						ScheduleWorkItem.class,
						FacebookFeedback.class,
						GenericWorkItem.class,
						MemoWorkItem.class
					} 
		)
		public String getType();
		public void setType(String type);

//		public boolean isLike();
//		public void setLike(boolean like);
		
		@Hidden
		public String getEndpoint();
		public void setEndpoint(String endpoint);

		@ORMapping(databaseFields = { "endpoint", "resname" }, objectFields = { "userId", "name" }	)
		public IUser getWriter();
		public void setWriter(IUser writer);
		
		
		@Hidden
		@ORMapping(
			databaseFields = {"content", "extfile", "tool"}, 
			objectFields = {"uploadedPath", "filename", "mimeType"},
			objectIsNullWhenFirstDBFieldIsNull = true,
			availableWhen = "type=='file'"		
		)
		public MetaworksFile getFile();
		public void setFile(MetaworksFile file);
		
		@Hidden
		@ORMapping(
			databaseFields = { 	"content" }, 
			objectFields = { 	"contents" },
			objectIsNullWhenFirstDBFieldIsNull = true,
			availableWhen= "type=='memo'"
		)
		public WebEditor getMemo();
		public void setMemo(WebEditor memo);
		
		public Long getInstId();
		public void setInstId(Long instId);

		public Long getRootInstId();
		public void setRootInstId(Long rootInstId);

		public Date getStartDate();
		public void setStartDate(Date date);

		public Date getEndDate();
		public void setEndDate(Date date);

		public Date getSaveDate();
		public void setSaveDate(Date date);

		public Date getDueDate();
		public void setDueDate(Date date);


		public String getTool();
		public void setTool(String tool);
		
		public int getDispatchOption();
		public void setDispatchOption(int dispatchOption);

		public String getRoleName();
		public void setRoleName(String roleName);
		
		@ServiceMethod(when = WHEN_VIEW)
		public void like() throws Exception;
		
		@ServiceMethod(when = WHEN_VIEW, callByContent=true)
		public void detail() throws Exception;

		@ServiceMethod(callByContent = true, target="popup")
		public Object[] add() throws Exception;

		@ServiceMethod(inContextMenu=true, needToConfirm=true, callByContent=true /*TODO: later add except*/)
		@Face(displayName="$Delete")
		public Object remove() throws Exception;

		@ServiceMethod(inContextMenu=true, callByContent=true)
		@Face(displayName="$Edit")
		public void edit() throws Exception;

		
		@ServiceMethod(callByContent = true)
		public void loadContents() throws Exception;
		
//		@ServiceMethod(callByContent=true)
		public IWorkItem find() throws Exception;
		
		@NonLoadable
		@NonSavable
		@Hidden(on=false)
		public WorkItemHandler getWorkItemHandler();
		public void setWorkItemHandler(WorkItemHandler workItemHandler);
		

		@NonLoadable
		@NonSavable
		@Hidden
		public boolean isInstantiation();
		public void setInstantiation(boolean instantiation);

		@NonLoadable
		@NonSavable
		@Hidden
		public boolean isInstantiated();
		public void setInstantiated(boolean instantiated);


		@NonLoadable
		@NonSavable
		@Hidden		
		public boolean isContentLoaded();
		public void setContentLoaded(boolean contentLoaded);

		@Face(displayName="Open")
		@ServiceMethod(inContextMenu=true, when = WHEN_VIEW, callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
		public ModalWindow workItemPopup() throws Exception;

		public String getStatus();
		public void setStatus(String status);

		////////// 
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		public IWorkItem newComment() throws Exception;
		
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		@Test(scenario="first", starter=true, instruction="메모를 입력할 수 있습니다.", next="autowiredObject.org.uengine.codi.mw3.model.WorkItem.newFile()")	
		public IWorkItem newSourceCode() throws Exception;
		
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
		public Popup newActivity() throws Exception;
		
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		public IWorkItem newSchedule();
		
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		public IWorkItem newImage();
		
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		public IWorkItem newMovie();

		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		public IWorkItem newFile() throws Exception;
		
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		public IWorkItem newMemo() throws Exception;

}

