package org.uengine.codi.mw3.model;

public class ExportAgentPanel {
	public ExportAgentPanel() throws Exception{
	}
	
	CompanyList companyList;
		public CompanyList getCompanyList() {
			return companyList;
		}
	
		public void setCompanyList(CompanyList companyList) {
			this.companyList = companyList;
		}

	ExportAgentDetail exportAgentDetail;
		public ExportAgentDetail getExportAgentDetail() {
			return exportAgentDetail;
		}
	
		public void setExportAgentDetail(ExportAgentDetail exportAgentDetail) {
			this.exportAgentDetail = exportAgentDetail;
		}

	ExportAgentPaging companyPaging;
		public ExportAgentPaging getCompanyPaging() {
			return companyPaging;
		}
	
		public void setCompanyPaging(ExportAgentPaging companyPaging) {
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
		companyList.getMetaworksContext().setHow("ExportAgent");
		companyList.load();
		exportAgentDetail = new ExportAgentDetail();
		companySearch = new InternalCompanySearch();
		companySearch.load();
		companyPaging = new ExportAgentPaging();
		companyPaging.setStartPage(1);
		companyPaging.setCurrentPage(1);
		companyPaging.load();
	}		
}
