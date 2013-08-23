package org.uengine.codi.mw3.processexplorer;

import java.util.Date;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;

public class DocumentFilePanel {
	
	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}	

	Date endDate;
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	
	DocumentFileViewPanel documentFileViewPanel;
		public DocumentFileViewPanel getDocumentFileViewPanel() {
			return documentFileViewPanel;
		}
		public void setDocumentFileViewPanel(DocumentFileViewPanel documentFileViewPanel) {
			this.documentFileViewPanel = documentFileViewPanel;
		}
	
//	@AutowiredFromClient
//	public DocumentNavigatorPanel documentNavigatorPanel;
	
	public void load(){
		
	}
	
	//다운로드
	@ServiceMethod(callByContent=true)
	public Object[] fileDownload() throws Exception{
		
		return null;
	}
	
	//상세보기
	@ServiceMethod(callByContent=true, target = ServiceMethodContext.TARGET_APPEND)
	public Object[] fileDetailView() throws Exception{
		return null;
	}
}
