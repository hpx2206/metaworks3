package org.uengine.codi.mw3.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.metaworks.dao.DAOFactory;
import org.metaworks.dao.Database;
import org.metaworks.dao.KeyGeneratorDAO;
import org.metaworks.dao.TransactionContext;

public class Comment extends Database<IComment> implements IComment {

	int id;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}

	int instance_id;
		public int getInstance_id() {
			return instance_id;
		}
		public void setInstance_id(int instance_id) {
			this.instance_id = instance_id;
		}

	String contents;
		public String getContents() {
			return contents;
		}
		public void setContents(String contents) {
			this.contents = contents;
		}

	String opt_type;
		public String getOpt_type() {
			return opt_type;
		}
		public void setOpt_type(String opt_type) {
			this.opt_type = opt_type;
		}

	String empNo;
		public String getEmpNo() {
			return empNo;
		}
		public void setEmpNo(String empNo) {
			this.empNo = empNo;
		}
		
	String empName;
		public String getEmpName() {
			return empName;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}

	String empTitle;
		public String getEmpTitle() {
			return empTitle;
		}
		public void setEmpTitle(String empTitle) {
			this.empTitle = empTitle;
		}

	String tracingTag;
		public String getTracingTag() {
			return tracingTag;
		}
		public void setTracingTag(String tracingTag) {
			this.tracingTag = tracingTag;
		}

	Date created_date;
		public Date getCreated_date() {
			return created_date;
		}
		public void setCreated_date(Date created_date) {
			this.created_date = created_date;
		}

	String created_by;
		public String getCreated_by() {
			return created_by;
		}
		public void setCreated_by(String created_by) {
			this.created_by = created_by;
		}

	Date updated_date;
		public Date getUpdated_date() {
			return updated_date;
		}
		public void setUpdated_date(Date updated_date) {
			this.updated_date = updated_date;
		}

	String updated_by;
		public String getUpdated_by() {
			return updated_by;
		}
		public void setUpdated_by(String updated_by) {
			this.updated_by = updated_by;
		}

	String apprTitle;
		public String getApprTitle() {
			return apprTitle;
		}
		public void setApprTitle(String apprTitle) {
			this.apprTitle = apprTitle;
		}
		
	@Override
	public void createMe() throws Exception {
		Map options = new HashMap();
		options.put("useTableNameHeader", "false");
		options.put("onlySequenceTable", "true");
		
		KeyGeneratorDAO kg = DAOFactory.getInstance(TransactionContext.getThreadLocalInstance()).createKeyGenerator("doc_comments", options);
		kg.select();
		kg.next();
				
		Number number = kg.getKeyNumber();
		
		setId(number.intValue());
		setCreated_date(new Date());
		setCreated_by(this.getEmpNo());
		setUpdated_date(new Date());
		setUpdated_by(this.getEmpNo());
		
		createDatabaseMe();
	}
	
	@Override
	public IComment findMeByInstanceId() throws Exception {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT *");
		sb.append("  FROM doc_comments");
		sb.append(" WHERE instance_id = ?instance_id");
		
		IComment dao = sql(sb.toString());
		dao.setInstance_id(this.getInstance_id());
		dao.select();
		
		return dao;
	}
}
