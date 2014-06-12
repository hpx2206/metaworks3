package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="pseip_oiscompany")
public interface IExportOISCompany extends IDAO{
	
	@Id
	public Long getId();
	public void setId(Long id);
	
	public String getComp_name();
	public void setComp_name(String comp_name);
	
	public String getSector();
	public void setSector(String sector);
	
	public String getEntered_con();
	public void setEntered_con(String entered_con);
	
	public String getEntered_reg();
	public void setEntered_reg(String entered_reg);
	
	public String getMcomp_addr();
	public void setMcomp_addr(String mcomp_addr);
	
	public String getMcomp_tel();
	public void setMcomp_tel(String mcomp_tel);
	
	public String getFax();
	public void setFax(String fax);
	
	public String getEmail();
	public void setEmail(String email);
	
	public String getHomepage();
	public void setHomepage(String homepage);
	
	public String getAreas();
	public void setAreas(String areas);
	
	public String getEntered_year();
	public void setEntered_year(String entered_year);
	
	public String getEntry_form();
	public void setEntry_form(String entry_form);
	
	public String getOversea_rep();
	public void setOversea_rep(String oversea_rep);
	
	public String getTel();
	public void setTel(String tel);
	
	public String getMcomp_name();
	public void setMcomp_name(String mcomp_name);
	
	public int getNum();
	public void setNum(int num);
	
	public int getCurrentPage();
	public void setCurrentPage(int currentPage);
	
	public IExportOISCompany load() throws Exception;
	public IExportOISCompany getRegions() throws Exception;
	
	@ServiceMethod(callByContent=true,mouseBinding=ServiceMethodContext.MOUSEBINDING_LEFTCLICK, bindingHidden=true, target=ServiceMethodContext.TARGET_APPEND)
	@Hidden
	public Object[] loadCompany() throws Exception;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] loadCompanyByNumber() throws Exception;
	
	public int getTotalPage() throws Exception;
}
