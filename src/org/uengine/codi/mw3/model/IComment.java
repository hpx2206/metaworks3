package org.uengine.codi.mw3.model;

import java.util.Date;

import javax.persistence.Table;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.dao.IDAO;

@Table(name="doc_comments")
public interface IComment extends IDAO {

	@Hidden
	public int getId();
	public void setId(int id);

	@Hidden
	public int getInstance_id();
	public void setInstance_id(int instance_id);

	@Face(displayName="첨언")
	public String getContents();
	public void setContents(String contents);

	@Hidden
	public String getOpt_type();
	public void setOpt_type(String opt_type);

	@Hidden
	public String getEmpNo();
	public void setEmpNo(String empNo);

	@Face(displayName="승인자")
	public String getEmpName();
	public void setEmpName(String empName);

	@Face(displayName="직책")
	public String getEmpTitle();
	public void setEmpTitle(String empTitle);

	@Hidden
	public String getTracingTag();
	public void setTracingTag(String tracingTag);
	
	@Face(displayName="승인일")
	public Date getCreated_date();
	public void setCreated_date(Date created_date);

	@Hidden
	public String getCreated_by();
	public void setCreated_by(String created_by);

	@Face(displayName="수정일")
	public Date getUpdated_date();
	public void setUpdated_date(Date updated_date);

	@Hidden
	public String getUpdated_by();
	public void setUpdated_by(String updated_by);

	public String getApprTitle();
	public void setApprTitle(String apprTitle);

	public void createMe() throws Exception;
	
	public IComment findMeByInstanceId() throws Exception;
}
