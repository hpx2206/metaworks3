package org.uengine.codi.mw3.model;

import java.util.HashMap;
import java.util.Map;

import org.metaworks.annotation.Id;
import org.metaworks.dao.DAOFactory;
import org.metaworks.dao.Database;
import org.metaworks.dao.KeyGeneratorDAO;
import org.metaworks.dao.TransactionContext;


public class EnterpriseService extends Database<IEnterpriseService> implements IEnterpriseService{
	
	public static final String SERVICE = "service";
	
	Long id;
		@Id
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
	String globalCom;
		public String getGlobalCom() {
			return globalCom;
		}
		public void setGlobalCom(String globalCom) {
			this.globalCom = globalCom;
		}

	String serviceForm;
		public String getServiceForm() {
			return serviceForm;
		}
		public void setServiceForm(String serviceForm) {
			this.serviceForm = serviceForm;
		}
		
	String productName;
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}

	String projectResult;
		public String getProjectResult() {
			return projectResult;
		}
		public void setProjectResult(String projectResult) {
			this.projectResult = projectResult;
		}
	
		
	public void saveService() throws Exception {
		this.createDatabaseMe();
	}
	
	public void modifyService() throws Exception {
		this.syncToDatabaseMe();
	}
	
	// sequence table에 생성하여 id 값을 +1씩 시켜주는 함수
	public int createNewNotiSettingId() throws Exception{
		Map options = new HashMap();
		options.put("useTableNameHeader", "false");
		options.put("onlySequenceTable", "true");
		  
		KeyGeneratorDAO kg = DAOFactory.getInstance(TransactionContext.getThreadLocalInstance()).createKeyGenerator("enterpriseService", options);
		kg.select();
		kg.next();
		  
		Number number = kg.getKeyNumber();
		  
		return number.intValue();
	}
	
	public IEnterpriseService findEnterpriseService(String globalCom) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from enterprise_service");
		sb.append(" where globalcom = ?globalcom");
		
		IEnterpriseService enterpriseService = (IEnterpriseService) sql(IEnterpriseService.class, sb.toString());
		enterpriseService.setGlobalCom(globalCom);
		enterpriseService.select();
		
		return enterpriseService;
	}
	
	public Long findServiceId(String globalCom) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select id");
		sb.append(" from enterprise_service");
		sb.append(" where globalcom = ?globalcom");
		
		IEnterpriseService enterpriseService = (IEnterpriseService) sql(IEnterpriseService.class, sb.toString());
		enterpriseService.setGlobalCom(globalCom);
		enterpriseService.select();
		
		Long id = null;
		
		while(enterpriseService.next()) {
			id = enterpriseService.getId();
		}
		
		return id;
	}
}
