package org.uengine.codi.mw3.model;

public class InstanceListener {

	Object applyItem;
		public Object getApplyItem() {
			return applyItem;
		}
		public void setApplyItem(Object applyItem) {
			this.applyItem = applyItem;
		}
	
	public InstanceListener(Object applyItem){
		this.setApplyItem(applyItem);
	}
}
