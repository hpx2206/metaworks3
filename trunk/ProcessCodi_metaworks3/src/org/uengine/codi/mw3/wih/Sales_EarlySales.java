package org.uengine.codi.mw3.wih;

import java.io.Serializable;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;

public class Sales_EarlySales implements ITool , Serializable {
	String instanceId;
	@Hidden
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}
		
    MetaworksContext metaworksContext;
        public MetaworksContext getMetaworksContext(){
            return this.metaworksContext;
        }
        public void setMetaworksContext(MetaworksContext metaworksContext){
            this.metaworksContext = metaworksContext;
        }
     
        
        
    @Override
	public void onLoad() {
        this.metaworksContext = new MetaworksContext();
        metaworksContext.setWhen(MetaworksContext.WHEN_NEW);
		// TODO Auto-generated method stub
        
	}

	@Override
	public void beforeComplete() {
		// TODO Auto-generated method stub
		//setCompYnVar(compYn == true ? "Y" : "N");
	}

	@Override
	public void afterComplete() {
		// TODO Auto-generated method stub	
		
	}
	
	
	String serviceName;
	
	String base_companyName;
	
	String base_position;
	
	String base_email;
	
	String base_mobile;
	
	String base_fax;
	
	String dist_company;
	
	String contact_point;
	
	String keyman_info;
	
	String sales_right_person;
	
	String tech_right_person;
	
	String etc;
	
	@Face(displayName="$SALESEARLYSALES_SERVICENAME")
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	@Face(displayName="$SALESEARLYSALES_BASECOMPANY_NAME")
	public String getBase_companyName() {
		return base_companyName;
	}
	public void setBase_companyName(String base_companyName) {
		this.base_companyName = base_companyName;
	}
	
	@Face(displayName="$SALESEARLYSALES_CONTACT")
	public String getBase_position() {
		return base_position;
	}
	public void setBase_position(String base_position) {
		this.base_position = base_position;
	}
	
	@Face(displayName="$SALESEARLYSALES_EMAIL")
	public String getBase_email() {
		return base_email;
	}
	public void setBase_email(String base_email) {
		this.base_email = base_email;
	}
	
	@Face(displayName="$SALESEARLYSALES_MOBILE_TEL")
	public String getBase_mobile() {
		return base_mobile;
	}
	public void setBase_mobile(String base_mobile) {
		this.base_mobile = base_mobile;
	}
	
	@Face(displayName="$SALESEARLYSALES_FAX")
	public String getBase_fax() {
		return base_fax;
	}
	public void setBase_fax(String base_fax) {
		this.base_fax = base_fax;
	}
	
	@Face(displayName="$SALESEARLYSALES_COMPANY_TYPE")
	public String getDist_company() {
		return dist_company;
	}
	public void setDist_company(String dist_company) {
		this.dist_company = dist_company;
	}
	
	@Face(displayName="$SALESEARLYSALES_CONTACT_POINT")
	public String getContact_point() {
		return contact_point;
	}
	public void setContact_point(String contact_point) {
		this.contact_point = contact_point;
	}
	
	@Face(displayName="$SALESEARLYSALES_KEY_MAN")
	public String getKeyman_info() {
		return keyman_info;
	}
	public void setKeyman_info(String keyman_info) {
		this.keyman_info = keyman_info;
	}
	
	@Face(displayName="$SALESEARLYSALES_SALES_CONTACT")
	public String getSales_right_person() {
		return sales_right_person;
	}
	public void setSales_right_person(String sales_right_person) {
		this.sales_right_person = sales_right_person;
	}
	
	@Face(displayName="$SALESEARLYSALES_TECHNICAL_SUPPORT")
	public String getTech_right_person() {
		return tech_right_person;
	}
	public void setTech_right_person(String tech_right_person) {
		this.tech_right_person = tech_right_person;
	}
	
	@Face(displayName="$SALESEARLYSALES_COMMENT")
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
		
}
