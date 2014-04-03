package org.uengine.codi.mw3.model;

import java.util.Date;

import org.metaworks.annotation.Id;
import org.metaworks.dao.Database;

public class PseipBidding extends Database<IPseipBidding> implements IPseipBidding {
	
	String projectCode;
		@Id
		public String getProjectCode() {
			return projectCode;
		}
		public void setProjectCode(String projectCode) {
			this.projectCode = projectCode;
		}

	String projectName;
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

	String industryCategory;
		public String getIndustryCategory() {
			return industryCategory;
		}
		public void setIndustryCategory(String industryCategory) {
			this.industryCategory = industryCategory;
		}

	String serviceCategory;
		public String getServiceCategory() {
			return serviceCategory;
		}
		public void setServiceCategory(String serviceCategory) {
			this.serviceCategory = serviceCategory;
		}

	String projectStatus;
		public String getProjectStatus() {
			return projectStatus;
		}
		public void setProjectStatus(String projectStatus) {
			this.projectStatus = projectStatus;
		}

	Date dueDate;
		public Date getDueDate() {
			return dueDate;
		}
		public void setDueDate(Date dueDate) {
			this.dueDate = dueDate;
		}

	String dueDateTime;
		public String getDueDateTime() {
			return dueDateTime;
		}
		public void setDueDateTime(String dueDateTime) {
			this.dueDateTime = dueDateTime;
		}

	Date regDate;
		public Date getRegDate() {
			return regDate;
		}
		public void setRegDate(Date regDate) {
			this.regDate = regDate;
		}

	String countryCode;
		public String getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}

	String fundType;
		public String getFundType() {
			return fundType;
		}
		public void setFundType(String fundType) {
			this.fundType = fundType;
		}

	String regEmpCode;
		public String getRegEmpCode() {
			return regEmpCode;
		}
		public void setRegEmpCode(String regEmpCode) {
			this.regEmpCode = regEmpCode;
		}
		
	int count;
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		
		
	// 국가 코드와 그 국가의 발주정보 갯수 가져옴
	public IPseipBidding loadCountryOrderInfo() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct c.alpha2 as countryCode, count(c.alpha2) as count");
		sb.append(" from pseip_bidding b, pseip_country c");
		sb.append(" where b.country_code = c.country_code");
		sb.append(" group by b.country_code, c.country_code");
		
		IPseipBidding countryOrderInfo = (IPseipBidding) sql(IPseipBidding.class, sb.toString());
		countryOrderInfo.select();
		
		return countryOrderInfo;
	}
	
	public IPseipBidding loadOrderInformation(String countryCode, int offset, int findCount) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select project_name as projectName, c1.code_name as industryCategory, c2.code_name as serviceCategory, c3.code_name as projectStatus, b.reg_date as regDate, b.fund_type as fundType");
		sb.append(" from pseip_bidding b, pseip_code c1, pseip_code c2, pseip_code c3, pseip_country c4");
		sb.append(" where b.country_code = ?countryCode");
		sb.append(" and b.industry_category = c1.code");
		sb.append(" and b.service_category = c2.code");
		sb.append(" and b.project_status = c3.code");
		sb.append(" and b.country_code = c4.country_code");
		sb.append(" limit " + offset + ", " + findCount);
		
		IPseipBidding informations = (IPseipBidding) sql(IPseipBidding.class, sb.toString());
		informations.setCountryCode(countryCode);
		informations.select();
		
		return informations;
		
	}
	
	public IPseipBidding loadAllOrderInformation() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select c4.name_ko as countryCode, project_name as projectName, c1.code_name as industryCategory, c2.code_name as serviceCategory, c3.code_name as projectStatus, b.reg_date as regDate, b.fund_type as fundType");
		sb.append(" from pseip_bidding b, pseip_code c1, pseip_code c2, pseip_code c3, pseip_country c4");
		sb.append(" where b.industry_category = c1.code");
		sb.append(" and b.service_category = c2.code");
		sb.append(" and b.project_status = c3.code");
		sb.append(" and b.country_code = c4.country_code");
		
		IPseipBidding allInformation = (IPseipBidding) sql(IPseipBidding.class, sb.toString());
		allInformation.select();
		
		return allInformation;
	}
	
	
	public IPseipBidding loadAllOrderInformation(int min, int max) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select c4.name_ko as countryCode, project_name as projectName, c1.code_name as industryCategory, c2.code_name as serviceCategory, c3.code_name as projectStatus, b.reg_date as regDate, b.fund_type as fundType");
		sb.append(" from pseip_bidding b, pseip_code c1, pseip_code c2, pseip_code c3, pseip_country c4");
		sb.append(" where b.industry_category = c1.code");
		sb.append(" and b.service_category = c2.code");
		sb.append(" and b.project_status = c3.code");
		sb.append(" and b.country_code = c4.country_code");
		sb.append(" limit " + min + ", " + max);
		
		IPseipBidding allInformation = (IPseipBidding) sql(IPseipBidding.class, sb.toString());
		allInformation.select();
		
		return allInformation;
	}
	
	public int loadRegionOrderInformationSize(String selectedRegion) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from pseip_bidding");
		sb.append(" where country_code = ?countryCode");
		
		
		IPseipBidding orderSize = (IPseipBidding) sql(IPseipBidding.class, sb.toString());
		orderSize.setCountryCode(selectedRegion);
		orderSize.select();
		
		return orderSize.size();
	}
	
	public int loadAllRegionOrderInformationSize() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from pseip_bidding");
		
		IPseipBidding allSize = (IPseipBidding) sql(IPseipBidding.class, sb.toString());
		allSize.select();
		
		return allSize.size();
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
//	public IOrderInformation findDistinctOrderInfo() throws Exception {
//		StringBuffer sb = new StringBuffer();
//		sb.append("select distinct name_ko as countryname, a.country_code as countrycode, b.lat, b.lng, count(a.country_code) as count");
//		sb.append(" from pseip_bidding as a, pseip_country as b");
//		sb.append(" where a.country_code = b.country_code");
//		sb.append(" group by a.country_code, b.country_code");
//		
//		IOrderInformation orderInformation = (IOrderInformation) sql(IOrderInformation.class, sb.toString());
//		orderInformation.select();
//		
//		return orderInformation;
//	}
	 
	
	// 세부 정보를 보기 위해 countrycode를 통해 클릭시 해당 국가의 발주 정보를 가져온다.
