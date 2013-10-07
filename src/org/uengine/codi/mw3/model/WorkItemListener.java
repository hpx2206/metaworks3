package org.uengine.codi.mw3.model;

public class WorkItemListener {
	Object applyItem;
		public Object getApplyItem() {
			return applyItem;
		}
		public void setApplyItem(Object applyItem) {
			this.applyItem = applyItem;
		}
		
	public WorkItemListener(Object applyItem){
		this.setApplyItem(applyItem);
	}
}
