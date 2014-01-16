package org.uengine.codi.mw3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.common.MetaworksUtil;
import org.metaworks.dao.Database;
import org.uengine.codi.mw3.admin.WebEditor;
import org.uengine.kernel.EJBProcessInstance;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.RoleMapping;
import org.uengine.persistence.processinstance.ProcessInstanceDAO;

//@Face(displayName="답글")
public class CommentWorkItem extends WorkItem{
	
	public CommentWorkItem(){
		setType("comment");
	}
	
	@Hidden(on=false)
	@Range(size = 80)
	@Face(displayName = "")
	public String getTitle() {
		return super.getTitle();
	}
	

	@Test(scenario="first", starter=true, instruction="$first.Add", next="autowiredObject.org.uengine.codi.mw3.model.InstanceView.monitor()")
	@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] add() throws Exception {
		
		// 덧글 상태일때 덧글이 길면 메모로 변경해주는 기능
		if(this.getTitle().length() > TITLE_LIMIT_SIZE){
			this.setType(WORKITEM_TYPE_MEMO);
			
			Class type = MetaworksUtil.getDesiredTypeByTypeSelector(this);
			if(type == null)
				throw new Exception("can't convert CommentWorkItem to MemoWorkItem");
			
			WorkItem workItem = (WorkItem)MetaworksUtil.cast(this, type);
			
			
			workItem.processManager = processManager;
			workItem.session = session;
			workItem.setContent(getTitle());
			workItem.setTitle(getTitle().substring(0, TITLE_LIMIT_SIZE) + "...");
			workItem.setMemo(new WebEditor(this.getContent()));
			
			return workItem.add();
		}else{
			return super.add();
		}
	}
	
	@Test(scenario="first", starter=true, instruction="$first.NewActivity", next="autowiredObject.org.uengine.codi.mw3.model.IProcessMap@IssueManagement.process.initiate()")
	public Popup newActivity() throws Exception {
		// TODO Auto-generated method stub
		return super.newActivity();
	}	
}
