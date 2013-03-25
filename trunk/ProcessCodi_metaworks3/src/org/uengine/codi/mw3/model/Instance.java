package org.uengine.codi.mw3.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.dao.DAOFactory;
import org.metaworks.dao.Database;
import org.metaworks.dao.TransactionContext;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.Login;
import org.uengine.processmanager.ProcessManagerRemote;

import com.efsol.util.StringUtils;

public class Instance extends Database<IInstance> implements IInstance{

	public final static String INSTNACE_STATUS_STOPPED		= "Stopped";
	public final static String INSTNACE_STATUS_FAILED		= "Failed";
	public final static String INSTNACE_STATUS_COMPLETED	= "Completed";
	
	public static final String TASK_DIRECT_APPEND_SQL_KEY = "task.";
	public static final String INSTANCE_DIRECT_APPEND_SQL_KEY = "inst.";
	
	public static final String DEFAULT_DEFVERID = "Unstructured.process";

	
	
	@Autowired
	public InstanceViewContent instanceViewContent;
	
	@AutowiredFromClient
	public Session session;
	
	public Instance(){
		
	}
	
	static public IInstance load(Navigation navigation, int page, int count)
			throws Exception {
		
		Map<String, String> criteria = new HashMap<String, String>();
		
		if( page < 0 ){
			page = 0;
		}
		// paging 
		String tempStr = "";
		tempStr = "" + (page * count);
		criteria.put("startIndex", tempStr);

		tempStr = "" + (page + 1) * count;
		criteria.put("lastIndex", tempStr);
		
		StringBuffer worklistSql = new StringBuffer();
		StringBuffer instanceSql = new StringBuffer();

		// TODO makes all criteria

		createSqlPhase1(navigation, 
				criteria, worklistSql, instanceSql);
		
		StringBuffer stmt = new StringBuffer();		

		String searchKeyword = navigation.getKeyword();
		if(searchKeyword != null && !searchKeyword.isEmpty()) {
			//stmt.append("(");
			
			StringBuffer appendedInstanceSql = new StringBuffer(instanceSql);
			
			if(	"inbox".equals(navigation.getPerspectiveType())) {				
				appendedInstanceSql.append("   AND (wl.title like ?keyword or inst.name like ?keyword)");
				
			}else{
				appendedInstanceSql.append(" AND (exists (select 1 from bpm_worklist wl where inst.INSTID = wl.instid and title like ?keyword) or inst.name like ?keyword)  ");
			}
			
			criteria.put("keyword", "%" + searchKeyword + "%");			
			
			createSQLPhase2(navigation, criteria, stmt, worklistSql, appendedInstanceSql);


			//stmt.append(") union (");


			//StringBuffer appendedWorkListSql = new StringBuffer(worklistSql);
			
			
			//criteria.put("worklistTitle", "%" + searchKeyword + "%");			
			
			//createSQLPhase2(session, criteria, stmt, appendedWorkListSql, instanceSql);
			
			//stmt.append(")");

		}else{

			createSQLPhase2(navigation, criteria, stmt, worklistSql, instanceSql);
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
		

/*		if ("ORACLE".equals(typeOfDBMS))
			bottomList.append( " limit " + criteria.get("startIndex") + ", "+InstanceList.PAGE_CNT);
		else if ("MYSQL".equals(typeOfDBMS))*/
			bottomList.append( " limit " + criteria.get("startIndex") + ", "+ ("phone".equals(navigation.getMedia())?InstanceList.PAGE_CNT_MOBILE:InstanceList.PAGE_CNT));
		
		//TODO delete printing
		System.out.println("worklist sql:" + bottomList.toString());
		
		IInstance instanceContents = (IInstance) sql(Instance.class, bottomList.toString());
		instanceContents.setInitComCd(navigation.getEmployee().getGlobalCom());
		instanceContents.set("endpoint", navigation.getEmployee().getEmpCode());
		instanceContents.set("partcode", navigation.getEmployee().getPartCode());

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
	
	static private void createSQLPhase2(Navigation navigation, Map<String, String> criteria, StringBuffer stmt, StringBuffer taskSql, StringBuffer instanceSql) {
		stmt.append("select");
		
		//if(oracle)
		//   stmt.append(" ROWNUM rindex,");
		
		
/*		if("inbox".equals(navigation.getPerspectiveType())) {
//			2012-10-25 내가할일 및 참여중 쿼리 변경
			stmt.append(" (select max(worklist.startdate) startdate, worklist.rootinstid ");
			stmt.append("from bpm_worklist worklist INNER JOIN bpm_rolemapping rolemapping ");
			stmt.append("ON (worklist.rolename = rolemapping.rolename OR worklist.refrolename=rolemapping.rolename) ");
			stmt.append("OR worklist.ENDPOINT=?endpoint ");
			stmt.append("where worklist.rootinstid=rolemapping.rootinstid and (worklist.status != '"+ DefaultWorkList.WORKITEM_STATUS_RESERVED +"') ");
		}else{*/
/*			stmt.append(" (select max(worklist.startdate) startdate, worklist.rootinstid ");
			stmt.append("from bpm_worklist worklist, bpm_rolemapping rolemapping ");
			stmt.append("where worklist.rootinstid=rolemapping.rootinstid and (worklist.status != '"+ DefaultWorkList.WORKITEM_STATUS_RESERVED +"') ");
		if(taskSql!=null) {
			stmt.append(taskSql);
		}			
*/			
		//}
		
		stmt.append(" instanceList.* FROM ")
		.append(" (SELECT distinct inst.*, task.startdate")
		.append("  FROM bpm_procinst inst");
				
		stmt
		.append("      ,(SELECT max(startdate) startdate, worklist.rootinstid")
		.append("   	   FROM bpm_worklist worklist");
		
		if("organization.group"	.equals(navigation.getPerspectiveType())) {
			stmt.append("   	  , 	bpm_rolemapping	  rolemapping ");
		}
		stmt.append("  		  WHERE worklist.status != 'RESERVED'");
		if("organization.group"	.equals(navigation.getPerspectiveType())) {
			stmt.append("and rolemapping.rootinstid = worklist.instid ");
			stmt.append("and rolemapping.endpoint in (select empcode from emptable where partcode=?lastSelectedItem) ");
		}
		
		stmt.append("  		  GROUP BY worklist.rootinstid) task ");
//		stmt
//		.append("      ,(SELECT max(startdate) startdate, worklist.rootinstid")
//		.append("   	   FROM bpm_worklist worklist")
//		.append("  		  WHERE worklist.status != 'RESERVED'")
//		.append("  		  GROUP BY worklist.rootinstid) task ");
		
		stmt.append(taskSql.toString());
		
		// add instance criteria
		stmt.append(" WHERE initcomcd=?initComCd");
		stmt.append("   AND inst.instid=task.rootinstid ");		
		
		if(instanceSql!=null) {
			stmt.append(instanceSql);
		}
		
		stmt.append(" ORDER BY task.startdate desc) instanceList ");
	}
	


	static private void createSqlPhase1(Navigation navigation,
			Map<String, String> criteria, StringBuffer taskSql,
			StringBuffer instanceSql) {

		if(	"inbox"
				.equals(navigation.getPerspectiveType())) {
			
			/*
			taskSql.append("and (worklist.status=?taskStatus1 or worklist.status=?taskStatus2) ");
			criteria.put("taskStatus1", "NEW");
			criteria.put("taskStatus2", "CONFIRMED");
			taskSql.append("and rolemapping.endpoint=?endpoint ");
			taskSql.append("or(worklist.duedate is not null and worklist.defid is null)");			
			if(session.getMetaworksContext().getWhen() != null && session.getMetaworksContext().getWhen().equals("todoBadge")){
				instanceSql.append("and inst.status !='Completed' ");
			}
			*/
			
			taskSql
			.append("      , bpm_worklist wl")
			.append("           INNER JOIN bpm_rolemapping role")
			.append("                   ON WL.INSTID=ROLE.INSTID")
			.append("                  AND role.endpoint=?endpoint");
			
			instanceSql
			.append("   AND wl.instid=inst.instid")			
			.append("   AND inst.status<>'" + Instance.INSTNACE_STATUS_STOPPED + "'")
			.append("   AND inst.status<>'" + Instance.INSTNACE_STATUS_FAILED + "'")
			.append("   AND inst.status<>'" + Instance.INSTNACE_STATUS_COMPLETED + "'")			
			.append("   AND ((inst.defVerId != '"+Instance.DEFAULT_DEFVERID+"' and wl.status in ('" + WorkItem.WORKITEM_STATUS_NEW + "','" + WorkItem.WORKITEM_STATUS_DRAFT + "','" + WorkItem.WORKITEM_STATUS_CONFIRMED + "'))")
			.append("     OR   (inst.defVerId = '"+Instance.DEFAULT_DEFVERID+"' and inst.DUEDATE is not null and wl.status = '" + WorkItem.WORKITEM_STATUS_FEED + "'))")			
			.append("   AND inst.isdeleted!=?instIsdelete ");
			
			criteria.put("instIsdelete", "1");			
		
		}else if("all"
				.equals(navigation.getPerspectiveType())) {
			
			taskSql
			.append("      , bpm_worklist wl")
			.append("           INNER JOIN bpm_rolemapping role")
			.append("                   ON WL.INSTID=ROLE.INSTID")
			.append("                  AND role.endpoint=?endpoint");
			
			instanceSql
			.append("   AND wl.instid=inst.instid")			
			.append("   AND inst.isdeleted!=?instIsdelete ");
			//.append("   and inst.status=?instStatus ");
			
			criteria.put("instIsdelete", "1");			
			//	criteria.put("instStatus", "Running");			
			
			// secureopt
			instanceSql			
			.append(" and	exists ( ")
			.append("			select 1 from bpm_procinst	 ")
			.append("			where inst.instid = instid	 ")
			.append("			and secuopt = 0	 ")
			.append("			union all	 ")
			.append("			select 1 from bpm_rolemapping rm	 ")
			.append("			where inst.instid = rm.rootinstid	 ")
			.append("			and inst.secuopt = 1	 ")
			.append("			and ( 	( assigntype = 0 and rm.endpoint = ?endpoint ) 	 ")
			.append("					or ( assigntype = 2 and rm.endpoint = ?partCode ) ) ")
			.append("			)	 ");
	

		}else if("running"
				.equals(navigation.getPerspectiveType())) {
			
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
			instanceSql.append("and inst.status!=?instStatus ");
			criteria.put("instStatus", "Stopped");
			// secureopt
			instanceSql
					.append("and (exists (select rootinstid from BPM_ROLEMAPPING rm where rm.endpoint=?endpoint and inst.rootinstid=rm.rootinstid)) ");
		}else if("request"
					.equals(navigation.getPerspectiveType())) {
			
			instanceSql.append(" and inst.initep=?instInitep ");
			criteria.put("instInitep", navigation.getEmployee().getEmpCode());
			instanceSql.append(" and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");

		}else if("stopped"
					.equals(navigation.getPerspectiveType())) {
			
			instanceSql.append("and inst.status=?instStatus ");
			criteria.put("instStatus", "Stopped");
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");

			// secureopt
			instanceSql
			.append(" and	exists ( ")
			.append("			select 1 from bpm_procinst	 ")
			.append("			where inst.instid = instid	 ")
			.append("			and secuopt = 0	 ")
			.append("			union all	 ")
			.append("			select 1 from bpm_rolemapping rm	 ")
			.append("			where inst.instid = rm.rootinstid	 ")
			.append("			and inst.secuopt = 1	 ")
			.append("			and ( 	( assigntype = 0 and rm.endpoint = ?endpoint ) 	 ")
			.append("					or ( assigntype = 2 and rm.endpoint = ?partcode ) ) ")
			.append("			)	 ");

		}else if("organization"
					.equals(navigation.getPerspectiveType())) {
			
			taskSql
			.append("      , bpm_worklist wl")
			.append("           INNER JOIN bpm_rolemapping role")
			.append("                   ON WL.INSTID=ROLE.INSTID")
			.append("                  AND role.endpoint=?taskEndpoint");
			
			instanceSql
			.append("   AND wl.instid=inst.instid")			
			.append("   AND inst.isdeleted!=?instIsdelete ");
			
			criteria.put("taskEndpoint", navigation.getPerspectiveValue());
			criteria.put("instIsdelete", "1");

			// secureopt
			instanceSql
			.append(" and	exists ( ")
			.append("			select 1 from bpm_procinst	 ")
			.append("			where inst.instid = instid	 ")
			.append("			and secuopt = 0	 ")
			.append("			union all	 ")
			.append("			select 1 from bpm_rolemapping rm	 ")
			.append("			where inst.instid = rm.rootinstid	 ")
			.append("			and inst.secuopt = 1	 ")
			.append("			and ( 	( assigntype = 0 and rm.endpoint = ?endpoint ) 	 ")
			.append("					or ( assigntype = 2 and rm.endpoint = ?partcode ) ) ")
			.append("			)	 ");
			
		}else if("organization.group"
				.equals(navigation.getPerspectiveType())) {
			
//			taskSql.append("and rolemapping.endpoint in (select empcode from emptable where partcode=?lastSelectedItem) ");
			
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			
			criteria.put("lastSelectedItem", navigation.getPerspectiveValue());			
			criteria.put("instIsdelete", "1");
			
			// secureopt
			instanceSql
			.append(" and	exists ( ")
			.append("			select 1 from bpm_procinst	 ")
			.append("			where inst.instid = instid	 ")
			.append("			and secuopt = 0	 ")
			.append("			union all	 ")
			.append("			select 1 from bpm_rolemapping rm	 ")
			.append("			where inst.instid = rm.rootinstid	 ")
			.append("			and inst.secuopt = 1	 ")
			.append("			and ( 	( assigntype = 0 and rm.endpoint = ?endpoint ) 	 ")
			.append("					or ( assigntype = 2 and rm.endpoint = ?partcode ) ) ")
			.append("			)	 ");

		}else if("process"
				.equals(navigation.getPerspectiveType())) {
			
			instanceSql.append("and inst.defverid=?instDefVerId ");
			criteria.put("instDefVerId", navigation.getPerspectiveValue());
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");

			// secureopt
			instanceSql
			.append(" and	exists ( ")
			.append("			select 1 from bpm_procinst	 ")
			.append("			where inst.instid = instid	 ")
			.append("			and secuopt = 0	 ")
			.append("			union all	 ")
			.append("			select 1 from bpm_rolemapping rm	 ")
			.append("			where inst.instid = rm.rootinstid	 ")
			.append("			and inst.secuopt = 1	 ")
			.append("			and ( 	( assigntype = 0 and rm.endpoint = ?endpoint ) 	 ")
			.append("					or ( assigntype = 2 and rm.endpoint = ?partcode ) ) ")
			.append("			)	 ");

		}else if("strategy"
				.equals(navigation.getPerspectiveType())) {
			
			instanceSql.append("and inst.strategyid=?instStrategyId ");
			criteria.put("instStrategyId", navigation.getPerspectiveValue());
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");

			// secureopt
			instanceSql
			.append(" and	exists ( ")
			.append("			select 1 from bpm_procinst	 ")
			.append("			where inst.instid = instid	 ")
			.append("			and secuopt = 0	 ")
			.append("			union all	 ")
			.append("			select 1 from bpm_rolemapping rm	 ")
			.append("			where inst.instid = rm.rootinstid	 ")
			.append("			and inst.secuopt = 1	 ")
			.append("			and ( 	( assigntype = 0 and rm.endpoint = ?endpoint ) 	 ")
			.append("					or ( assigntype = 2 and rm.endpoint = ?partcode ) ) ")
			.append("			)	 ");

		}else if("taskExt1"
				.equals(navigation.getPerspectiveType())) {
			
			instanceSql.append("and task.ext1=?taskExt1 ");
			criteria.put("taskExt1", navigation.getPerspectiveValue());
			instanceSql.append("and inst.status=?status ");
			criteria.put("status", "Running");
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");

			// secureopt
			instanceSql
			.append(" and	exists ( ")
			.append("			select 1 from bpm_procinst	 ")
			.append("			where inst.instid = instid	 ")
			.append("			and secuopt = 0	 ")
			.append("			union all	 ")
			.append("			select 1 from bpm_rolemapping rm	 ")
			.append("			where inst.instid = rm.rootinstid	 ")
			.append("			and inst.secuopt = 1	 ")
			.append("			and ( 	( assigntype = 0 and rm.endpoint = ?endpoint ) 	 ")
			.append("					or ( assigntype = 2 and rm.endpoint = ?partcode ) ) ")
			.append("			)	 ");

		}else if("status".equals(navigation.getPerspectiveType())) {
			instanceSql.append("and inst.status=?status ");
			criteria.put("status", navigation.getPerspectiveValue());
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");

			// secureopt
			instanceSql
			.append(" and	exists ( ")
			.append("			select 1 from bpm_procinst	 ")
			.append("			where inst.instid = instid	 ")
			.append("			and secuopt = 0	 ")
			.append("			union all	 ")
			.append("			select 1 from bpm_rolemapping rm	 ")
			.append("			where inst.instid = rm.rootinstid	 ")
			.append("			and inst.secuopt = 1	 ")
			.append("			and ( 	( assigntype = 0 and rm.endpoint = ?endpoint ) 	 ")
			.append("					or ( assigntype = 2 and rm.endpoint = ?partcode ) ) ");

		}else if("allICanSee".equals(navigation.getPerspectiveType())) {
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
			instanceSql.append("and inst.status!=?instStatus ");
			criteria.put("instStatus", "Stopped");
			// secureopt
			if(navigation.getEmployee().isApproved()){
				instanceSql
				.append(" and	exists ( ")
				.append("			select 1 from bpm_procinst	 ")
				.append("			where inst.instid = instid	 ")
				.append("			and secuopt = 0	 ")
				.append("			union all	 ")
				.append("			select 1 from bpm_rolemapping rm	 ")
				.append("			where inst.instid = rm.rootinstid	 ")
				.append("			and inst.secuopt = 1	 ")
				.append("			and ( 	( assigntype = 0 and rm.endpoint = ?endpoint ) 	 ")
				.append("					or ( assigntype = 2 and rm.endpoint = ?partcode ) ) ")
				.append("			union all 	 ")
				.append("			select 1 from bpm_topicmapping tm	 ")
				.append("			where inst.topicId = tm.topicId	 ")
				.append("			and inst.secuopt = 3	 ")
				.append("			and ( 	( assigntype = 0 and tm.userid = ?endpoint ) 	 ")
				.append("					or ( assigntype = 2 and tm.userid = ?partcode ) ) ")
				.append("			)	 ");
			}else{
				instanceSql
				.append(" and	exists ( ")
				.append("			select 1 from bpm_rolemapping rm	 ")
				.append("			where inst.instid = rm.rootinstid	 ")
				.append("			and assigntype = 0 and rm.endpoint = ?endpoint")
				.append("			)	 ");
			}
			

//			.append("and (inst.secuopt='0' OR (inst.secuopt=1 and ( exists (select rootinstid from BPM_ROLEMAPPING rm where rm.endpoint=?endpoint and inst.rootinstid=rm.rootinstid) ")
//			.append("																or ?endpoint in ( select empcode from emptable where partcode in (  ")
//			.append("																				select endpoint from bpm_rolemapping where assigntype = 2 and instid = inst.rootinstid))))  ")
//			.append("		OR (inst.secuopt=3 and ( exists (select topicId from BPM_TOPICMAPPING tm where tm.userId=?endpoint and inst.topicId=tm.topicId) ")
//			.append(" 																	 or ?endpoint in ( select empcode from emptable where partcode in (  ")
//			.append(" 																	 						select userId from BPM_TOPICMAPPING where assigntype = 2 and topicId = inst.topicId )))))  ");
		}else if("topic".equals(navigation.getPerspectiveType())) {
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
			instanceSql.append("and inst.status!=?instStatus ");
			criteria.put("instStatus", "Stopped");
			instanceSql.append("and inst.topicId =?topicId ");
			criteria.put("topicId", navigation.getPerspectiveValue());
			// secureopt
			instanceSql
			.append(" and	exists ( ")
			.append("			select 1 from bpm_procinst	 ")
			.append("			where inst.instid = instid	 ")
			.append("			and secuopt = 0	 ")
			.append("			union all	 ")
			.append("			select 1 from bpm_rolemapping rm	 ")
			.append("			where inst.instid = rm.rootinstid	 ")
			.append("			and inst.secuopt = 1	 ")
			.append("			and ( 	( assigntype = 0 and rm.endpoint = ?endpoint ) 	 ")
			.append("					or ( assigntype = 2 and rm.endpoint = ?partcode ) ) ")
			.append("			union all 	 ")
			.append("			select 1 from bpm_topicmapping tm	 ")
			.append("			where inst.topicId = tm.topicId	 ")
			.append("			and inst.secuopt = 3	 ")
			.append("			and ( 	( assigntype = 0 and tm.userid = ?endpoint ) 	 ")
			.append("					or ( assigntype = 2 and tm.userid = ?partcode ) ) ")
			.append("			)	 ");

		}else{
			// personal inbox
			taskSql.append("and (worklist.status=?taskStatus1 or worklist.status=?taskStatus2) ");
			criteria.put("taskStatus1", "NEW");
			criteria.put("taskStatus2", "CONFIRMED");
			taskSql.append("and rolemapping.endpoint=?taskEndpoint ");
			criteria.put("taskEndpoint", navigation.getEmployee().getEmpCode());
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
		}
	}
	
	public Object detail() throws Exception{

		if(getMetaworksContext()==null){
			setMetaworksContext(new MetaworksContext());
		}
		
		if("sns".equals(session.getEmployee().getPreferUX()) ){
			getMetaworksContext().setHow("sns");
			
			InstanceViewThreadPanel panel = new InstanceViewThreadPanel();
			panel.getMetaworksContext().setHow("sns");
			
			if(this.getInstanceViewThreadPanel() == null || "".equals(StringUtils.nullToEmpty(this.getInstanceViewThreadPanel().getInstanceId()))){
				panel.session = session;
				panel.load(this.getInstId().toString());
				
//				followers = new InstanceFollowers();
//				followers.setInstanceId(this.getInstId().toString());
//				followers.load();
				this.fillFollower();
					
//				InstanceMonitorPanel processInstanceMonitorPanel = new InstanceMonitorPanel();
//				processInstanceMonitorPanel.processManager = processManager;
//				processInstanceMonitorPanel.session = session;
//				processInstanceMonitorPanel.load(this.getInstId().toString());
//				FollowerPanel followerPanel = new FollowerPanel("instance", this.getFollowers());			
//				final Object[] returnObjects = new Object[]{new Refresh(processInstanceMonitorPanel), new Refresh(followerPanel)};				
//				MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(session.getEmployee().getEmpCode()), returnObjects);				
			}
			
			setInstanceViewThreadPanel(panel);
			
			return this;
		}else{
			getMetaworksContext().setHow("");
			getMetaworksContext().setWhere("");
			TransactionContext.getThreadLocalInstance().setSharedContext("codi_session", session);
			instanceViewContent.session = session;
			instanceViewContent.setMetaworksContext(getMetaworksContext());
			instanceViewContent.load(this);
			
			return instanceViewContent;
		}
		
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
		
	String lastCmnt2;
		public String getLastCmnt2() {
			return lastCmnt2;
		}
		public void setLastCmnt2(String lastCmnt2) {
			this.lastCmnt2 = lastCmnt2;
		}
		
	IUser lastCmntUser;
		public IUser getLastCmntUser() {
			return lastCmntUser;
		}
		public void setLastCmntUser(IUser lastCmntUser) {
			this.lastCmntUser = lastCmntUser;
		}
		
	IUser lastCmnt2User;
		public IUser getLastCmnt2User() {
			return lastCmnt2User;
		}
		public void setLastCmnt2User(IUser lastCmnt2User) {
			this.lastCmnt2User = lastCmnt2User;
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

	Date	startedDate;
		public Date getStartedDate() {
			return startedDate;
		}
	
		public void setStartedDate(Date startedDate) {
			this.startedDate = startedDate;
		}

	Date finishedDate;
		public Date getFinishedDate() {
			return finishedDate;
		}
	
		public void setFinishedDate(Date finishedDate) {
			this.finishedDate = finishedDate;
		}

	Date dueDate;
		@Override
		public Date getDueDate() {
			return dueDate;
		}
		public void setDueDate(Date dueDate) {
			this.dueDate = dueDate;
		}
		
	Date	defModDate;
		public Date getDefModDate() {
			return defModDate;
		}
		public void setDefModDate(Date defModDate) {
			this.defModDate = defModDate;
		}

	Date	modDate;
		public Date getModDate() {
			return modDate;
		}
		public void setModDate(Date modDate) {
			this.modDate = modDate;
		}

	String status;
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}

	String info;
		@Override
		public String getInfo() {
			return this.info;
		}
		@Override
		public void setInfo(String info) {
			this.info = info;
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

	boolean isDeleted;
		@Override
		public boolean getIsDeleted() {
			return this.isDeleted;
		}
		@Override
		public void setIsDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

	boolean isAdhoc;
		@Override
		public boolean getIsAdhoc() {
			return this.isAdhoc;
		}
		@Override
		public void setIsAdhoc(boolean isAdhoc) {
			this.isAdhoc = isAdhoc;
		}

	boolean isSubProcess;
		@Override
		public boolean getIsSubProcess() {
			return this.isSubProcess;
		}
		@Override
		public void setIsSubProcess(boolean isSubProcess) {
			this.isSubProcess = isSubProcess;
		}

	Long rootInstId;
		@Override
		public Long getRootInstId() {
			return this.rootInstId;
		}
		@Override
		public void setRootInstId(Long rootInstId) {
			this.rootInstId = rootInstId;
		}

	Long mainInstId;
		@Override
		public Long getMainInstId() {
			return this.mainInstId;
		}
		@Override
		public void setMainInstId(Long mainInstId) {
			this.mainInstId = mainInstId;
		}

	String mainActTrcTag;
		@Override
		public String getMainActTrcTag() {
			return this.mainActTrcTag;
		}
		@Override
		public void setMainActTrcTag(String mainActTrcTag) {
			this.mainActTrcTag = mainActTrcTag;
		}

	String mainExecScope;
		@Override
		public String getMainExecScope() {
			return this.mainExecScope;
		}
		@Override
		public void setMainExecScope(String mainExecScope) {
			this.mainExecScope = mainExecScope;
			
		}

	Long mainDefVerId;
		@Override
		public Long getMainDefVerId() {
			return this.mainDefVerId;
		}
		@Override
		public void setMainDefVerId(Long mainDefVerId) {
			this.mainDefVerId = mainDefVerId;
		}

	boolean isArchive;
		@Override
		public boolean getIsArchive() {
			return this.isArchive;
		}
		@Override
		public void setIsArchive(boolean isArchive) {
			this.isArchive = isArchive;
		}

	String absTrcPath;
		@Override
		public String getAbsTrcPath() {
			return this.absTrcPath;
		}
		@Override
		public void setAbsTrcPath(String absTrcPath) {
			this.absTrcPath = absTrcPath;
		}

	boolean dontReturn;	
		@Override
		public boolean getDontReturn() {
			return this.dontReturn;
		}
		@Override
		public void setDontReturn(boolean dontReturn) {
			this.dontReturn = dontReturn;
		}

	Number strategyId;
		@Override
		public Number getStrategyId() {
			return this.strategyId;
		}
		@Override
		public void setStrategyId(Number strategyId) {
			this.strategyId = strategyId;
		}

	String initEp;
		@Override
		public String getInitEp() {
			return this.initEp;
		}
		@Override
		public void setInitEp(String initEp) {
			this.initEp = initEp;
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
		
	int bvBenefit;
		@Override
		public int getBVBenefit() {
			return bvBenefit;
		}
		public void setBVBenefit(int benefit) {
			this.bvBenefit = benefit;
		}
	
	int bvPenalty;
		@Override
		public int getBVPenalty() {
			return bvPenalty;
		}
		public void setBVPenalty(int penalty) {
			this.bvPenalty = penalty;
		}

	int effort;
		@Override
		public int getEffort() {
			return effort;
		}
		public void setEffort(int effort) {
			this.effort = effort;
		}

	/*
	 * 2013-01-10 cjw
	 * push client 의 보안 처리
	 */
	InstanceFollowers followers;
		public InstanceFollowers getFollowers() {
			return followers;
		}
		public void setFollowers(InstanceFollowers followers) {
			this.followers = followers;
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
	
	public void fillFollower(){
		/*
		 * 2013-01-10 cjw
		 * push client 의 보안 처리
		 */
		try{
			InstanceFollowers followers = new InstanceFollowers();
			followers.setInstanceId(this.getInstId().toString());
			followers.load();

			this.setFollowers(followers);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static public int countTodo(Session session) {
		
		int result = 0;
		
		StringBuffer sb = new StringBuffer();		
		sb
		.append("select count(*) cnt from (")
		.append("SELECT distinct inst.instid")
		.append("  FROM bpm_procinst inst,")
		.append("       bpm_worklist wl INNER JOIN bpm_rolemapping role ON wl.instid=role.instid AND role.endpoint=?endpoint")
		.append(" WHERE wl.instid=inst.instid")
		.append("   AND inst.isdeleted = 0")
		.append("   AND initcomcd = ?initcomcd")
		.append("   AND inst.status <> '" + Instance.INSTNACE_STATUS_STOPPED + "'")
		.append("   AND inst.status <> '" + Instance.INSTNACE_STATUS_FAILED + "'")
		.append("   AND inst.status <> '" + Instance.INSTNACE_STATUS_COMPLETED + "'")
				

		.append("   AND ((inst.defVerId != '"+Instance.DEFAULT_DEFVERID+"' and wl.status in ('" + WorkItem.WORKITEM_STATUS_NEW + "','" + WorkItem.WORKITEM_STATUS_DRAFT + "','" + WorkItem.WORKITEM_STATUS_CONFIRMED + "'))")
		.append("     OR   (inst.defVerId = '"+Instance.DEFAULT_DEFVERID+"' and inst.DUEDATE is not null and wl.status = '" + WorkItem.WORKITEM_STATUS_FEED + "'))")
		.append(") a");

		
		try{
			IInstance instance = (IInstance) sql(Instance.class, sb.toString());
			instance.set("endpoint", session.getEmployee().getEmpCode());
			instance.setInitComCd(session.getEmployee().getGlobalCom());
			instance.select();
			
			if(instance.next())
				result = instance.getInt("cnt");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	static public int countTodo(String empCode, String comCode) {
		
		int result = 0;
		
		StringBuffer sb = new StringBuffer();		
		sb
		.append("select count(*) cnt from (")
		.append("SELECT distinct inst.instid")
		.append("  FROM bpm_procinst inst,")
		.append("       bpm_worklist wl INNER JOIN bpm_rolemapping role ON wl.instid=role.instid AND role.endpoint=?endpoint")
		.append(" WHERE wl.instid=inst.instid")
		.append("   AND inst.isdeleted = 0")
		.append("   AND initcomcd = ?initcomcd")
		.append("   AND inst.status <> '" + Instance.INSTNACE_STATUS_STOPPED + "'")
		.append("   AND inst.status <> '" + Instance.INSTNACE_STATUS_FAILED + "'")
		.append("   AND inst.status <> '" + Instance.INSTNACE_STATUS_COMPLETED + "'")
				

		.append("   AND ((inst.defVerId != '"+Instance.DEFAULT_DEFVERID+"' and wl.status in ('" + WorkItem.WORKITEM_STATUS_NEW + "','" + WorkItem.WORKITEM_STATUS_DRAFT + "','" + WorkItem.WORKITEM_STATUS_CONFIRMED + "'))")
		.append("     OR   (inst.defVerId = '"+Instance.DEFAULT_DEFVERID+"' and inst.DUEDATE is not null and wl.status = '" + WorkItem.WORKITEM_STATUS_FEED + "'))")
		.append(") a");

		
		try{
			IInstance instance = (IInstance) sql(Instance.class, sb.toString());
			instance.set("endpoint", empCode);
			instance.setInitComCd(comCode);
			instance.select();
			
			if(instance.next())
				result = instance.getInt("cnt");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
}
