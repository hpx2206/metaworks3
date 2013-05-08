package org.uengine.codi.mw3.project;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Range;
import org.uengine.codi.ITool;

@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs")
public class ManagerApproval implements ITool  {
	
	public ManagerApproval() { 
	}
	
	String approval;
		@Face(displayName="요청된 개발기 생성을 승인합니다.")
		@Range(options={"승인", "거부"}, values={"approval", "reject"})
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
