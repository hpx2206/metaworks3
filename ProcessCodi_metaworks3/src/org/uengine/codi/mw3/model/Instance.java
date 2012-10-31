package org.uengine.codi.mw3.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dwr.MetaworksRemoteService;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.Login;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.webservices.worklist.DefaultWorkList;

import com.efsol.util.StringUtils;



public class Instance extends Database<IInstance> implements IInstance{

	
	public static final String TASK_DIRECT_APPEND_SQL_KEY = "task.";
	public static final String INSTANCE_DIRECT_APPEND_SQL_KEY = "inst.";

	
	
	@Autowired
	public InstanceViewContent instanceViewContent;
	
	@AutowiredFromClient
	public Session session;

	public Instance(){
		
	}
	
	static public IInstance load(Session session, int page, int count)
			throws Exception {
		
		Map<String, String> criteria = new HashMap<String, String>();

		// paging 
		String tempStr = "";
		tempStr = "" + (page * count);
		criteria.put("startIndex", tempStr);

		tempStr = "" + (page + 1) * count;
		criteria.put("lastIndex", tempStr);
		
		StringBuffer worklistSql = new StringBuffer();
		StringBuffer instanceSql = new StringBuffer();

		// TODO makes all criteria

		createSqlPhase1(session, 
				criteria, worklistSql, instanceSql);
		
		StringBuffer stmt = new StringBuffer();		

		String searchKeyword = session.getSearchKeyword();
		if(searchKeyword != null && !searchKeyword.isEmpty()) {
			stmt.append("(");
			
			StringBuffer appendedInstanceSql = new StringBuffer(instanceSql);
			
			appendedInstanceSql.append(" AND inst.name LIKE ?instName ");
			criteria.put("instName", "%" + searchKeyword + "%");			
			
			createSQLPhase2(session, criteria, stmt, worklistSql, appendedInstanceSql);


			stmt.append(") union (");


			StringBuffer appendedWorkListSql = new StringBuffer(worklistSql);
			
			appendedWorkListSql.append(" AND worklist.title LIKE ?worklistTitle ");
			criteria.put("worklistTitle", "%" + searchKeyword + "%");			
			
			createSQLPhase2(session, criteria, stmt, appendedWorkListSql, instanceSql);
			
			stmt.append(")");

		}else{

			createSQLPhase2(session, criteria, stmt, worklistSql, instanceSql);
		}
		
		// TODO add direct append to sql
//		criteria.put(Instance.TASK_DIRECT_APPEND_SQL_KEY, taskSql.toString());
//		criteria.put(Instance.INSTANCE_DIRECT_APPEND_SQL_KEY,
//				instanceSql.toString());
		
		
		
		
		StringBuffer bottomList = new StringBuffer();
		bottomList.append("select bottomlist.* from (");	
		bottomList
			.append(stmt)
			.append(") bottomlist");
		
//		if(oracle)
//			stmt.append("where rindex between ?startIndex and ?lastIndex ");
		
		bottomList.append( " limit " + criteria.get("startIndex") + ", "+InstanceList.PAGE_CNT);

		
		//TODO delete printing
		System.out.println("worklist sql:" + bottomList.toString());
		
		IInstance instanceContents = (IInstance) sql(Instance.class, bottomList.toString());
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
//		instanceContents.getCurrentUser().getMetaworksContext().setHow("small");
		
		return instanceContents;
	}

	
	public Session cut(){
		session.setClipboard(this);
		return session;
	}
	
