package bootstrap.org.metaworks.widget;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.website.statistics.IStatisticsDAO;
import org.metaworks.website.statistics.StatisticsDAO;

@Face( ejsPathForArray="dwr/metaworks/bootstrap/org/metaworks/widget/List.ejs")
public class SampleDTO {

    String endpoint;
		@Id
//		@Available(how="search_box")
		@Face(displayName="아이디")
		public String getEndpoint() {
			return endpoint;
		}
		public void setEndpoint(String endpoint) {
			this.endpoint = endpoint;
		}
		
	String empname;
		@Name
//		@Available(how="list")
		@Face(displayName="이름", options={"responsive"}, values={"visible-desktop"})
		public String getEmpname() {
			return empname;
		}
		public void setEmpname(String empname) {
			this.empname = empname;
		}
		
	int completeWorklistCountByPerson;
		@Name
		//@Available(how="list")
		@Face(displayName="업무수")
		public int getCompleteWorklistCountByPerson() {
			return completeWorklistCountByPerson;
		}
		public void setCompleteWorklistCountByPerson(int completeWorklistCountByPerson) {
			this.completeWorklistCountByPerson = completeWorklistCountByPerson;
		}
		
	public SampleDTO() {}
	
	
	public Object dataList(){
		
		IStatisticsDAO cimpl = null;
		StatisticsDAO cdao = new StatisticsDAO();
		SampleDTO[] resources = null;
		try {
			cdao.setEndpoint(endpoint);
			cdao.setEmpName(empname);
			cimpl = cdao.getWorklistCountPerPerson();
			
			if(cimpl != null){
				int i=0;
				resources = new SampleDTO[cimpl.size()];
				while(cimpl.next()){
					SampleDTO resource = new SampleDTO();
					resource.setEndpoint(cimpl.getEndpoint());
					resource.setEmpname(cimpl.getEmpName());
					resource.setCompleteWorklistCountByPerson(cimpl.getCompleteWorklistCountByPerson());
		
					resources[i] = resource;
					i++;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resources;
	}
}
