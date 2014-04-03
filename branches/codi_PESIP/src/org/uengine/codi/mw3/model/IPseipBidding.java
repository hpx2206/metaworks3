package org.uengine.codi.mw3.model;

import java.util.Date;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="pseip_bidding")
public interface IPseipBidding extends IDAO{
	
	@Id
	public String getProjectCode();
	public void setProjectCode(String projectCode);
	
	public String getProjectName();
	public void setProjectName(String projectName);
	
	public String getIndustryCategory();
	public void setIndustryCategory(String industryCategory);
	
	public String getServiceCategory();
	public void setServiceCategory(String serviceCategory);
	
	public String getProjectStatus();
	public void setProjectStatus(String projectStatus);
	
	public Date getDueDate();
	public void setDueDate(Date dueDate);
	
	public String getDueDateTime();
	public void setDueDateTime(String dueDateTime);
	
	public Date getRegDate();
	public void setRegDate(Date regDate);
	
	public String getCountryCode();
	public void setCountryCode(String countryCode);
	
	public String getFundType();
	public void setFundType(String fundType);
	
	public String getRegEmpCode();
	public void setRegEmpCode(String regEmpCode);
	
	@NonSavable
	public int getCount();
	public void setCount(int count);
	
//	public IOrderInformation findOrderInfo() throws Exception;
//	public IOrderInformation findDistinctOrderInfo() throws Exception;
//	public IOrderInformation loadDetailOrderInfo() throws Exception;
//	public IOrderInformation loadPastOrderInfo() throws Exception;
	
}
