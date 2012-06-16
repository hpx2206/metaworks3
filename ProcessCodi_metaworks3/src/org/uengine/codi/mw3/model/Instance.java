package org.uengine.codi.mw3.model;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.processmanager.ProcessManagerRemote;

public class Instance extends Database<IInstance> implements IInstance{

	
	public static final String TASK_DIRECT_APPEND_SQL_KEY = "task.";
	public static final String INSTANCE_DIRECT_APPEND_SQL_KEY = "inst.";

	
	
	@Autowired
	public InstanceViewContent instanceViewContent;
	
	@AutowiredFromClient
	public Session session;

	public Instance(){
		
	}
	
	@Override
	public IInstance load(Session session, Map<String, String> criteria)
			throws Exception {
		StringBuffer stmt = new StringBuffer();
		stmt.append("select bottomlist.* from ");
		
		stmt.append("(select");
		
		//if(oracle)
		//   stmt.append(" ROWNUM rindex,");
		
		stmt.append(" instanceList.* from ");
		stmt.append(" (select inst.*, task.startdate from ");
		stmt.append(" BPM_PROCINST inst, ");
		
		// TASK
		stmt.append(" (select max(worklist.startdate) startdate, worklist.rootinstid ");
		stmt.append("from bpm_worklist worklist, bpm_rolemapping rolemapping ");
		stmt.append("where worklist.rootinstid=rolemapping.rootinstid ");
		if(criteria.containsKey(TASK_DIRECT_APPEND_SQL_KEY)) {
			stmt.append(criteria.get(TASK_DIRECT_APPEND_SQL_KEY));
		}
		stmt.append("group by worklist.rootinstid) task ");
		
		// add instance criteria
		stmt.append("where inst.instid=task.rootinstid ");
		stmt.append("and initcomcd=?initComCd ");
		if(criteria.containsKey(INSTANCE_DIRECT_APPEND_SQL_KEY)) {
			stmt.append(criteria.get(INSTANCE_DIRECT_APPEND_SQL_KEY));
		}
		
		stmt.append("order by task.startdate desc) instanceList ");
		stmt.append(") bottomlist");
		
//		if(oracle)
//			stmt.append("where rindex between ?startIndex and ?lastIndex ");
		
		stmt.append( " limit " + criteria.get("startIndex") + ", "+InstanceList.PAGE_CNT);

		
		//TODO delete printing
		System.out.println("worklist sql:" + stmt.toString());
		
		IInstance instanceContents = sql(stmt.toString());
		instanceContents.setInitComCd(session.getEmployee().getGlobalCom());

		// TODO add criteria
		Set<String> keys = criteria.keySet();
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			if(key.equals(INSTANCE_DIRECT_APPEND_SQL_KEY) || key.equals(TASK_DIRECT_APPEND_SQL_KEY)) {
				continue;
			} else {
				instanceContents.set(key, criteria.get(key));
			}
		}

		instanceContents.select();
		return instanceContents;
	}
	
	
	public ContentWindow detail() throws Exception{
		//InstanceViewContent instanceViewContent = new InstanceViewContent();
		//instanceViewContent.processManager = this.processManager;
		//instanceViewContent.
		
		TransactionContext.getThreadLocalInstance().setSharedContext("codi_session", session);
		instanceViewContent.load(this);
		instanceViewContent.session = session;
		
		return instanceViewContent;
	}
	
	public ProcessInstanceMonitor flowchart() throws Exception{
		ProcessInstanceMonitor processInstanceMonitor = new ProcessInstanceMonitor();
		processInstanceMonitor.setInstanceId(getInstId().toString());
		processInstanceMonitor.load(processManager);

		return processInstanceMonitor;
	}
	
	@Autowired
	public ProcessManagerRemote processManager;
	
	Long instId;
		@Id
		public Long getInstId() {
			return instId;
		}
		public void setInstId(Long instId) {
			this.instId = instId;
		}
		
	String initComCd;
	
		public String getInitComCd() {
			return initComCd;
		}
	
		public void setInitComCd(String initComCd) {
			this.initComCd = initComCd;
		}
		
	@Override
	public String getDefVerId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefVerId(String defVerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDefId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefId(String defId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDefPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefPath(String DefPath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDefName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefName(String defName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getStartedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStartedDate(Date when) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getFinishedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFinishedDate(Date when) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getDueDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDueDate(Date when) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getDefModDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefModDate(Date when) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getModDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModDate(Date when) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStatus(String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInfo(String info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getIsDeleted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsDeleted(boolean isDeleted) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getIsAdhoc() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsAdhoc(boolean isAdhoc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getIsSubProcess() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsSubProcess(boolean isSubProcess) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getRootInstId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRootInstId(Long instanceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getMainInstId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMainInstId(Long instanceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getMainActTrcTag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMainActTrcTag(String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getMainExecScope() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMainExecScope(String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getMainDefVerId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMainDefVerId(Long defVerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getIsArchive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsArchive(boolean isArchive) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAbsTrcPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAbsTrcPath(String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getDontReturn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDontReturn(boolean value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Number getStrategyId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStrategyId(Number strategyId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInitEp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInitEp(String initEp) {
		// TODO Auto-generated method stub
		
	}
	
	IUser currentUser;
		public IUser getCurrentUser() {
			return currentUser;
		}
		public void setCurrentUser(IUser currentUser) {
			this.currentUser = currentUser;
		}

	IUser initiator;
		public IUser getInitiator() {
			return initiator;
		}
		public void setInitiator(IUser initiator) {
			this.initiator = initiator;
		}

		String secuopt;
		@Override
		public String getSecuopt() {
			return secuopt;
		}

		@Override
		public void setSecuopt(String secuopt) {
			this.secuopt = secuopt;
		}

}
