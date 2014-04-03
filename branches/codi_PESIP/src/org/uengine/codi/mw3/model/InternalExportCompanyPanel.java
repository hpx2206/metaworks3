package org.uengine.codi.mw3.model;

public class InternalExportCompanyPanel {
	
	public InternalExportCompanyPanel() throws Exception{
	}
	
	CompanyList companyList;
		public CompanyList getCompanyList() {
			return companyList;
		}
	
		public void setCompanyList(CompanyList companyList) {
			this.companyList = companyList;
		}

	InternalCompanyDetail internalCompanyDetail;
		public InternalCompanyDetail getInternalCompanyDetail() {
			return internalCompanyDetail;
		}
	
		public void setInternalCompanyDetail(InternalCompanyDetail internalCompanyDetail) {
			this.internalCompanyDetail = internalCompanyDetail;
		}

	InternalCompanyPaging companyPaging;
		public InternalCompanyPaging getCompanyPaging() {
			return companyPaging;
		}
	
		public void setCompanyPaging(InternalCompanyPaging companyPaging) {
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
		companyList.getMetaworksContext().setHow("InternalCompany");
		companyList.load();
		internalCompanyDetail = new InternalCompanyDetail();
		companyPaging = new InternalCompanyPaging();
		companyPaging.setStartPage(1);
		companyPaging.setCurrentPage(1);
		companyPaging.load();
		companySearch = new InternalCompanySearch();
		companySearch.load();
	}

}
