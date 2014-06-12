package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;

public class Assessment extends Database<IAssessment> implements IAssessment{

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	String userName;
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}

	String empCode;
		public String getEmpCode() {
			return empCode;
		}
		public void setEmpCode(String empCode) {
			this.empCode = empCode;
		}

	int reliability;
		public int getReliability() {
			return reliability;
		}
		public void setReliability(int reliability) {
			this.reliability = reliability;
		}

	int integrity;
		public int getIntegrity() {
			return integrity;
		}
		public void setIntegrity(int integrity) {
			this.integrity = integrity;
		}

	int performance;
		public int getPerformance() {
			return performance;
		}
		public void setPerformance(int performance) {
			this.performance = performance;
		}
		
	ArrayList<IUser> followers;
		public ArrayList<IUser> getFollowers() {
			return followers;
		}
		public void setFollowers(ArrayList<IUser> followers) {
			this.followers = followers;
		}
		
	public void save() throws Exception{
		this.createDatabaseMe();
		
	}

	
	public IAssessment findAvg() throws Exception{
		//선택된 유저의 평가지표를 보인다.
		StringBuffer sb = new StringBuffer();
		sb.append("select reliability, integrity, performance from ");
		sb.append("assessment ");
		sb.append("where empCode=?empCode");
		
//		String sql = "select avg(reliability), avg(integrity), avg(performance) from assessment where empCode=?empCode";
//		   ResultSet rs = stmt.executeQuery(selectSql);   
//		   
//		   while (rs.next()) {
//		    for (int i = 1; i < 4; i++) {
//		     System.out.println(rs.getString(i).substring(0, 3));
//		    }
//		   }
		IAssessment iAssessment = (IAssessment) sql(sb.toString());
		iAssessment.set("empCode", this.getEmpCode());
		iAssessment.select();
		return iAssessment;
	}
}
