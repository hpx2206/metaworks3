package org.uengine.codi.mw3.marketplace.model;

import org.uengine.codi.ITool;

public class RejectForm implements ITool {

	String rejectReason;
		public String getRejectReason() {
			return rejectReason;
		}
	
		public void setRejectReason(String rejectReason) {
			this.rejectReason = rejectReason;
		}

	@Override
	public void onLoad() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
