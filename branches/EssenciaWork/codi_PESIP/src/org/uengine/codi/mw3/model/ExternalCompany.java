package org.uengine.codi.mw3.model;

import org.metaworks.Refresh;
import org.metaworks.dao.Database;

public class ExternalCompany extends  Database<IExternalCompany> implements IExternalCompany{

	Long id;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}

	String company_name;
		public String getCompany_name() {
			return company_name;
		}
		public void setCompany_name(String company_name) {
			this.company_name = company_name;
		}

	String category;
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}

	String addr;
		public String getAddr() {
			return addr;
		}
		public void setAddr(String addr) {
			this.addr = addr;
		}

	String city;
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}

	String country;
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		
	int num;
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		
	String fax_num;
		public String getFax_num() {
			return fax_num;
		}
		public void setFax_num(String fax_num) {
			this.fax_num = fax_num;
		}

	String phone_num;
		public String getPhone_num() {
			return phone_num;
		}
		public void setPhone_num(String phone_num) {
			this.phone_num = phone_num;
		}
		
	String homepage;
		public String getHomepage() {
			return homepage;
		}
		public void setHomepage(String homepage) {
			this.homepage = homepage;
		}

	String business_type;
		public String getBusiness_type() {
			return business_type;
		}
		public void setBusiness_type(String business_type) {
			this.business_type = business_type;
		}

	String related_items;
		public String getRelated_items() {
			return related_items;
		}
		public void setRelated_items(String related_items) {
			this.related_items = related_items;
		}

	String ceo_name;
		public String getCeo_name() {
			return ceo_name;
		}
		public void setCeo_name(String ceo_name) {
			this.ceo_name = ceo_name;
		}
		
	int currentPage;
		public int getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}		
	
	
	public IExternalCompany load() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		if(getCurrentPage() == 0){
			setCurrentPage(1);
		}
		sb.append("select * from ( select @rnum := @rnum +1 as rownum, pseip_b2g.*" +
				" from (select @rnum := 0) r, pseip_b2g order by id) result" +
				" where rownum between (1+("+getCurrentPage()+"+-1)*10) and ("+getCurrentPage()+"*10)");
		IExternalCompany iExternalCompany = (IExternalCompany) sql(IExternalCompany.class, sb.toString());
		iExternalCompany.select();
		
		return iExternalCompany;
	}
	public Object[] loadCompany() throws Exception {
		ExternalCompanyDetail companyDetailList = new ExternalCompanyDetail();
		companyDetailList.setId(this.getId());
		companyDetailList.setCompany_name(this.getCompany_name());
		companyDetailList.setFax(this.getFax_num());
		companyDetailList.setAddr(this.getAddr());
		companyDetailList.setPhone(this.getPhone_num());
		companyDetailList.setCountry(this.getCountry());
		companyDetailList.setCity(this.getCity());
		companyDetailList.setHomepage(this.getHomepage());
		companyDetailList.setBusiness_type(this.getBusiness_type());
		companyDetailList.setRelated_items(this.getRelated_items());
		companyDetailList.setCeo_name(this.getCeo_name());
		companyDetailList.setPhone_num(this.getPhone_num());
		return new Object[]{new Refresh(companyDetailList)};
	}
	
	public int getTotalPage() throws Exception{
		int totalPage = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("select round(count(*)/10) as num from pseip_b2g");
		IExternalCompany iExternalCompany =(IExternalCompany) sql(IExternalCompany.class, sb.toString());
		iExternalCompany.select();
		while(iExternalCompany.next()){
			totalPage = iExternalCompany.getNum();
		}
		return totalPage;
	}
}