	public Object[] paste() throws Exception{
		Object clipboard = session.getClipboard();
		if(clipboard instanceof IInstance){
			IInstance instanceInClipboard = (IInstance) clipboard;
			
			
			if(instanceInClipboard.getInstId().equals(getInstId())){
				return null; //ignores drag n drop same object 
			}
			
			Instance locatorForInstanceInClipboard = new Instance();
			locatorForInstanceInClipboard.setInstId(instanceInClipboard.getInstId());
			
			instanceInClipboard = locatorForInstanceInClipboard.databaseMe();
			
			instanceInClipboard.setRootInstId(this.getInstId());
			instanceInClipboard.setMainInstId(this.getInstId());
			
			IWorkItem workItemsToUpdate = (IWorkItem) sql(IWorkItem.class, "update bpm_worklist set rootInstId=?rootInstId where rootInstId = ?oldRootInstId");
			workItemsToUpdate.setRootInstId(this.getInstId());
			workItemsToUpdate.set("oldRootInstId", instanceInClipboard.getInstId());
			workItemsToUpdate.update();
			
			IRoleMapping roleMappingsToUpdate = (IRoleMapping) sql(IRoleMapping.class, "update bpm_rolemapping set rootInstId=?rootInstId where rootInstId = ?oldRootInstId");
			roleMappingsToUpdate.setRootInstId(this.getInstId());
			roleMappingsToUpdate.set("oldRootInstId", instanceInClipboard.getInstId());
			roleMappingsToUpdate.update();
			
			getMetaworksContext().setWhen("instanceList");
			
			return new Object[]{new Remover(locatorForInstanceInClipboard)};
		}else{
			return null;//new Object[]{new Refresh(this.databaseMe())};
		}
	}
	public Object[] addTrayBar() throws Exception{
		Browser.withSession(Login.getSessionIdWithUserId(session.getUser().getUserId()), new Runnable(){
			InstanceView instanceView = ((InstanceViewContent)detail()).getInstanceView();
			@Override
			public void run() {
				ScriptSessions.addFunctionCall("mw3.getAutowiredObject('org.uengine.codi.mw3.model.Tray').__getFaceHelper().addTray", new Object[]{instanceView.getInstanceName(), getInstId()+""});
			}
			
		});
		return null;
	}
	
	static private void createSQLPhase2(Session session, Map<String, String> criteria, StringBuffer stmt, StringBuffer taskSql, StringBuffer instanceSql) {
		stmt.append("select");
		
		//if(oracle)
		//   stmt.append(" ROWNUM rindex,");
		
		stmt.append(" instanceList.* from ");
		stmt.append(" (select inst.*, task.startdate from ");
		stmt.append(" BPM_PROCINST inst, ");
		
		if("allICanSee".equals(session.getLastPerspecteType())) {
			stmt.append(" (select max(worklist.startdate) startdate, worklist.rootinstid ");
			stmt.append("from bpm_worklist worklist, bpm_rolemapping rolemapping ");
			stmt.append("where worklist.rootinstid=rolemapping.rootinstid and (worklist.status != '"+ DefaultWorkList.WORKITEM_STATUS_RESERVED +"' or worklist.status is null) ");
		}else{
//			2012-10-25 내가할일 및 참여중 쿼리 변경
			stmt.append(" (select max(worklist.startdate) startdate, worklist.rootinstid ");
			stmt.append("from bpm_worklist worklist INNER JOIN bpm_rolemapping rolemapping ");
			stmt.append("ON (worklist.rolename = rolemapping.rolename OR worklist.refrolename=rolemapping.rolename) ");
			stmt.append("OR worklist.ENDPOINT=?rmEndpoint ");
			stmt.append("where worklist.rootinstid=rolemapping.rootinstid and (worklist.status != '"+ DefaultWorkList.WORKITEM_STATUS_RESERVED +"' or worklist.status is null) ");
		}
		if(taskSql!=null) {
			stmt.append(taskSql);
		}
		stmt.append("group by worklist.rootinstid) task ");
		
		// add instance criteria
		stmt.append("where inst.instid=task.rootinstid ");
		stmt.append("and initcomcd=?initComCd ");
		if(instanceSql!=null) {
			stmt.append(instanceSql);
		}
		
		stmt.append("order by task.startdate desc) instanceList ");
	}
	


