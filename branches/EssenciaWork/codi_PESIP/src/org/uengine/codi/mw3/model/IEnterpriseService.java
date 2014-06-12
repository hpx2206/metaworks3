package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="enterprise_service")
public interface IEnterpriseService extends IDAO {
	
	@Id
	public Long getId();
	public void setId(Long id);
	
	@Hidden
	public String getGlobalCom();
	public void setGlobalCom(String globalCom);
	
	public String getServiceForm();
	public void setServiceForm(String serviceForm);
	
	public String getProductName();
	public void setProductName(String productName);
	
	public String getProjectResult();
	public void setProjectResult(String projectResult);
	
	public void saveService() throws Exception;
	public void modifyService() throws Exception;
	public Long findServiceId(String globalCom) throws Exception;
	public IEnterpriseService findEnterpriseService(String globalCom) throws Exception;
	
}
