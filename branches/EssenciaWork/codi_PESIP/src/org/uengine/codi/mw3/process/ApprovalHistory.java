package org.uengine.codi.mw3.process;

import java.util.Map;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.dao.TransactionContext;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.common.board.BoardList;
import org.uengine.codi.mw3.model.Comment;
import org.uengine.codi.mw3.model.IComment;

@Face(ejsPath="genericfaces/CleanObjectFace.ejs")
public class ApprovalHistory implements ITool, ContextAware {

	transient BoardList approvalHistory;
		@Face(displayName="결재목록", options={"hideLabel"}, values={"true"})
		public BoardList getApprovalHistory() {
			return approvalHistory;
		}
		public void setApprovalHistory(BoardList approvalHistory) {
			this.approvalHistory = approvalHistory;
		}
		
	@Override
	public void onLoad() throws Exception {
		Map map = (Map)TransactionContext.getThreadLocalInstance().getSharedContext(ITOOL_MAP_KEY);
		
		String instId = (String)map.get(ITOOL_INSTANCEID_KEY);
		
		IComment comment = new Comment();
		comment.setInstance_id(Integer.valueOf(instId));
		IComment result = comment.findMeByInstanceId();
		
		if(result.size() > 0){
			result.getMetaworksContext().setHow(MetaworksContext.HOW_IN_LIST);
			result.getMetaworksContext().setWhen("list");
			
			this.setApprovalHistory(new BoardList(null , result, MetaworksContext.HOW_IN_LIST));
		}else{
			this.getMetaworksContext().setWhen("___hidden___");
		}
	}
	
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	@Override
	public void beforeComplete() throws Exception {
		
	}
	
	@Override
	public void afterComplete() throws Exception {
		
	}
}
