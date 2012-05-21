package org.uengine.codi.mw3.model;

import java.util.HashMap;
import java.util.Map;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;

public class InstanceList {

	final static int PAGE_CNT = 15;

	int page;
	IInstance instances;
	InstanceList moreInstanceList;

	@Id
	@Hidden
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public IInstance getInstances() {
		return instances;
	}

	public void setInstances(IInstance instances) {
		this.instances = instances;
	}

	public InstanceList getMoreInstanceList() {
		return moreInstanceList;
	}

	public void setMoreInstanceList(InstanceList moreInstanceList) {
		this.moreInstanceList = moreInstanceList;
	}

	@AutowiredFromClient
	public Session session;

//	@ServiceMethod(callByContent = true, except = { "instances",
//			"moreInstanceList" })
//	public void search() throws Exception {
//		setPage(0);
//		load(session);
//	}

	@ServiceMethod(callByContent = true, except = { "instances" })
	public void more() throws Exception {
		load(session);
	}

	public void init() {
		setPage(0);
	}

	public InstanceList load(Session session) throws Exception {
		IInstance instanceContents = getInstanceContents(session,
				getPage(), PAGE_CNT);
		instanceContents.setMetaworksContext(new MetaworksContext());
		instanceContents.getMetaworksContext().setWhere("instanceList");
		setInstances(instanceContents);

		// setting moreInstanceList
		setMoreInstanceList(new InstanceList());
		getMoreInstanceList().setPage(getPage() + 1);
		return this;
	}

	public IInstance getInstanceContents(Session session, int page, int amount)
			throws Exception {
		IInstance instanceContents = new Instance();
		Map<String, String> criteria = new HashMap<String, String>();

		// paging 
		String tempStr = "";
		tempStr = "" + (page * amount + 1);
		criteria.put("startIndex", tempStr);

		tempStr = "" + (page + 1) * amount;
		criteria.put("lastIndex", tempStr);
		
		StringBuffer taskSql = new StringBuffer();
		StringBuffer instanceSql = new StringBuffer();

		// TODO makes all criteria

		getNavigatedInstListSql(session, 
				criteria, taskSql, instanceSql);

//		// add Search keyword
//		SearchKeywordBox searchKeywordBox = session.getSearchKeywordBox();
//		if (searchKeywordBox != null && searchKeywordBox.getKeyword() != null
//				&& !"".equals(searchKeywordBox.getKeyword())) {
//			instanceSql.append(" AND inst.name LIKE ?instName ");
//			criteria.put("instName", "%" + searchKeywordBox.getKeyword() + "%");
//		}

		// TODO add direct append to sql
		criteria.put(Instance.TASK_DIRECT_APPEND_SQL_KEY, taskSql.toString());
		criteria.put(Instance.INSTANCE_DIRECT_APPEND_SQL_KEY,
				instanceSql.toString());

		return instanceContents.load(session, criteria);
	}

	private void getNavigatedInstListSql(Session session,
			Map<String, String> criteria, StringBuffer taskSql,
			StringBuffer instanceSql) {

		switch (findPerspectiveTypeCode(session.getLastPerspecteType())) {
		case 0://"inbox"
			taskSql.append("and (status=?taskStatus1 or status=?taskStatus2) ");
			criteria.put("taskStatus1", "NEW");
			criteria.put("taskStatus2", "CONFIRMED");
			taskSql.append("and endpoint=?taskEndpoint ");
			criteria.put("taskEndpoint", session.getEmployee().getEmpCode());
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
			break;
		case 1://"all"
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
			// Forx Only
			instanceSql.append("and inst.status!=?instStatus ");
			criteria.put("instStatus", "Stopped");
			break;
		case 2://"request"
			instanceSql.append("and inst.initep=?instInitep ");
			criteria.put("instInitep", session.getEmployee().getEmpCode());
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
			break;
		case 3://"stopped"
			instanceSql.append("and inst.status=?instStatus ");
			criteria.put("instStatus", "Stopped");
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
			break;
		case 4://"organization"
			taskSql.append("and endpoint=?taskEndpoint ");
			criteria.put("taskEndpoint", session.getLastSelectedItem());
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
			break;
		case 5://"process"
			instanceSql.append("and inst.defid=?instDefId ");
			criteria.put("instDefId", session.getLastSelectedItem());
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
			break;
		case 6: //"strategy"
			instanceSql.append("and inst.strategyid=?instStrategyId ");
			criteria.put("instStrategyId", session.getLastSelectedItem());
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
			break;
		case 7: //"taskExt1"
			instanceSql.append("and task.ext1=?taskExt1 ");
			criteria.put("taskExt1", session.getLastSelectedItem());
			instanceSql.append("and inst.status=?status ");
			criteria.put("status", "Running");
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
			break;
		case 8: //"status"
			instanceSql.append("and inst.status=?status ");
			criteria.put("status", session.getLastSelectedItem());
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
			break;
		default:
			// personal inbox
			taskSql.append("and (status=?taskStatus1 or status=?taskStatus2) ");
			criteria.put("taskStatus1", "NEW");
			criteria.put("taskStatus2", "CONFIRMED");
			taskSql.append("and endpoint in (?taskEndpoint) ");
			criteria.put("taskEndpoint", session.getEmployee().getEmpCode());
			instanceSql.append("and inst.isdeleted!=?instIsdelete ");
			criteria.put("instIsdelete", "1");
			break;
		}
	}
	
	private int findPerspectiveTypeCode(String typeString) {
		if(typeString == null) {
			return -1;
		} else if(typeString.equals("inbox")) {
			return 0;
		} else if(typeString.equals("all")) {
			return 1;
		} else if(typeString.equals("request")) {
			return 2;
		} else if(typeString.equals("stopped")) {
			return 3;
		} else if(typeString.equals("organization")) {
			return 4;
		} else if(typeString.equals("process")) {
			return 5;
		} else if(typeString.equals("strategy")) {
			return 6;
		} else if(typeString.equals("taskExt1")) {
			return 7;
		} else if(typeString.equals("status")) {
			return 8;
		} else {
			//return 8;
		}
		return -1;
	}
}