//	public IOrderInformation loadDetailOrderInfo() throws Exception {
//		StringBuffer sb = new StringBuffer();
//		sb.append("select @ROWNUM := @ROWNUM + 1 as number, b.project_name as projectname, ic.code_name as industrypart, sc.code_name as servicepart, st.code_name as status, b.reg_date as regdate, c.name_ko as countryname");
//		sb.append(" from pseip_bidding as b, pseip_code as ic, pseip_code as sc, pseip_code as st, (SELECT @ROWNUM := 0) as R, pseip_country as c");
//		sb.append(" where b.industry_category = ic.code");
//		sb.append(" 	and b.service_category = sc.code");
//		sb.append(" 	and b.project_status = st.code");
//		sb.append(" 	and b.country_code = c.country_code");
//		sb.append(" 	and c.country_code = ?countrycode");
//		
//		
//		IOrderInformation orderInformation = (IOrderInformation) sql(IOrderInformation.class, sb.toString());
//		orderInformation.setCountrycode(this.getCountrycode());
//		orderInformation.select();
//		
//		return orderInformation;
//	}
	
	// 발주정보의 모든 리스트를 가져와야 한다. 가져오는 쿼리문
//	public IOrderInformation loadPastOrderInfo() throws Exception {
//		StringBuffer sb = new StringBuffer();
//		sb.append("select distinct name_ko as countryname, count(a.country_code) as count, a.project_name as projectname, a.reg_date as regdate");
//		sb.append(" from pseip_bidding as a, pseip_country as b");
//		sb.append(" where a.country_code = b.country_code");
//		sb.append(" group by a.country_code, b.country_code");
//		
//		IOrderInformation pastOrderInformation = (IOrderInformation) sql(IOrderInformation.class, sb.toString());
//		pastOrderInformation.select();
//		
//		return pastOrderInformation;
//	}
	
}
