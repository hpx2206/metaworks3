package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="public_introduce_service")
public interface IPublicServiceIntroduceService extends IDAO {

	@Id
	public Long getNumber();
	public void setNumber(Long number);
	
	public String getBusinessName();
	public void setBusinessName(String businessName);
	
	public String getYear();
	public void setYear(String year);
	
	public String getEnterpriseName();
	public void setEnterpriseName(String enterpriseName);
	
	public String getEtc();
	public void setEtc(String etc);
	
	public Long getItemId();
	public void setItemId(Long itemId);
	
}
