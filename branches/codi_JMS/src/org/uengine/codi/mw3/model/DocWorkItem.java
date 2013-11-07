package org.uengine.codi.mw3.model;

import org.directwebremoting.io.FileTransfer;
import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.website.MetaworksFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.knowledge.KnowledgeTool;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.persistence.dao.UniqueKeyGenerator;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.processmanager.ProcessManagerRemote;
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
		genericWI.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		genericWI.setWriter(session.getUser());
		genericWI.setTitle(this.getTitle());//parent.getName());
		genericWI.setGenericWorkItemHandler(genericWIH);
		
		return genericWI.add();
	}
	
}
