package org.uengine.codi.mw3.model;

import org.metaworks.annotation.ServiceMethod;

public class Preview {

	String pageCount;
		public String getPageCount() {
			return pageCount;
		}
		public void setPageCount(String pageCount) {
			this.pageCount = pageCount;
		}
		
	Long taskId;
		public Long getTaskId() {
			return taskId;
		}
		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}
	
	String mimeType;
		public String getMimeType() {
			return mimeType;
		}
		public void setMimeType(String mimeType) {
			this.mimeType = mimeType;
		}

	String convertingResult;
		public String getConvertingResult() {
			return convertingResult;
		}
		public void setConvertingResult(String convertingResult) {
			this.convertingResult = convertingResult;
		}		

	String statusPreview;
		public String getStatusPreview() {
			return statusPreview;
		}
		public void setStatusPreview(String statusPreview) {
			this.statusPreview = statusPreview;
		}	
		

	public void setPageCountInt(int pageCount) {
		this.pageCount = Integer.toString(pageCount);
	}
	
	@ServiceMethod(callByContent=true)
	public void viewAsPDF() throws Exception{
		setMimeType("pdf");
	}
	
	@ServiceMethod(callByContent=true)
	public void viewAsImages()throws Exception{
		setMimeType("image");
	}
	
}
