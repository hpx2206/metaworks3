package org.uengine.codi.mw3.model;

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

	public void setPageCountInt(int pageCount) {
		this.pageCount = Integer.toString(pageCount);
	}
}