	static private void createSqlPhase1(Session session,
			Map<String, String> criteria, StringBuffer taskSql,
			StringBuffer instanceSql) {

		if(	"inbox"
				.equals(session.getLastPerspecteType())) {
			
			taskSql.append("and (worklist.status=?taskStatus1 or worklist.status=?taskStatus2) ");
			criteria.put("taskStatus1", "NEW");
			criteria.put("taskStatus2", "CONFIRMED");
			taskSql.append("and rolemapping.endpoint=?taskEndpoint ");
			criteria.put("taskEndpoint", session.getEmployee().getEmpCode());
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
		
		}else if("all"
				.equals(session.getLastPerspecteType())) {
			
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
			instanceSql.append("and inst.status=?instStatus ");
			criteria.put("instStatus", "Running");
			// secureopt
			instanceSql
					.append("and (exists (select rootinstid from BPM_ROLEMAPPING rm where rm.endpoint=?rmEndpoint and inst.rootinstid=rm.rootinstid)) ");
			criteria.put("rmEndpoint", session.getEmployee().getEmpCode());
		}else if("running"
				.equals(session.getLastPerspecteType())) {
			
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
			instanceSql.append("and inst.status!=?instStatus ");
			criteria.put("instStatus", "Stopped");
			// secureopt
			instanceSql
					.append("and (exists (select rootinstid from BPM_ROLEMAPPING rm where rm.endpoint=?rmEndpoint and inst.rootinstid=rm.rootinstid)) ");
			criteria.put("rmEndpoint", session.getEmployee().getEmpCode());
		}else if("request"
					.equals(session.getLastPerspecteType())) {
			
			instanceSql.append(" and inst.initep=?instInitep ");
			criteria.put("instInitep", session.getEmployee().getEmpCode());
			instanceSql.append(" and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");

		}else if("stopped"
					.equals(session.getLastPerspecteType())) {
			
			instanceSql.append("and inst.status=?instStatus ");
			criteria.put("instStatus", "Stopped");
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");

			// secureopt
			instanceSql
					.append("and (inst.secuopt='0' OR (inst.secuopt=1 and exists (select rootinstid from BPM_ROLEMAPPING rm where rm.endpoint=?rmEndpoint and inst.rootinstid=rm.rootinstid))) ");
			criteria.put("rmEndpoint", session.getEmployee().getEmpCode());

		}else if("organization"
					.equals(session.getLastPerspecteType())) {
			
			taskSql.append("and rolemapping.endpoint=?taskEndpoint ");
			criteria.put("taskEndpoint", session.getLastSelectedItem());
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");

			// secureopt
			instanceSql
					.append("and (inst.secuopt='0' OR (inst.secuopt=1 and exists (select rootinstid from BPM_ROLEMAPPING rm where rm.endpoint=?rmEndpoint and inst.rootinstid=rm.rootinstid))) ");
			criteria.put("rmEndpoint", session.getEmployee().getEmpCode());

		}else if("process"
				.equals(session.getLastPerspecteType())) {
			
			instanceSql.append("and inst.defverid=?instDefVerId ");
			criteria.put("instDefVerId", session.getLastSelectedItem());
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");

			// secureopt
			instanceSql
					.append("and (inst.secuopt='0' OR (inst.secuopt=1 and exists (select rootinstid from BPM_ROLEMAPPING rm where rm.endpoint=?rmEndpoint and inst.rootinstid=rm.rootinstid))) ");
			criteria.put("rmEndpoint", session.getEmployee().getEmpCode());

		}else if("strategy"
				.equals(session.getLastPerspecteType())) {
			
			instanceSql.append("and inst.strategyid=?instStrategyId ");
			criteria.put("instStrategyId", session.getLastSelectedItem());
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");

			// secureopt
			instanceSql
					.append("and (inst.secuopt='0' OR (inst.secuopt=1 and exists (select rootinstid from BPM_ROLEMAPPING rm where rm.endpoint=?rmEndpoint and inst.rootinstid=rm.rootinstid))) ");
			criteria.put("rmEndpoint", session.getEmployee().getEmpCode());

		}else if("taskExt1"
				.equals(session.getLastPerspecteType())) {
			
			instanceSql.append("and task.ext1=?taskExt1 ");
			criteria.put("taskExt1", session.getLastSelectedItem());
			instanceSql.append("and inst.status=?status ");
			criteria.put("status", "Running");
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");

			// secureopt
			instanceSql
					.append("and (inst.secuopt='0' OR (inst.secuopt=1 and exists (select rootinstid from BPM_ROLEMAPPING rm where rm.endpoint=?rmEndpoint and inst.rootinstid=rm.rootinstid))) ");
			criteria.put("rmEndpoint", session.getEmployee().getEmpCode());

		}else if("status".equals(session.getLastPerspecteType())) {
			instanceSql.append("and inst.status=?status ");
			criteria.put("status", session.getLastSelectedItem());
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");

			// secureopt
			instanceSql
					.append("and (inst.secuopt='0' OR (inst.secuopt=1 and exists (select rootinstid from BPM_ROLEMAPPING rm where rm.endpoint=?rmEndpoint and inst.rootinstid=rm.rootinstid))) ");
			criteria.put("rmEndpoint", session.getEmployee().getEmpCode());

		}else if("allICanSee".equals(session.getLastPerspecteType())) {
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
			instanceSql.append("and inst.status!=?instStatus ");
			criteria.put("instStatus", "Stopped");
			// secureopt
			instanceSql
			.append("and (inst.secuopt='0' OR (inst.secuopt=1 and exists (select rootinstid from BPM_ROLEMAPPING rm where rm.endpoint=?rmEndpoint and inst.rootinstid=rm.rootinstid)) ")
			.append("		OR (inst.secuopt=3 and exists (select topicId from BPM_TOPICMAPPING tm where tm.userId=?rmEndpoint and inst.topicId=tm.topicId))) ");
			criteria.put("rmEndpoint", session.getEmployee().getEmpCode());
		}else if("topic".equals(session.getLastPerspecteType())) {
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
			instanceSql.append("and inst.status!=?instStatus ");
			criteria.put("instStatus", "Stopped");
			instanceSql.append("and inst.topicId =?topicId ");
			criteria.put("topicId", session.getLastSelectedItem());
			// secureopt
			instanceSql
			.append("and (inst.secuopt='0' OR (inst.secuopt=1 and exists (select rootinstid from BPM_ROLEMAPPING rm where rm.endpoint=?rmEndpoint and inst.rootinstid=rm.rootinstid)) ")
			.append("		OR (inst.secuopt=3 and exists (select topicId from BPM_TOPICMAPPING tm where tm.userId=?rmEndpoint and inst.topicId=tm.topicId))) ");
			criteria.put("rmEndpoint", session.getEmployee().getEmpCode());

		}else{
			// personal inbox
			taskSql.append("and (worklist.status=?taskStatus1 or worklist.status=?taskStatus2) ");
			criteria.put("taskStatus1", "NEW");
			criteria.put("taskStatus2", "CONFIRMED");
			taskSql.append("and rolemapping.endpoint=?taskEndpoint ");
			criteria.put("taskEndpoint", session.getEmployee().getEmpCode());
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
		}
	}
	
	
	public ContentWindow detail() throws Exception{
		//InstanceViewContent instanceViewContent = new InstanceViewContent();
		//instanceViewContent.processManager = this.processManager;
		//instanceViewContent.
		
		TransactionContext.getThreadLocalInstance().setSharedContext("codi_session", session);
		instanceViewContent.session = session;
		instanceViewContent.load(this);
		
		return instanceViewContent;
	}
	
