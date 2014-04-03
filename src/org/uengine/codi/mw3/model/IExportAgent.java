package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="inke_chair")
public interface IExportAgent extends IDAO{

	@Id
	public Long getId();
	public void setId(Long id);
	
	public String getRegion();
	public void setRegion(String region);
	
	public String getChairName();
	public void setChairName(String chairName);
	
	public String getCompanyName();
	public void setCompanyName(String companyName);
	
	public String getBusinessArea();
	public void setBusinessArea(String businessArea);
	
	public String getTel();
	public void setTel(String tel);
	
	public String getEmail();
	public void setEmail(String email);
	
	public int getNum();
	public void setNum(int num);
	
	public int getCurrentPage();
	public void setCurrentPage(int currentPage);
	
	public IExportAgent load() throws Exception;
	
	@ServiceMethod(callByContent=true,mouseBinding=ServiceMethodContext.MOUSEBINDING_LEFTCLICK, bindingHidden=true, target=ServiceMethodContext.TARGET_APPEND)
	@Hidden
	public Object[] loadCompany() throws Exception;
	
	public int getTotalPage() throws Exception;
}
