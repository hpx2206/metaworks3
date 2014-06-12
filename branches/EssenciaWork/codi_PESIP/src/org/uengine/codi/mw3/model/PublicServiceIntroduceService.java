package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.dao.Database;

public class PublicServiceIntroduceService extends Database<IPublicServiceIntroduceService> implements IPublicServiceIntroduceService {

	Long number;
		@Id
		public Long getNumber() {
			return number;
		}
		public void setNumber(Long number) {
			this.number = number;
		}

	String businessName;
		public String getBusinessName() {
			return businessName;
		}
		public void setBusinessName(String businessName) {
			this.businessName = businessName;
		}

	String year;
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}

	String enterpriseName;
		public String getEnterpriseName() {
			return enterpriseName;
		}
		public void setEnterpriseName(String enterpriseName) {
			this.enterpriseName = enterpriseName;
		}

	String etc;
		public String getEtc() {
			return etc;
		}
		public void setEtc(String etc) {
			this.etc = etc;
		}
		
	Long itemId;
		public Long getItemId() {
			return itemId;
		}
		public void setItemId(Long itemId) {
			this.itemId = itemId;
		}
		
	public IPublicServiceIntroduceService loadService(Long itemId) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from public_introduce_service");
		sb.append(" where itemId=?itemId");
		
		IPublicServiceIntroduceService services = (IPublicServiceIntroduceService) sql(IPublicServiceIntroduceService.class, sb.toString());
		services.setItemId(itemId);
		services.select();
		
		return services;
		
	}
	
}
