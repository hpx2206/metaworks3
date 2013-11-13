package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
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
			setTitle(getFile().getFileTransfer().getFilename());
		}
			FileWorkItem fileWorkItem = new FileWorkItem();
			fileWorkItem.session = session;
			fileWorkItem.processManager = this.processManager;
			fileWorkItem.instanceViewContent = this.instanceViewContent;
			fileWorkItem.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
			
			fileWorkItem.setWriter(session.getUser());
			fileWorkItem.setFile(this.getFile());
			fileWorkItem.setTitle(getFile().getFileTransfer().getFilename());
			fileWorkItem.setMajorVer(1);
			fileWorkItem.setFolderId(this.getFolderId());
			fileWorkItem.setGrpTaskId(this.getTaskId());
			fileWorkItem.add();
			
			DocumentTool tool = new DocumentTool();
			tool.session = session;
			tool.setInstId(fileWorkItem.getInstId().toString());
			
			GenericWorkItemHandler genericWIH = new GenericWorkItemHandler();
			genericWIH.setTool(tool);
			
			GenericWorkItem genericWI = new GenericWorkItem();
			genericWI.session = session;
			genericWI.processManager = this.processManager;
			genericWI.instanceViewContent = this.instanceViewContent;
			if("normal".equals(this.getMetaworksContext().getHow())){
				genericWI.setInstId(this.getInstId());
			}else{
				genericWI.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
			}
			genericWI.setWriter(session.getUser());
			genericWI.setTitle(this.getTitle());//parent.getName());
			genericWI.setGenericWorkItemHandler(genericWIH);
			
			return genericWI.add();
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
			fileWorkItem.setWriter(session.getUser());
			fileWorkItem.setFile(this.getFile());
			fileWorkItem.setGrpTaskId(item.getGrpTaskId());
			fileWorkItem.setMajorVer(item.getMajorVer()+1);
			fileWorkItem.setTitle(getFile().getFileTransfer().getFilename());
//			fileWorkItem.add();	
//			returnObjects = new Object[]{new ToAppend(fileWorkItem, this), new Refresh(fileWorkItem,false,true),new Refresh(this)};
			returnObjects = fileWorkItem.add();
		}
		
		
		
		return returnObjects;
		
		
	}
	
	
}
