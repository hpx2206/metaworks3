package org.uengine.codi.mw3.process;

import java.util.Map;

import javax.validation.constraints.NotNull;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Range;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.model.Comment;
import org.uengine.codi.mw3.model.IComment;
import org.uengine.codi.mw3.model.Session;

@Face(ejsPath="genericfaces/FormFace.ejs", options={"fieldOrder"}, values={"approval,comment"})
public class RequestApproval implements ITool, ContextAware {

	String approval;
		@NotNull(message="결재여부를 선탁하세요.")
		@Range(options={"선택", "결재", "반려"}, values={"", "1", "0"})		
		@Face(displayName="결재여부")
		@Available(when=MetaworksContext.WHEN_EDIT)
		public String getApproval() {
			return approval;
		}
		public void setApproval(String approval) {
			this.approval = approval;
		}
		
	transient String comment;
		@Face(displayName="첨언", ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"5", "50"})
		@Available(when=MetaworksContext.WHEN_EDIT)
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}		
	
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	@Override
	public void onLoad() throws Exception {
		
		if(MetaworksContext.WHEN_EDIT.equals(this.getMetaworksContext().getWhen())){
			this.setApproval("");
		}
	}

	@Override
	public void beforeComplete() throws Exception {
		if(MetaworksContext.WHEN_EDIT.equals(this.getMetaworksContext().getWhen())){
			Map map = (Map)TransactionContext.getThreadLocalInstance().getSharedContext(ITOOL_MAP_KEY);
			
			Session session = (Session)map.get(ITOOL_SESSION_KEY);
			String instId = (String)map.get(ITOOL_INSTANCEID_KEY);
			String tracingTag = (String)map.get(ITOOL_TRACINGTAG_KEY);
			
			IComment comment = new Comment();
			comment.setInstance_id(Integer.valueOf(instId));
			comment.setTracingTag(tracingTag);
			comment.setEmpNo(session.getEmployee().getEmpCode());
			comment.setEmpName(session.getEmployee().getEmpName());
			comment.setEmpTitle(session.getEmployee().getJikName());
			comment.setContents(this.getComment());
			comment.setOpt_type("1".equals(this.getApproval())?"approved":"reject");
			comment.setApprTitle("1".equals(this.getApproval())?"결재":"반려");
			comment.createMe();
		}
	}

	@Override
	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
