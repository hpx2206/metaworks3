package org.uengine.codi.mw3.model;

import java.util.HashMap;
import java.util.Map;

import org.metaworks.annotation.Id;
import org.metaworks.dao.DAOFactory;
import org.metaworks.dao.Database;
import org.metaworks.dao.KeyGeneratorDAO;
import org.metaworks.dao.TransactionContext;

public class EnterprisePatent extends Database<IEnterprisePatent> implements IEnterprisePatent {

	public final static String PATENT = "patent";
	
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

	String patentForm;
		public String getPatentForm() {
			return patentForm;
		}
		public void setPatentForm(String patentForm) {
			this.patentForm = patentForm;
		}

	String patentName;
		public String getPatentName() {
			return patentName;
		}
		public void setPatentName(String patentName) {
			this.patentName = patentName;
		}

	String registrationDate;
		public String getRegistrationDate() {
			return registrationDate;
		}
		public void setRegistrationDate(String registrationDate) {
			this.registrationDate = registrationDate;
		}

	String registrationNumber;
		public String getRegistrationNumber() {
			return registrationNumber;
		}
		public void setRegistrationNumber(String registrationNumber) {
			this.registrationNumber = registrationNumber;
		}

	String registrationMembers;
		public String getRegistrationMembers() {
			return registrationMembers;
		}
		public void setRegistrationMembers(String registrationMembers) {
			this.registrationMembers = registrationMembers;
		}

	String etc;
		public String getEtc() {
			return etc;
		}
		public void setEtc(String etc) {
			this.etc = etc;
		}
		
	public void savePatent() throws Exception {
		this.createDatabaseMe();
	}
	
	public void modifyPatent() throws Exception {
		this.syncToDatabaseMe();
	}
		
	// sequence table에 생성하여 id 값을 +1씩 시켜주는 함수
	public int createNewNotiSettingId() throws Exception{
		Map options = new HashMap();
		options.put("useTableNameHeader", "false");
		options.put("onlySequenceTable", "true");
		  
		KeyGeneratorDAO kg = DAOFactory.getInstance(TransactionContext.getThreadLocalInstance()).createKeyGenerator("enterprisePatent", options);
		kg.select();
		kg.next();
		  
		Number number = kg.getKeyNumber();
		  
		return number.intValue();
	}
	
	public IEnterprisePatent findEnterprisePatent(String globalCom) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from enterprise_patent");
		sb.append(" where globalcom = ?globalcom");
		
		IEnterprisePatent enterprisePatent = (IEnterprisePatent) sql(IEnterprisePatent.class, sb.toString());
		enterprisePatent.setGlobalCom(globalCom);
		enterprisePatent.select();
		
		return enterprisePatent;
	}
	
	public Long findPatentId(String globalCom) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select id");
		sb.append(" from enterprise_patent");
		sb.append(" where globalcom = ?globalcom");
		
		IEnterprisePatent enterprisePatent = (IEnterprisePatent) sql(IEnterprisePatent.class, sb.toString());
		enterprisePatent.setGlobalCom(globalCom);
		enterprisePatent.select();
		
		Long id = null;
		
		while(enterprisePatent.next()) {
			id = enterprisePatent.getId();
		}
		
		return id;
	}
}
