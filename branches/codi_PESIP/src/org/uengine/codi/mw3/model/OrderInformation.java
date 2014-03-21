package org.uengine.codi.mw3.model;

import java.util.Date;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;

public class OrderInformation extends Database<IOrderInformation> implements IOrderInformation {
	
	Long number;
		public Long getNumber() {
			return number;
		}
		public void setNumber(Long number) {
			this.number = number;
		}

	String projectname;
		public String getProjectname() {
			return projectname;
		}
		public void setProjectname(String projectname) {
			this.projectname = projectname;
		}
	
	String industrypart;
		public String getIndustrypart() {
			return industrypart;
		}
		public void setIndustrypart(String industrypart) {
			this.industrypart = industrypart;
		}
		
	String status;
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
	
	Date regdate;
		public Date getRegdate() {
			return regdate;
		}
		public void setRegdate(Date regdate) {
			this.regdate = regdate;
		}
	
	String servicepart;
		public String getServicepart() {
			return servicepart;
		}
		public void setServicepart(String servicepart) {
			this.servicepart = servicepart;
		}
	
	String countryname;
		public String getCountryname() {
			return countryname;
		}
		public void setCountryname(String countryname) {
			this.countryname = countryname;
		}
		
	String countrycode;
		public String getCountrycode() {
			return countrycode;
		}
		public void setCountrycode(String countrycode) {
			this.countrycode = countrycode;
		}
		
	String lat;
		public String getLat() {
			return lat;
		}
		public void setLat(String lat) {
			this.lat = lat;
		}
	
	String lng;
		public String getLng() {
			return lng;
		}
		public void setLng(String lng) {
			this.lng = lng;
		}
		
	Long count;
		public Long getCount() {
			return count;
		}
		public void setCount(Long count) {
			this.count = count;
		}
		
	String dueDateTime;
		public String getDueDateTime() {
			return dueDateTime;
		}
		public void setDueDateTime(String dueDateTime) {
			this.dueDateTime = dueDateTime;
		}
		
// 한번에 모든 값을 처리하는 것을 알게되면 사용...주석처리
		
//	public IOrderInformation findOrderInfo() throws Exception {
//			
//		StringBuffer sb = new StringBuffer();
//		sb.append("select @ROWNUM := @ROWNUM + 1 AS number, b.project_name as projectname, ic.code_name as industrypart, sc.code_name as servicepart, st.code_name as status, b.reg_date as regdate, c.alpha2 as countrycode");
//		sb.append(" from pseip_bidding as b, pseip_code as ic, pseip_code as sc, pseip_code as st, (SELECT @ROWNUM := 0) as R, pseip_country as c");
//		sb.append(" where b.industry_category = ic.code");
//		sb.append(" and b.service_category = sc.code");
//		sb.append(" and b.project_status = st.code");
//		sb.append(" and b.country_code = c.country_code");
//		
//		IOrderInformation orderInformation = (IOrderInformation) sql(IOrderInformation.class, sb.toString());
//		orderInformation.select();
//		
//		return orderInformation;
//		
//	}
	
	// 중복제거 된 결과 값. 마커를 그리기 위해..
	public IOrderInformation findDistinctOrderInfo() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct name_ko as countryname, a.country_code as countrycode, b.lat, b.lng, count(a.country_code) as count");
		sb.append(" from pseip_bidding as a, pseip_country as b");
		sb.append(" where a.country_code = b.country_code");
		sb.append(" group by a.country_code, b.country_code");
		
		IOrderInformation orderInformation = (IOrderInformation) sql(IOrderInformation.class, sb.toString());
		orderInformation.select();
		
		return orderInformation;
	}
	 
	
	// 세부 정보를 보기 위해 countrycode를 통해 클릭시 해당 국가의 발주 정보를 가져온다.
	public IOrderInformation loadDetailOrderInfo() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select @ROWNUM := @ROWNUM + 1 as number, b.project_name as projectname, ic.code_name as industrypart, sc.code_name as servicepart, st.code_name as status, b.reg_date as regdate, c.name_ko as countryname");
		sb.append(" from pseip_bidding as b, pseip_code as ic, pseip_code as sc, pseip_code as st, (SELECT @ROWNUM := 0) as R, pseip_country as c");
		sb.append(" where b.industry_category = ic.code");
		sb.append(" 	and b.service_category = sc.code");
		sb.append(" 	and b.project_status = st.code");
		sb.append(" 	and b.country_code = c.country_code");
		sb.append(" 	and c.country_code = ?countrycode");
		
		
		IOrderInformation orderInformation = (IOrderInformation) sql(IOrderInformation.class, sb.toString());
		orderInformation.setCountrycode(this.getCountrycode());
		orderInformation.select();
		
		return orderInformation;
	}
	
	// 발주정보의 모든 리스트를 가져와야 한다. 가져오는 쿼리문
	public IOrderInformation loadPastOrderInfo() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct name_ko as countryname, count(a.country_code) as count, a.project_name as projectname, a.reg_date as regdate");
		sb.append(" from pseip_bidding as a, pseip_country as b");
		sb.append(" where a.country_code = b.country_code");
		sb.append(" group by a.country_code, b.country_code");
		
		IOrderInformation pastOrderInformation = (IOrderInformation) sql(IOrderInformation.class, sb.toString());
		pastOrderInformation.select();
		
		return pastOrderInformation;
	}
	
}
