package org.uengine.codi.mw3.marketplace.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.Range;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.marketplace.IApp;

@Face(
	ejsPath="dwr/metaworks/genericfaces/FormFace.ejs",
	options={"fieldOrder"},
	values={"approval"}
)
public class ManagerApproval implements ITool  {
	
	public ManagerApproval() { 
	}
	
	String approval;
		@Face(displayName="승인여부")
		@Range(options={"$Approval", "$Reject"}, values={"approval", "reject"})
		public String getApproval() {
			return approval;
		}
		public void setApproval(String approval) {
			this.approval = approval;
		}
		
	@Override
	public void onLoad() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
	}

	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
	}
}
