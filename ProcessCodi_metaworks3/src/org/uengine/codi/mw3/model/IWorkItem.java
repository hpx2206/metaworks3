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
@Table(name = "bpm_worklist")
@Face(
		ejsPathMappingByContext=
	{
		"{when: 'new', face: 'faces/org/uengine/codi/mw3/model/IWorkItem_edit.ejs'}",
		"{when: 'edit', face: 'faces/org/uengine/codi/mw3/model/IWorkItem_edit.ejs'}",
	}		

)
public interface IWorkItem extends IDAO{
		
		@Id
		//@GeneratedValue(strategy=GenerationType.TABLE, generator="SEQ_BPM")
		public Long getTaskId();
		public void setTaskId(Long taskId);
	
		public String getTitle();
		public void setTitle(String title);
		
		@Hidden
		public String getContent();
		public void setContent(String content);

		@Hidden
		@ORMapping(databaseFields = { "content" }, objectFields = { "code" })
		public SourceCode getSourceCode();
		public void setSourceCode(SourceCode sourceCode);

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
						//"generic"
					}, 
				classes = 		{ 
						WorkItem.class,  	
						CommentWorkItem.class,					
						ImageWorkItem.class,
						MovieWorkItem.class,
						SourceCodeWorkItem.class,
						FileWorkItem.class,
						ScheduleWorkItem.class,
						PostingsWorkItem.class,
						//GenericWorkItem.class
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
			databaseFields = { 	"content", 		"tool" }, 
			objectFields = { 	"uploadedPath", "mimeType" }
		)
		public MetaworksFile getFile();
		public void setFile(MetaworksFile file);
		
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
		

		@ServiceMethod(when = WHEN_VIEW)
		public void like() throws Exception;
		
		@ServiceMethod(when = WHEN_VIEW, callByContent=true)
		public WorkItemHandler detail() throws Exception;

		@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_SELF)
		public WorkItem[] add() throws Exception;

		
//		@ServiceMethod(callByContent=true)
		public IWorkItem find() throws Exception;
		
		@NonLoadable
		@NonSavable
		public WorkItemHandler getWorkItemHandler();
		public void setWorkItemHandler(WorkItemHandler workItemHandler);

		@Face(displayName="Open")
		@ServiceMethod(inContextMenu=true, when = WHEN_VIEW, callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
		public ModalWindow workItemPopup() throws Exception;

		public String getStatus();
		public void setStatus(String status);

		////////// 
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		public IWorkItem newComment();
		
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		public IWorkItem newSourceCode();
		
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
		public Popup newActivity() throws Exception;
		
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		public IWorkItem newSchedule();
		
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		public IWorkItem newImage();
		
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		public IWorkItem newMovie();

		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		public IWorkItem newFile();
		
		
}

