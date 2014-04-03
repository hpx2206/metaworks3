package org.uengine.codi.mw3.model;

public class ExternalExportCompanyPanel {
	public ExternalExportCompanyPanel() throws Exception{
	}
	
	CompanyList companyList;
		public CompanyList getCompanyList() {
			return companyList;
		}
	
		public void setCompanyList(CompanyList companyList) {
			this.companyList = companyList;
		}
		
	ExternalCompanyDetail externalCompanyDetail;
		public ExternalCompanyDetail getExternalCompanyDetail() {
			return externalCompanyDetail;
		}
	
		public void setExternalCompanyDetail(ExternalCompanyDetail externalCompanyDetail) {
			this.externalCompanyDetail = externalCompanyDetail;
		}
		
	ExternalCompanyPaging companyPaging;
		public ExternalCompanyPaging getCompanyPaging() {
			return companyPaging;
		}
	
		public void setCompanyPaging(ExternalCompanyPaging companyPaging) {
			this.companyPaging = companyPaging;
		}
		
	InternalCompanySearch companySearch;
		public InternalCompanySearch getCompanySearch() {
			return companySearch;
		}
		public void setCompanySearch(InternalCompanySearch companySearch) {
			this.companySearch = companySearch;
		}	
	
	public void load() throws Exception{
		companyList = new CompanyList();
		companyList.getMetaworksContext().setHow("ExternalCompany");
		companyList.load();
		externalCompanyDetail = new ExternalCompanyDetail();
		companySearch = new InternalCompanySearch();
		companySearch.load();
		companyPaging = new ExternalCompanyPaging();
		companyPaging.setStartPage(1);
		companyPaging.setCurrentPage(1);
		companyPaging.load();
	}
}
