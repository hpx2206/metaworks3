package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="enterprise_patent")
public interface IEnterprisePatent extends IDAO {
	
	@Id
	public Long getId();
	public void setId(Long id);
	
	public String getGlobalCom();
	public void setGlobalCom(String globalCom);
	
	public String getPatentForm();
	public void setPatentForm(String patentForm);
	
	public String getPatentName();
	public void setPatentName(String patentName);
	
	public String getRegistrationDate();
	public void setRegistrationDate(String registrationDate);
	
	public String getRegistrationNumber();
	public void setRegistrationNumber(String registrationNumber);
	
	public String getRegistrationMembers();
	public void setRegistrationMembers(String registrationMembers);
	
	public String getEtc();
	public void setEtc(String etc);
	
	public void savePatent() throws Exception;
	public void modifyPatent() throws Exception;
	public Long findPatentId(String globalCom) throws Exception;
	public IEnterprisePatent findEnterprisePatent(String globalCom) throws Exception;
}
