package org.uengine.codi.mw3.process.report;

import javax.validation.constraints.NotNull;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.website.MetaworksFile;
import org.uengine.codi.ITool;

@Face(ejsPath="genericfaces/FormFace.ejs", options={"fieldOrder"}, values={"summary,content,attachFile"})
public class WeeklyReport implements ITool, ContextAware {

	String summary;
		@Face(displayName="내역요약", ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"5", "50"})
		@NotNull(message="내역요약을 입력하세요.")
		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}
	
	String content;
		@Face(displayName="내역상세", ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"20", "50"})
		@NotNull(message="내역상세을 입력하세요.")
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		
	MetaworksFile attachFile;
		@Face(displayName="첨부파일")
		public MetaworksFile getAttachFile() {
			return attachFile;
		}
		public void setAttachFile(MetaworksFile attachFile) {
			this.attachFile = attachFile;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

		
	@Override
	public void onLoad() throws Exception {
		if(MetaworksContext.WHEN_EDIT.equals(this.getMetaworksContext().getWhen())){
			if(this.getAttachFile() == null) 
				setAttachFile(new MetaworksFile());
		}
		
	}

	@Override
	public void beforeComplete() throws Exception {
		if(MetaworksContext.WHEN_EDIT.equals(this.getMetaworksContext().getWhen())){
			if(this.getAttachFile() != null && this.getAttachFile().getFileTransfer() != null && this.getAttachFile().getFileTransfer().getFilename().length() >0){
				this.getAttachFile().upload();
			}
		}		
	}

	@Override
	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
