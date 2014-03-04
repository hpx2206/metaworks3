package org.uengine.codi.mw3.model;

import java.util.Date;

import org.metaworks.annotation.Id;
import org.metaworks.dao.IDAO;

public interface IOrderInformation extends IDAO{
	
	@Id
	public Long getNumber();
	public void setNumber(Long number);
	
	public String getProjectname();
	public void setProjectname(String projectname);
	
	public String getIndustrypart();
	public void setIndustrypart(String industrypart);
	
	public String getStatus();
	public void setStatus(String status);
	
	public Date getRegdate();
	public void setRegdate(Date regdate);
	
	public String getServicepart();
	public void setServicepart(String servicepart);
	
	public String getCountrycode();
	public void setCountrycode(String countrycode);
	
	public String getLat();
	public void setLat(String lat);
	
	public String getLng();
	public void setLng(String lng);
	
	public String getCountryname();
	public void setCountryname(String countryname);
	
	public Long getCount();
	public void setCount(Long count);
	
//	public IOrderInformation findOrderInfo() throws Exception;
	public IOrderInformation findDistinctOrderInfo() throws Exception;
	public IOrderInformation loadDetailOrderInfo() throws Exception;
	public IOrderInformation loadPastOrderInfo() throws Exception;
	
}
