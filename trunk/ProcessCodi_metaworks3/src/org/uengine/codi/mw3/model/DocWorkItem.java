package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.website.MetaworksFile;
import org.uengine.util.UEngineUtil;

public class DocWorkItem extends WorkItem {
	
	
	public DocWorkItem(){
		setType(WorkItem.WORKITEM_TYPE_DOCUMENT);
		setFile(new MetaworksFile());
	}
	
	@Override
	@Hidden(on=false)
	public MetaworksFile getFile(){
		return super.getFile();
	}
	
	@Override
	@Hidden(on=false)
	public void setFile(MetaworksFile file) {
		this.file = file;
	}
	
	@Override
	public Object[] add() throws Exception{
		if (this.getFile() == null || this.getFile().getFileTransfer() == null
				|| this.getFile().getFileTransfer().getFilename() == null)
			throw new MetaworksException("파일을 첨부해주세요.");
		
		// 제목이 없으면 파일명을 제목으로
		if(!UEngineUtil.isNotEmpty(getTitle())){
			setTitle(new String(this.getFile().getFilename()));
		}
		Object[] returnObj = null;
//		String mimeType = this.getFile().getFileTransfer().getMimeType();
//		// txt 파일은 SourceCodeWorkItem으로
//		if( mimeType != null && "text/plain".equals(mimeType)){
//			
//			Class dstClass = null;
//
//			WebObjectType srcWOT = MetaworksRemoteService.getInstance().getMetaworksType(this.getClass().getName());
//			ObjectInstance srcInstance = (ObjectInstance) srcWOT.metaworks2Type().createInstance();
//			srcInstance.setObject(this);
//
//			WebObjectType dstWOT = MetaworksRemoteService.getInstance().getMetaworksType(SourceCodeWorkItem.class.getName());
//			ObjectInstance dstInstance = (ObjectInstance) dstWOT.metaworks2Type().createInstance();
//
//			for (FieldDescriptor fd : dstWOT.metaworks2Type().getFieldDescriptors()) {
//				if (fd.getAttribute("ormapping") == null)
//					dstInstance.setFieldValue(fd.getName(),
//							srcInstance.getFieldValue(fd.getName()));
//			}
//			
//			// 파일 업로드
//			getFile().upload();
//			
//			SourceCodeWorkItem sourceCodeWorkItem = (SourceCodeWorkItem)dstInstance.getObject();
//			sourceCodeWorkItem.session = session;
//			sourceCodeWorkItem.processManager = processManager;
//			sourceCodeWorkItem.instanceViewContent = this.instanceViewContent;
//			sourceCodeWorkItem.setType(WORKITEM_TYPE_SRC);
//			sourceCodeWorkItem.setTool(mimeType);
//			sourceCodeWorkItem.setExtFile(this.getFile().getUploadedPath());
//			sourceCodeWorkItem.setContent("loading...");
//			returnObj = sourceCodeWorkItem.add();
//		}else{
		
			FileWorkItem fileWorkItem = new FileWorkItem();
			fileWorkItem.session = session;
			fileWorkItem.processManager = this.processManager;
			fileWorkItem.instanceViewContent = this.instanceViewContent;
			fileWorkItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
			
			fileWorkItem.setWriter(session.getUser());
			fileWorkItem.setFile(this.getFile());
			fileWorkItem.setTitle(this.getTitle());
			fileWorkItem.setMajorVer(1);
			fileWorkItem.setFolderId(this.getFolderId());
			fileWorkItem.setGrpTaskId(this.getTaskId());
			fileWorkItem.setNotReturn(true);
			fileWorkItem.add();

			
			DocumentTool tool = new DocumentTool();
			tool.session = session;
			tool.setInstId(fileWorkItem.getInstId().toString());
			fileWorkItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			fileWorkItem.getMetaworksContext().setHow(MetaworksContext.HOW_MINIMISED);
			fileWorkItem.setFileTransfer(null);
			tool.setWorkitem(fileWorkItem);
			
			GenericWorkItemHandler genericWIH = new GenericWorkItemHandler();
			genericWIH.setTool(tool);
			
			GenericWorkItem genericWI = new GenericWorkItem();
			genericWI.session = session;
			genericWI.processManager = this.processManager;
			genericWI.instanceViewContent = this.instanceViewContent;
			genericWI.setInstId(this.getInstId());
			genericWI.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
//			if("normal".equals(this.getMetaworksContext().getHow())){
//				genericWI.setInstId(this.getInstId());
//			}else{
//				genericWI.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
//			}
			genericWI.setWriter(session.getUser());
			genericWI.setTitle(this.getTitle());//parent.getName());
			genericWI.setGrpTaskId(fileWorkItem.getTaskId());
			genericWI.setGenericWorkItemHandler(genericWIH);
			
			returnObj = genericWI.add();
			
			// TODO: ProcesManagerRemote 의 여러개의 인스턴스에 대해서 applyChange 시 기존 인스턴스에 대한 영향도 존재
			// 수정처리 해야함
			Instance tempInstance = new Instance();
			tempInstance.setInstId(fileWorkItem.getInstId());
			tempInstance.databaseMe().setStatus(WORKITEM_STATUS_RUNNING);
//		}
			return returnObj;
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] addDocument() throws Exception{
		Object[] returnObjects = null;
		String sql = " select * from bpm_worklist where instId=?instId";
		IWorkItem workitem = (IWorkItem) sql(IWorkItem.class , sql);
		workitem.set("instId", this.getInstId());
		workitem.select();
		
		WorkItem item = new WorkItem();
		while(workitem.next()){
			item.copyFrom(workitem);
			System.out.println(item.getMajorVer());
			System.out.println(item.getGrpTaskId());
		}
		
		InstanceViewThreadPanel instanceViewThreadPanel = new InstanceViewThreadPanel();
		instanceViewThreadPanel.setInstanceId(this.getInstId().toString());
		
		if("document".equals(this.getType())){
			FileWorkItem fileWorkItem = new FileWorkItem();
			fileWorkItem.setInstId(this.getInstId());
			fileWorkItem.session = session;
			fileWorkItem.processManager = this.processManager;
			fileWorkItem.instanceViewContent = this.instanceViewContent;
			fileWorkItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
			fileWorkItem.getMetaworksContext().setHow("document");
			fileWorkItem.setWriter(session.getUser());
			fileWorkItem.setFile(this.getFile());
			fileWorkItem.setGrpTaskId(item.getGrpTaskId());
			fileWorkItem.setMajorVer(item.getMajorVer()+1);
			if(!UEngineUtil.isNotEmpty(getTitle()))
				fileWorkItem.setTitle(new String(this.getFile().getFilename()));
			else
				fileWorkItem.setTitle(this.getTitle());
//			fileWorkItem.add();	
//			returnObjects = new Object[]{new ToAppend(fileWorkItem, this), new Refresh(fileWorkItem,false,true),new Refresh(this)};
			returnObjects = fileWorkItem.add();
		}
		
		
		
		return returnObjects;
		
		
	}
	
	
}
