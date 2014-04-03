package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ToAppend;
import org.metaworks.annotation.Id;
import org.metaworks.dao.Database;

public class ExportOISCompany extends Database<IExportOISCompany> implements IExportOISCompany{

		Long id; //번호
			@Id
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			
		String comp_name; //기업명
		String sector; //업종
		String entered_con; //진출국가
		String entered_reg; //진출지역
		String mcomp_addr; //주소
		String mcomp_tel; //전화번호
		String fax; //팩스
		String email; //메일
		String homepage; //홈페이지
		String entered_year; //진출년도
		String entry_form; //진출형태
		String oversea_rep; //해외대표
		String tel;
		String mcomp_name; //모기업명
		String areas;
		
		public String getAreas() {
			return areas;
		}
		public void setAreas(String areas) {
			this.areas = areas;
		}
		public String getEntered_year() {
			return entered_year;
		}
		public void setEntered_year(String entered_year) {
			this.entered_year = entered_year;
		}
		public String getEntry_form() {
			return entry_form;
		}
		public void setEntry_form(String entry_form) {
			this.entry_form = entry_form;
		}
		public String getOversea_rep() {
			return oversea_rep;
		}
		public void setOversea_rep(String oversea_rep) {
			this.oversea_rep = oversea_rep;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getMcomp_name() {
			return mcomp_name;
		}
		public void setMcomp_name(String mcomp_name) {
			this.mcomp_name = mcomp_name;
		}

		int num;
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		
		public String getComp_name() {
			return comp_name;
		}
		public void setComp_name(String comp_name) {
			this.comp_name = comp_name;
		}

		public String getSector() {
			return sector;
		}
		public void setSector(String sector) {
			this.sector = sector;
		}

		public String getEntered_con() {
			return entered_con;
		}
		public void setEntered_con(String entered_con) {
			this.entered_con = entered_con;
		}

		public String getEntered_reg() {
			return entered_reg;
		}
		public void setEntered_reg(String entered_reg) {
			this.entered_reg = entered_reg;
		}

		public String getMcomp_addr() {
			return mcomp_addr;
		}
		public void setMcomp_addr(String mcomp_addr) {
			this.mcomp_addr = mcomp_addr;
		}

		public String getMcomp_tel() {
			return mcomp_tel;
		}
		public void setMcomp_tel(String mcomp_tel) {
			this.mcomp_tel = mcomp_tel;
		}

		public String getFax() {
			return fax;
		}
		public void setFax(String fax) {
			this.fax = fax;
		}

		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}

		public String getHomepage() {
			return homepage;
		}
		public void setHomepage(String homepage) {
			this.homepage = homepage;
		}

		int currentPage;
			public int getCurrentPage() {
				return currentPage;
			}
			public void setCurrentPage(int currentPage) {
				this.currentPage = currentPage;
			}

		public IExportOISCompany load() throws Exception{
			StringBuffer sb = new StringBuffer();
			if(getCurrentPage() == 0){
				setCurrentPage(1);
			}
			sb.append("select * from ( select @rnum := @rnum +1 as rownum, oiscompany.*" +
					" from (select @rnum := 0) r, oiscompany order by id) result" +
					" where rownum between (1+("+getCurrentPage()+"+-1)*10) and ("+getCurrentPage()+"*10)");
			IExportOISCompany iExportOISCompany = (IExportOISCompany) sql(IExportOISCompany.class, sb.toString());
			iExportOISCompany.select();
			
			return iExportOISCompany;
		}
		
		public IExportOISCompany getRegions() throws Exception{
			StringBuffer sb = new StringBuffer();
			sb.append("select distinct(sector) from oiscompany");
			IExportOISCompany iExportOISCompany = (IExportOISCompany) sql(IExportOISCompany.class, sb.toString());
			iExportOISCompany.select();
			
			return iExportOISCompany;
		}
		
		public Object[] loadCompany() throws Exception{
			InternalCompanyDetail companyDetailList = new InternalCompanyDetail();
			companyDetailList.setId(this.getId());
			companyDetailList.setComp_name(this.getComp_name());
			companyDetailList.setEmail(this.getEmail());
			companyDetailList.setEntered_con(this.getEntered_con());
			companyDetailList.setEntered_reg(this.getEntered_reg());
			companyDetailList.setFax(this.getFax());
			companyDetailList.setHomepage(this.getHomepage());
			companyDetailList.setSector(this.getSector());
			companyDetailList.setMcomp_addr(this.getMcomp_addr());
			companyDetailList.setMcomp_tel(this.getMcomp_tel());
			companyDetailList.setMcomp_name(this.getMcomp_name());
			companyDetailList.setEntered_year(this.getEntered_year());
			companyDetailList.setEntry_form(this.getEntry_form());
			companyDetailList.setOversea_rep(this.getOversea_rep());
			companyDetailList.setTel(this.getTel());
			companyDetailList.setAreas(this.getAreas());
			return new Object[]{new Refresh(companyDetailList)};
		}
		
		public Object[] loadCompanyByNumber() throws Exception{
			// TODO Auto-generated method stub
			StringBuffer sb = new StringBuffer();
			
			sb.append("select * from ( select @rnum := @rnum +1 as rownum, oiscompany.*" +
					" from (select @rnum := 0) r, oiscompany order by id) result" +
					" where rownum between (1+("+getCurrentPage()+"+-1)*10) and ("+getCurrentPage()+"*10)");
			IExportOISCompany iExportOISCompany = (IExportOISCompany) sql(IExportOISCompany.class, sb.toString());
			iExportOISCompany.select();
			
			CompanyList companyList = new CompanyList();
			ExportOISCompany companyData = new ExportOISCompany();
			while(iExportOISCompany.next()){
				companyData.setMetaworksContext(new MetaworksContext());
				companyData.getMetaworksContext().setHow("ExportCompany");
				companyData.copyFrom(iExportOISCompany);
				companyList.setiExportOISCompany(new ArrayList<IExportOISCompany>());
				companyList.getiExportOISCompany().add(companyData);
			}
			
			return new Object[]{new Refresh(companyList)};
		}
		
		public int getTotalPage() throws Exception{
			int totalPage = 0;
			StringBuffer sb = new StringBuffer();
			sb.append("select round(count(*)/10) as num from oiscompany");
			IExportOISCompany iExportOISCompany =(IExportOISCompany) sql(IExportOISCompany.class, sb.toString());
			iExportOISCompany.select();
			while(iExportOISCompany.next()){
				totalPage = iExportOISCompany.getNum();
			}
			return totalPage;
		}
}
