package org.uengine.codi.mw3.model;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="b2g")
public interface IExternalCompany extends IDAO{
	
	@Id
	public Long getId();
	public void setId(Long id);
	
	public String getCompany_name();
	public void setCompany_name(String company_name);
	
	public String getCategory();
	public void setCategory(String category);
	
	public String getAddr();
	public void setAddr(String addr);
	
	public String getCity();
	public void setCity(String city);
	
	public String getCountry();
	public void setCountry(String country);

	public String getFax_num();
	public void setFax_num(String fax_num);

	public String getPhone_num();
	public void setPhone_num(String phone_num);
	
	public String getHomepage();
	public void setHomepage(String homepage);
	
	public String getBusiness_type();
	public void setBusiness_type(String business_type);
	
	public String getRelated_items();
	public void setRelated_items(String related_items);
	
	public String getCeo_name();
	public void setCeo_name(String ceo_name);
	
	public int getNum();
	public void setNum(int num);
	
	public int getCurrentPage();
	public void setCurrentPage(int currentPage);
	
	public IExternalCompany load() throws Exception;
	
	@ServiceMethod(callByContent=true,mouseBinding=ServiceMethodContext.MOUSEBINDING_LEFTCLICK, bindingHidden=true, target=ServiceMethodContext.TARGET_APPEND)
	@Hidden
	public Object[] loadCompany() throws Exception;
	
	public int getTotalPage() throws Exception;
}