	public ModalWindow popupDetail() throws Exception{
		ModalWindow modalWindow = new ModalWindow();
		InstanceView instanceView = ((InstanceViewContent)detail()).getInstanceView();
		modalWindow.setPanel(instanceView);
		modalWindow.setTitle(instanceView.getInstanceName());
		modalWindow.setWidth(800);
		
		return modalWindow;
	}
	
	public Object replaceDetail() throws Exception{
		PinterestBox box = new PinterestBox();
		//Window window = new Window ();
		InstanceView instanceView = ((InstanceViewContent)detail()).getInstanceView();
		//window.setPanel(instanceView);
		box.setContent(instanceView);
		
		//return window;
		
		return box;
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
		
	String lastCmnt;
	
		public String getLastCmnt() {
			return lastCmnt;
		}
	
		public void setLastCmnt(String lastCmnt) {
			this.lastCmnt = lastCmnt;
		}
	
		

	String defId;
		public String getDefId() {
			return this.defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}

	String defVerId;
		public String getDefVerId() {
			return defVerId;
		}
		public void setDefVerId(String defVerId) {
			this.defVerId = defVerId;
		}

	String defPath;
		public String getDefPath() {
			return defPath;
		}
		public void setDefPath(String defPath) {
			this.defPath = defPath;
		}
	String defName;
		public String getDefName() {
			return defName;
		}
		public void setDefName(String defName) {
			this.defName = defName;
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

	String status;
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
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

	String name;
		@Override
		public String getName() {
			return this.name;
		}
		@Override
		public void setName(String name) {
			this.name = name;
			
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
		
	String topicId;
		public String getTopicId() {
			return topicId;
		}
		public void setTopicId(String topicId) {
			this.topicId = topicId;
		}
	
	String assignee;
		public String getAssignee() {
			return assignee;
		}
	
		public void setAssignee(String assignee) {
			this.assignee = assignee;
		}

	//only the initiator can complete this thread
	boolean initCmpl;
		public boolean isInitCmpl() {
			return initCmpl;
		}
		public void setInitCmpl(boolean initCmpl) {
			this.initCmpl = initCmpl;
		}
	String progress;
		public String getProgress() {
			return progress;
		}
		public void setProgress(String progress) {
			this.progress = progress;
		}	
		
	InstanceViewThreadPanel instanceViewThreadPanel;
		public InstanceViewThreadPanel getInstanceViewThreadPanel() {
			return instanceViewThreadPanel;
		}
		public void setInstanceViewThreadPanel(InstanceViewThreadPanel instanceViewThreadPanel) {
			this.instanceViewThreadPanel = instanceViewThreadPanel;
		}		
	InstanceTooltip instanceTooltip;
		public InstanceTooltip getInstanceTooltip() {
			return instanceTooltip;
		}
		public void setInstanceTooltip(InstanceTooltip instanceTooltip) {
			this.instanceTooltip = instanceTooltip;
		}
	public InstanceDrag instanceDrag;
		public InstanceDrag getInstanceDrag() {
			return instanceDrag;
		}
		public void setInstanceDrag(InstanceDrag instanceDrag) {
			this.instanceDrag = instanceDrag;
		}
		
	public void over() throws Exception{
		InstanceViewThreadPanel panel = new InstanceViewThreadPanel();
		
		if("".equals(StringUtils.nullToEmpty(this.getInstanceViewThreadPanel().getInstanceId()))){
			panel.session = session;
			panel.getMetaworksContext().setHow("instanceList");
			panel.load(this.getInstId().toString());
			if( "sns".equals(session.getTheme())){
				MetaworksRemoteService.pushClientObjects(new Object[]{new Refresh(flowchart())});
			}
		}
		
		setInstanceViewThreadPanel(panel);
	}
	
	@Override
	public void split() throws Exception {
		Long root = new Long(-1);
		databaseMe().setMainInstId(root);
		databaseMe().setRootInstId(root);

		IWorkItem workItemsToUpdate = (IWorkItem) sql(IWorkItem.class, "update bpm_worklist set rootInstId=?rootInstId where rootInstId = ?oldRootInstId");
		workItemsToUpdate.setRootInstId(root);
		workItemsToUpdate.update();
		
		IRoleMapping roleMappingsToUpdate = (IRoleMapping) sql(IRoleMapping.class, "update bpm_rolemapping set rootInstId=?rootInstId where rootInstId = ?oldRootInstId");
		roleMappingsToUpdate.setRootInstId(root);
		roleMappingsToUpdate.update();

	}
}
