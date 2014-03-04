package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;


@Table(name="enterprise")
public interface IEnterpriseInformation extends IDAO{
	
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
	
	public String getCarryon();
	public void setCarryon(String carryon);
	
	@ServiceMethod(callByContent=true)
	public Object inputNextService();
	
	@ServiceMethod(callByContent=true)
	public Object inputNextPatent();
	
	@ServiceMethod(callByContent=true)
	public Object inputNextSurvey();
	
}
