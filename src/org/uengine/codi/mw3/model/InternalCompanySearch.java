package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.metaworks.dao.Database;

//@Face(ejsPath="dwr/metaworks/genericfaces/FormFace.ejs", 
//options={"fieldOrder"}, values={"region"})
public class InternalCompanySearch{

	public InternalCompanySearch(){
		setMetaworksContext(new MetaworksContext());
	}
	SelectBox region;
		@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT})
		public SelectBox getRegion() {
			return region;
		}
		public void setRegion(SelectBox region) {
			this.region = region;
		}
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	SelectBox businessArea;
		@Available(when={MetaworksContext.WHEN_NEW, MetaworksContext.WHEN_EDIT})
		public SelectBox getBusinessArea() {
			return businessArea;
		}
		public void setBusinessArea(SelectBox businessArea) {
			this.businessArea = businessArea;
		}
		
	public void getRegions() throws Exception{
		IExportOISCompany exportOISCompany = new ExportOISCompany();

		exportOISCompany = (IExportOISCompany) Database.sql(IExportOISCompany.class, "select distinct(sector) from oiscompany");
		exportOISCompany.select();
		
		if(region == null)
			region = new SelectBox();
		
		if(exportOISCompany.size() > 0){
			while(exportOISCompany.next()){
				String name = exportOISCompany.getSector();
				region.add(name, name);
			}
		}
	}
	
	public void getCountry() throws Exception{
		IExportOISCompany exportOISCompany = new ExportOISCompany();
		exportOISCompany = (IExportOISCompany) Database.sql(IExportOISCompany.class,"select distinct(entered_con) from oiscompany");
		exportOISCompany.select();
		
		if(businessArea == null)
			businessArea = new SelectBox();
		
		if(exportOISCompany.size() > 0){
			while(exportOISCompany.next()){
				String name = exportOISCompany.getEntered_con();
				businessArea.add(name, name);
			}
		}
	}
	
	@ServiceMethod(callByContent=true)	
	public IExportOISCompany choose() throws Exception{
		int i=0;
		for(String value : getBusinessArea().getOptionValues()){
			if(value.equals(getBusinessArea().getSelected())){
				
				return null;
			}
			i++;
		}
		
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
		getCountry();
	}

}
