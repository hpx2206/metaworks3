package org.uengine.codi.mw3.project;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.NonEditable;
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
	String resultStr;
		@NonEditable
		@Face(displayName="진행여부.")
		public String getResultStr() {
			return resultStr;
		}
		public void setResultStr(String resultStr) {
			this.resultStr = resultStr;
		}
	@Override
	public void onLoad() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
		if( approval != null && approval.equals("approval") ){
			setResultStr("진행중 입니다.(약 30분의 시간이 걸립니다.)");
		}else{
			setResultStr("취소 되었습니다.");
		}
	}

	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
	}
}
