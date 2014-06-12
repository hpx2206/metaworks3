package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.ServiceMethod;

public class CompanyList implements ContextAware  {
	
	public CompanyList(){
		setMetaworksContext(new MetaworksContext());
//		setiExportOISCompany(new ArrayList<IExportOISCompany>());
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	int currentPage;
		public int getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}

	ArrayList<IExportOISCompany> iExportOISCompany;
		public ArrayList<IExportOISCompany> getiExportOISCompany() {
			return iExportOISCompany;
		}
		public void setiExportOISCompany(ArrayList<IExportOISCompany> iExportOISCompany) {
			this.iExportOISCompany = iExportOISCompany;
		}
	
	ArrayList<IExternalCompany> iExternalCompany;
		public ArrayList<IExternalCompany> getiExternalCompany() {
			return iExternalCompany;
		}
		public void setiExternalCompany(ArrayList<IExternalCompany> iExternalCompany) {
			this.iExternalCompany = iExternalCompany;
		}
		
	ArrayList<IExportAgent> iExportAgent;
		public ArrayList<IExportAgent> getiExportAgent() {
			return iExportAgent;
		}
		public void setiExportAgent(ArrayList<IExportAgent> iExportAgent) {
			this.iExportAgent = iExportAgent;
		}
		
	IExportOISCompany	iExportCompany;
		public IExportOISCompany getiExportCompany() {
			return iExportCompany;
		}
		public void setiExportCompany(IExportOISCompany iExportCompany) {
			this.iExportCompany = iExportCompany;
		}
		
	public void load() throws Exception{
		if("InternalCompany".equals(this.getMetaworksContext().getHow())){
			IExportOISCompany exportOISCompany = new ExportOISCompany();
			exportOISCompany.setCurrentPage(getCurrentPage());
			exportOISCompany =  exportOISCompany.load();
			iExportOISCompany = new ArrayList<IExportOISCompany>();
			while(exportOISCompany.next()){
				ExportOISCompany companyData = new ExportOISCompany();
				companyData.setMetaworksContext(new MetaworksContext());
				companyData.getMetaworksContext().setHow("InternalCompany");
				companyData.copyFrom(exportOISCompany);
				this.getiExportOISCompany().add(companyData);
			}
		}else if("ExternalCompany".equals(this.getMetaworksContext().getHow())){
			IExternalCompany externalCompany = new ExternalCompany();
			externalCompany.setCurrentPage(getCurrentPage());
			externalCompany = externalCompany.load();
			iExternalCompany = new ArrayList<IExternalCompany>();
			while(externalCompany.next()){
				ExternalCompany companyData = new ExternalCompany();
				companyData.setMetaworksContext(new MetaworksContext());
				companyData.getMetaworksContext().setHow("ExternalCompany");
				companyData.copyFrom(externalCompany);
				this.getiExternalCompany().add(companyData);
			}
		}else if("ExportAgent".equals(this.getMetaworksContext().getHow())){
			IExportAgent iExportAgentMember = new ExportAgent();
			iExportAgentMember.setCurrentPage(getCurrentPage());
			iExportAgentMember =iExportAgentMember.load();
			iExportAgent = new ArrayList<IExportAgent>();
			while(iExportAgentMember.next()){
				ExportAgent exportAgent = new ExportAgent();
				exportAgent.setMetaworksContext(new MetaworksContext());
				exportAgent.getMetaworksContext().setHow("ExportAgent");
				exportAgent.copyFrom(iExportAgentMember);
				this.getiExportAgent().add(exportAgent);
			}
		}
	}
			
		
}
