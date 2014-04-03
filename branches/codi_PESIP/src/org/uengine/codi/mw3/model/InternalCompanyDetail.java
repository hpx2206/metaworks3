package org.uengine.codi.mw3.model;


public class InternalCompanyDetail {

	public InternalCompanyDetail(){
	}

		
	Long id;
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
		String fax; //팩스
		String email; //메일
		String homepage; //홈페이지
		String mcomp_name; //모기업명
		String mcomp_tel; //모기업 전화번호
		
		String entered_year; //진출년도
		String entry_form; //진출형태
		String oversea_rep; //해외대표
		String tel;
		String areas;
		
		
		
		public String getAreas() {
			return areas;
		}

		public void setAreas(String areas) {
			this.areas = areas;
		}

		public String getMcomp_name() {
			return mcomp_name;
		}

		public void setMcomp_name(String mcomp_name) {
			this.mcomp_name = mcomp_name;
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

	

}
