package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksException;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.dao.TransactionContext;
import org.metaworks.website.MetaworksFile;
import org.uengine.persistence.dao.UniqueKeyGenerator;
import org.uengine.processmanager.ProcessManagerBean;
import org.uengine.util.UEngineUtil;

public class FileWorkItem extends WorkItem{
	
	
	public FileWorkItem(){
		setType("file");
		setFile(new MetaworksFile()); 
	}
	
	
	String versionUpOption;
		@Range(
				options={"Major Upgrade", "Minor Upgrade"},
				values={"Major", "Minor"}
		)
		public String getVersionUpOption() {
			return versionUpOption;
		}
		public void setVersionUpOption(String versionUpOption) {
			this.versionUpOption = versionUpOption;
		}

	@Override
	@Hidden(on=false) //overrides the annotation
	public MetaworksFile getFile() {
		return super.getFile();
	}

	@Override
	@Test(scenario="first", instruction="$first.FileWorkItem.add")
	public Object[] add() throws Exception {
		
		if(this.getFile() == null || this.getFile().getFileTransfer() == null || this.getFile().getFileTransfer().getFilename() == null)
			throw new MetaworksException("파일을 첨부해주세요.");
		
		this.setTaskId(UniqueKeyGenerator.issueWorkItemKey(((ProcessManagerBean)processManager).getTransactionContext()));
		
		// 추가모드 일때
		if(WHEN_NEW.equals(this.getMetaworksContext().getWhen())){		
			this.setGrpTaskId(this.getTaskId());

			// default 버전
			this.setMajorVer(1);
			this.setMinorVer(0);

		// 수정모드 일때
		}else if(WHEN_EDIT.equals(this.getMetaworksContext().getWhen())){
			// 기존 버전 delete 처리하여 안보이게
			IWorkItem worklist = sql("update bpm_worklist set isdeleted=1 where grptaskid=?grpTaskId");
			worklist.set("grpTaskId", getGrpTaskId());
			worklist.update();

			// 새로운 버전 업 처리
			if("Major".equals(getVersionUpOption())){
				setMajorVer(getMajorVer()+1);
				setMinorVer(0);
			}else{
				setMinorVer(getMinorVer()+1);
			}
			
			//this.setWorkItemVersionChooser(null);
		}
		
		// 제목이 없으면 파일명을 제목으로
		if(!UEngineUtil.isNotEmpty(getTitle())){
			setTitle(getFile().getFileTransfer().getFilename());
		}
		
		// 파일 업로드
		getFile().upload();
		
		this.setContent(this.getFile().getUploadedPath());
		this.setTool(this.getFile().getMimeType());
		this.setExtFile(this.getFile().getFilename());
		
		// office 파일 pdf 로 변환
		if(getFile().getMimeType() != null && getFile().getMimeType().indexOf("office") > 0){
			String prefix = TransactionContext.getThreadLocalInstance()
					.getRequest().getSession().getServletContext()
					.getRealPath("/images/pdf/");
			
			String inputFilePath = getFile().overrideUploadPathPrefix()+ getFile().getUploadedPath();
			String outputFilePath = prefix + "/" + this.getGrpTaskId() + "_" + String.valueOf(this.getMajorVer()) + "_" + String.valueOf(this.getMinorVer()) + ".pdf";
			
			ConvertDocToPdf convertDoc = new ConvertDocToPdf();
			boolean isConvert = convertDoc.convertPdf(inputFilePath, outputFilePath);
			
			if(isConvert){
				this.setExt3(String.valueOf(isConvert));
			}
		}
		
		// WorkItem 추가
		Object[] returnObject = super.add();		

		this.setWorkItemVersionChooser(this.databaseMe().getWorkItemVersionChooser());
		
		return returnObject;
	}

	@ServiceMethod(inContextMenu=true, callByContent=true, except="file")
	public void edit() throws Exception {
		setFile(new MetaworksFile());
		
		super.edit();
	}
}
