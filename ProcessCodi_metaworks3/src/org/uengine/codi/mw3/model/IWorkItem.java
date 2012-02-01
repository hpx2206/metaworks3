package org.uengine.codi.mw3.model;

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
import org.metaworks.website.ParagraphContents;
import org.metaworks.website.SourceCodeContents;

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
						"postings"
					}, 
				classes = 		{ 
						WorkItem.class,  	
						CommentWorkItem.class,					
						ImageWorkItem.class,
						MovieWorkItem.class,
						SourceCodeWorkItem.class,
						FileWorkItem.class,
						ScheduleWorkItem.class,
						PostingsWorkItem.class
					} 
		)
		public String getType();
		public void setType(String type);

//		public boolean isLike();
//		public void setLike(boolean like);
		
		@Hidden
		public String getEndpoint();
		public void setEndpoint(String endpoint);

		@ORMapping(databaseFields = { "endpoint" }, objectFields = { "userId" }
				)
		public IUser getWriter();
		public void setWriter(IUser writer);
		
		
		public Long getInstId();
		public void setInstId(Long instId);

		
		public String getTool();
		public void setTool(String tool);
		

		@ServiceMethod(when = WHEN_VIEW)
		public void like() throws Exception;
		
		@ServiceMethod(when = WHEN_VIEW, callByContent=true)
		public void detail() throws Exception;

		@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_SELF)
		public WorkItem[] add() throws Exception;

		
//		@ServiceMethod(callByContent=true)
		public IWorkItem find() throws Exception;
		
		@NonLoadable
		@NonSavable
		public WorkItemHandler getWorkItemHandler();
		public void setWorkItemHandler(WorkItemHandler workItemHandler);

		
		////////// 
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		public IWorkItem newComment();
		
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		public IWorkItem newSourceCode();
		
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		public IWorkItem newSchedule();
		
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		public IWorkItem newImage();
		
		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		public IWorkItem newMovie();

		@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_SELF)
		public IWorkItem newFile();
		
		
}

