package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.dao.IDAO;

public interface IEnterprise extends IDAO{
	
	@Id
	public String getEnterpriseName();
	public void setEnterpriseName(String enterpriseName);
	
	public String getRepresentative();
	public void setRepresentative(String representative);
	
	public String getBusinessPart();
	public void setBusinessPart(String businessPart);
	
	public String getAddress();
	public void setAddress(String address);
	
	public String getFoundation();
	public void setFoundation(String foundation);
	
	public String getNumber();
	public void setNumber(String number);
	
	public String getFax();
	public void setFax(String Fax);
	
	public String getCarryon();
	public void setCarryon(String carryon);
	
}
