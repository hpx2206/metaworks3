package org.uengine.codi.mw3.model;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;

public class InstanceList {
	
	IInstance instances;
			
		public IInstance getInstances() {
			return instances;
		}
	
		public void setInstances(IInstance instances) {
			this.instances = instances;
		}
		
	InstanceList moreInstanceList;
		public InstanceList getMoreInstanceList() {
			return moreInstanceList;
		}
	
		public void setMoreInstanceList(InstanceList moreInstanceList) {
			this.moreInstanceList = moreInstanceList;
		}
		
	int page;
		@Id
		@Hidden
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		
	protected void load(ILogin login, Navigation navigation) throws Exception{
		load(login, navigation, null);
	}

	protected void load(ILogin login, Navigation navigation, String keyword) throws Exception{
		instances = (IInstance) Database.sql(IInstance.class, 
					  "select * from bpm_procinst where initEp=?initEp "
					+ (keyword !=null ? " and name like '%" + keyword + "'" : "") 
					+ " order by starteddate desc limit " + page*5 + ", 5"
				);
		
		instances.setInitEp(login.getUserId());
		instances.select();

		moreInstanceList = new InstanceList();
		moreInstanceList.setPage(getPage()+1);
	}
	
	
	
	@ServiceMethod
	public void more() throws Exception{
		load(session.getLogin(), session.getNavigation());
	}
	
	@AutowiredFromClient
	public Session session;

}
