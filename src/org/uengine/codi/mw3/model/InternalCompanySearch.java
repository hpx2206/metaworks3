package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.dao.Database;

public class InternalCompanySearch{

	SelectBox region;
		public SelectBox getRegion() {
			return region;
		}
	
		public void setRegion(SelectBox region) {
			this.region = region;
		}

	ArrayList<IExportOISCompany> businessArea;
		public ArrayList<IExportOISCompany> getBusinessArea() {
			return businessArea;
		}
	
		public void setBusinessArea(ArrayList<IExportOISCompany> businessArea) {
			this.businessArea = businessArea;
		}
		
	public void getRegions() throws Exception{
		IExportOISCompany exportOISCompany = new ExportOISCompany();

		exportOISCompany = (IExportOISCompany) Database.sql(IExportOISCompany.class, "select distinct(sector) from oiscompany");
		exportOISCompany.select();
		
		region = new SelectBox();
		
		if(exportOISCompany.size() > 0){
			while(exportOISCompany.next()){
				String name = exportOISCompany.getSector();
				region.add(name, name);
			}
		}
	}
	
	@ServiceMethod(callByContent=true)	
	public IExportOISCompany choose() throws Exception{
		int i=0;
		for(String value : getRegion().getOptionValues()){
			if(value.equals(getRegion().getSelected())){
				
				return null;
			}
			i++;
		}
		return null;
	}
		
//		exportOISCompany = exportOISCompany.getRegions();
//		region = new ArrayList<IExportOISCompany>();
//		while(exportOISCompany.next()){
//			ExportOISCompany companyData = new ExportOISCompany();
//			companyData.copyFrom(exportOISCompany);
//			this.getRegion().add(companyData);
//		}
	
	public void load() throws Exception{
		getRegions();
	}
}
