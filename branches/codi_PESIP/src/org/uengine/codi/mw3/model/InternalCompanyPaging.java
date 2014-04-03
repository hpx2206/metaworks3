package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;

public class InternalCompanyPaging {
		
	final int PAGEBLOCKCNT = 10;
	
	public InternalCompanyPaging() throws Exception{
		page = new ArrayList<String>();
		setPageBlock(10);
	}
	

	int currentPageNo;
		public int getCurrentPageNo() {
			return currentPageNo;
		}
		public void setCurrentPageNo(int currentPageNo) {
			this.currentPageNo = currentPageNo;
		}
	int currentPage;
		public int getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}

	int startPage;
		public int getStartPage() {
			return startPage;
		}
		public void setStartPage(int startPage) {
			this.startPage = startPage;
		}

	ArrayList<String> page;
		public ArrayList<String> getPage() {
			return page;
		}
		public void setPage(ArrayList<String> page) {
			this.page = page;
		}
	
	int totalPage;
		public int getTotalPage() {
				return totalPage;
			}
		public void setTotalPage(int totalPage) {
				this.totalPage = totalPage;
			}

	int pageBlock;
		public int getPageBlock() {
			return pageBlock;
		}
		public void setPageBlock(int pageBlock) {
			this.pageBlock = pageBlock;
		}

	String result;
		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}

	public ArrayList<String> load() throws Exception{
			IExportOISCompany exportOISCompany = new ExportOISCompany();
			setTotalPage(exportOISCompany.getTotalPage());
		for(int i = getStartPage(); i<=getTotalPage(); i++){
			if(i >getTotalPage() ) 
				break;
			if(i<=getPageBlock()){
				page.add(""+i+"");
				setStartPage(i);
			}
			
		}
		result =page.toString();
		System.out.println(result);
		return page;
	}
	
	@ServiceMethod(callByContent=true,target=ServiceMethodContext.TARGET_APPEND)	
	public Object[] loadCompanyByNumber() throws Exception{
		CompanyList companyList = new CompanyList();
			InternalExportCompanyPanel internalExportCompanyPanel = new InternalExportCompanyPanel();
			internalExportCompanyPanel.load();
			companyList.getMetaworksContext().setHow("InternalCompany");
			
			companyList.setCurrentPage(getCurrentPageNo());
			companyList.load();
			internalExportCompanyPanel.setCompanyList(companyList);
			internalExportCompanyPanel.setCompanyPaging(this);
			
			return new Object[]{new Refresh(internalExportCompanyPanel)};
	}
	
	@ServiceMethod(callByContent=true,target=ServiceMethodContext.TARGET_APPEND)
	public Object[] next() throws Exception{
		this.getPage().removeAll(getPage());
		this.setCurrentPage(getCurrentPage() + 1);
		this.setPageBlock(getCurrentPage() * this.PAGEBLOCKCNT);
		load();
		
		return new Object[]{new Refresh(this)};
	}
	
	@ServiceMethod(callByContent=true,target=ServiceMethodContext.TARGET_APPEND)
	public Object[] previous() throws Exception{
		this.getPage().removeAll(getPage());
		this.setCurrentPage(getCurrentPage() - 1);
		this.setPageBlock(getCurrentPage() * this.PAGEBLOCKCNT);
		this.setStartPage(getStartPage() - 19);
		load();
		
		return new Object[]{new Refresh(this)};
	}
	
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] last() throws Exception{
		this.getPage().removeAll(getPage());
		this.setCurrentPage((int) Math.ceil(getTotalPage()/10)+1);
		this.setPageBlock(getCurrentPage() * this.PAGEBLOCKCNT);
		this.setStartPage(getTotalPage()-9);
		load();
		
		return new Object[]{new Refresh(this)};
	}
}
