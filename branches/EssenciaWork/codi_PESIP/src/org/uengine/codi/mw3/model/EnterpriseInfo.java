package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class EnterpriseInfo {
	
	public static final String MODIFY = "modify";
	public static final String NEW = "new";
	
	String globalCom;
		public String getGlobalCom() {
			return globalCom;
		}
		public void setGlobalCom(String globalCom) {
			this.globalCom = globalCom;
		}

	Enterprise enterprise;
		public Enterprise getEnterprise() {
			return enterprise;
		}
		public void setEnterprise(Enterprise enterprise) {
			this.enterprise = enterprise;
		}
	
	ArrayList<EnterpriseService> enterpriseServiceList;
		public ArrayList<EnterpriseService> getEnterpriseServiceList() {
			return enterpriseServiceList;
		}
		public void setEnterpriseServiceList(
				ArrayList<EnterpriseService> enterpriseServiceList) {
			this.enterpriseServiceList = enterpriseServiceList;
		}

	ArrayList<EnterprisePatent> enterprisePatentList;
		public ArrayList<EnterprisePatent> getEnterprisePatentList() {
			return enterprisePatentList;
		}
		public void setEnterprisePatentList(
				ArrayList<EnterprisePatent> enterprisePatentList) {
			this.enterprisePatentList = enterprisePatentList;
		}
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	public void load(String globalCom) throws Exception {
		
		this.setGlobalCom(globalCom);
		if(metaworksContext == null)
			metaworksContext = new MetaworksContext();
		
		Enterprise findEnterprise = new Enterprise();
		IEnterprise iEnterprise = findEnterprise.findEnterprise(this.getGlobalCom());
		
		if(iEnterprise.size() == 0) {
			this.getMetaworksContext().setWhen(NEW);
			
			enterprise = new Enterprise();
			
			enterpriseServiceList = new ArrayList<EnterpriseService>();
			enterpriseServiceList.add(new EnterpriseService());
			
			enterprisePatentList = new ArrayList<EnterprisePatent>();
			enterprisePatentList.add(new EnterprisePatent());
			
		}
		else {
			this.getMetaworksContext().setWhen(MODIFY);
			
			// enterprise
			while(iEnterprise.next()) {
				enterprise = new Enterprise();
				enterprise.copyFrom(iEnterprise);
			}
			
			// service
			enterpriseServiceList = new ArrayList<EnterpriseService>();
			
			EnterpriseService findService = new EnterpriseService();
			IEnterpriseService iEnterpriseService = findService.findEnterpriseService(this.getGlobalCom());
			while(iEnterpriseService.next()) {
				EnterpriseService enterpriseService = new EnterpriseService();
				enterpriseService.copyFrom(iEnterpriseService);
				enterpriseServiceList.add(enterpriseService);
			}
			
			// patent
			enterprisePatentList = new ArrayList<EnterprisePatent>();
			
			EnterprisePatent findPatent = new EnterprisePatent();
			IEnterprisePatent iEnterprisePatent = findPatent.findEnterprisePatent(this.getGlobalCom());
			while(iEnterprisePatent.next()) {
				EnterprisePatent enterprisePatent = new EnterprisePatent();
				enterprisePatent.copyFrom(iEnterprisePatent);
				enterprisePatentList.add(enterprisePatent);
			}
			
		}
		
	}
	
	@ServiceMethod(callByContent=true)
	public Object addService() throws Exception {
		this.getEnterpriseServiceList().add(new EnterpriseService());
		return this;
	}
	
	@ServiceMethod(callByContent=true)
	public Object addPatent() throws Exception {
		this.getEnterprisePatentList().add(new EnterprisePatent());
		
		return this;
	}
	
	@ServiceMethod(callByContent=true, needToConfirm=true)
	public Object save() throws Exception {
		
		// enterprise 정보 save 추가 2014_03_12
		if(NEW.equals(this.getMetaworksContext().getWhen())) {
			this.getEnterprise().setGlobalCom(this.getGlobalCom());
			this.getEnterprise().createDatabaseMe();
			
			for(int i = 0; i < this.getEnterpriseServiceList().size(); i++) {
				this.getEnterpriseServiceList().get(i).setId(new Long(this.getEnterprisePatentList().get(i).createNewNotiSettingId()));
				this.getEnterpriseServiceList().get(i).setGlobalCom(this.getGlobalCom());
				this.getEnterpriseServiceList().get(i).createDatabaseMe();
			}
			
			for(int i = 0; i < this.getEnterprisePatentList().size(); i++) {
				this.getEnterprisePatentList().get(i).setId(new Long(this.getEnterprisePatentList().get(i).createNewNotiSettingId()));
				this.getEnterprisePatentList().get(i).setGlobalCom(this.getGlobalCom());
				this.getEnterprisePatentList().get(i).createDatabaseMe();
			}
			
		} else {
			this.getEnterprise().syncToDatabaseMe();
			
			for(int i = 0; i < this.getEnterpriseServiceList().size(); i++) {
				this.getEnterpriseServiceList().get(i).syncToDatabaseMe();
			}
			
			for(int i = 0; i < this.getEnterprisePatentList().size(); i++) {
				this.getEnterprisePatentList().get(i).syncToDatabaseMe();
			}
			
		}
		
		return new Remover(new ModalWindow()); 
		
	}
	
}
