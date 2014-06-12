package org.uengine.codi.mw3.model;

import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;

public class ExportAgent extends Database<IExportAgent> implements IExportAgent{

	Long id;
	String region;
	String chairName;
	String companyName;
	String businessArea;
	String tel;
	String email;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getChairName() {
		return chairName;
	}
	public void setChairName(String chairName) {
		this.chairName = chairName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBusinessArea() {
		return businessArea;
	}
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	int num;
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		
	int currentPage;
		public int getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}	
		
	public IExportAgent load() throws Exception{
		StringBuffer sb = new StringBuffer();
		if(getCurrentPage() == 0){
			setCurrentPage(1);
		}
		sb.append("select * from ( select @rnum := @rnum +1 as rownum, pseip_inke_chair.*" +
				" from (select @rnum := 0) r, pseip_inke_chair order by id) result" +
				" where rownum between (1+("+getCurrentPage()+"+-1)*10) and ("+getCurrentPage()+"*10)");
		IExportAgent iExportAgent = (IExportAgent) sql(IExportAgent.class, sb.toString());
		iExportAgent.select();
		
		return iExportAgent;
		
	}
	
	public int getTotalPage() throws Exception{
		int totalPage = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("select round(count(*)/10) as num from pseip_inke_chair");
		IExportAgent iExportAgent =(IExportAgent) sql(IExportAgent.class, sb.toString());
		iExportAgent.select();
		while(iExportAgent.next()){
			totalPage = iExportAgent.getNum();
		}
		return totalPage;
	}
	
	public Object[] loadCompany() throws Exception{
		ExportAgentDetail exportAgentDetail = new ExportAgentDetail();
		exportAgentDetail.setCompanyName(this.getCompanyName());
		exportAgentDetail.setEmail(this.getEmail());
		exportAgentDetail.setRegion(this.getRegion());
		exportAgentDetail.setChairName(this.getChairName());
		exportAgentDetail.setBusinessArea(this.getBusinessArea());
		exportAgentDetail.setTel(this.getTel());
		return new Object[]{new Refresh(exportAgentDetail)};
		
	}
}
